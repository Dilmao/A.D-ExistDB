package org.example.data.GenerarXml;

import org.example.conexion.ConexionCollection;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XPathQueryService;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

public class GenerarXmlDatosProyectos {
    static Collection col = null;

    public static void cargarDatos() {
        col = ConexionCollection.conectar();

        if (col != null) {
            try {
                String consulta = """
                        let $xml := doc("proyectosFP.xml")
                        return
                        <Proyectos>{
                            for $row in $xml//Row
                            return
                                <Proyecto>
                                    <CENTROCOORDINADOR>{data($row/CENTROCOORDINADOR)}</CENTROCOORDINADOR>
                                    <TÍTULODELPROYECTO>{data($row/TÍTULODELPROYECTO)}</TÍTULODELPROYECTO>
                                    <AUTORIZACIÓN>{data($row/AUTORIZACIÓN)}</AUTORIZACIÓN>
                                    <CONTINUIDAD>{data($row/CONTINUIDAD)}</CONTINUIDAD>
                                    <COORDINACIÓN>{data($row/COORDINACIÓN)}</COORDINACIÓN>
                                    <CONTACTO>{data($row/CONTACTO)}</CONTACTO>
                                    <CENTROSANEXIONADOS>{data($row/CENTROSANEXIONADOS)}</CENTROSANEXIONADOS>
                                </Proyecto>
                        }</Proyectos>
                        """;

                XPathQueryService servicioXPath = (XPathQueryService) col.getService("XPathQueryService", "3.0");
                col.setProperty("indent", "yes");
                servicioXPath.setProperty("indent", "yes");
                ResourceSet resultadoConsulta = servicioXPath.query(consulta);
                ResourceIterator iterador = resultadoConsulta.getIterator();

                if (!iterador.hasMoreResources()) {
                    System.err.println(">>> ERROR: La consulta no devuelve ningún resultado o está mal escrita");
                }

                FileWriter fw = new FileWriter("target/Proyectos.xml");
                Resource recurso = null;

                while (iterador.hasMoreResources()) {
                    recurso = iterador.nextResource();
                    fw.write(recurso.getContent().toString());
                }

                fw.close();

                try {
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    Source source = new StreamSource(new StringReader(recurso.getContent().toString()));
                    StreamResult result = new StreamResult(new File("target/Proyectos.xml"));
                    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    transformer.setOutputProperty("{http:xml.apache.org/xslt}indent-amount", "4");
                    transformer.transform(source, result);
                } catch (TransformerException e) {
                    System.err.println(">>> Error al transformar el documento XML: " + e.getMessage());
                }

                System.out.println("Datos generados correctamente");

                // Cerrar la conexión a la base de datos
                col.close();
            } catch (XMLDBException e) {
                System.err.println(">>> Error al consultar el documento XML");
                e.printStackTrace();
            } catch (IOException e) {
                System.err.println(">>> Error de entrada/salida al escribir el archivo XML: " + e.getMessage());
            }
        } else {
            System.err.println("No se pudo establecer conexión con la base de datos");
        }
    }
}

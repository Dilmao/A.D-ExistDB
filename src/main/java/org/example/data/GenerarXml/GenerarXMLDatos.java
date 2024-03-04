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

public class GenerarXMLDatos {
    static Collection col = null;

    // Método para cargar datos XML desde una base de datos XML nativa utilizando una consulta XPath
    public static void cargarDatos(String nombreArchivo, String consulta) {
        // Establecer conexión con la colección XML
        col = ConexionCollection.conectar();

        if (col != null) {
            try {
                // Obtener servicio para ejecutar consultas XPath sobre la colección
                XPathQueryService servicioXPath = (XPathQueryService) col.getService("XPathQueryService", "3.0");

                // Establecer propiedades de indentación para la colección y el servicio XPath
                col.setProperty("indent", "yes");
                servicioXPath.setProperty("indent", "yes");

                // Ejecutar la consulta XPath
                ResourceSet resultadoConsulta = servicioXPath.query(consulta);
                ResourceIterator iterador = resultadoConsulta.getIterator();

                // Verificar si la consulta devuelve resultados
                if (!iterador.hasMoreResources()) {
                    System.err.println(">>> ERROR: La consulta no devuelve ningún resultado o está mal escrita");
                    return; // Evitar continuar si no hay resultados
                }

                // Escribir los resultados en un archivo XML
                FileWriter fw = new FileWriter("target/" + nombreArchivo + ".xml");
                Resource recurso = null;

                while (iterador.hasMoreResources()) {
                    recurso = iterador.nextResource();
                    fw.write(recurso.getContent().toString());
                }

                fw.close();

                // Transformar el documento XML para que tenga una estructura legible
                try {
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    Source source = new StreamSource(new StringReader(recurso.getContent().toString()));
                    StreamResult result = new StreamResult(new File("target/" + nombreArchivo + ".xml"));

                    // Configurar propiedades de salida para la transformación
                    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    transformer.setOutputProperty("{http:xml.apache.org/xslt}indent-amount", "4");

                    // Realizar la transformación y escribir el resultado en un archivo
                    transformer.transform(source, result);
                } catch (TransformerException e) {
                    System.err.println(">>> Error al transformar el documento XML: " + e.getMessage());
                }

                System.out.println("Datos de " + nombreArchivo + " generados correctamente");

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

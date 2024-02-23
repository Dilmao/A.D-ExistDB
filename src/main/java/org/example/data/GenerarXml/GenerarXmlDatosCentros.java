package org.example.data.GenerarXml;


import org.example.conexion.ConexionCollection;
import org.w3c.dom.Node;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

public class GenerarXmlDatosCentros {
    static Collection col = null;
    static Node nodo = null;
    static XMLResource nodoDom = null;

    public static void CargarDatos() {

        col = ConexionCollection.conectar();

        String Consulta = "declare namespace ss=\"urn:schemas-microsoft-com:office:spreadsheet\";\n" +
                "<Centros>{\n" +
                "    for $xml in doc(\"CentrosCFGMyS.xml\")//ss:Row\n" +
                "    return\n" +
                "    <Centro>\n" +
                "        <nombre>{$xml/ss:Cell[6]/ss:Data/data()}</nombre>\n" +
                "        <codigo>{$xml/ss:Cell[4]/ss:Data/data()}</codigo>\n" +
                "        <web>{$xml/ss:Cell[11]/ss:Data/data()}</web>\n" +
                "        <correoElectronico>{$xml/ss:Cell[10]/ss:Data/data()}</correoElectronico>\n" +
                "    </Centro>}\n" +
                "</Centros>";


        if (col != null) {
            try {
                XPathQueryService facturasCod1;
                facturasCod1 = (XPathQueryService) col.getService("XPathQueryService", "3.0");
                col.setProperty("indent", "yes");
                facturasCod1.setProperty("indent", "yes");
                ResourceSet result = facturasCod1.query(Consulta);
                ResourceIterator i;
                i = result.getIterator();
                if (!i.hasMoreResources()) {
                    System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
                }

                FileWriter fw = new FileWriter("target/Centros.xml");
                Resource r = null;

                while (i.hasMoreResources()) {
                    r = i.nextResource();
                    fw.write(r.getContent().toString());
                }

                fw.close();

                try {
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    Source source = new StreamSource(new StringReader(r.getContent().toString()));
                    StreamResult result1 = new StreamResult(new File("target/Centros.xml"));
                    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    transformer.setOutputProperty("{http:xml.apache.org/xslt}indent-amount", "4");
                    transformer.transform(source, result1);
                } catch (TransformerException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("datos generados");
                //crear un archivo que lo contiene

                col.close();
            } catch (XMLDBException e) {
                System.out.println(" ERROR AL CONSULTAR DOCUMENTO.");
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

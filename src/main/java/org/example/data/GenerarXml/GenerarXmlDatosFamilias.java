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


public class GenerarXmlDatosFamilias {
    static Collection col = null;

    public static void CargarDatos(){
        col = ConexionCollection.conectar();

        String Consulta="let $b := doc(\"familias.xml\")\n" +
                "return\n" +
                "<familias>\n" +
                "{\n" +
                "    for $a in $b//option\n" +
                "    return\n" +
                "        <familia>\n" +
                "            <nombre>{ $a/data() }</nombre>\n" +
                "            <codigo>{$a/@value}</codigo>\n" +
                "        </familia>\n" +
                "}\n" +
                "</familias>";
        if (col!=null) {
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
                FileWriter fw = new FileWriter("target/Familias.xml");
                Resource r = null;

                while (i.hasMoreResources()) {
                    r = i.nextResource();
                    fw.write(r.getContent().toString());
                }

                fw.close();

                try {
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    Source source = new StreamSource(new StringReader(r.getContent().toString()));
                    StreamResult result1 = new StreamResult(new File("target/Familias.xml"));
                    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    transformer.setOutputProperty("{http:xml.apache.org/xslt}indent-amount", "4");
                    transformer.transform(source, result1);
                } catch (TransformerException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("datos generados");
                col.close();
            }catch (XMLDBException e){
                System.out.println(" ERROR AL CONSULTAR DOCUMENTO.");
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

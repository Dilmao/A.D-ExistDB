package org.example.data;

import org.example.conexion.ConexionCollection;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;

import java.io.File;

public class InsertarXML {
    static Collection col = null;
    //Metodo para insertar los datos Xml a la colecion de ExistDb
    public static void Insertar(){

        File XmlFamilia = new File("src/main/resources/familias.xml");
        File XmlProyectosFP = new File("src/main/resources/proyectosFP.xml");
        File XmlCentros = new File("src/main/resources/CentrosCFGMyS.xml");
        File XmlProyectos = new File("src/main/resources/proyectos.xml");

        col = ConexionCollection.conectar();

        if (col!=null) {
            //Manera de insertar el xml
            System.out.println("Exito de conexion");

            // Insercción XMLFamilia
            if (!XmlFamilia.canRead()){
                System.out.println("Error al intentar leer el documento Familias");
            }else {
                try {
                    Resource resource = col.createResource(XmlFamilia.getName(),"XMLResource");
                    resource.setContent(XmlFamilia);
                    col.storeResource(resource);
                }catch (XMLDBException e){
                    System.err.println(">>> Error: ");
                }
            }

            // Insercción XMLProyectosFP
            if (!XmlProyectosFP.canRead()){
                System.out.println("Error al intentar leer el documento ProyectosFP");
            }else {
                try {
                    Resource resource = col.createResource(XmlProyectosFP.getName(),"XMLResource");
                    resource.setContent(XmlProyectosFP);
                    col.storeResource(resource);
                }catch (XMLDBException e){
                    System.err.println(">>> Error: ");
                }
            }

            // Insercción XMLCentros
            if (!XmlCentros.canRead()){
                System.out.println("Error al intentar leer el documento Centros");
            }else {
                try {
                    Resource resource = col.createResource(XmlCentros.getName(),"XMLResource");
                    resource.setContent(XmlCentros);
                    col.storeResource(resource);
                }catch (XMLDBException e){
                    System.err.println(">>> Error: ");
                }
            }

            // Insercción XMLProyectos
            if (!XmlProyectos.canRead()){
                System.out.println("Error al intentar leer el documento Proyectos");
            }else {
                try {
                    Resource resource = col.createResource(XmlProyectos.getName(),"XMLResource");
                    resource.setContent(XmlProyectos);
                    col.storeResource(resource);
                }catch (XMLDBException e){
                    System.err.println(">>> Error: ");
                }
            }
        }
    }
}

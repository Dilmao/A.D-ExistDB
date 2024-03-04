package org.example.data.InsertarExistDB;

import org.example.conexion.ConexionCollection;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;

import java.io.File;

public class InsertarXML {
    static Collection col = null;

    // Metodo para insertar los datos XML a la colecion de ExistDB
    public static void insertarXML(){
        // Archivos XML que se van a insertar en la colección
        File familiaXML = new File("src/main/resources/familias.xml");
        File proyectosFPXML = new File("src/main/resources/proyectosFP.xml");
        File centrosXML = new File("src/main/resources/CentrosCFGMyS.xml");
        File proyectosXML = new File("src/main/resources/proyectos.xml");

        // Conexión a la colección de ExistDB.
        col = ConexionCollection.conectar();

        if (col != null) {
            // Insertamos cada archivo XML en la colección
            System.out.println("Conexión exitosa");

            // Inserción de XML Familias
            insertarArchivoXML(familiaXML);

            // Inserción de XML Centros
            insertarArchivoXML(proyectosFPXML);

            // Inserción de XML Centros
            insertarArchivoXML(centrosXML);

            // Inserción de XML Proyectos
            insertarArchivoXML(proyectosXML);
        }
    }

    // Método para insertar un archivo XML en la colección
    private static void insertarArchivoXML(File archivoXML) {
        if (archivoXML.canRead()) {
            try {
                // Creamos un recurso en la colección con el contenido del archivo XML
                Resource resource = col.createResource(archivoXML.getName(), "XMLResource");
                resource.setContent(archivoXML);

                // Almacenamos el recurso en la colección
                col.storeResource(resource);

                // Se imprime un mensaje de éxito al insertar el documento
                System.out.println("Documento " + archivoXML.getName() + " insertado correctamente.");
            } catch (XMLDBException e) {
                System.err.println(">>> Error al intentar insertar el documento " + archivoXML.getName() + ": " + e.getMessage());
            }
        } else {
            // Se imprime un mensaje de error si el archivo no puede ser leído.
            System.err.println(">>> Error al intentar leer el documento: " + archivoXML.getName());
        }
    }
}

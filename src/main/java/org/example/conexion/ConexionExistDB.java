package org.example.conexion;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;

public class ConexionExistDB {
    final static String driver = "org.exist.xmldb.DatabaseImpl";
    final static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/"; // URI
    final static String usu = "admin"; // Usuario
    final static String usuPwd = ""; // Contraseña
    static Collection colRaiz = null; // Colección para almacenar las consultas+
    public static Collection conectar(){
        try {
            Class cl = Class.forName(driver); // Cargar del driver
            Database database = (Database) cl.newInstance(); // Instancia de la BD
            DatabaseManager.registerDatabase(database); // Registro del driver
            colRaiz = DatabaseManager.getCollection(URI, usu, usuPwd);
            return colRaiz;
        } catch (XMLDBException e) {
            System.out.println("Error al inicializar la BD eXist.");
            // e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Error en el driver.");
            // e.printStackTrace();
        } catch (InstantiationException e) {
            System.out.println("Error al instanciar la BD.");
            // e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("Error al instanciar la BD.");
            // e.printStackTrace();
        }
        return null;
    }
    public static void desconectar() throws XMLDBException {
        colRaiz.close();
    }
}

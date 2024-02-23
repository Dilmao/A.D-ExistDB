package org.example.conexion;

import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;

public class ConexionCollection {
    static Collection col = null;
    static Collection colDATOS = null;
    public static Collection conectar(){
        col = ConexionExistDB.conectar();
        if (col!=null){

            try {
                CollectionManagementService service = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");
                colDATOS=service.createCollection("colecionXML");
            } catch (XMLDBException e) {
                throw new RuntimeException(e);
            }
        }

        return colDATOS;
    }
    public static void desconectar() throws XMLDBException {
        colDATOS.close();
    }
}

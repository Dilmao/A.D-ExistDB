package org.example;

import org.example.data.GenerarXml.*;
import org.example.data.InsertarExistDB.CSVtoXML;
import org.example.data.InsertarExistDB.InsertarXML;
import org.example.libs.Leer;

public class Main {
    public static void main(String[] args) {
        boolean salir = false;
        String opcion;
        while (!salir) {
            System.out.println("""
                    *******************************************
                    0. Salir
                    1. Insertar ExistDB
                    2. Generar XMLs
                    *******************************************""");
            opcion = Leer.pedirCadena("Introduce una opción");

            switch (opcion) {
                case "0" -> salir = true;
                case "1" -> {
                    CSVtoXML.convertirCSVaXML();
                    InsertarXML.insertarXML();
                }
                case "2" -> {
                    // TODO comprobar si funciona de esta manera y borrar las clases:
                    //  'GenerarXmlDatosCentros', 'GenerarXmlDatosFamilias' y 'GenerarXmlDatosProyectos'
                    GenerarXMLs.generarDatosCentros();
                    GenerarXMLs.generarDatosFamilias();
                    GenerarXMLs.generarDatosProyectos();
                    // TODO en caso de no funcionar, volver a llamar a las clases de arriba y borrar:
                    //  'GenerarXMLs' y 'GenerarXMLDatos'
                }
                default -> System.err.println("La opción introducida no es valida");
            }
        }
    }
}
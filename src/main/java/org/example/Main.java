package org.example;

import org.example.data.CSVtoXML;
import org.example.data.GenerarXml.GenerarXmlDatosCentros;
import org.example.data.GenerarXml.GenerarXmlDatosFamilias;
import org.example.data.GenerarXml.GenerarXmlDatosProyectos;
import org.example.data.InsertarXML;
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
                    CSVtoXML.CSVtoXML();
                    InsertarXML.Insertar();
                }
                case "2" -> {
                    GenerarXmlDatosCentros.CargarDatos();
                    GenerarXmlDatosFamilias.CargarDatos();
                    GenerarXmlDatosProyectos.CargarDatos();
                }
                default -> System.err.println("La opción introducida no es valida");
            }
        }
    }
}
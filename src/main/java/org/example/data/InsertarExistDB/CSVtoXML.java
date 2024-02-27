package org.example.data.InsertarExistDB;

import com.aspose.cells.Workbook;

public class CSVtoXML {
    // Método para convertir archivos CSV a XML
    public static void convertirCSVaXML() {
        try {
            // Creamos un workbook para el archivo 'CentrosCFGMyS.csv'
            Workbook workbook1 = new Workbook("src/main/resources/CentrosCFGMyS.csv");
            // Creamos un workbook para el archivo 'proyectos.csv'
            Workbook workbook2 = new Workbook("src/main/resources/proyectos.csv");

            // Guardamos los workbooks en formato XML en la misma ruta con una extensión diferente
            workbook1.save("src/main/resources/CentrosCFGMyS.xml");
            workbook2.save("src/main/resources/proyectos.xml");
        } catch (Exception e) {
            // Si ocurre un error, lo lanzamos como una RuntimeException
            System.err.println(">>> Error al convertir archivos CSV a XML: " + e.getMessage());
        }
    }
}

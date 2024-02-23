package org.example.data;

import com.aspose.cells.Workbook;

public class CSVtoXML {
    //Metodo para pasar de CSV a XML utilizando la dependencia AsposeJavaAPI
    public static void CSVtoXML(){
        try {
            //creamos el workbook de cada csv y luego lo guardamos donde queremos que se situe
            Workbook workbook = new Workbook("src/main/resources/CentrosCFGMyS.csv");
            Workbook workbook2 = new Workbook("src/main/resources/proyectos.csv");
            workbook.save("src/main/resources/CentrosCFGMyS.xml");
            workbook2.save("src/main/resources/proyectos.xml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package org.example.data.GenerarXml;

public class GenerarXMLs {
    // Método para generar datos de centros a partir de un archivo XML de configuración
    public static void generarDatosCentros() {
        // Nombre del archivo de salida
        String archivo = "Centros";

        // Consulta XPath para extraer datos de centros
        String consulta = """
                declare namespace ss="urn:schemas-microsoft-com:office:spreadsheet";
                <Centros>{
                    for $xml in doc("CentrosCFGMyS.xml")//ss:Row
                    return
                    <Centro>
                        <nombre>{$xml/ss:Cell[6]/ss:Data/data()}</nombre>
                        <codigo>{$xml/ss:Cell[4]/ss:Data/data()}</codigo>
                        <web>{$xml/ss:Cell[11]/ss:Data/data()}</web>
                        <correoElectronico>{$xml/ss:Cell[10]/ss:Data/data()}</correoElectronico>
                    </Centro>}
                </Centros>
                """;

        // Llamar al método cargarDatos de GenerarXMLDatos para generar el archivo XML
        GenerarXMLDatos.cargarDatos(archivo, consulta);
    }

    // Método para generar datos de familias a partir de un archivo XML
    public static void generarDatosFamilias() {
        // Nombre del archivo de salida
        String archivo = "Familias";

        // Consulta XPath para extraer datos de familias
        String consulta = """
                        let $b := doc("familias.xml")
                        return
                        <familias>
                        {
                            for $a in $b//option
                            return
                                <familia>
                                    <nombre>{ $a/data() }</nombre>
                                    <codigo>{$a/@value}</codigo>
                                </familia>
                        }
                        </familias>
                        """;

        // Llamar al método cargarDatos de GenerarXMLDatos para generar el archivo XML
        GenerarXMLDatos.cargarDatos(archivo, consulta);
    }

    // Método para generar datos de proyectos a partir de un archivo XML
    public static void generarDatosProyectos() {
        // Nombre del archivo de salida
        String archivo = "Proyectos";

        // Consulta XPath para extraer datos de proyectos
        String consulta = """
                        let $xml := doc("proyectosFP.xml")
                        return
                        <Proyectos>{
                            for $row in $xml//Row
                            return
                                <Proyecto>
                                    <CENTROCOORDINADOR>{data($row/CENTROCOORDINADOR)}</CENTROCOORDINADOR>
                                    <TÍTULODELPROYECTO>{data($row/TÍTULODELPROYECTO)}</TÍTULODELPROYECTO>
                                    <FECHA_INICIO>{data($row/AUTORIZACIÓN)}</FECHA_INICIO>
                                    <FECHA_FIN>{data($row/CONTINUIDAD)}</FECHA_FIN>
                                    <USER_MANAGER>{data($row/COORDINACIÓN)}</USER_MANAGER>
                                    <USER_EMAIL>{data($row/CONTACTO)}</USER_EMAIL>
                                    <CENTROSANEXIONADOS>{data($row/CENTROSANEXIONADOS)}</CENTROSANEXIONADOS>
                                </Proyecto>
                        }</Proyectos>
                        """;

        // Llamar al método cargarDatos de GenerarXMLDatos para generar el archivo XML
        GenerarXMLDatos.cargarDatos(archivo, consulta);
    }
}

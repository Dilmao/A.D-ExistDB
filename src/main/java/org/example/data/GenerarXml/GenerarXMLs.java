package org.example.data.GenerarXml;

public class GenerarXMLs {
    public static void generarDatosCentros() {
        String archivo = "Centros";
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

        GenerarXMLDatos.cargarDatos(archivo, consulta);
    }

    public static void generarDatosFamilias() {
        String archivo = "Familias";
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

        GenerarXMLDatos.cargarDatos(archivo, consulta);
    }

    public static void generarDatosProyectos() {
        String archivo = "Proyectos";
        String consulta = """
                        let $xml := doc("proyectosFP.xml")
                        return
                        <Proyectos>{
                            for $row in $xml//Row
                            return
                                <Proyecto>
                                    <CENTROCOORDINADOR>{data($row/CENTROCOORDINADOR)}</CENTROCOORDINADOR>
                                    <TÍTULODELPROYECTO>{data($row/TÍTULODELPROYECTO)}</TÍTULODELPROYECTO>
                                    <AUTORIZACIÓN>{data($row/AUTORIZACIÓN)}</AUTORIZACIÓN>
                                    <CONTINUIDAD>{data($row/CONTINUIDAD)}</CONTINUIDAD>
                                    <COORDINACIÓN>{data($row/COORDINACIÓN)}</COORDINACIÓN>
                                    <CONTACTO>{data($row/CONTACTO)}</CONTACTO>
                                    <CENTROSANEXIONADOS>{data($row/CENTROSANEXIONADOS)}</CENTROSANEXIONADOS>
                                </Proyecto>
                        }</Proyectos>
                        """;

        GenerarXMLDatos.cargarDatos(archivo, consulta);
    }
}

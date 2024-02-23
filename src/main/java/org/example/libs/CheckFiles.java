package org.example.libs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CheckFiles {
    public static boolean dirEscribible(Path p) {
        //método que chequea si se puede escribir en un directorio y si no lo crea
        boolean dirOK = false;
        if (Files.exists(p) && Files.isDirectory(p)) {
            if (Files.isWritable(p)) {
                dirOK = true;
            }

        } else {
            try {
                Files.createDirectory(p);
                dirOK = true;
            } catch (IOException e) {
                System.out.println("Error al intentar crear el fichero.");
            }
        }
        return dirOK;
    }

    //
    public static boolean ficheroLegible(Path p) {
        boolean ficheroOK = false;
        if (Files.exists(p)) {
            if (Files.isReadable(p)) {
                ficheroOK = true;
            }
        } else {
            try {
                Files.createFile(p);
                ficheroOK = true;
            } catch (IOException e) {
                System.out.println("Error al intentar crear el fichero.");
            }
        }
        return ficheroOK;
    }

    public static boolean ficheroEscribible(Path p) {
    //método que comprueba si se puede escribir en un fichero y si no lo crea
        boolean ficheroOK = false;
        if (Files.exists(p)) {
            if (Files.isWritable(p)){
                ficheroOK = true;
            }

        }
        else {
            try {
                Files.createFile(p);
                ficheroOK = true;
            } catch (IOException e) {
                System.out.println("Error al intentar crear el fichero.");
            }
        }
        return ficheroOK;
    }

}

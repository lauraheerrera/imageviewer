package software.ulpgc.imageviewer;

import software.ulpgc.imageviewer.io.FileImageLoader;
import software.ulpgc.imageviewer.io.ImageLoader;
import software.ulpgc.imageviewer.model.Image;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File folder = new File("C:\\Users\\Usuario\\Downloads\\Imagenes");

        if (folder.exists() && folder.isDirectory()) {
            ImageLoader imageLoader = new FileImageLoader(folder);

            Image image = imageLoader.load();

            System.out.println("Imagen cargada:");
            System.out.println("Nombre: " + image.name());
            System.out.println("Formato: " + image.format());
            System.out.println("Contenido (bytes): " + image.content().length + " bytes");

            System.out.println("\nNavegando a la siguiente imagen...");
            image = image.next();
            System.out.println("Nombre de la siguiente imagen: " + image.name());

            System.out.println("\nNavegando a la siguiente imagen...");
            image = image.next();
            System.out.println("Nombre de la siguiente imagen: " + image.name());

            System.out.println("\nNavegando a la imagen anterior...");
            image = image.previous();
            System.out.println("Nombre de la imagen anterior: " + image.name());
        } else {
            System.out.println("La carpeta no existe o no es válida.");
        }
    }
}

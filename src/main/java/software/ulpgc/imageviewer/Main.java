package software.ulpgc.imageviewer;

import software.ulpgc.imageviewer.view.ViewPort;

public class Main {
    public static void main(String[] args) {
        int viewportWidth = 800;
        int viewportHeight = 600;

        ViewPort viewport = ViewPort.ofSize(viewportWidth, viewportHeight);
        System.out.println("ViewPort inicial: " + viewport + "\n");

        int imageWidth = 800;
        int imageHeight = 600;

        ViewPort adjustedViewport = viewport.fit(imageWidth, imageHeight);
        System.out.println("Caso 1 - Imagen con las dimensiones del ViewPort:");
        System.out.println("ViewPort ajustado: " + adjustedViewport + "\n");

        imageWidth = 1600;
        imageHeight = 600;

        adjustedViewport = viewport.fit(imageWidth, imageHeight);
        System.out.println("Caso 2 - Imagen más ancha que el ViewPort:");
        System.out.println("ViewPort ajustado: " + adjustedViewport + "\n");

        imageWidth = 400;
        imageHeight = 300;

        adjustedViewport = viewport.fit(imageWidth, imageHeight);
        System.out.println("Caso 3 - Imagen más pequeña que el ViewPort:");
        System.out.println("ViewPort ajustado: " + adjustedViewport + "\n");

        imageWidth = 600;
        imageHeight = 1200;

        adjustedViewport = viewport.fit(imageWidth, imageHeight);
        System.out.println("Caso 4 - Imagen más alta que el ViewPort:");
        System.out.println("ViewPort ajustado: " + adjustedViewport + "\n");
    }
}

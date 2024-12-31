package software.ulpgc.imageviewer;

import software.ulpgc.imageviewer.application.MainFrame;
import software.ulpgc.imageviewer.control.NextCommand;
import software.ulpgc.imageviewer.control.PreviousCommand;
import software.ulpgc.imageviewer.io.FileImageLoader;
import software.ulpgc.imageviewer.model.Image;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = MainFrame.create();
        mainFrame
                .initWith(firstImage())
                .add(">>", new NextCommand(mainFrame.imageDisplay()))
                .add("<<", new PreviousCommand(mainFrame.imageDisplay()))
                .setVisible(true);
    }
    private static Image firstImage() {
        return new FileImageLoader(new File("C:\\Users\\Usuario\\Downloads\\Imagenes")).load();
    }

}

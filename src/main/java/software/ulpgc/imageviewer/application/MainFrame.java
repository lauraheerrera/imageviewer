package software.ulpgc.imageviewer.application;

import software.ulpgc.imageviewer.model.Image;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final SwingImageDisplay imageDisplay;

    private MainFrame() throws HeadlessException {
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(imageDisplay = createImageDisplay());
    }

    private SwingImageDisplay createImageDisplay() {
        return new SwingImageDisplay(new SwingImageDeserializer());
    }

    public MainFrame initWith(Image image) {
        imageDisplay.show(image);
        return this;
    }

    public static MainFrame create() {
        return new MainFrame();
    }

}

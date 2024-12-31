package software.ulpgc.imageviewer.application;

import software.ulpgc.imageviewer.control.Command;
import software.ulpgc.imageviewer.model.Image;
import software.ulpgc.imageviewer.view.ImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private final SwingImageDisplay imageDisplay;
    private final Map<String, Command> commandMap;

    private MainFrame() throws HeadlessException {
        this.commandMap = new HashMap<>();
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(imageDisplay = createImageDisplay());
        this.add(toolBar(), BorderLayout.SOUTH);
    }

    private Component toolBar() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(button("<<"));
        panel.add(button(">>"));
        return panel;
    }

    private Component button(String name) {
        JButton button = new JButton(name);
        button.addActionListener(_ -> commandMap.get(name).execute());
        return button;
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

    public MainFrame add(String name, Command command) {
        commandMap.put(name, command);
        return this;
    }

    public ImageDisplay imageDisplay() {
        return imageDisplay;
    }
}

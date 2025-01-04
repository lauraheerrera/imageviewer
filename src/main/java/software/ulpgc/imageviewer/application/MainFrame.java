package software.ulpgc.imageviewer.application;

import software.ulpgc.imageviewer.control.Command;
import software.ulpgc.imageviewer.view.ImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private final SwingImageDisplay imageDisplay;
    private final Map<String, Command> commandMap;

    private MainFrame() {
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        this.commandMap = new HashMap<>();
        this.imageDisplay = createImageDisplay();
        this.add(imageDisplay, BorderLayout.CENTER);
        this.add(createToolBar(), BorderLayout.SOUTH);
    }

    private Component createToolBar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(createButton("<<"));
        panel.add(createButton(">>"));
        panel.add(createButton("\uD83D\uDD0D➕"));
        panel.add(createButton("\uD83D\uDD0D➖"));
        return panel;
    }

    private Component createButton(String commandName) {
        JButton button = new JButton(commandName);
        button.addActionListener(_ -> executeCommand(commandName));
        return button;
    }

    private void executeCommand(String commandName) {
        Command command = commandMap.get(commandName);
        if (command != null) {
            command.execute();
        }
    }
    private SwingImageDisplay createImageDisplay() {
        return new SwingImageDisplay(new SwingImageDeserializer());
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

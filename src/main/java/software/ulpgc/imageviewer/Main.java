package software.ulpgc.imageviewer;

import software.ulpgc.imageviewer.application.MainFrame;
import software.ulpgc.imageviewer.control.*;
import software.ulpgc.imageviewer.io.FileImageLoader;
import software.ulpgc.imageviewer.model.Image;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = MainFrame.create();
        ImagePresenter imagePresenter = new ImagePresenter(mainFrame.imageDisplay());
        Image firstImage = new FileImageLoader().load();
        imagePresenter.show(firstImage);
        mainFrame
                .add(">>", new NextCommand(imagePresenter))
                .add("<<", new PreviousCommand(imagePresenter))
                .setVisible(true);
    }
}

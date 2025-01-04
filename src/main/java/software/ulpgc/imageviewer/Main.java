package software.ulpgc.imageviewer;

import software.ulpgc.imageviewer.application.MainFrame;
import software.ulpgc.imageviewer.control.*;
import software.ulpgc.imageviewer.io.FileImageLoader;
import software.ulpgc.imageviewer.model.Image;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = MainFrame.create();
        Image firstImage = new FileImageLoader().load();
        ImagePresenter imagePresenter = new ImagePresenter(mainFrame.imageDisplay());
        mainFrame.imageDisplay().setPresenter(imagePresenter);

        imagePresenter.show(firstImage);
        mainFrame
                .add(">>", new NextCommand(imagePresenter))
                .add("<<", new PreviousCommand(imagePresenter))
                .add("\uD83D\uDD0D\u2795", new ZoomInCommand(imagePresenter))
                .add("\uD83D\uDD0D\u2796", new ZoomOutCommand(imagePresenter))
                .setVisible(true);
    }
}

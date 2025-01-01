package software.ulpgc.imageviewer.control;

import software.ulpgc.imageviewer.model.Image;
import software.ulpgc.imageviewer.view.ImageDisplay;

public class ZoomOutCommand implements Command {
    private final ImageDisplay imageDisplay;

    public ZoomOutCommand(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }

    @Override
    public void execute() {
        Image image = imageDisplay.currentImage();
        if (image != null) {
            image.zoomOut();
            imageDisplay.show(image);
        }
    }
}

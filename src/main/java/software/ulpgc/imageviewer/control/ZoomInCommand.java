package software.ulpgc.imageviewer.control;

import software.ulpgc.imageviewer.model.Image;
import software.ulpgc.imageviewer.view.ImageDisplay;

public class ZoomInCommand implements Command {
    private final ImageDisplay imageDisplay;

    public ZoomInCommand(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }

    @Override
    public void execute() {
        Image image = imageDisplay.currentImage();
        if (image != null) {
            image.zoomIn();
            imageDisplay.show(image);
        }
    }
}


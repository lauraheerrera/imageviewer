package software.ulpgc.imageviewer.control;

import software.ulpgc.imageviewer.model.Image;
import software.ulpgc.imageviewer.view.ImageDisplay;

import static software.ulpgc.imageviewer.view.ImageDisplay.*;

public class ImagePresenter implements ZoomHandler, ImageChangeHandler {
    private final ImageDisplay display;
    private Image image;

    public ImagePresenter(ImageDisplay display) {
        this.display = display;
        this.display.on(shift());
        this.display.on(released());
        this.display.setZoomHandler(this);
    }

    public void show(Image image) {
        this.image = image;
        this.display.paint(paintOrderForCurrentImageWith(0));
    }

    @Override
    public void zoomIn() {
        image.zoomIn();
        display.paint(paintOrderForCurrentImageWith(0));
    }

    @Override
    public void zoomOut() {
        image.zoomOut();
        display.paint(paintOrderForCurrentImageWith(0));
    }

    public Image getCurrentImage() {
        return image;
    }

    private Shift shift() {
        return offset -> display.paint(
                paintOrderForCurrentImageWith(offset),
                isDisplayingPreviousImageOn(offset) ?
                        paintOrderForPreviousImageWith(offset - display.width()) :
                        paintOrderForNextImageWith(display.width() + offset)
        );
    }

    private Release released() {
        return offset -> {
            changeCurrentImageOn(offset);
            display.paint(paintOrderForCurrentImageWith(0));
        };
    }

    private void changeCurrentImageOn(int offset) {
        if (isDisplayingOnCurrentImageOn(offset)) return;
        image = isDisplayingPreviousImageOn(offset) ?
                image.previous() :
                image.next();
    }

    private boolean isDisplayingOnCurrentImageOn(int offset) {
        return Math.abs(offset) <= display.width() / 2;
    }

    private boolean isDisplayingPreviousImageOn(int offset) {
        return offset > 0;
    }

    private PaintOrder paintOrderForCurrentImageWith(int offset) {
        return new PaintOrder(image.content(), offset, image);
    }

    private PaintOrder paintOrderForNextImageWith(int offset) {
        return new PaintOrder(image.next().content(), offset, image);
    }

    private PaintOrder paintOrderForPreviousImageWith(int offset) {
        return new PaintOrder(image.previous().content(), offset, image);
    }
}

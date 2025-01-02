package software.ulpgc.imageviewer.application;

import software.ulpgc.imageviewer.io.ImageDeserializer;
import software.ulpgc.imageviewer.view.ImageDisplay;
import software.ulpgc.imageviewer.model.Image;
import software.ulpgc.imageviewer.view.ViewPort;

import javax.swing.*;
import java.awt.*;
public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private final ImageDeserializer deserializer;
    private Image image;

    public SwingImageDisplay(ImageDeserializer deserializer) {
        this.deserializer = deserializer;
    }

    public Image currentImage() {
        return image;
    }

    @Override
    public void show(Image image) {
        this.image = image;
        this.revalidate();
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawImage(g);
    }

    private void drawImage(Graphics g) {
        if (image == null) return;
        java.awt.Image image = deserialize();
        Dimension imageSize = calculateNewSize();

        ViewPort viewPort = ViewPort.ofSize(getWidth(), getHeight())
                .fit(imageSize.width, imageSize.height, this.image.zoomLevel());
        int xOffset = (getWidth() - viewPort.width()) / 2;
        int yOffset = (getHeight() - viewPort.height()) / 2;

        g.drawImage(image, xOffset, yOffset, viewPort.width(), viewPort.height(), null);
    }

    public java.awt.Image deserialize() {
        return (java.awt.Image) deserializer.desearilize(image.content());
    }

    private Dimension calculateNewSize() {
        if (image == null) return new Dimension(0, 0);
        java.awt.Image awtImage = deserialize();
        int originalWidth = awtImage.getWidth(null);
        int originalHeight = awtImage.getHeight(null);
        int newWidth = originalWidth * image.zoomLevel() / 100;
        int newHeight = originalHeight * image.zoomLevel() / 100;
        return new Dimension(newWidth, newHeight);
    }
}

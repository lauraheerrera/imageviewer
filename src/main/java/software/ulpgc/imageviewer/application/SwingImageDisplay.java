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

    @Override
    public Image currentImage() {
        return image;
    }

    @Override
    public void show(Image image) {
        this.image = image;
        this.setPreferredSize(calculateNewSize());
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
        ViewPort viewPort = ViewPort.ofSize(getWidth(), getHeight())
                .fit(image.getWidth(null) * this.image.zoomLevel() / 100,
                        image.getHeight(null) * this.image.zoomLevel() / 100);
        g.drawImage(image, viewPort.x(), viewPort.y(), viewPort.width(), viewPort.height(), null);
    }

    private java.awt.Image deserialize() {
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

package software.ulpgc.imageviewer.application;

import software.ulpgc.imageviewer.control.ImagePresenter;
import software.ulpgc.imageviewer.io.ImageDeserializer;
import software.ulpgc.imageviewer.view.ImageDisplay;
import software.ulpgc.imageviewer.view.ViewPort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private final List<PaintOrder> orders = new ArrayList<>();
    private Shift shift = Shift.Null;
    private Release release = Release.Null;
    private int initialOffset;
    private final ImageDeserializer deserializer;
    private ImagePresenter presenter;

    public SwingImageDisplay(ImageDeserializer deserializer) {
        this.deserializer = deserializer;
        this.addMouseListener(mouseListener());
        this.addMouseMotionListener(mouseMotionListener());
        this.addMouseWheelListener(mouseWheelListener());
    }

    @Override
    public void setPresenter(ImagePresenter presenter) {
        this.presenter = presenter;
    }

    private boolean isPresenterInitialized() {
        return presenter != null;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        for(PaintOrder order : orders) {
            paintOrder(g, order);
        }
    }

    private void paintOrder(Graphics g, PaintOrder order) {
        BufferedImage image = deserialize(order.content());
        ViewPort viewPort = viewPortOf(image, order.getImage().zoomLevel());
        g.drawImage(image, viewPort.x() + order.offset(), viewPort.y(), viewPort.width(), viewPort.height(), null);
    }

    private ViewPort viewPortOf(BufferedImage image, int zoomLevel) {
        return ViewPort.ofSize(this.getWidth(), this.getHeight())
                .fit(image.getWidth(null), image.getHeight(null), zoomLevel);
    }

    @Override
    public int width() {
        return getWidth();
    }

    @Override
    public void paint(PaintOrder... orders) {
        this.orders.clear();
        Collections.addAll(this.orders, orders);
        repaint();
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift != null ? shift : Shift.Null;
    }

    @Override
    public void on(Release release) {
        this.release = release != null ? release : Release.Null;
    }

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialOffset = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                release.offset(e.getX() - initialOffset);
            }

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };
    }

    private MouseMotionListener mouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                shift.offset(e.getX() - initialOffset);
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        };
    }

    private MouseWheelListener mouseWheelListener() {
        return new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (!isPresenterInitialized()) {
                    return;
                }

                int sensitivity = 10;
                if (e.getWheelRotation() < 0) {
                    for (int i = 0; i < Math.abs(e.getWheelRotation()) * sensitivity; i++) {
                        presenter.zoomIn();
                    }
                } else {
                    for (int i = 0; i < Math.abs(e.getWheelRotation()) * sensitivity; i++) {
                        presenter.zoomOut();
                    }
                }
            }
        };
    }

    private BufferedImage deserialize(byte[] content){
        return (BufferedImage) deserializer.desearilize(content);
    }
}

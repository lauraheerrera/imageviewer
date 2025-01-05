package software.ulpgc.imageviewer.view;

import software.ulpgc.imageviewer.model.Image;

public interface ImageDisplay {
    int width();

    void paint(PaintOrder... orders);

    void on(Shift shift);
    void on(Release release);

    void setZoomHandler(ZoomHandler zoomHandler);

    interface Shift {
        Shift Null = _ -> {};
        void offset(int offset);
    }

    interface Release {
        Release Null = _ -> {};
        void offset(int offset);
    }

    interface ZoomHandler {
        void zoomIn();
        void zoomOut();
        void resetZoom();
    }

    interface ImageChangeHandler {
    }

    record PaintOrder(byte[] content, int offset, Image image) {
        public Image getImage() {
            return image;
        }
    }
}

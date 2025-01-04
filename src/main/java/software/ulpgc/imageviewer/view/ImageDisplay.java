package software.ulpgc.imageviewer.view;

import software.ulpgc.imageviewer.control.ImagePresenter;
import software.ulpgc.imageviewer.model.Image;

public interface ImageDisplay {
    int width();

    void paint(PaintOrder... orders);

    void on(Shift shift);
    void on(Release release);

    void setPresenter(ImagePresenter imagePresenter);

    interface Shift {
        Shift Null = _ -> {};
        void offset(int offset);
    }

    interface Release {
        Release Null = _ -> {};
        void offset(int offset);
    }

    record PaintOrder(byte[] content, int offset, Image image) {
        public Image getImage() {
            return image;
        }
    }
}

package software.ulpgc.imageviewer.view;

public interface ImageDisplay {
    int width();

    void paint(PaintOrder... orders);

    void on(Shift shift);
    void on(Release release);

    interface Shift {
        Shift Null = _ -> {};
        void offset(int offset);
    }

    interface Release {
        Release Null = _ -> {};
        void offset(int offset);
    }

    record PaintOrder(byte[] content, int offset) {}

}

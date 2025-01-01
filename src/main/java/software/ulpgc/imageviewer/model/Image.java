package software.ulpgc.imageviewer.model;

public interface Image {
    byte[] content();
    Image next();
    Image previous();

    int zoomLevel();
    void zoomIn();
    void zoomOut();

    enum Format {
        Jpg, Jpeg, Png, Gif
    }
}
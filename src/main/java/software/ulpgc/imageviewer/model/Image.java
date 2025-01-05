package software.ulpgc.imageviewer.model;

public interface Image {
    byte[] content();
    Image next();
    Image previous();

    int zoomLevel();
    void zoomIn();
    void zoomOut();
    void resetZoom();
}
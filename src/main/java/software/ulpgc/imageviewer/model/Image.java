package software.ulpgc.imageviewer.model;

public interface Image {
    String name();
    byte[] content();
    Format format();
    Image next();
    Image previous();

    enum Format {
        Jpg, Jpeg, Png, Gif
    }

    record Dimension(int width, int height) {}
}
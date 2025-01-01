package software.ulpgc.imageviewer.model;

public interface Image {
    byte[] content();
    Image next();
    Image previous();

    enum Format {
        Jpg, Jpeg, Png, Gif
    }
}
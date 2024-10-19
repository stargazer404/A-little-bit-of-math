package MathLib.objects;

import java.awt.image.BufferedImage;

public abstract class ImageInterpolator {
    private BufferedImage image;

    public ImageInterpolator(BufferedImage image) {
        this.image = image;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public int getPixel(int x, int y) {
        if (x >= 0 & x < image.getWidth() && y >= 0 && y < image.getHeight()) {
            return image.getRGB(x, y);
        } else {
            return 0;
        }
    }

    public abstract int getNewPixel(Vector2D p);
}

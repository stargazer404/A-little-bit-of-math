package MathLib.objects;

import java.awt.image.BufferedImage;

public class NearestNeighbor extends ImageInterpolator{

    public NearestNeighbor(BufferedImage image) {
        super(image);
    }


    @Override
    public int getNewPixel(Vector2D p) {
        int x = (int) (p.getX() + 0.5);
        int y = (int) (p.getY() + 0.5);
        return getPixel(x, y);
    }
}

package MathLib.objects;

import java.awt.image.BufferedImage;

public class Bilinear extends ImageInterpolator{

    public Bilinear(BufferedImage image) {
        super(image);
    }

    @Override
    public int getNewPixel(Vector2D p) {
        if (p.getX() > -1 && p.getX() < getWidth() && p.getY() > -1 && p.getY() < getHeight()) {
            int x = (int) p.getX();
            int y = (int) p.getY();
            double dx = p.getX() - Math.floor(p.getX());
            double dy = p.getY() - Math.floor(p.getY());
            int[] pix = {getPixel(x, y), getPixel(x + 1, y), getPixel(x, y + 1), getPixel(x + 1, y + 1)};
            int finalRGB = 0;
            for (int i = 0; i < 3; i++) {
                int s = 8 * i;
                int a0 = (pix[0] >> s) & 0x000000FF;
                int a1 = (pix[1] >> s) & 0x000000FF;
                int b0 = (pix[2] >> s) & 0x000000FF;
                int b1 = (pix[3] >> s) & 0x000000FF;
                int m = (int) (a0 + (a1 - a0)*dx + (b0 - a0)*dy + (b1 - b0 + a0 - a1)*dx*dy);
                if (m < 0) {
                    m = 0;
                } else if (m > 255) {
                    m = 255;
                }
                finalRGB = (m << s) | finalRGB;
            }
            return finalRGB;
        } else {
            return 0;
        }
    }
}

package MathLib.objects;

import MathLib.data.Interpolation;

import java.awt.image.BufferedImage;

public class Bicubic extends ImageInterpolator{

    public Bicubic(BufferedImage image) {
        super(image);
    }

    @Override
    public int getNewPixel(Vector2D p) {
        if (p.getX() > -1 && p.getX() < getWidth() && p.getY() > -1 && p.getY() < getHeight()) {
            int x = (int) p.getX();
            int y = (int) p.getY();
            double dx = p.getX() - Math.floor(p.getX());
            double dy = p.getY() - Math.floor(p.getY());
            int finalRGB = 0;
            int[][] chunk = getChunk(x - 1, y - 1);
            double[] pomcofx = {dx*dx, dx*dx*dx};
            double[] pomcofy = {dy*dy, dy*dy*dy};
            for (int c = 0; c < 3; c++) {
                int s = 8*c;
                double[] pol = new double[4];
                for (int i = 0; i < 4; i++) {
                    int p0 = (chunk[i][0] >> s) & 0x000000FF;
                    int p1 = (chunk[i][1] >> s) & 0x000000FF;
                    int p2 = (chunk[i][2] >> s) & 0x000000FF;
                    int p3 = (chunk[i][3] >> s) & 0x000000FF;
                    double[] cof = Interpolation.cubic(p0, p1, p2, p3);
                    pol[i] = cof[0] + cof[1]*dx + cof[2]*pomcofx[0] + cof[3]*pomcofx[1];
                }
                double[] cof = Interpolation.cubic(pol[0], pol[1], pol[2], pol[3]);
                int m = (int) (cof[0] + cof[1]*dy + cof[2]*pomcofy[0] + cof[3]*pomcofy[1]);
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

    private int[][] getChunk(int x, int y) {
        int[][] chunk = new int[4][4];
        for (int iy = 0; iy < 4; iy++) {
            for (int ix = 0; ix < 4; ix++) {
                chunk[iy][ix] = getPixel(x + ix, y + iy);
            }
        }
        return chunk;
    }
}

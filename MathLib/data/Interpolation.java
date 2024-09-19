package MathLib.data;

public class Interpolation {

    public static double[] linear(double y0, double y1) {
        return new double[]{y0, y1 - y0};
    }

    public static double[] square(double y0, double y1, double y2) {
        double[] result = new double[3];
        result[0] = y0;
        result[1] = y1*2 - (y0*3 + y2)/2;
        result[2] = (y2 + y0)/2 - y1;
        return result;
    }

    public static double[] cubic(double y0, double y1, double y2, double y3) {
        double[] result = new double[4];
        result[0] = y1;
        result[1] = y2 - (y0*2 + y1*3 + y3)/6;
        result[2] = (y0 + y2)/2 - y1;
        result[3] = (y1 - y2)/2 + (y3 - y0)/6;
        return result;
    }

    public static double[][] espline(double[] data) {
        int polinN = data.length - 1;
        int coefN = polinN*3 - 1;
        double[][] mat = new double[coefN][2];
        double k = data[1] - data[0];
        mat[0][0] = 1.0; mat[0][1] = k;
        mat[1][0] = -0.5; mat[1][1] = -0.5*k;
        mat[2][0] = -2.0/3; mat[2][1] = k;
        int n = 3;
        for (int i = 1; i < polinN - 1; i++) {
            k = data[i + 1] - data[i];
            double c = 1.0 - mat[n - 1][0];
            mat[n][0] = 1.0/c; mat[n][1] = (k - mat[n - 1][1])/c;
            c = 2.0 - mat[n][0];
            n++;
            mat[n][0] = -1.0/c; mat[n][1] = -(k + mat[n - 1][1])/c;
            c = 1.0 - mat[n][0];
            n++;
            mat[n][0] = -1.0/c; mat[n][1] = (k - mat[n - 1][1])/c;
            n++;
        }
        k = data[polinN] - data[polinN - 1];
        double c = 1.0 - mat[coefN - 3][0];
        mat[coefN - 2][0] = 1.0/c; mat[coefN - 2][1] = (k - mat[coefN - 3][1])/c;
        c = 3.0 - mat[coefN - 2][0];
        mat[coefN - 1][0] = 1.0; mat[coefN - 1][1] = -mat[coefN - 2][1]/c;

        for (int i = coefN - 2; i >= 0; i--) {
            mat[i][1] -= mat[i][0]*mat[i + 1][1];
        }

        double[][] polinMat = new double[polinN][4];
        polinMat[0][0] = data[0];
        polinMat[0][1] = mat[0][1];
        polinMat[0][2] = 0.0;
        polinMat[0][3] = mat[1][1];
        int x = 2;
        for (int i = 1; i < polinN; i++) {
            polinMat[i][0] = data[i];
            polinMat[i][1] = mat[x][1];
            x++;
            polinMat[i][2] = mat[x][1];
            x++;
            polinMat[i][3] = mat[x][1];
            x++;
        }
        return polinMat;
    }
}

package MathLib.processing;

public class MatProcessing {

    public static double[] gaus(double[][] mat) {
        int coefN = mat[0].length - 1;
        double[] coef;
        if (mat[0].length - mat.length == 1) {
            coef = new double[coefN];
            for (int i = 0; i < coefN - 1; i++) {
                double b = 0.0;
                int l = i;
                for (int s = i; s < coefN; s++) {
                    double t = Math.abs(mat[s][i]);
                    if (t >= b) {
                        b = t;
                        l = s;
                    }
                }
                double[] buf = mat[l];
                mat[l] = mat[i];
                mat[i] = buf;
                for (int c = i + 1; c < coefN; c++) {
                    if (mat[c][i] != 0) {
                        double pom = mat[c][i]/mat[i][i];
                        for (int n = i; n <= coefN; n++) {
                            mat[c][n] -= mat[i][n]*pom;
                        }
                    }
                }
            }

            for (int i = coefN - 1; i >= 0; i--) {
                coef[i] = mat[i][coefN]/mat[i][i];
                for (int c = coefN - 1; c >= 0; c--) {
                    mat[c][coefN] -= mat[c][i]*coef[i];
                }
            }
        } else {
            coef = null;
        }
        return coef;
    }
}

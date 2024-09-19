package MathLib.data;

import MathLib.objects.Vector2D;
import MathLib.processing.MatProcessing;


public class Approximation {

    public static double[] linear(Vector2D[] points) {
        double x = 0, x2 = 0, y = 0, xy = 0;
        for (Vector2D p : points) {
            double px = p.getX();
            double py = p.getY();
            x += px;
            x2 += px*px;
            y += py;
            xy += px*py;
        }
        double[] result = new double[2];
        double n = points.length;
        result[1] = (xy - x*y/n)/(x2 - x*x/n);
        result[0] = (y - result[1]*x)/n;
        return result;
    }

    public static double[] linear(Vector2D[] points, double[] weights) {
        double n = points.length;
        double x = 0, x2 = 0, y = 0, xy = 0;
        for (int i = 0; i < n; i++) {
            double px = points[i].getX();
            double py = points[i].getY();
            double pom = px*weights[i];
            x += pom;
            x2 += px*pom;
            y += py*weights[i];
            xy += py*pom;
        }
        double[] result = new double[2];
        result[1] = (xy - x*y/n)/(x2 - x*x/n);
        result[0] = (y - result[1]*x)/n;
        return result;
    }

    public static double[] square(Vector2D[] points) {
        double x = 0, x2 = 0, x3 = 0, x4 = 0, y = 0, yx = 0, yx2 = 0;
        for (Vector2D p : points) {
            double px = p.getX();
            double py = p.getY();
            x += px;
            double t2 = px*px;
            x2 += t2;
            x3 += t2*px;
            x4 += t2*t2;
            y += py;
            yx += px*py;
            yx2 += py*t2;
        }
        double[][] mat = new double[3][4];
        mat[0][0] = points.length; mat[0][1] = x; mat[0][2] = x2; mat[0][3] = y;
        mat[1][0] = x; mat[1][1] = x2; mat[1][2] = x3; mat[1][3] = yx;
        mat[2][0] = x2; mat[2][1] = x3; mat[2][2] = x4; mat[2][3] = yx2;
        return MatProcessing.gaus(mat);
    }

    public static double[] square(Vector2D[] points, double[] weights) {
        int n = points.length;
        double x = 0, x2 = 0, x3 = 0, x4 = 0, y = 0, yx = 0, yx2 = 0;
        for (int i = 0; i < n; i++) {
            double px = points[i].getX();
            double py = points[i].getY();
            double pom = px*weights[i];
            x += pom;
            x2 += px*pom;
            double t2 = px*px;
            x3 += t2*pom;
            x4 += t2*px*pom;
            y += py*weights[i];
            yx += py*pom;
            yx2 += py*px*pom;
        }
        double[][] mat = new double[3][4];
        mat[0][0] = n; mat[0][1] = x; mat[0][2] = x2; mat[0][3] = y;
        mat[1][0] = x; mat[1][1] = x2; mat[1][2] = x3; mat[1][3] = yx;
        mat[2][0] = x2; mat[2][1] = x3; mat[2][2] = x4; mat[2][3] = yx2;
        return MatProcessing.gaus(mat);
    }

    public static double[] cubic(Vector2D[] points) {
        double x = 0, x2 = 0, x3 = 0, x4 = 0, x5 = 0, x6 = 0, y = 0, yx = 0, yx2 = 0, yx3 = 0;
        for (Vector2D p : points) {
            double px = p.getX();
            double py = p.getY();
            x += px;
            double t2 = px*px;
            x2 += t2;
            x3 += t2*px;
            x4 += t2*t2;
            x5 += t2*t2*px;
            x6 += t2*t2*t2;
            y += py;
            yx += px*py;
            yx2 += py*t2;
            yx3 += py*t2*px;
        }
        double[][] mat = new double[4][5];
        mat[0][0] = points.length; mat[0][1] = x; mat[0][2] = x2; mat[0][3] = x3; mat[0][4] = y;
        mat[1][0] = x; mat[1][1] = x2; mat[1][2] = x3; mat[1][3] = x4; mat[1][4] = yx;
        mat[2][0] = x2; mat[2][1] = x3; mat[2][2] = x4; mat[2][3] = x5; mat[2][4] = yx2;
        mat[3][0] = x3; mat[3][1] = x4; mat[3][2] = x5; mat[3][3] = x6; mat[3][4] = yx3;
        return MatProcessing.gaus(mat);
    }

    public static double[] cubic(Vector2D[] points, double[] weights) {
        int n = points.length;
        double x = 0, x2 = 0, x3 = 0, x4 = 0, x5 = 0, x6 = 0, y = 0, yx = 0, yx2 = 0, yx3 = 0;
        for (int i = 0; i < n; i++) {
            double px = points[i].getX();
            double py = points[i].getY();
            double pom = px*weights[i];
            x += pom;
            double t2 = px*px;
            x2 += px*pom;
            x3 += t2*pom;
            x4 += t2*px*pom;
            x5 += t2*t2*pom;
            x6 += t2*t2*px*pom;
            y += py*weights[i];
            yx += py*pom;
            yx2 += py*px*pom;
            yx3 += py*t2*pom;
        }
        double[][] mat = new double[4][5];
        mat[0][0] = n; mat[0][1] = x; mat[0][2] = x2; mat[0][3] = x3; mat[0][4] = y;
        mat[1][0] = x; mat[1][1] = x2; mat[1][2] = x3; mat[1][3] = x4; mat[1][4] = yx;
        mat[2][0] = x2; mat[2][1] = x3; mat[2][2] = x4; mat[2][3] = x5; mat[2][4] = yx2;
        mat[3][0] = x3; mat[3][1] = x4; mat[3][2] = x5; mat[3][3] = x6; mat[3][4] = yx3;
        return MatProcessing.gaus(mat);
    }

    public static double[][] optimalSRS(Vector2D[] firstSet, Vector2D[] secondSet) {
        Vector2D m = new Vector2D();
        Vector2D k = new Vector2D();
        int n = firstSet.length;

        for (int i = 0; i < n; i++) {
            m.add(firstSet[i]);
            k.add(secondSet[i]);
        }
        m.div(n);
        k.div(n);

        double sumA = 0.0, sumB = 0.0, sqB = 0.0;
        for (int i = 0; i < n; i++) {
            Vector2D am = (Vector2D) firstSet[i].clone();
            Vector2D bm = (Vector2D) secondSet[i].clone();
            am.sub(m);
            bm.sub(k);
            sumA += Vector2D.scalarMul(am, bm);
            sumB += Vector2D.scalarMul(am, new Vector2D(-bm.getY(), bm.getX()));
            sqB += Vector2D.scalarMul(bm, bm);
        }

        double a = sumA/sqB, b = sumB/sqB;

        double[][] mat = new double[3][3];
        mat[0][0] = a;
        mat[0][1] = -b;
        mat[0][2] = m.getX() - a*k.getX() + b*k.getY();
        mat[1][0] = b;
        mat[1][1] = a;
        mat[1][2] = m.getY() - b*k.getX() - a*k.getY();
        mat[2][2] = 1.0;
        return mat;
    }

    public static double[][] optimalSRS(Vector2D[] firstSet, Vector2D[] secondSet, double[] weights) {
        Vector2D m = new Vector2D();
        Vector2D k = new Vector2D();
        int n = firstSet.length;
        double sumW = 0.0;
        for (int i = 0; i < n; i++) {
            Vector2D am = (Vector2D) firstSet[i].clone();
            Vector2D bm = (Vector2D) secondSet[i].clone();
            am.mul(weights[i]);
            bm.mul(weights[i]);
            m.add(am);
            k.add(bm);
            sumW += weights[i];
        }
        m.div(sumW);
        k.div(sumW);

        double sumA = 0.0, sumB = 0.0, sqB = 0.0;
        for (int i = 0; i < n; i++) {
            Vector2D am = (Vector2D) firstSet[i].clone();
            Vector2D bm = (Vector2D) secondSet[i].clone();
            am.sub(m);
            bm.sub(k);
            sumA += Vector2D.scalarMul(am, bm)*weights[i];
            sumB += Vector2D.scalarMul(am, new Vector2D(-bm.getY(), bm.getX()))*weights[i];
            sqB += Vector2D.scalarMul(bm, bm)*weights[i];
        }

        double a = sumA/sqB, b = sumB/sqB;

        double[][] mat = new double[3][3];
        mat[0][0] = a;
        mat[0][1] = -b;
        mat[0][2] = m.getX() - a*k.getX() + b*k.getY();
        mat[1][0] = b;
        mat[1][1] = a;
        mat[1][2] = m.getY() - b*k.getX() - a*k.getY();
        mat[2][2] = 1.0;
        return mat;
    }

    public static double[][] optimalAffine(Vector2D[] firstSet, Vector2D[] secondSet) {
        Vector2D m = new Vector2D();
        Vector2D k = new Vector2D();
        int n = firstSet.length;

        for (int i = 0; i < n; i++) {
            m.add(firstSet[i]);
            k.add(secondSet[i]);
        }
        m.div(n);
        k.div(n);

        double sumX2 = 0.0, sumY2 = 0.0, sumXY = 0.0;
        double[] sumF = new double[4];

        for (int i = 0; i < n; i++) {
            Vector2D am = (Vector2D) firstSet[i].clone();
            Vector2D bm = (Vector2D) secondSet[i].clone();
            am.sub(m);
            bm.sub(k);
            sumX2 += bm.getX()*bm.getX();
            sumY2 += bm.getY()*bm.getY();
            sumXY += bm.getX()*bm.getY();
            sumF[0] += am.getX()*bm.getX();
            sumF[1] += am.getX()*bm.getY();
            sumF[2] += am.getY()*bm.getX();
            sumF[3] += am.getY()*bm.getY();
        }

        double[][] mat0 = new double[4][5];
        mat0[0][0] = sumX2;
        mat0[0][1] = sumXY;
        mat0[0][4] = sumF[0];
        mat0[1][0] = sumXY;
        mat0[1][1] = sumY2;
        mat0[1][4] = sumF[1];
        mat0[2][2] = sumX2;
        mat0[2][3] = sumXY;
        mat0[2][4] = sumF[2];
        mat0[3][2] = sumXY;
        mat0[3][3] = sumY2;
        mat0[3][4] = sumF[3];

        double[] coef = MatProcessing.gaus(mat0);
        double[][] result = new double[3][3];
        result[0][0] = coef[0];
        result[0][1] = coef[1];
        result[0][2] = m.getX() - coef[0]*k.getX() - coef[1]*k.getY();
        result[1][0] = coef[2];
        result[1][1] = coef[3];
        result[1][2] = m.getY() - coef[2]*k.getX() - coef[3]*k.getY();
        result[2][2] = 1.0;
        return result;
    }

    public static double[][] optimalAffine(Vector2D[] firstSet, Vector2D[] secondSet, double[] weights) {
        Vector2D m = new Vector2D();
        Vector2D k = new Vector2D();
        int n = firstSet.length;

        double sumW = 0.0;
        for (int i = 0; i < n; i++) {
            Vector2D am = (Vector2D) firstSet[i].clone();
            Vector2D bm = (Vector2D) secondSet[i].clone();
            am.mul(weights[i]);
            bm.mul(weights[i]);
            m.add(am);
            k.add(bm);
            sumW += weights[i];
        }
        m.div(sumW);
        k.div(sumW);

        double sumX2 = 0.0, sumY2 = 0.0, sumXY = 0.0;
        double[] sumF = new double[4];

        for (int i = 0; i < n; i++) {
            Vector2D am = (Vector2D) firstSet[i].clone();
            Vector2D bm = (Vector2D) secondSet[i].clone();
            am.sub(m);
            bm.sub(k);
            double xw = bm.getX()*weights[i], yw = bm.getY()*weights[i];
            sumX2 += bm.getX()*xw;
            sumY2 += bm.getY()*yw;
            sumXY += bm.getX()*yw;
            sumF[0] += am.getX()*xw;
            sumF[1] += am.getX()*yw;
            sumF[2] += am.getY()*xw;
            sumF[3] += am.getY()*yw;
        }

        double[][] mat0 = new double[4][5];
        mat0[0][0] = sumX2;
        mat0[0][1] = sumXY;
        mat0[0][4] = sumF[0];
        mat0[1][0] = sumXY;
        mat0[1][1] = sumY2;
        mat0[1][4] = sumF[1];
        mat0[2][2] = sumX2;
        mat0[2][3] = sumXY;
        mat0[2][4] = sumF[2];
        mat0[3][2] = sumXY;
        mat0[3][3] = sumY2;
        mat0[3][4] = sumF[3];

        double[] coef = MatProcessing.gaus(mat0);
        double[][] result = new double[3][3];
        result[0][0] = coef[0];
        result[0][1] = coef[1];
        result[0][2] = m.getX() - coef[0]*k.getX() - coef[1]*k.getY();
        result[1][0] = coef[2];
        result[1][1] = coef[3];
        result[1][2] = m.getY() - coef[2]*k.getX() - coef[3]*k.getY();
        result[2][2] = 1.0;
        return result;
    }
}

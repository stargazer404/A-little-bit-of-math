package MathLib.objects;

import java.awt.geom.Point2D;

public class Vector2D extends Point2D {
    private double x, y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2D b) {
        x += b.x;
        y += b.y;

    }

    public void sub(Vector2D b) {
        x -= b.x;
        y -= b.y;
    }

    public void mul(double b) {
        x *= b;
        y *= b;
    }

    public void div(double b) {
        x /= b;
        y /= b;
    }

    public static double scalarMul(Vector2D a, Vector2D b) {
        return a.x*b.x + a.y* b.y;
    }


}

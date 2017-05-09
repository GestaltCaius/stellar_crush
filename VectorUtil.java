public class VectorUtil {
    // Class containing additional utility functions for working with vectors.

    public static final Vector TWO_D_ZERO = new Vector(new double[]{0, 0});

    // found the formula here : http://stackoverflow.com/questions/22818531/how-to-rotate-2d-vector
    static Vector rotate(Vector v, double ang) {
        // Rotate v by ang radians - two dimensions only.
        double cos = Math.cos(ang);
        double sin = Math.sin(ang);
        double vx = getX(v);
        double vy = getY(v);
        return new Vector(new double[]{vx * cos - vy * sin, vx * sin + vy * cos});
    }

    static Vector direction(Vector v) {
        // Returns direction of v, but sets angle to Math.PI/2 when v is the zero vector
		// Used to avoid exception in Vector.java
        if (v == TWO_D_ZERO) return new Vector(new double[]{0, 1}); // 90°
        return v.direction();
    }

    // getters for rx and ry, otherwise I can't draw the object
    static double getX(Vector v) {
        return v.cartesian(0);
    }

    static double getY(Vector v) {
        return v.cartesian(1);
    }

}
public class VectorUtil {
    // Class containing additional utility functions for working with vectors.

    public static final Vector TWO_D_ZERO = new Vector(new double[]{0, 0});

    static Vector rotate(Vector v, double ang) {
        // Rotate v by ang radians - two dimensions only.
        return new Vector(2);
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
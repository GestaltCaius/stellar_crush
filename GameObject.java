import java.awt.Color;

public class GameObject {
    // Default implementation of a game object
    private Vector r;
    private Vector v;
    private final double mass;
    private final Color color;
    private final double radius; //"This default pen radius is about 1/200 the width of the default canvas" @StdDraw doc

    public GameObject(Vector r, Vector v, double mass, Color color, double radius) {
     this.r = r;
     this.v = v;
     this.mass = mass;
     this.color = color;
     this.radius = radius;
    }

    // calcultesForces from GameState has updated f, move then updates v and moves the GameObject
    public void move(Vector f, double dt) {
     Vector a = f.times(1/mass); // a = F/m
     v = v.plus(a.times(dt)); // updates velocity
     r = r.plus(v.times(dt)); // moves the GO
    }

    public void draw(Draw dr) {
     dr.setPenColor(color);
     dr.filledCircle(VectorUtil.getX(r), VectorUtil.getY(r), radius);
    }
    
    // return distance to the pow of 2
    public double distanceTo(GameObject that) {
      return this.r.distanceTo(that.r);
    }
    
    public Vector forceFrom(GameObject that) {
      Vector delta = this.r.minus(that.r); // a - b
      double dist = delta.magnitude();
      double F = (StellarCrush.G * this.mass * that.mass) / 
        (dist * dist + Math.pow(StellarCrush.softE, 2));
      return delta.direction().times(F);
    }

    public Vector getLocation() {
        return new Vector(new double[]{VectorUtil.getX(r), VectorUtil.getY(r)});
    }

    public Vector getVelocity() {
        return new Vector(new double[]{VectorUtil.getX(v), VectorUtil.getY(v)});
    }

    // Return the color of the object (useful to draw it in the IViewPort)
        // I could've return color, but I just want to make sure that nobody can access my private fields
    public Color getColor() {
        return new Color(color.getRed(), color.getGreen(), color.getBlue());
    }

    public double getRadius() {
        return radius;
    }
}

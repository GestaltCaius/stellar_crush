import java.awt.Color;

public class GameObject {
    // Default implementation of a game object
    private Vector r;
    private Vector v;
    private final double mass;
    private final Color color;
    private final double radius;


    public GameObject(Vector r, Vector v, double mass, Color color, double radius) {
     this.r = r;
     this.v = v;
     this.mass = mass;
     this.color = color;
     this.radius = radius;
    }
    
    // getter mass (MAY BE USELESS NOW, TODO: CHECK if useless)
    public double getMass() {
      return mass;
    }

    // calcultesForces from GameState has updated f, move then updates v and moves the GameObject
    public void move(Vector f, double dt) {
     Vector a = f.times(1/mass); // a = F/m
     v = v.plus(a.times(dt)); // updates velocity
     r = r.plus(v.times(dt)); // moves the GO
    }

    public void draw() {
     StdDraw.setPenColor(color);
     StdDraw.filledCircle(r.getX(), r.getY(), radius);
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
}

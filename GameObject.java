public class GameObject {
    // Default implementation of a game object
    private Vector r;
    private Vector v;
    private final double mass;
    private final Color color;
    private finale double radius;


    public GameObject(Vector r, Vector v, double mass, Color color, double radius) {
    	this.r = r;
    	this.v = v;
    	this.mass = mass;
    	this.color = color;
    	this.radius = radius;
    }

    // calcultesForces from GameState has updated f, move then updates v and moves the GameObject
    public move(Vector f, double dt) {
    	Vector a = f.times(1/mass); // a = F/m
    	v = v.plus(a.times(dt)); // updates velocity
    	r = r.plus(v.times(dt)); // moves the GO
    }

    public draw() {
    	StdDraw.setPenColor(color);
    	StdDraw.filledCircle(r.data[0], r.data[1], radius);
    }
}

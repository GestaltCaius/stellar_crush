public class GameObject {
    // Default implementation of a game object
    private Vector r;
    private Vector v;
    private final double mass;

    public GameObject(Vector r, Vector v, double mass) {
    	this.r = r;
    	this.v = v;
    	this.mass = mass;
    }
}

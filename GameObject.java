import java.awt.Color;

public class GameObject {
    // Default implementation of a game object
    // mass and radius not final anymore because when objects merge, they get bigger and heavier
    private Vector r;
    private Vector v;
    private double mass;
    private final Color color;
    private double radius; //"This default pen radius is about 1/200 the width of the default canvas" @StdDraw doc

    public GameObject(Vector r, Vector v, double mass, Color color, double radius) {
        this.r = r;
        this.v = v;
        this.mass = mass;
        this.color = color;
        this.radius = radius;
    }

    // calcultesForces from GameState has updated f, move then updates v and moves the GameObject
    public void move(Vector f, double dt) {
        Vector a = f.times(1 / mass); // a = F/m
        v = v.plus(a.times(dt)); // updates velocity
        // Cap velocity
        if (VectorUtil.getX(v) > GameObjectLibrary.VELOCITY_MAX) v = new Vector(new double[]{GameObjectLibrary.VELOCITY_MAX, VectorUtil.getY(v)});
        if (VectorUtil.getY(v) > GameObjectLibrary.VELOCITY_MAX) v = new Vector(new double[]{VectorUtil.getX(v), GameObjectLibrary.VELOCITY_MAX});
        r = r.plus(v.times(dt)); // moves the GO
        double rx = VectorUtil.getX(r);
        double ry = VectorUtil.getY(r);
        double sc = StellarCrush.scale;
        if (rx <= -sc -this.radius) r = new Vector(new double[]{rx + 2*sc, ry});
        if (rx >= sc + this.radius) r = new Vector(new double[]{rx - 2*sc, ry});
        if (ry <= -sc -this.radius) r = new Vector(new double[]{rx, ry + 2*sc});
        if (ry >= sc + this.radius) r = new Vector(new double[]{rx, ry -2*sc});
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

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    // Return the color of the object (useful to draw it in the IViewPort)
    // I could've return color, but I just want to make sure that nobody can access my private fields
    public Color getColor() {
        return new Color(color.getRed(), color.getGreen(), color.getBlue());
    }

    public double getRadius() {
        return radius;
    }

    // Had to put it here and not in PlayerObject because I need to edit Vector v
    public void playerRotate(double ang) {
        this.v = VectorUtil.rotate(this.v, ang);
    }

    public void playerMove(Vector facing, boolean up) {
        if (!up) this.playerRotate(Math.PI); // change direction
        Vector addpos = facing.times(this.getRadius() / 2); // it's either + or - MOVING_VELOCITY
        this.r = this.r.plus(addpos);
    }

    public boolean touch(GameObject that) {
        return this.distanceTo(that) <= this.getRadius()+ that.getRadius();
    }

    public void merge(GameObject that) {
        this.radius += that.radius;
    }

}

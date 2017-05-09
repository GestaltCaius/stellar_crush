import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.*;

public class Camera {
    // Virtual camera - uses a plane one unit away from the focal point
    // For ease of use, this simply locates where the centre of the object is, and renders it if that is in the field of view.
    // Further, the correct rendering is approximated by a circle centred on the projected centre point.

    private final IViewPort holder; // Object from whose perspective the first-person view is drawn
    private final Draw dr; // Canvas on which to draw
    private double FOV; // field of view of camera

    Camera(IViewPort holder, double FOV) {
        // Constructs a camera with field of view FOV, held by holder, and rendered on canvas dr.
        this.holder = holder;
        this.FOV = FOV;
        this.dr = new Draw();
        dr.enableDoubleBuffering();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        dr.setCanvasSize((int) (dim.width * 0.9) / 2, (int) (dim.height * 0.9));
        dr.setLocationOnScreen(dim.width / 2, 1);
        dr.setXscale(-StellarCrush.scale, StellarCrush.scale);
        double Yscale = ((PlayerObject) holder).getRadius() * 10;
        dr.setYscale(-Yscale, Yscale);
    }

    void render(ArrayList<GameObject> objects) {
        // Renders the collection from the camera perspective
        HashMap<Double, GameObject> distances = new HashMap<>(); // Have to use Double and not double which is immutable... @http://stackoverflow.com/questions/8224240/issue-with-using-double-as-value-in-hashmap
        ArrayList<Double> distList = new ArrayList<>();
        // Add every enemy in the FOV to distances and distList
        for (GameObject asteroid : objects) {
            if (this.isInFOV(asteroid)) {
                Double d = holder.getLocation().distanceTo(asteroid.getLocation());
                distances.put(d, asteroid);
                distList.add(d);
            }
        }
        // Sort distList to be able to draw the most far away enemies first
        Collections.sort(distList);
        Collections.reverse(distList); // descending order
        // Draw the enemies
        dr.clear(Draw.BLACK);
        for (Double d : distList) {
            renderObject(distances.get(d));
        }
        dr.show();
    }

    Draw getDraw() {
        return this.dr;
    }

    private void renderObject(GameObject object) {
        Vector delta = object.getLocation().minus(holder.getLocation());
        dr.setPenColor(object.getColor());
        dr.filledCircle(VectorUtil.getX(delta), 0,
                object.getRadius());
    }


    // return true is the angle between facing and the enemy-player vector is less than FOV/2
    public boolean isInFOV(GameObject that) {
        Vector facingForward = holder.getFacingVector();
        Vector dist = that.getLocation().minus(holder.getLocation()); // Vector between player and enemy( b - a)
        double theta = Math.acos( (facingForward.dot(dist)) /
                (facingForward.magnitude() * dist.magnitude()));
        return theta < this.FOV / 2;
    }
}
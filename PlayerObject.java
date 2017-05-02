import java.awt.Color;
import java.awt.event.KeyEvent;

public class PlayerObject extends GameObject implements IViewPort {

    private static final Color DEFAULT_COLOR = StdDraw.WHITE;
    private static final Color DEFAULT_FACING_COLOR = StdDraw.BLACK;
    private static final double DEFAULT_FOV = Math.PI/2; // field of view of player's viewport
    private static final double FOV_INCREMENT = Math.PI/36; // rotation speed

    private Camera cam;

    // How to make extended class constructors :
    // http://stackoverflow.com/questions/2056097/java-extending-class-with-the-constructor-of-main-class-has-parameter
    public PlayerObject(Vector r, double mass, double radius) {
        super(r, new Vector(2), mass, DEFAULT_COLOR, radius);
        this.cam = new Camera(this, DEFAULT_FOV);
    }

	//@Override I'm not using processCommand in GameObject (yet)
    void processCommand(int delay) {
        boolean up, down, left, right;
        // Process keys applying to the player
		// Retrieve 
        if (cam != null) {
            // No commands if no draw canvas to retrieve them from!
            Draw dr = cam.getDraw();
            if (dr != null) {
				// Example code
                if (dr.isKeyPressed(KeyEvent.VK_UP)) up = true;
                if (dr.isKeyPressed(KeyEvent.VK_DOWN)) down = true;
            }
        }
    }

    // location of camera
    public Vector getLocation() {
        return new Vector(2);
        //TODO
    }

    //direction camera is facing in
    public Vector getFacingVector() {
        return new Vector(2);
        //TODO
    }

    // highlight objects below this mass
    public double highlightLevel() {
        return 42.0;
        //TODO
    }
}
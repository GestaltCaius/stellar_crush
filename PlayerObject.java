import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

public class PlayerObject extends GameObject implements IViewPort {

    private static final Color DEFAULT_COLOR = Draw.WHITE;
    private static final Color DEFAULT_FACING_COLOR = Draw.BLACK;
    private static final double DEFAULT_FOV = Math.PI / 2; // field of view of player's viewport
    private static final double FOV_INCREMENT = Math.PI / 36; // rotation speed
    private static final double MOVING_VELOCITY = GameObjectLibrary.VELOCITY_MAX * 100000;

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
            char key = '\0';
            if (dr.hasNextKeyTyped()) key = dr.nextKeyTyped();
            switch (key) {
                case 'q':
                    System.exit(0); // return doesnt work, dunno why
                    return;
                case 'p':
                    StellarCrush.takeScreenshot(dr);
                    break;
                case 'i': // UP
                    this.playerMove(0, -MOVING_VELOCITY);
                    break;
                case 'k': // DOWN
                    this.playerMove(0, +MOVING_VELOCITY);
                    break;
                case 'j': // LEFT
                    this.playerMove(-MOVING_VELOCITY, 0);
                    break;
                case 'l': // RIGHT
                    this.playerMove(+MOVING_VELOCITY, 0);
                    break;
                default:
                    break;
            }
        }
    }

    public void updatePlayerView(ArrayList<GameObject> objects) {
        cam.getDraw().clear(Draw.BLACK);
        cam.render(objects);
        cam.getDraw().show();
    }

    //direction camera is facing in (we can use the direction vector)
    // btw, I did not have to implement another version of direction() in VectorUtility
    // TODO make sure it's *always* OK to use the initial Vector direction method. Might cause bugs
    public Vector getFacingVector() {
        return VectorUtil.direction(this.getVelocity());
    }

    // highlight objects below this mass
    public double highlightLevel() {
        return 42.0;
        //TODO
    }
}
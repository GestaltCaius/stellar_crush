import java.util.*;

public class GameState {
    // Class representing the game state and implementing main game loop update step.

    private ArrayList<GameObject> objects;
    private final PlayerObject player;
    private final Draw dr;

    void update(int delay) {
        // Main game loop update step
        HashMap<GameObject, Vector> forces = calculateForces();
        dr.clear(Draw.BLACK);
        // if too slow, could do two separate loops for move and show, with clear in the middle
        for (GameObject asteroid : objects) {
            asteroid.move(forces.get(asteroid), delay);
            asteroid.draw(dr);
        }
        Vector playerEyesight = player.getLocation().plus(player.getFacingVector().times(player.getRadius()));
        dr.setPenColor(Draw.RED);
        dr.filledCircle(VectorUtil.getX(playerEyesight), VectorUtil.getY(playerEyesight), player.getRadius() * 0.5);
        dr.show(); // show(int i) is deprecated: "replaced by enableDoubleBuffering(), show(), and pause(int t)"

        // Player View
        player.updatePlayerView(objects);
        player.processCommand(delay);
    }

    private HashMap<GameObject, Vector> calculateForces() {
        // Calculate the force on each object for the next update step. (TODO: use the Barnes-Hut method)
        HashMap<GameObject, Vector> forces = new HashMap<GameObject, Vector>();
        for (GameObject asteroid : objects) {
            Vector f = new Vector(new double[]{0, 0});
            for (GameObject otherAsteroid : objects) {
                if (asteroid != otherAsteroid) {
                    f = f.plus(asteroid.forceFrom(otherAsteroid));
                }
            }
            forces.put(asteroid, f);
        }
        return forces;
    }

    // Create a new universe
    public GameState(Draw dr) {
        this.dr = dr;
        dr.setXscale(-StellarCrush.scale, StellarCrush.scale);
        dr.setYscale(-StellarCrush.scale, StellarCrush.scale);
        // Random amount of bodies in our universe (at least five)
            // Not a class constant because I wanted it to change everytime we start a new game
        int BODIES_NUMBER = (int) (Math.random() * 10) + 5;
        objects = GameObjectLibrary.createObjects(BODIES_NUMBER);
        // init of forces Map
        HashMap<GameObject, Vector> forces = new HashMap<GameObject, Vector>();
        for (GameObject asteroid : objects) { // for each asteroid, we compute the force using the otherAsteroids of the objects collection
            forces.put(asteroid, new Vector(new double[]{0, 0}));
        }
        // Player Object creation
        player = (PlayerObject) objects.get(0); // Since objects[0] is the player, that should be OK
    }
}

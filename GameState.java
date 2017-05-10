import java.util.*;

public class GameState {
    // Class representing the game state and implementing main game loop update step.

    private ArrayList<GameObject> objects;
    private final PlayerObject player;
    private final Draw dr;

    // Add return value to quit game when player is dead
    int update(int delay) {
        // Main game loop update step
        HashMap<GameObject, Vector> forces = calculateForces();
        dr.clear(Draw.BLACK);
        // if too slow, could do two separate loops for move and show, with clear in the middle
        double px = VectorUtil.getX(player.getLocation());
        double py = VectorUtil.getY(player.getLocation());
        double pr = player.getRadius();
        ArrayList<GameObject> toBeRemoved = new ArrayList<>(); // I use that list to remove the dead enemies once we are out of the foreach loop
        for (GameObject asteroid : objects) {
            asteroid.move(forces.get(asteroid), delay);
            if (asteroid != player && asteroid.touch(player)) {
                if (asteroid.getMass() <= player.getMass()) toBeRemoved.add(asteroid);
                else return -1; // dead
            }
            else asteroid.draw(dr);
        }
        for (GameObject object : toBeRemoved) {
            player.merge(object);
            objects.remove(object);
        }
        if (objects.size() == 1) return 1; // You're the only object left on the screen. Congrats.
        Vector playerEyesight = player.getLocation().plus(player.getFacingVector().times(player.getRadius()));
        dr.setPenColor(Draw.RED);
        dr.filledCircle(VectorUtil.getX(playerEyesight), VectorUtil.getY(playerEyesight), player.getRadius() * 0.5);
        dr.show(); // show(int i) is deprecated: "replaced by enableDoubleBuffering(), show(), and pause(int t)"

        // Player View
        player.updatePlayerView(objects);
        player.processCommand(delay);

        return 0; // everything's fine, keep updating
    }

    private HashMap<GameObject, Vector> calculateForces() {
        // Calculate the force on each object for the next update step. (TODO: use the Barnes-Hut method)
        HashMap<GameObject, Vector> forces = new HashMap<GameObject, Vector>();
        for (GameObject asteroid : objects) {
            Vector f = new Vector(new double[]{0, 0});
            for (GameObject otherAsteroid : objects) {
                if (asteroid != otherAsteroid) {
                    f = f.plus(asteroid.forceFrom(otherAsteroid));
                    if (asteroid == player) f = new Vector(new double[]{player.getRadius(), player.getRadius()}); // otherwise I can't move the player when I get too close
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

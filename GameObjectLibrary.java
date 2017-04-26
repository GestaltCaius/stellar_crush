import java.util.*;
import java.awt.Color;

public class GameObjectLibrary {
// Class for defining various game objects, and putting them together to create content
// for the game world.  Default assumption is objects face in the direction of their velocity, and are spherical.

    // UNIVERSE CONSTANTS - TUNED BY HAND FOR RANDOM GENERATION
    private static final double ASTEROID_RADIUS = 0.5; // Location of asteroid belt for random initialization
    private static final double ASTEROID_WIDTH = 0.2; // Width of asteroid belt
    private static final double ASTEROID_MIN_MASS = 1E24;
    private static final double ASTEROID_MAX_MASS = 1E26;
    private static final double PLAYER_MASS = 1E25;
    private static final double VELOCITY_MAX = StellarCrush.scale * 0.1; // Velocity max when we create a body

    // create the collection of N asteroids for GameState
    public static ArrayList<GameObject> createObjects(int N) {
     ArrayList<GameObject> objects = new ArrayList<GameObject>();
     for (int i = 0; i < N ; i++) {
      Vector r = new Vector(new double[]{Math.random() * StellarCrush.scale, Math.random() * StellarCrush.scale});
      Vector v = new Vector(new double[]{Math.random() * VELOCITY_MAX, Math.random() * VELOCITY_MAX});
      double mass = Math.random() * ASTEROID_MAX_MASS;
      mass = mass < ASTEROID_MIN_MASS ? mass + ASTEROID_MIN_MASS : mass; // ugly way of making sure MIN MASS <= mass < MAX MASS
      Color color = new Color((int) Math.random() * 255, (int) Math.random() * 255, (int) Math.random() * 255); 
      // TODO: Make sure the body is visible (not same color as the background)
      objects.add(new GameObject(r, v, mass, color, ASTEROID_RADIUS)); // returns a boolean that i could use
     }
     return objects;
    }
}

import java.util.*;
import java.awt.Color;

public class GameObjectLibrary {
// Class for defining various game objects, and putting them together to create content
// for the game world.  Default assumption is objects face in the direction of their velocity, and are spherical.

    // UNIVERSE CONSTANTS - TUNED BY HAND FOR RANDOM GENERATION
    private static final double ASTEROID_RADIUS = StellarCrush.scale / 50; // Location of asteroid belt for random initialization
    private static final double ASTEROID_WIDTH = 0.2; // Width of asteroid belt
    private static final double ASTEROID_MIN_MASS = 1E24;
    private static final double ASTEROID_MAX_MASS = 1E26;
    private static final double PLAYER_MASS = 1E25;
    public static final double VELOCITY_MAX = ASTEROID_WIDTH * 10000; // Velocity max when we create a body

    // create the collection of N asteroids for GameState
    public static ArrayList<GameObject> createObjects(int N) {
        ArrayList<GameObject> objects = new ArrayList<GameObject>();
        // Player object is the FIRST (index 0) element of the asteroids ArrayList
        objects.add(new PlayerObject(new Vector(new double[]{Math.random() * StellarCrush.scale * 2 - StellarCrush.scale, Math.random() * StellarCrush.scale * 2 - StellarCrush.scale}),
                PLAYER_MASS, ASTEROID_RADIUS));
        // Other asteroids are added here
        for (int i = 0; i < N; i++) {
            // Position and velocity vectors
            Vector r = new Vector(new double[]{Math.random() * StellarCrush.scale * 2 - StellarCrush.scale,
                    Math.random() * StellarCrush.scale * 2 - StellarCrush.scale});
            Vector v = new Vector(new double[]{Math.random() * VELOCITY_MAX, Math.random() * VELOCITY_MAX});

            // Mass, with an ugly way of making sure MIN MASS <= mass < MAX MASS
            double mass = Math.random() * ASTEROID_MAX_MASS;
            mass = mass < ASTEROID_MIN_MASS ? mass + ASTEROID_MIN_MASS : mass;
         
            // Color: making sure it's not white (player color) nor black (background color)
            int red, blue, green;
            do {
                red = (int) (Math.random() * 255);
                green = (int) (Math.random() * 255);
                blue = (int) (Math.random() * 255);
                
            } while (red > 50 && red < 200 && green > 50 && green < 200 && blue > 50 && blue < 200);
            Color color = new Color(red, blue, green);

            // Add the new element to the list
            objects.add(new GameObject(r, v, mass, color, ASTEROID_RADIUS)); // returns a boolean that i could use
        }
        return objects;
    }
}

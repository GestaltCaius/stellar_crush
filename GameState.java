import java.util.*;

public class GameState {
    // Class representing the game state and implementing main game loop update step.

    private ArrayList<GameObject> objects;
    private HashMap<GameObject,Vector> forces;
    //private final PlayerObject player;

    void update(int delay) {
        // Main game loop update step
     forces = calculateForces();
     StdDraw.clear();
     // if too slow, could do two seperates loops for move and show, with clear in the middle
     for (GameObject asteroid : objects) {
      asteroid.move(forces.get(asteroid), delay);
      asteroid.draw();
     }
     StdDraw.show(delay);
    }

    private HashMap<GameObject,Vector> calculateForces() {
  // Calculate the force on each object for the next update step. (TODO: use the Barnes-Hut method)
      for (GameObject asteroid : objects) {
        Vector f = new Vector(new double[]{0, 0});
        for (GameObject otherAsteroid : objects) {
          if (asteroid != otherAsteroid) {
            f = f.plus(asteroid.forceFrom(otherAsteroid));
          }
        }
        forces.put(asteroid, f);
      }
    }

    // Create a new universe
    void create() {
     StdDraw.setScale(0, StellarCrush.scale);
     int BODIES_NUMBER = (int) (Math.random() * 10) + 1; // Random amount of bodies in our universe (at least one)
     objects = GameObjectLibrary.createObjects(BODIES_NUMBER);
     // init of forces Map
     HashMap<GameObject,Vector> forces = new HashMap<GameObject,Vector>();
     for (GameObject asteroid : objects) { // for each asteroid, we compute the force using the otherAsteroids of the objects collection
      forces.put(asteroid, new Vector(new double[]{0, 0}));
     }
     // TODO : create player
    }
}

/* Acknowledgements/notes:
 - Some of this code based on code for Rubrica by Steve Kroon
 - Original inspiration idea for this project was IntelliVision's AstroSmash, hence the name
*/

/* Ideas for extensions/improvements:
PRESENTATION:
-theme your game
-hall of fame/high score screen
-modifiable field of view, rear-view mirror, enhance first-person display by showing extra information on screen
-mouse control
-autoscaling universe to keep all universe objects on screen (or making the edge of the universe repel objects)
-better rendering in camera (better handling of objects on edges, and more accurate location rendering
-improved gameplay graphics, including pictures/sprites/textures for game objects
-add sounds for for various game events/music: Warning: adding both sounds and music will likely lead to major
 headaches and frustration, due to the way the StdAudio library works.  If you go down this route, you choose
 to walk the road alone...
-full 3D graphics with 3D universe (no libraries)

MECHANICS/GAMEPLAY CHANGES:
-avoid certain other game objects rather than/in addition to riding into them
-more interactions - missiles, auras, bombs, explosions, shields, etc.
-more realistic physics for thrusters, inertia, friction, momentum, relativity?
-multiple levels/lives
-energy and hit points/health for game objects and players
-multi-player mode (competitive/collaborative)
-checking for impacts continuously during moves, rather than at end of each time step
-Optimize your code to be able to deal with more objects (e.g. with a quad-tree) - document the improvement you get
--QuadTree implementation with some of what you may want at : http://algs4.cs.princeton.edu/92search/QuadTree.java.html
--https://github.com/phishman3579/java-algorithms-implementation/blob/master/src/com/jwetherell/algorithms/data_structures/QuadTree.java may also be useful - look at the Point Region Quadtree
*/
import java.awt.Font;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.*;

public class StellarCrush {
    // Main game class

    // CONSTANTS TUNED FOR GAMEPLAY EXPERIENCE
    static final int GAME_DELAY_TIME = 5000; // in-game time units between frame updates
    static final int TIME_PER_MS = 1000; // how long in-game time corresponds to a real-time millisecond
    static final double G = 6.67e-11; // gravitational constant
    static final double softE = 0.001; // softening factor to avoid division by zero calculating force for co-located objects
    static double scale = 5e10; // plotted universe size

    public static void main(String[] args) {
  /*** Title screen ***/
  // Title screen background
  StdDraw.setScale(0, scale);
  StdDraw.setPenColor(StdDraw.BLACK);
  StdDraw.filledSquare(scale / 2.0, scale / 2.0, scale / 2.0);

  // Title screen texts
  String GAME_NAME = "STELLAR CRUSH";
  String TITLESCREEN_INSTRUCTIONS = "Press any key to start the game.";
  String GAME_CONTROLS = "Use arrows to rotate and control your speed";
  String GAME_INSTRUCTIONS = "Eat all the other spheres to win.";
  String GAME_KEYS = "Press m to quit the game.";
  Font font = new Font("Helvetica", Font.BOLD, 60);
  StdDraw.setPenColor(StdDraw.RED);
  StdDraw.text(scale * 0.5, scale * 0.80, GAME_NAME);
  StdDraw.text(scale * 0.5, scale * 0.65, TITLESCREEN_INSTRUCTIONS);
  StdDraw.text(scale * 0.5, scale * 0.35, GAME_CONTROLS);
  StdDraw.text(scale * 0.5, scale * 0.25, GAME_INSTRUCTIONS);
  StdDraw.text(scale * 0.5, scale * 0.10, GAME_KEYS);
   
  while (true) {
   if (StdDraw.isKeyPressed('m')) {
    // quit the game
   }
   if (StdDraw.hasNextKeyTyped()) {
    // Start the game
   }
  }

     return;
    }

}

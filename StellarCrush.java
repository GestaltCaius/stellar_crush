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

import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.*;

import javax.tools.Tool;

public class StellarCrush {
    // Main game class

    // CONSTANTS TUNED FOR GAMEPLAY EXPERIENCE
    static final int GAME_DELAY_TIME = 5000; // in-game time units between frame updates
    static final int TIME_PER_MS = 1000; // how long in-game time corresponds to a real-time millisecond
    static final double G = 6.67e-11; // gravitational constant
    static final double softE = 0.001; // softening factor to avoid division by zero calculating force for co-located objects
    static double scale = 5e10; // plotted universe size
    public static String screenshotsPath;

    public static void main(String[] args) {
        // Screenshots folder and pathname (only works on Windows, because on Linux it creates a stellarcrush\screenshots folder)
        screenshotsPath = new File("").getAbsolutePath() + "\\screenshots";
        File screenshotFolder = new File(screenshotsPath);
        if (!screenshotFolder.exists()) screenshotFolder.mkdir();
        screenshotsPath += "\\";

        // Draw
        Draw dr = new Draw();
        dr.enableDoubleBuffering();
        boolean TitleScreen = true;
        boolean newGame = false;
        GameState game = null;
        drawTitle(dr);
        while (true) {
            if (newGame) {
                if (TitleScreen) {
                    game = new GameState(dr);
                    TitleScreen = false;
                } else {
                    game.update(GAME_DELAY_TIME);
                }
            }
            char key = '\0';
            if (dr.hasNextKeyTyped()) key = dr.nextKeyTyped();
            switch (key) {
                case 'q':
                    System.exit(0); // return doesnt work, dunno why
                case 'p':
                    String filename = new Date().toString();
                    filename = filename.replace(':', '-'); // : seems to be an illegal filename
                    filename = filename.replace(' ', '_'); // space isnt, but i find it ugly
                    dr.save(screenshotsPath + filename + ".jpg");
                    break;
                case '\0':
                    break;
                default:
                    if (TitleScreen) {
                        newGame = true;
                        break;
                    }
            }
        }
    }

    private static void drawTitle(Draw dr) {
        // Title screen background
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        dr.setCanvasSize((int) (dim.width * 0.9) / 2, (int) (dim.height * 0.9));
        dr.setXscale(0, scale);
        dr.setYscale(0, scale);
        dr.clear(Draw.BLACK);

        // Title screen texts
        dr.setPenColor(Draw.BOOK_RED);
        dr.text(scale * 0.5, scale * 0.80, "STELLAR CRUSH");
        dr.text(scale * 0.5, scale * 0.65, "Press any key to start the game.");
        dr.text(scale * 0.5, scale * 0.35, "Use I,J,K,L to rotate and control your speed");
        dr.text(scale * 0.5, scale * 0.25, "Eat all the other spheres to win.");
        dr.text(scale * 0.5, scale * 0.15, "Press p to take a screenshot");
        dr.text(scale * 0.5, scale * 0.10, "Press q to quit the game.");

        dr.show();
    }

    public static void takeScreenshot(Draw dr) {
        String filename = new Date().toString();
        filename = filename.replace(':', '-'); // : seems to be an illegal filename
        filename = filename.replace(' ', '_'); // space isnt, but i find it ugly
        dr.save(screenshotsPath + filename + ".jpg");
    }
}

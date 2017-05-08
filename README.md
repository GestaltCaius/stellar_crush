# StellarCrush.java

# Detect a key pressed
## At first I wanted to use that (KeyEvent, KeyDispatcher, etc.)
https://docs.oracle.com/javase/7/docs/api/java/awt/KeyboardFocusManager.html
https://docs.oracle.com/javase/7/docs/api/java/awt/KeyEventDispatcher.html
http://stackoverflow.com/questions/18037576/how-do-i-check-if-the-user-is-pressing-a-key

## But StdDraw Keyboard interactions section seems much simpler
http://introcs.cs.princeton.edu/java/stdlib/StdDraw.java.html
http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/awt/event/KeyEvent.java
https://docs.oracle.com/javase/7/docs/api/constant-values.html
### Spoiler alert : it was way easier.

# To position the Draw canvas on the user screen
http://stackoverflow.com/questions/5689676/how-can-i-get-the-screen-resolution-in-java

# How to know if the enemy is in the FoV of the player?
* You have to compute the angle between the FacingForward vector of the player and the vector between the player and the enemy.
--> If that angle is less than FOV/2, then the enemy is in the FoV.

# TO DO NEXT
* game over screen

* Camera
** Detect whether the asteroid is in the PLAYER FOV or not
** rotate player
** keys to move the player
** draw the enemies, while respecting the perspective ie the more it is far away the smaller it is
** highlight weak enemies

* Asteroids "handling"
** bounce on the borders
** merge/split
** be able to eat them
# StellarCrush.java

* Detect a key pressed
** At first I wanted to use that (KeyEvent, KeyDispatcher, etc.)
https://docs.oracle.com/javase/7/docs/api/java/awt/KeyboardFocusManager.html
https://docs.oracle.com/javase/7/docs/api/java/awt/KeyEventDispatcher.html
http://stackoverflow.com/questions/18037576/how-do-i-check-if-the-user-is-pressing-a-key

** But StdDraw Keyboard interactions section seems much simpler
http://introcs.cs.princeton.edu/java/stdlib/StdDraw.java.html
http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/awt/event/KeyEvent.java
https://docs.oracle.com/javase/7/docs/api/constant-values.html

# TO DO NEXT
* N-Body simulation (no collision at first)
* game over screen
* each body of the universe is a GameObject
* animation loop in GameState
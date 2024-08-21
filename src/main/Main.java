package main;

import javax.swing.SwingUtilities;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main extends Thread {

    private boolean running = true;
    private ScreenManager screenManager = new ScreenManager();
    private Spaceship spaceship;
    private List<Alien> aliens;
    private List<Alien1> aliens1;
    private List<Alien2> aliens2;
    private List<Bullet> bullets = new ArrayList<>(); // New list for bullets

    private CollisionManager collisionManager;

    public Main() {
        initializeGame();
    }

    private void initializeGame() {
        // Load background image and create game entities
        screenManager.loadBackgroundImage("space.png");
        aliens = AlienFactory.createAliens(11, 30, 30, 50);
        aliens1 = Alien1Factory.createAliens(2, 11, 30, 90, 50, 60);
        aliens2 = Alien2Factory.createAliens(2, 11, 30, 200, 50, 70);
        spaceship = new Spaceship(screenManager, screenManager.getWidth() / 2, screenManager.getHeight() - 50);

        // Add entities to screen manager
        screenManager.addSprites(aliens);
        screenManager.addSprites(aliens1);
        screenManager.addSprites(aliens2);
        screenManager.addSprites(bullets); // Add bullets to the sprites list
        screenManager.addSprite(spaceship);

        // Create list of collidables for collision manager
        List<Collidable> allCollidables = new ArrayList<>();
        allCollidables.addAll(aliens);
        allCollidables.addAll(aliens1);
        allCollidables.addAll(aliens2);
        allCollidables.addAll(bullets);
        allCollidables.add(spaceship);

        // Initialize collision manager
        collisionManager = new CollisionManager(allCollidables, screenManager);

        // Set up key listener for spaceship movement and shooting
        screenManager.addKeyListener(new SpaceshipKeyListener());
        screenManager.setFocusable(true);

        // Make the screen manager visible
        screenManager.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            main.start();
        });
    }

    public void run() {
        // Main game loop
        while (running) {
            screenManager.repaint();

            // Move game entities and check collisions
            moveAliens();
            moveAliens1();
            moveAliens2();
            moveBullets();
            collisionManager.checkCollisions();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void moveAliens() {
        // Move aliens in the first group
        for (Alien alien : aliens) {
            alien.move();
        }
    }

    private void moveAliens1() {
        // Move aliens in the second group
        for (Alien1 alien1 : aliens1) {
            alien1.move();
        }
    }

    private void moveAliens2() {
        // Move aliens in the third group
        for (Alien2 alien2 : aliens2) {
            alien2.move();
        }
    }

 // List to keep track of collidable objects for additional processing if needed
    private List<Collidable> collidables = new ArrayList<>();

    private void moveBullets() {
        // Iterate through bullets and handle movements and collisions
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();

            // Check if the bullet is not null before moving it
            if (bullet != null) {
                bullet.moveUp(); // Make sure the bullets move upward

                // Check if the bullet needs removal
                if (bullet.needsRemoval()) {
                    bulletIterator.remove(); // Remove the bullet using the iterator
                    screenManager.removeSprite(bullet);
                } else {
                    // Check for collisions with any collidable
                    Collidable collidedCollidable = collisionManager.checkBulletCollision(bullet);
                    if (collidedCollidable != null) {
                        // Bullet hit a collidable, remove the collidable
                        collidables.remove(collidedCollidable);
                        screenManager.removeSprite(collidedCollidable);

                        // Remove the bullet after handling the collision
                        bulletIterator.remove(); // Remove the bullet using the iterator
                        screenManager.removeSprite(bullet);
                    }
                }
            }
        }
    }

    // Key listener for spaceship movement and shooting
    private class SpaceshipKeyListener implements KeyListener {
        private boolean spaceKeyPressed = false;

        public void keyTyped(KeyEvent e) {
            // Empty implementation if you don't need to handle key-typed events
        }

        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_LEFT) {
                spaceship.moveLeft();
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                spaceship.moveRight();
            } else if (keyCode == KeyEvent.VK_SPACE && !spaceKeyPressed) {
                // Create a new bullet using spaceship.shoot()
                Bullet bullet = spaceship.shoot();

                // Add the bullet to the bullets list
                bullets.add(bullet);
                System.out.println("Bullet added");

                // Add the bullet to the sprites list
                screenManager.addSprite(bullet);

                // Update the spaceKeyPressed flag
                spaceKeyPressed = true;
            }
        }

        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                // Update the spaceKeyPressed flag
                spaceKeyPressed = false;
            }
        }
    }
}

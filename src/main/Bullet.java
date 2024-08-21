// Bullet.java
package main;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

public class Bullet extends Sprite implements Collidable {
    // Constructor for creating a Bullet at a specific location
    public Bullet(int x, int y) {
        super("bullet1.png", x, y, "Bullet");
        System.out.println("Bullet created");
    }

    // Move the bullet upward
    public void moveUp() {
        // Move the bullet upward (negative y direction)
        setY(getY() - 20); // Adjust the speed by changing the value
    }

    // Check if the bullet needs to be removed (out of bounds)
    @Override
    public boolean needsRemoval() {
        return getY() < 0;
    }

    // Check if the bullet collides with any Alien in the provided list
    public boolean collidesWithAlien(List<? extends Alien> aliens) {
        for (Alien alien : aliens) {
            if (collidesWith(alien)) {
                return true;
            }
        }
        return false;
    }

    // Check if the bullet is close to any Alien in the provided list
    public boolean isCloseToAlien(List<? extends Alien> aliens) {
        for (Alien alien : aliens) {
            // Adjust the distance threshold based on your preference
            if (Math.abs(getX() - alien.getX()) < 20 && Math.abs(getY() - alien.getY()) < 20) {
                return true;
            }
        }
        return false;
    }

    //---------------------------------------------------------------------

    // Check if the bullet collides with any Alien1 in the provided list
    public boolean collidesWithAlien1(List<? extends Alien1> aliens1) {
        for (Alien1 alien : aliens1) {
            if (collidesWith(alien)) {
                return true;
            }
        }
        return false;
    }

    // Check if the bullet collides with any Alien2 in the provided list
    public boolean collidesWithAlien2(List<? extends Alien2> aliens2) {
        for (Alien2 alien : aliens2) {
            if (collidesWith(alien)) {
                return true;
            }
        }
        return false;
    }

    // Override the collidesWith method to check for collisions with Aliens
    @Override
    public boolean collidesWith(Collidable other) {
        if (other instanceof Alien || other instanceof Alien1 || other instanceof Alien2) {
            Sprite alien = (Sprite) other;

            int x1 = getX();
            int y1 = getY();
            int width1 = getWidth() - 70;
            int height1 = getHeight();

            int x2 = alien.getX();
            int y2 = alien.getY();
            int width2 = alien.getWidth() - 90;
            int height2 = alien.getHeight();

            // Check for collision using modified coordinates and dimensions
            return x1 < x2 + width2 - 400 && x1 + width1 - 400 > x2 && y1 < y2 + height2 - 490 && y1 + height1 > y2;
        }
        return false;
    }

    // Override the paint method to draw the bullet
    @Override
    public void paint(Graphics g) {
        Image image = getImage();
        if (image != null) {
            int width = 20;
            int height = 20;
            g.drawImage(image, getX(), getY(), width, height, null);
        } else {
            System.out.println("Bullet image is null");  // Add this line for debugging
        }
    }
}

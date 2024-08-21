// Alien.java
package main;

import java.awt.Graphics;
import java.awt.Image;

// Class representing an Alien in the game, extending the Sprite class and implementing Collidable interface
public class Alien extends Sprite implements Collidable {

    private static int direction = 1; // 1 for moving right, -1 for moving left
    private boolean isAlive = true; // Flag to indicate if the Alien is alive

    // Constructor to initialize the Alien with specified coordinates
    public Alien(int x, int y) {
        super("alien.png", x, y, "Alien");
    }

    // Method to move the Alien horizontally within the specified boundaries
    public void move() {
        if (isAlive) { // Only move if the Alien is alive
            int newX = getX() + direction * 8; // Adjust the value based on your desired movement speed
            setX(newX);

            // Check if the alien has reached the right or left boundary
            if (newX >= 900 || newX <= 0) {
                // Reverse the direction for the entire row
                direction *= -1;
            }
        }
    }

    // Override the paint method to draw the alien
    @Override
    public void paint(Graphics g) {
        if (isAlive) { // Only paint if the Alien is alive
            int width = 50; // Adjust the width based on your preference
            int height = 50; // Adjust the height based on your preference
            g.drawImage(getImage(), getX(), getY(), width, height, null);
        }
    }

    // Override the getImage method to retrieve the image
    @Override
    public Image getImage() {
        return super.getImage();
    }

    // Override the collidesWith method to check for collisions with Bullets
    @Override
    public boolean collidesWith(Collidable other) {
        if (isAlive) { // Only check for collision if the Alien is alive
            // Check if the collision is with a Bullet
            if (other instanceof Bullet) {
                Bullet bullet = (Bullet) other;

                int x1 = getX();
                int y1 = getY();
                int width1 = getWidth();
                int height1 = getHeight();

                int x2 = bullet.getX();
                int y2 = bullet.getY();
                int width2 = bullet.getWidth();
                int height2 = bullet.getHeight();

                // Check for collision
                return x1 < x2 + width2 &&
                        x1 + width1 > x2 &&
                        y1 < y2 + height2 &&
                        y1 + height1 > y2;
            }
        }
        return false;
    }

    // Setter method to update the alive status of the Alien
    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    // Getter method to check if the Alien is alive
    public boolean isAlive() {
        return isAlive;
    }
}

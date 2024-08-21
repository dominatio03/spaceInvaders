// Spaceship.java
package main;

// Spaceship class represents the player-controlled spaceship
public class Spaceship extends Sprite {
    private ScreenManager screenManager;
    private boolean bulletInMotion = false; // Flag to check if a bullet is in motion

    // Constructor for Spaceship class
    public Spaceship(ScreenManager screenManager, int x, int y) {
        // Call the constructor of the superclass (Sprite) with relevant parameters
        super("spaceship.png", x, y, "Spaceship");
        this.screenManager = screenManager;
    }
    
    // Method to check if a bullet is in motion
    public boolean isBulletInMotion() {
        return bulletInMotion;
    }

    // Method to create and return a new Bullet fired from the spaceship
    public Bullet shoot() {
        // Check if a bullet is already in motion
        // if (!bulletInMotion) {
            // Set the flag to true before creating the bullet
            bulletInMotion = true;

            // Set the initial position of the bullet 10 pixels left from the center of the spaceship
            int bulletX = getX() + getWidth() / 2 - 240; // Adjust based on your desired horizontal offset
            int bulletY = screenManager.getHeight() - 60; // Adjust based on your desired distance from the bottom

            // Create a new bullet
            Bullet bullet = new Bullet(bulletX, bulletY);

            // Return the bullet
            return bullet;
        // }

        // Return null if a bullet is already in motion
        // return null;
    }

    // Method to get the width of the spaceship image
    public int getWidth() {
        // Return the width of the spaceship image using the getWidth method of the superclass (Sprite)
        return super.getWidth();
    }

    // Method to move the spaceship left
    public void moveLeft() {
        // Calculate the new X-coordinate for moving left
        int newX = getX() - 50; // Adjust based on your desired left movement speed
        // Ensure the spaceship stays within the left boundary
        setX(Math.max(newX, 0));
    }

    // Method to move the spaceship right
    public void moveRight() {
        // Calculate the new X-coordinate for moving right
        int newX = getX() + 50; // Adjust based on your desired right movement speed
        // Ensure the spaceship stays within the right boundary
        int maxX = screenManager.getWidth() - getWidth() + 460;
        setX(Math.min(newX, maxX));
    }

    // Method to reset the bulletInMotion flag when the bullet is removed
    public void resetBulletInMotion() {
        // Reset the flag to indicate that no bullet is in motion
        bulletInMotion = false;
    }
}

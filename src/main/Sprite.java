// Sprite.java
package main;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite implements Collidable {
    private Image img;
    private int x, y;

    // Constructor to initialize a Sprite with an image, position, and object type
    public Sprite(String imageName, int x, int y, String objectType) {
        this.x = x;
        this.y = y;
        img = loadImage(imageName);
    }

    // Helper method to load the image from a file
    private Image loadImage(String imageName) {
        try {
            File imageFile = new File("src/icons/" + imageName);
            System.out.println("Loading image from: " + imageFile.getAbsolutePath());
            return ImageIO.read(imageFile);
        } catch (IOException e) {
            System.out.println("Failed to load image: " + "icons/" + imageName);
            e.printStackTrace();
            return null;
        }
    }

    // Method to paint the sprite on the screen
    public void paint(Graphics g) {
        int width = 50;
        int height = 50;
        g.drawImage(img, x, y, width, height, null);
    }

    // Get the width of the sprite
    public int getWidth() {
        return img.getWidth(null);
    }

    // Get the height of the sprite (from Collidable interface)
    @Override
    public int getHeight() {
        return img.getHeight(null);
    }

    // Get the X coordinate of the sprite
    public int getX() {
        return x;
    }

    // Set the X coordinate of the sprite
    public void setX(int x) {
        this.x = x;
    }

    // Get the Y coordinate of the sprite
    public int getY() {
        return y;
    }

    // Set the Y coordinate of the sprite
    public void setY(int y) {
        this.y = y;
    }

    // Implement the collidesWith method from the Collidable interface
    public boolean collidesWith(Collidable other) {
        // Check if the collision is with an Alien, Alien1, or Alien2
        if (other instanceof Alien || other instanceof Alien1 || other instanceof Alien2) {
            Sprite alien = (Sprite) other;

            int x1 = getX();
            int y1 = getY();
            int width1 = getWidth();	
            int height1 = getHeight();

            int x2 = alien.getX();
            int y2 = alien.getY();
            int width2 = alien.getWidth();
            int height2 = alien.getHeight();

            // Check for collision
            boolean collision = x1 < x2 + width2 &&
                    x1 + width1 > x2 &&
                    y1 < y2 + height2 &&
                    y1 + height1 > y2;

            return collision;
        }
        return false;
    }

    // Retrieve the image of the sprite
    public Image getImage() {
        return img;
    }

    // Determine if the sprite needs removal (from Collidable interface)
    @Override
    public boolean needsRemoval() {
        // Add logic here based on when the sprite needs to be removed
        return false; // Change this based on your requirements
    }
}

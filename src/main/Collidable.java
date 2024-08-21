// Collidable.java
package main;

import java.awt.Image;

// Interface representing objects that can collide with each other
public interface Collidable {

    // Check if this object collides with another Collidable object
    boolean collidesWith(Collidable other);

    // Determine if this object needs to be removed from the game
    boolean needsRemoval();

    // Get the X coordinate of this object
    int getX();

    // Get the Y coordinate of this object
    int getY();

    // Get the width of this object
    int getWidth();

    // Get the height of this object
    int getHeight();

    // Get the image associated with this object
    Image getImage();
}

// Alien1Factory.java
package main;

import java.util.ArrayList;
import java.util.List;

// Factory class for creating Alien1 objects
public class Alien1Factory {

    // Create a list of Alien1 objects based on the specified parameters
    public static List<Alien1> createAliens(int rowCount, int aliensPerRow, int startX, int startY, int spacingX, int spacingY) {
        List<Alien1> aliens = new ArrayList<>();
        
        // Iterate through rows and columns to create Alien1 objects
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < aliensPerRow; j++) {
                // Calculate the position of each Alien1 based on the specified parameters
                Alien1 alien = new Alien1(startX + j * spacingX, startY + i * spacingY);
                aliens.add(alien); // Add the created Alien1 to the list
            }
        }
        
        return aliens; // Return the list of created Alien1 objects
    }
}

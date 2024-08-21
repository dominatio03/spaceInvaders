// AlienFactory.java
package main;

import java.util.ArrayList;
import java.util.List;

// Factory class for creating Alien objects
public class AlienFactory {

    // Create a list of Alien objects with specified count, starting coordinates, and spacing
    public static List<Alien> createAliens(int count, int startX, int startY, int spacingX) {
        List<Alien> aliens = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            // Create a new Alien at the specified position
            Alien alien = new Alien(startX + i * spacingX, startY);
            
            // Add the created Alien to the list
            aliens.add(alien);
        }
        return aliens; // Return the list of created Alien objects
    }
}

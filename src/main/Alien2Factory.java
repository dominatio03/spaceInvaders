// Alien2Factory.java
package main;

import java.util.ArrayList;
import java.util.List;

// Factory class for creating instances of Alien2
public class Alien2Factory {

    // Method to create a list of Alien2 instances based on the specified parameters
    public static List<Alien2> createAliens(int rowCount, int aliensPerRow, int startX, int startY, int spacingX, int spacingY) {
        List<Alien2> aliens = new ArrayList<>();

        // Loop through rows
        for (int i = 0; i < rowCount; i++) {
            // Loop through aliens in each row
            for (int j = 0; j < aliensPerRow; j++) {
                // Create a new instance of Alien2 with adjusted coordinates
                Alien2 alien = new Alien2(startX + j * spacingX, startY + i * spacingY);
                // Add the created Alien2 instance to the list
                aliens.add(alien);
            }
        }

        // Return the list of created Alien2 instances
        return aliens;
    }
}

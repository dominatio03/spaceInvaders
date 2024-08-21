// ScreenManager.java
package main;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

// Manages the game screen, including sprites and background
public class ScreenManager {
    private static final int GAME_WIDTH = 1024;
    private static final int GAME_HEIGHT = 768;

    private JFrame frame = new JFrame();
    private Canvas canvas = new Canvas();
    private List<Sprite> spriteList = new ArrayList<>();
    private BufferedImage backgroundImage;

    // Inner class representing the canvas where the game is drawn
    class Canvas extends JPanel {
        private static final long serialVersionUID = 1L;

        public Canvas() {
            // Load the background image here
            loadBackgroundImage("space.png");
        }

        // Override paintComponent to draw background and sprites
        public final void paintComponent(Graphics g) {
            // Draw the background image
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
            } else {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
            }

            // Draw the sprites
            for (Sprite sprite : spriteList) {
                if (sprite != null) {
                    sprite.paint(g);
                }
            }
        }
    }

    // Load the background image from the specified file path
    public void loadBackgroundImage(String imagePath) {
        try {
            // Adjust the path to the "src/icons" directory
            File file = new File("src/icons/" + imagePath);
            System.out.println("Absolute Path: " + file.getAbsolutePath());
            backgroundImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add a list of sprites to the spriteList and repaint the canvas
    public void addSprites(List<? extends Sprite> sprites) {
        spriteList.addAll(sprites);
        repaint();
    }

    // Add a single sprite to the spriteList and repaint the canvas
    public void addSprite(Sprite sprite) {
        spriteList.add(sprite);
        repaint();
    }

    // Get a copy of the spriteList
    public List<Sprite> getSprites() {
        return new ArrayList<>(spriteList);
    }

    // Remove a collidable sprite from the spriteList and repaint the canvas
    public void removeSprite(Collidable collidable) {
        spriteList.remove(collidable);
        repaint();
    }

    // Constructor to initialize the frame and canvas
    public ScreenManager() {
        canvas.setSize(GAME_WIDTH, GAME_HEIGHT);
        frame.setSize(GAME_WIDTH, GAME_HEIGHT);
        frame.setResizable(false);
        frame.add(canvas);
        frame.setVisible(true);

        // Add window listener to handle closing event
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frame.dispose();
                System.exit(0);
            }
        });
    }

    // Get the width of the canvas
    public int getWidth() {
        return canvas.getWidth();
    }

    // Get the height of the canvas
    public int getHeight() {
        return canvas.getHeight();
    }

    // Repaint the canvas
    public void repaint() {
        canvas.repaint();
    }

    // Set the visibility of the frame
    public void setVisible(boolean b) {
        frame.setVisible(b);
    }

    // Add a key listener to the frame
    public void addKeyListener(KeyListener listener) {
        frame.addKeyListener(listener);
    }

    // Set the focusability of the frame
    public void setFocusable(boolean focusable) {
        frame.setFocusable(focusable);
    }
}

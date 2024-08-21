// CollisionManager.java
package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollisionManager {
    private List<Collidable> collidables;
    private ScreenManager screenManager;

    // Constructor to initialize CollisionManager with collidables and screenManager
    public CollisionManager(List<Collidable> collidables, ScreenManager screenManager) {
        this.collidables = collidables;
        this.screenManager = screenManager;
    }

    // Check for collision between a bullet and aliens, and handle accordingly
    public Collidable checkBulletCollision(Bullet bullet) {
        Iterator<Collidable> iterator = collidables.iterator();
        while (iterator.hasNext()) {
            Collidable collidable = iterator.next();
            if (collidable instanceof Alien || collidable instanceof Alien1 || collidable instanceof Alien2) {
                if (bullet.collidesWith(collidable)) {
                    // Handle collision based on the type of collidable
                    if (collidable instanceof Alien && ((Alien) collidable).isAlive()) {
                        handleCollision(bullet, collidable);
                        ((Alien) collidable).setAlive(false);
                        iterator.remove();
                        return collidable;
                    } else if (collidable instanceof Alien1 && ((Alien1) collidable).isAlive()) {
                        handleCollision(bullet, collidable);
                        ((Alien1) collidable).setAlive(false);
                        iterator.remove();
                        return collidable;
                    } else if (collidable instanceof Alien2 && ((Alien2) collidable).isAlive()) {
                        handleCollision(bullet, collidable);
                        ((Alien2) collidable).setAlive(false);
                        iterator.remove();
                        return collidable;
                    }
                }
            }
        }
        return null;
    }

    // Check for collisions between bullets and aliens, and handle accordingly
    public void checkCollisions() {
        List<Collidable> collidablesToRemove = new ArrayList<>();

        for (Collidable collidable1 : collidables) {
            if (collidable1 instanceof Bullet) {
                Bullet bullet = (Bullet) collidable1;
                for (Collidable collidable2 : collidables) {
                    if ((collidable2 instanceof Alien || collidable2 instanceof Alien1 || collidable2 instanceof Alien2)
                            && bullet.collidesWith(collidable2)) {
                        collidablesToRemove.add(collidable1);

                        // Handle collision based on the type of collidable
                        if (collidable2 instanceof Alien && ((Alien) collidable2).isAlive()) {
                            ((Alien) collidable2).setAlive(false);
                            collidablesToRemove.add(collidable2);
                        } else if (collidable2 instanceof Alien1 && ((Alien1) collidable2).isAlive()) {
                            ((Alien1) collidable2).setAlive(false);
                            collidablesToRemove.add(collidable2);
                        } else if (collidable2 instanceof Alien2 && ((Alien2) collidable2).isAlive()) {
                            ((Alien2) collidable2).setAlive(false);
                            collidablesToRemove.add(collidable2);
                        }
                    }
                }
            }
        }

        // Remove collidables after the iteration
        for (Collidable collidable : collidablesToRemove) {
            screenManager.removeSprite(collidable);
        }
        collidables.removeAll(collidablesToRemove);
    }

    // Handle collision by removing the bullet (handled in checkCollisions())
    private void handleCollision(Bullet bullet, Collidable collidable) {
        screenManager.removeSprite(bullet);
        // No need to remove collidable here, as it will be removed in checkCollisions()
    }
}

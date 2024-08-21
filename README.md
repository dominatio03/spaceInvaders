Project Manifest

-------------------------------

Project Overview:

The project is a simple 2D game implemented in Java. It features different types of aliens, bullets, and a collision manager to handle interactions between entities. 
The game's graphical elements are managed by a ScreenManager, and the main game logic is orchestrated by the Game class.

-------------------------------

Principles Applied:

1. Single Responsibility:

   - CollisionManager.java: Manages collisions between entities.
   - Alien.java, Alien1.java, Alien2.java, Bullet.java: Define behaviors and check collisions for specific entities.
   - ScreenManager.java: Manages graphical elements and sprites.
   - Main.java: Orchestrates the game flow.
   - AlienFactory.java, Alien1Factory.java, Alien2Factory.java: Create instances of specific alien types.

2. Open/Closed:

   - CollisionManager.java: Open for extension (new collidable types), closed for modification.
   - Alien.java, Alien1.java, Alien2.java, Bullet.java: Open for extension (new entity types), closed for modification.
   - ScreenManager.java: Open for extension (new graphical elements), closed for modification.
   - Main.java: Open for extension (new game features), closed for modification.
   - AlienFactory.java, Alien1Factory.java, Alien2Factory.java: Open for extension, closed for modification.

3. Liskov Substitution:

   - CollisionManager.java: Handles any class implementing the Collidable interface.
   - Alien.java, Alien1.java, Alien2.java, Bullet.java: Subclasses of Collidable, ensuring interchangeability.
   - AlienFactory.java, Alien1Factory.java, Alien2Factory.java: Return lists of specific alien types, adhering to expected behavior.

4. Interface Separation:

   - Collidable.java: Interface for collision handling.
   - Alien.java, Alien1.java, Alien2.java, Bullet.java: Implement Collidable, providing a common way to handle collisions.
   - ScreenManager.java: Uses Collidable interface without knowledge of specific collidable types.
   - AlienFactory.java, Alien1Factory.java, Alien2Factory.java: Provide factory methods adhering to specific interfaces.

5. Dependency Inversion:

   - Abstract super classes and final concrete classes: Depend on the Collidable interface. Classes adhere to interfaces, promoting flexibility in development.

-------------------------------

## File Relationships
- `CollisionManager.java`: Manages collisions between Bullets and Aliens. Utilizes Singleton pattern.
  - Depends on: `Collidable.java`, `Bullet.java`, `Alien.java`, `Alien1.java`, `Alien2.java`, `ScreenManager.java`
  - Used by: `Main.java`

- `Main.java`: Main game logic.
  - Depends on: `CollisionManager.java`, `Collidable.java`, `Bullet.java`, `Alien.java`, `Alien1.java`, `Alien2.java`, `ScreenManager.java`, `AlienFactory.java`, `Alien1Factory.java`, `Alien2Factory.java`

- `Collidable.java`: Interface for collidable entities.
  - Implemented by: `Alien.java`, `Alien1.java`, `Alien2.java`, `Bullet.java`

- `Alien.java`: Represents the Alien entity.
  - Depends on: `Collidable.java`

- `Alien1.java`: Represents another type of Alien entity.
  - Depends on: `Collidable.java`

- `Alien2.java`: Represents yet another type of Alien entity.
  - Depends on: `Collidable.java`

- `Bullet.java`: Represents the Bullet entity.
  - Depends on: `Collidable.java`

- `ScreenManager.java`: Manages the game screen and sprites.
  - Used by: `Main.java`, `CollisionManager.java`

- `AlienFactory.java`: Factory for creating instances of Alien entities.
  - Used by: `Main.java`

- `Alien1Factory.java`: Factory for creating instances of Alien1 entities.
  - Used by: `Main.java`

- `Alien2Factory.java`: Factory for creating instances of Alien2 entities.
  - Used by: `Main.java`

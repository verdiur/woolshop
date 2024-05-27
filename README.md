# woolshop
jeu tout choupi

## Diagramme de classes
```mermaid
classDiagram
    
    class DirEnum {    
    }
    <<enumeration>> DirEnum

    class GamePanel {
    }
    
    class KeyHandler {
    }

    class Entity {
        +float m_x
        +float m_y
        +draw(Graphics2D g2)* void
    }
    <<abstract>> Entity

    class Collision {
        +isColliding(Entity ent, DirEnum dir)* bool
        +isNext(Entity ent)* bool
        +isAt(Entity ent, DirEnum dir)* bool
    }
    <<interface>> Collision

    class Actor {
        +HashMap~ArrayList~BufferedImage~~ m_sprite_map
        +GamePanel m_gp
        +draw(Graphics2D g2)* void
        +move(DirEnum dir) void
    }
    <<abstract>> Actor

    class Tile {
        BufferedImage m_sprite
    }

    class GameObj_placeholder_name {
        +???
    }

    class Interactible {
        +???
    }

    class Npc {
        +???
    }

    class Player {
        +???
    }

    Entity <|-- Actor
    Collision <|-- Actor

    Entity <|-- Tile

    Collision <|-- GameObj_placeholder_name
    Tile <|-- GameObj_placeholder_name

    GameObj_placeholder_name <|-- Interactible

    Actor <|-- Npc

    Actor <|-- Player
```

## Structure du projet
- `bin/` contient les binaires.
- `res/` contient les ressources.
- `src/` contient le code source :
    - `util/` contient des éléments utilitaires.
    - `main/` contient le programme principal.
    - `entity/` contient la classe `Entity` et ses classes filles directes (`Tile` et `Actor`).
    - `tile/` contient les classes filles de `Tile`.
    - `actor/` contient les classes filles de `Actor`.
    - `collision/` contient `Collision`.

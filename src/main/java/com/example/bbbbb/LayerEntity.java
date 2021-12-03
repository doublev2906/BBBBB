package com.example.bbbbb;

import com.example.bbbbb.tiles.Brick;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LayerEntity extends Entity{
    public List<Entity> entities = new ArrayList<>();

    public LayerEntity( Entity ... entities) {
        this.entities.addAll(Arrays.asList(entities));
    }

    @Override
    public void render(GraphicsContext gc) {
        for (Entity e : entities){
            e.render(gc);
        }
    }

    public Entity getEntity(){
        return entities.get(entities.size() - 1);
    }
    public void explodeBrick(){
        if (entities.get(entities.size() -1) instanceof Brick ){
            ((Brick)(entities.get(entities.size() - 1))).isDestroy = true;
        }

    }

    @Override
    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
            if (entities.get(i).isRemove){
                entities.remove(i);
            }
        }
    }

    @Override
    public boolean collide(Entity e) {
        return entities.get(entities.size() - 1).collide(e);
    }
}

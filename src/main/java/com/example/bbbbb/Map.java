package com.example.bbbbb;

import com.example.bbbbb.bomb.Bomb;
import com.example.bbbbb.tiles.Block;
import com.example.bbbbb.tiles.Brick;
import com.example.bbbbb.tiles.Grass;
import com.example.bbbbb.tiles.Tile;
import javafx.scene.canvas.GraphicsContext;

public class Map implements Render{
    public static final int WIDTH = 23;
    public static final int HEIGHT = 13;
    public static final int TILE_SIZE = 32;

    public static int map[][] = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 2, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 2, 2, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };
    public Tile [][] mapTile = new Tile[HEIGHT][WIDTH];
    Bomb bomb;


    public Map() {
        createTile();
    }

    private void createTile(){
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (map[j][i] == 0) mapTile[j][i] = new Grass(i*32,j*32);
                else if (map[j][i] == 1) mapTile[j][i] = new Block(i*32,j*32);
                else mapTile[j][i] = new Brick(i*32,j*32);
            }
        }
    }

    public void addBomb(int x, int y){
        if (bomb == null){
            bomb = new Bomb();
            bomb.setX(x);
            bomb.setY(y);
            bomb.setmMap(this);
        }

    }


    @Override
    public void update() {
        updateBomb();
        updateMap();
    }

    private void updateMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                mapTile[j][i].update();
                if (mapTile[j][i] instanceof Brick){
                    if (((Brick) mapTile[j][i]).isRemove){
                        mapTile[j][i] = new Grass(i*32,j*32);
                    }
                }

            }
        }
    }

    private void updateBomb(){
        if (bomb != null){
            bomb.update();
            if (bomb.explode){
//                bomb.flame.mMap = this;
//                if (bomb.flame.stop) {
//                    bomb = null;
//                }
                if (bomb.stopFlame){
                    bomb = null;
                }
            }
        }
    }



    @Override
    public void render(GraphicsContext gc) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                mapTile[i][j].render(gc);
            }
        }
        if (bomb != null){
            bomb.render(gc);
        }
    }
}

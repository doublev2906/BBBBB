package com.example.bbbbb;

import com.example.bbbbb.enemy.Balloon;
import com.example.bbbbb.enemy.Needle;
import com.example.bbbbb.enemy.SkullHead;
import com.example.bbbbb.item.BombItem;
import com.example.bbbbb.item.FlameItem;
import com.example.bbbbb.item.Portal;
import com.example.bbbbb.item.SpeedItem;
import com.example.bbbbb.tiles.Block;
import com.example.bbbbb.tiles.Brick;
import com.example.bbbbb.tiles.Grass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Level {
    public char[][] map;
    public String lv;
    public int height;
    public int width;
    Board board;


    public Level(String lv, Board board) {
        this.board = board;
        this.lv = lv;
        loadMap();
    }

    private void loadMap() {
        File file = new File(lv+".txt");
        List<String> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] arr = list.get(0).trim().split(" ");
        height = Integer.parseInt(arr[1]);
        width = Integer.parseInt(arr[2]);
        map = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = list.get(i + 1).charAt(j);
            }
        }
    }

    public void createEntities() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                char c = map[y][x];
                if (c == '#') {
                    board.entities[y][x] = new Block(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE);
                } else if (c == ' ') {
                    board.entities[y][x] = new Grass(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE);
                } else if (c == 'p') {
                    board.entities[y][x] = new Grass(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE);
                    board.addBomber(new Bomber(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE, board));
                } else if (c == '2') {
                    board.entities[y][x] = new Grass(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE);
                    board.enemies.add(new Needle(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE, board));
                }
                else if (c == '1') {
                    board.entities[y][x] = new Grass(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE);
                    board.enemies.add(new Balloon(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE, board));
                }
                else if (c == '3') {
                    board.entities[y][x] = new Grass(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE);
                    board.enemies.add(new SkullHead(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE, board));
                }
                else if (c == 'x'){
                    board.entities[y][x] = new LayerEntity(new Grass(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE),
                            new Portal(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE),
                            new Brick(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE));
                    board.items.add(new Portal(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE));
                }
                else if (c == 'b') {
                    board.entities[y][x] = new LayerEntity(new Grass(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE),
                            new BombItem(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE),
                            new Brick(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE));
                    board.items.add(new BombItem(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE));
                }
                else if (c == 's') {
                    board.entities[y][x] = new LayerEntity(new Grass(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE),
                            new SpeedItem(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE),
                            new Brick(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE));
                    board.items.add(new SpeedItem(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE));
                }
                else if (c == 'f'){
                    board.entities[y][x] = new LayerEntity(new Grass(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE),
                            new FlameItem(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE),
                            new Brick(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE));
                    board.items.add(new FlameItem(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE));
                }
                else {
                    board.entities[y][x] = new LayerEntity(new Grass(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE)
                            , new Brick(x * Game.ENTITY_SIZE, y * Game.ENTITY_SIZE));
                }
            }
        }
    }
}

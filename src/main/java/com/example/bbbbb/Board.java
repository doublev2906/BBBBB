package com.example.bbbbb;

import com.example.bbbbb.bomb.Bomb;
import com.example.bbbbb.enemy.Enemy;
import com.example.bbbbb.item.Item;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Board implements Render{
    private Bomber bomber;
    private Bomb bomb;
    private Bomb bomb1;
    private List<Bomb> bombs;
    public Entity [][] entities;
    public List<Enemy> enemies = new ArrayList<>();
    public List<Item> items = new ArrayList<>();
    private Level level;
    private Input input;
    public int mapH;
    public int mapW;
    public boolean win = false;
    public boolean buffBomb = false;
    public boolean lose = false;
    public int score = 0;
    public Game game;
    int countTime = 0, timeNeed = 120;



    public int getMapH() {
        return mapH;
    }

    public int getMapW() {
        return mapW;
    }

    public Board(Input input) {
        this.input = input;
        bombs = new ArrayList<>();
        level = new Level("level1",this);
        mapH = level.height;
        mapW = level.width;
        entities = new Entity[mapH][mapW];
        level.createEntities();
    }
    public Board(Input input, String lv) {
        this.input = input;
        bombs = new ArrayList<>();
        level = new Level(lv,this);
        mapH = level.height;
        mapW = level.width;
        entities = new Entity[mapH][mapW];
        level.createEntities();
    }

    public void addBomber(Bomber bomber){
        this.bomber = bomber;
        bomber.setInput(input);
    }
    
    public void addBomb(int x, int y, boolean buff){
        if (bomb == null){
            bomb = new Bomb(x,y,this);
            bomb.buffFlame = buff;
        }
        else if (bomb1 == null && buffBomb && (x != bomb.x || y != bomb.y)){
            bomb1 = new Bomb(x,y,this);
            bomb1.buffFlame = buff;
        }

    }


    @Override
    public void update() {
        if(countTime < timeNeed){
            countTime++;
        }
        else countTime = timeNeed;
        if (countTime == timeNeed){
            detectedBomber();
            if (bomber != null){
                bomber.update();
                if (!bomber.alive){
                    if (bomber.mSprite.end){
                        Sound.playerDead();
                        bomber = null;
                        lose = true;
                    }

                }
            }

            for (int i = 0; i < mapH; ++i) {

                for (int j = 0; j < mapW; j++) {
                    entities[i][j].update();
                }
            }
            for (int i = 0; i < enemies.size(); i++) {
                enemies.get(i).update();
                if (!enemies.get(i).isAlive()){
                    score += 1000;
                    enemies.remove(i);
                }
            }
            if (bomb != null){
                bomb.update();
                if (bomb.stopFlame) {
                    Sound.playSound("explosionBomb");
                    bomb = null;
                }
            }
            if (bomb1 != null){
                bomb1.update();
                if (bomb1.stopFlame){
                    Sound.playSound("explosionBomb");
                    bomb1 = null;
                }
            }


            if (win){
                System.out.println("Win");
            }
        }


    }

    @Override
    public void render(GraphicsContext gc) {
        if (countTime == timeNeed){
            for (int i = 0; i < mapH; i++) {
                for (int j = 0; j < mapW; j++) {
                    entities[i][j].render(gc);
                }
            }

            if (bomb != null){
                bomb.render(gc);
            }
            if (bomb1 != null){
                bomb1.render(gc);
            }
            for (int i = 0; i < bombs.size(); i++) {
                bombs.get(i).render(gc);
            }
            for (int i = 0; i < enemies.size(); i++) {
                enemies.get(i).render(gc);
            }
            if (bomber != null){
                bomber.render(gc);
            }
        } else {
            game.drawlv(level.lv);
        }
    }

    public void detectedBomber(){
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            if(bomber != null){
                if ((e.x + 8)/32 == bomber.x/32 && (e.y + 4)/32 == bomber.y/32){
                    bomber.alive = false;
                }
            }
        }
    }
    public void playAgain(){
    }

    public Bomb getBomb() {
        return bomb;
    }
    public Bomb getBomb1() {
        return bomb1;
    }

    public Bomber getBomber() {
        return bomber;
    }
}

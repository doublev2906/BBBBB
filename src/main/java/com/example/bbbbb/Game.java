package com.example.bbbbb;

import com.example.bbbbb.sprite.Sprite;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Game extends Canvas implements Render {

    public static final int ENTITY_SIZE = 32;
    int info = 50;
    Input input;

    GraphicsContext gc;
    Board board;
    private int time = 200;
    double w,h;
    Timeline gameLoop;

    public Game(Input input) {
        gc = getGraphicsContext2D();
        this.input = input;
        board = new Board(input);
        setWidth(board.getMapW()*32);
        setHeight(board.getMapH()*32 + info);
        board.game = this;
        w = getWidth();
        h = getHeight();
    }

    public void start(){
        gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );

        KeyFrame kf = new KeyFrame(
                Duration.millis((double) 1000 / 60),                // 60 FPS
                new EventHandler<>() {
                    long timer = System.currentTimeMillis();
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (System.currentTimeMillis() - timer > 1000) {
                            time--;
                            timer = System.currentTimeMillis();
                        }
                        update();
                        render();


                    }
                });

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();
    }

    @Override
    public void update() {
        if (board != null){
            board.update();
            if (board.win){
                board = new Board(input,"level2");
                board.game = this;
                time = 200;
            }
            if (board.lose){
                board = null;
            }
        } else {
            if (input.space){
                board = new Board(input);
                board.game = this;
                time = 200;
            }
        }

    }

    @Override
    public void render(GraphicsContext gc) {

    }

    public void render() {
        gc.clearRect(0,0,getWidth(),getHeight());
//        if (board.lose) {
//            drawPlayAgain();
//            if (input.space) {
//                board = new Board(input);
//            }
//        }
        if (board != null){
            board.render(gc);
            drawInfo();
        } else {
            drawPlayAgain();
        }



    }
    void drawInfo(){
        gc.setFill(Color.BLACK);
        gc.fillRect(0,board.getMapH()*32,getWidth(),info);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial",15));
        gc.fillText("Time: " + String.valueOf(time),150,board.getMapH() * 32 + 30);
        gc.fillText("Point: " + String.valueOf(board.score),600,board.getMapH() * 32 + 30);
    }
    void drawPlayAgain(){
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,w,h);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial",30));
        gc.fillText("Click space to play again",getWidth()/2 - 150,getHeight()/2);
    }
    void drawlv(String lv){
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,w,h);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial",40));
        gc.fillText(lv,getWidth()/2 - 60,getHeight()/2);

    }
}

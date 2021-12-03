package com.example.bbbbb;

import com.example.bbbbb.tiles.Block;
import com.example.bbbbb.tiles.Brick;
import com.example.bbbbb.tiles.Grass;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Bomberman extends Application
{

    private Game game;
    private Group root;
    Input input;
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage) {
        theStage.setTitle("Bomberman");

        root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);
        input = new Input();
        theScene.addEventHandler(KeyEvent.ANY, input);
        game = new Game(input);
        root.getChildren().add(game);
        game.start();
        Sound.ThemeSound();

        theStage.show();
    }



}

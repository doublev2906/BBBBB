package com.example.bbbbb.sprite;

import com.example.bbbbb.Bomberman;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class MSprite{
    public  Image[] bomber_anim_left;
    public  Image[] bomber_anim_right;
    public  Image[] bomber_anim_up;
    public  Image[] bomber_anim_down;

    public  Image[] bombAnim;

    public Image[] flameAnimCenter;
    public Image[] flameAnimLeft;
    public Image[] flameAnimRight;
    public Image[] flameAnimUp;
    public Image[] flameAnimDown;

    public Image[] flameAnimCenterLeft;
    public Image[] flameAnimCenterRight;
    public Image[] flameAnimCenterUp;
    public Image[] flameAnimCenterDown;



    public Image[] brickAnim;

    public Image[] bomberDead;

    private int framePlayers = 0,intervalPlayers = 4,indexBomberAnimations = 0;
    private int frameBomb = 0,intervalBomb = 7,indexBombAnimation = 0;
    public int frameFlame = 0,intervalFlame = 3,indexFlameAnimation = 0;
    public boolean flameStop = false;
    private  int frameBrick = 0,intervalBrick = 5,indexBrickAnimation = 0;
    public boolean end = false;
    private  int frameDead = 0,intervalDead = 8, indexDeadAnimation = 0;

    public static final Image balloonLeft = new Image(String.valueOf(Bomberman.class.getResource("balloonLeft.png")));
    public static final Image balloonRight= new Image(String.valueOf(Bomberman.class.getResource("balloonEast.png")));
    public static final Image balloonUp = new Image(String.valueOf(Bomberman.class.getResource("balloonNorth.png")));
    public static final Image balloonDown = new Image(String.valueOf(Bomberman.class.getResource("balloonSouth.png")));

    public static final Image needleLeft = new Image(String.valueOf(Bomberman.class.getResource("needleWest.png")));
    public static final Image needleRight= new Image(String.valueOf(Bomberman.class.getResource("needleEast.png")));
    public static final Image needleUp = new Image(String.valueOf(Bomberman.class.getResource("needleNorth.png")));
    public static final Image needleDown = new Image(String.valueOf(Bomberman.class.getResource("needleSouth.png")));

    public static final Image blockHeadLeft = new Image(String.valueOf(Bomberman.class.getResource("blockHeadWest.png")));
    public static final Image blockHeadRight= new Image(String.valueOf(Bomberman.class.getResource("blockHeadEast.png")));
    public static final Image blockHeadUp = new Image(String.valueOf(Bomberman.class.getResource("blockHeadNorth.png")));
    public static final Image blockHeadDown = new Image(String.valueOf(Bomberman.class.getResource("blockHeadSouth.png")));

    public MSprite() {
    }

    public void initBomberAnimate(){
        bomber_anim_left = new Image[7];
        bomber_anim_right = new Image[7];
        bomber_anim_up = new Image[7];
        bomber_anim_down = new Image[7];

        Image image = new Image(String.valueOf((Bomberman.class.getResource("animation_character.png"))));
        for (int i = 0; i < 7; i++)
            bomber_anim_right[i] = new WritableImage(image.getPixelReader(), i * 32+8, 36, 16, 24);
        for (int i = 0; i < 7; i++)
            bomber_anim_up[i] = new WritableImage(image.getPixelReader(), i * 32+8, 4, 16, 24);
        for (int i = 0; i < 7; i++)
            bomber_anim_down[i] = new WritableImage(image.getPixelReader(), i * 32+8, 68, 16, 24);
        for (int i = 0; i < 7; i++)
            bomber_anim_left[i] = new WritableImage(image.getPixelReader(), i * 32+8, 100, 16, 24);
    }

    public Image moveSprite(Image[] player){
        framePlayers++;
        if (framePlayers > intervalPlayers){
            framePlayers = 0;
            indexBomberAnimations++;
            if (indexBomberAnimations > 6){
                indexBomberAnimations = 0;
            }
        }
        return player[indexBomberAnimations];
    }

    public void initBombSprite(){
        bombAnim = new Image[3];
        bombAnim[0] = Sprite.bomb.getFxImage();
        bombAnim[1] = Sprite.bomb_1.getFxImage();
        bombAnim[2] = Sprite.bomb_2.getFxImage();
    }

    public Image bombExplosion(){
        frameBomb++;
        if (frameBomb == intervalBomb){
            frameBomb = 0;
            indexBombAnimation++;
            if (indexBombAnimation > 2){
                indexBombAnimation = 0;
            }
        }
        return bombAnim[indexBombAnimation];
    }

    public void initFlameAnim(){
        flameAnimCenter = new Image[4];
        flameAnimLeft = new Image[4];
        flameAnimRight = new Image[4];
        flameAnimUp = new Image[4];
        flameAnimDown = new Image[4];

        flameAnimCenterLeft = new Image[4];
        flameAnimCenterRight = new Image[4];
        flameAnimCenterUp = new Image[4];
        flameAnimCenterDown = new Image[4];

        flameAnimCenter[0] = Sprite.font_explosion_1.getFxImage();
        flameAnimCenter[1] = Sprite.font_explosion_2.getFxImage();
        flameAnimCenter[2] = Sprite.font_explosion_3.getFxImage();
        flameAnimCenter[3] = Sprite.font_explosion_4.getFxImage();

        flameAnimLeft[0] = Sprite.left_explosion_1.getFxImage();
        flameAnimLeft[1] = Sprite.left_explosion_2.getFxImage();
        flameAnimLeft[2] = Sprite.left_explosion_3.getFxImage();
        flameAnimLeft[3] = Sprite.left_explosion_4.getFxImage();

        flameAnimRight[0] = Sprite.right_explosion_1.getFxImage();
        flameAnimRight[1] = Sprite.right_explosion_2.getFxImage();
        flameAnimRight[2] = Sprite.right_explosion_3.getFxImage();
        flameAnimRight[3] = Sprite.right_explosion_4.getFxImage();

        flameAnimUp[0] = Sprite.up_explosion_1.getFxImage();
        flameAnimUp[1] = Sprite.up_explosion_2.getFxImage();
        flameAnimUp[2] = Sprite.up_explosion_3.getFxImage();
        flameAnimUp[3] = Sprite.up_explosion_4.getFxImage();


        flameAnimDown[0] = Sprite.down_explosion_1.getFxImage();
        flameAnimDown[1] = Sprite.down_explosion_2.getFxImage();
        flameAnimDown[2] = Sprite.down_explosion_3.getFxImage();
        flameAnimDown[3] = Sprite.down_explosion_4.getFxImage();

        flameAnimCenterLeft[0] = Sprite.left_center_explosion_1.getFxImage();
        flameAnimCenterLeft[1] = Sprite.left_center_explosion_2.getFxImage();
        flameAnimCenterLeft[2] = Sprite.left_center_explosion_3.getFxImage();
        flameAnimCenterLeft[3] = Sprite.left_center_explosion_4.getFxImage();

        flameAnimCenterRight[0] = Sprite.right_center_explosion_1.getFxImage();
        flameAnimCenterRight[1] = Sprite.right_center_explosion_2.getFxImage();
        flameAnimCenterRight[2] = Sprite.right_center_explosion_3.getFxImage();
        flameAnimCenterRight[3] = Sprite.right_center_explosion_4.getFxImage();

        flameAnimCenterUp[0] = Sprite.up_center_explosion_1.getFxImage();
        flameAnimCenterUp[1] = Sprite.up_center_explosion_2.getFxImage();
        flameAnimCenterUp[2] = Sprite.up_center_explosion_3.getFxImage();
        flameAnimCenterUp[3] = Sprite.up_center_explosion_4.getFxImage();


        flameAnimCenterDown[0] = Sprite.down_center_explosion_1.getFxImage();
        flameAnimCenterDown[1] = Sprite.down_center_explosion_2.getFxImage();
        flameAnimCenterDown[2] = Sprite.down_center_explosion_3.getFxImage();
        flameAnimCenterDown[3] = Sprite.down_center_explosion_4.getFxImage();
    }

    public Image flameExplosion(Image[] flame){
        frameFlame++;
        if (frameFlame == intervalFlame){
            frameFlame = 0;
            indexFlameAnimation++;
            if (indexFlameAnimation == 4){
                indexFlameAnimation = 0;
                flameStop = true;
            }
        }
        return flame[indexFlameAnimation];
    }


    public void initBrickSprite(){
        Image image = new Image(String.valueOf((Bomberman.class.getResource("sheets.png"))));
        brickAnim = new Image[6];
        int x = 0;
        for (int i = 5; i < 11; i++) {
            brickAnim[x] = new WritableImage(image.getPixelReader(), i * 16, 48, 16, 16);
            x++;
        }
    }

    public Image brickFade(){
        frameBrick++;
        if (frameBrick == intervalBrick){
            frameBrick = 0;
            indexBrickAnimation++;
            if (indexBrickAnimation > 5){
                indexBrickAnimation = 5;
                end = true;
            }
        }
        return brickAnim[indexBrickAnimation];
    }

    public void initBomberDead(){
        bomberDead = new Image[7];
        Image image = new Image(String.valueOf(Bomberman.class.getResource("sheets.png")));
        for (int i = 0; i < 7; i++) {
            bomberDead[i] = new WritableImage(image.getPixelReader(),i*16,32,16,16);
        }
    }

    public Image deading(){
        frameDead++;
        if (frameDead == intervalDead){
            frameDead = 0;
            indexDeadAnimation++;
            if (indexDeadAnimation > 6){
                indexDeadAnimation = 6;
                end = true;
            }
        }
        return bomberDead[indexDeadAnimation];

    }

}

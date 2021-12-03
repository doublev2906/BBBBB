package com.example.bbbbb;

import com.example.bbbbb.bomb.Bomb;
import com.example.bbbbb.sprite.MSprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bomber extends Entity {

    public int speed = 1;
    public boolean buffFlame = false;


    private Input input;
    private int direction = -1;
    public boolean isMoving;
    public boolean alive = true;
    int check = 0;

    private Board board;



    MSprite mSprite = new MSprite();

    public void setInput(Input input) {
        this.input = input;
    }

    public void setmMap(Map mMap) {
        this.mMap = mMap;
    }

    Map mMap;

    public double t;


    public Image characterImage;
    private int map[][];

    public Bomber(Input input) {
        x = 32;
        y = 32;
        this.input = input;
        mSprite.initBomberAnimate();
        mSprite.initBomberDead();
    }

    public Bomber(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
        mSprite.initBomberAnimate();
        mSprite.initBomberDead();
    }

    @Override
    public void update() {
        setItem();
        calculateMove();

        if (input.space) {
            board.addBomb(x,y,buffFlame);
            Sound.playSound("placeGentle");
        }
        isKill();


    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }

    @Override
    public void render(GraphicsContext gc) {
        if (alive){

            chooseSprite();
            gc.drawImage(characterImage, x, y);
        }
        else {
//            gc.drawImage(new Image(String.valueOf(Bomberman.class.getResource("grass.png"))),x,y,Game.ENTITY_SIZE,Game.ENTITY_SIZE);
            if (!mSprite.end){
                gc.drawImage(mSprite.deading(), x,y,Game.ENTITY_SIZE,Game.ENTITY_SIZE);
            }
        }

    }


    protected void calculateMove() {
        int xa = 0, ya = 0;
        if (input.up) ya--;
        else if (input.down) ya++;
        else if (input.left) xa--;
        else if (input.right) xa++;

        if (xa != 0 || ya != 0) {
            move(xa * speed, ya * speed);
            isMoving = true;
        } else isMoving = false;
    }

    public void move(double xa, double ya) {
        if (xa > 0) direction = 1;
        if (xa < 0) direction = 3;
        if (ya > 0) direction = 2;
        if (ya < 0) direction = 0;

        if (canMove(x + xa, y + ya)) {
            y += ya;
            x += xa;
        }
    }

    public void chooseSprite() {

        switch (direction) {
            case 0:
                characterImage = mSprite.bomber_anim_up[0];
                if (isMoving) {
                    characterImage = mSprite.moveSprite(mSprite.bomber_anim_up);
                }
                break;
            case 2:
                characterImage = mSprite.bomber_anim_down[0];
                if (isMoving) {
                    characterImage = mSprite.moveSprite(mSprite.bomber_anim_down);
                }
                break;
            case 3:
                characterImage = mSprite.bomber_anim_left[0];
                if (isMoving) {
                    characterImage = mSprite.moveSprite(mSprite.bomber_anim_left);
                }
                break;
            default:
                characterImage = mSprite.bomber_anim_right[0];
                if (isMoving) {
                    characterImage = mSprite.moveSprite(mSprite.bomber_anim_right);
                }
                break;

        }
    }

    private boolean canMove(double nextX, double nextY) {
        int nextX_1 = (int) (nextX / Game.ENTITY_SIZE);
        int nextY_1 = (int) (nextY / Game.ENTITY_SIZE);

        int nextX_2 = (int) (nextX + Game.ENTITY_SIZE - 16) / Game.ENTITY_SIZE;
        int nextY_2 = (int) (nextY / Game.ENTITY_SIZE);

        int nextX_3 = (int) (nextX / Game.ENTITY_SIZE);
        int nextY_3 = (int) (nextY + Game.ENTITY_SIZE - 8) / Game.ENTITY_SIZE;

        int nextX_4 = (int) (nextX + Game.ENTITY_SIZE - 16) / Game.ENTITY_SIZE;
        int nextY_4 = (int) (nextY + Game.ENTITY_SIZE - 8) / Game.ENTITY_SIZE;
//
//        return !((map[nextY_1][nextX_1] == 1 || map[nextY_1][nextX_1] == 2) ||
//                (map[nextY_2][nextX_2] == 1 || map[nextY_2][nextX_2] == 2) ||
//                (map[nextY_3][nextX_3] == 1 || map[nextY_3][nextX_3] == 2) ||
//                (map[nextY_4][nextX_4] == 1 || map[nextY_4][nextX_4] == 2));
        return !(board.entities[nextY_1][nextX_1].collide(this) ||
                board.entities[nextY_2][nextX_2].collide(this) ||
                board.entities[nextY_3][nextX_3].collide(this) ||
                board.entities[nextY_4][nextX_4].collide(this));
    }
    public void isKill(){
        Bomb bomb = board.getBomb();
        if (bomb != null){
            if (bomb.fl.size() > 0){
                int mx = (x + 8 )/ Game.ENTITY_SIZE * Game.ENTITY_SIZE;
                int my = (y + 8) / Game.ENTITY_SIZE * Game.ENTITY_SIZE;
                bomb.fl.forEach(a -> {
                    if (a.x == mx && a.y == my){
                        alive = false;
                    }
                });
            }
        }
        Bomb bomb1 = board.getBomb1();
        if (bomb1 != null){
            if (bomb1.fl.size() > 0){
                int mx = (x + 8 )/ Game.ENTITY_SIZE * Game.ENTITY_SIZE;
                int my = (y + 8) / Game.ENTITY_SIZE * Game.ENTITY_SIZE;
                bomb1.fl.forEach(a -> {
                    if (a.x == mx && a.y == my){
                        alive = false;
                    }
                });
            }
        }
    }

    public void setItem(){
        int mx = (x + 8 )/ Game.ENTITY_SIZE * Game.ENTITY_SIZE;
        int my = (y + 8) / Game.ENTITY_SIZE * Game.ENTITY_SIZE;
        for (int i = 0; i < board.items.size(); i++) {
            if (board.items.get(i).x  == mx && board.items.get(i).y == my) {
                if (!board.items.get(i).isRemove){
                    board.items.get(i).isRemove = true;
                    ((LayerEntity) board.entities[board.items.get(i).y / 32][board.items.get(i).x / 32]).collide(this);
//                    int x = ((LayerEntity) board.entities[board.items.get(i).y / 32][board.items.get(i).x / 32]).entities.size() -1;
//                    ((LayerEntity) board.entities[board.items.get(i).y / 32][board.items.get(i).x / 32]).entities.get(x).isRemove = true;
                }

            }
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setWinGame(){
        board.win = true;
    }
    public Board getBoard() {
        return board;
    }
    public void setBuffBomb(){
        board.buffBomb = true;
    }


}


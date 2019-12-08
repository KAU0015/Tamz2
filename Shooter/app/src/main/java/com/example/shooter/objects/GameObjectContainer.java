package com.example.shooter.objects;

import android.graphics.Canvas;

import com.example.shooter.Camera;
import com.example.shooter.PlayerUI;
import com.example.shooter.objects.packs.Packs;
import com.example.shooter.objects.players.Players;
import com.example.shooter.objects.players.enemies.MovableEnemy;
import com.example.shooter.objects.players.player.Player;

import java.util.ArrayList;

public class GameObjectContainer {
    public ArrayList<GameObject> objectList;
    private Camera cam;
    private int width, height;

    public GameObjectContainer(Camera cam, int width, int height) {
        this.cam = cam;
        this.objectList = new ArrayList<>();
        this.width = width;
        this.height = height;
    }


    public synchronized void drawObject(Canvas canvas) {
        for(GameObject o : objectList){
           // if (o.getX() + o.getWidth() >= -cam.getX() && o.getX() <= -cam.getX() + MainApplication.getWidth() && o.getY() + o.getHeight() >= -cam.getY() && o.getY() <= -cam.getY() + MainApplication.getHeight()) {
                o.draw(canvas);
            //}
        }
    }


    public synchronized void checkObjectCollision() {
        for(GameObject o : objectList){

            if(o instanceof MovableEnemy){
                ((MovableEnemy) o).setLeft(false);
                ((MovableEnemy) o).setRight(false);
            }

            if(o instanceof Players){
                for(GameObject ob : objectList){
                    ((Players) o).checkCollision(ob);
                }

                if(o instanceof Player){
                    cam.setX(-((Player) o).getX() + width / 2-200);
                    cam.setY(-((Player) o).getY() + height /2 +100);

                }
            }

            if(o instanceof  Players && o instanceof MovableEnemy && (!((MovableEnemy) o).getLeft() || !((MovableEnemy) o).getRight())){
                ((Players) o).setxVel(-((Players) o).getxVel());
                ((Players) o).setToRight(!((Players) o).getToRight());
            }
        }
        removeObject();
    }


    public synchronized void addObject(GameObject o) {
        objectList.add(o);
    }


    public synchronized void removeObject() {

        for(int i = 0; i < objectList.size(); i++){
            if(objectList.get(i) instanceof Players && ((Players) objectList.get(i)).getLife() <= 0){
                if(!(objectList.get(i) instanceof Player))
                    PlayerUI.getInstance().addScore(50);
                objectList.remove(i);
            }
            else if(objectList.get(i) instanceof Packs && ((Packs) objectList.get(i)).isActivated()){
                objectList.remove(i);
            }
        }
    }


    public void moveObject() {
        for(GameObject o : objectList){
            if(o instanceof Players){
                ((Players) o).move();
            }
        }
    }

    public ArrayList<GameObject> getObjectList(){
        return objectList;
    }
}

package com.example.shooter.objects.players.player;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.shooter.objects.GameObject;
import com.example.shooter.objects.players.Players;

public class Player extends Players {
    private float gravity = 0.4f;
    private boolean jumping = false;
    private boolean falling = true;
    private final float maxSpeed = 8;
    private Bitmap imageIdleRight;
    private Bitmap imageIdleLeft;
    //private PlayerAnimation animation;
    private boolean fire = false;
   // private List<Shoot> shoots;
    private int speedFire = 8;
    private int firing = 0;
    private boolean nearExit = false;


    public Player(float x, float y){
        this.life = 100;
        this.x = x;
        this.y = y;
        this.xVel = 0;
        this.yVel = 0;
        this.width = 40;
        this.height = 60;
        /*this.animation = new PlayerAnimation();
        this.shoots = new ArrayList<Shoot>();*/

      //  this.imageIdleRight = new Image(this.getClass().getResourceAsStream("/textures/objects/player/soldierIdle1.png"));
       // this.imageIdleLeft = new Image(this.getClass().getResourceAsStream("/textures/objects/player/soldierIdleLeft.png"));
    }

    public boolean isNearExit() {
        return nearExit;
    }

    public void setYVel(float yVel){
        this.yVel = yVel;
    }

    public void setXVel(float xVel){
        this.xVel = xVel;
    }

    public void draw(Canvas canvas){
      /*  g.setColor(Color.CYAN);
        g.fillRect((int)x, (int)y, width, height);*/

      /*  if(xVel != 0 && !jumping && yVel == 0){
            //animation.drawAnimation(gc, (int)x, (int)y);
        }
        else if(toRight){
            gc.drawImage(imageIdleRight, (int)x, (int)y);
        }
        else if(!toRight){
            gc.drawImage(imageIdleLeft, (int)x, (int)y);
        }*/

        /*for(Shoot s : shoots){
            s.render(gc);
        }*/

       /* g.setColor(Color.red);
        ((Graphics2D) g).draw(getDownBounds());
        ((Graphics2D) g).draw(getUpBounds());
        ((Graphics2D) g).draw(getLeftBounds());
        ((Graphics2D) g).draw(getRightBounds());*/

    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public void  setToRight(boolean toRight){
        this.toRight = toRight;
    }

    @Override
    public void move() {
        x += xVel;
        y += yVel;

        if(falling || jumping){
            yVel += gravity;

            if(yVel > maxSpeed){
                yVel = maxSpeed;
            }
        }

       /* if(xVel > 0)
            animation.runAnimationR();
        else if(xVel < 0)
            animation.runAnimationL();

        if(PlayerUI.getInstance().getAmmo() > 0){
            if(fire){
                firing++;

                if(firing > speedFire){
                    firing = 0;
                    if(toRight){
                        shoots.add(new Shoot(x+width-9, y+38, toRight));
                        PlayerUI.getInstance().setAmmo(-1);

                    }
                    else{
                        shoots.add(new Shoot(x-7, y+38, toRight));
                        PlayerUI.getInstance().setAmmo(-1);
                    }
                }
            }
        }

        for(Shoot s : shoots){
            s.move();
        }*/
    }

    @Override
    public void checkCollision(GameObject ob) {

        // nearExit = false;

       /* if(!(ob instanceof Players) && !(ob instanceof Packs) && !(ob instanceof Exit)){
            if(getUpBounds().toRectBounds().intersects(ob.getBounds().toRectBounds())){
                y = ob.getY() + ob.getHeight()+1;
                yVel = 0;
                falling = true;
            }
            if(getDownBounds().toRectBounds().intersects(ob.getBounds().toRectBounds())){
                y = ob.getY() - height;
                yVel = 0;
                jumping = false;
                falling = false;
            }
            else {
                falling = true;
            }
            if(getRightBounds().toRectBounds().intersects(ob.getBounds().toRectBounds())){
                x = ob.getX() - width;
                // xVel = 0;
            }
            if(getLeftBounds().toRectBounds().intersects(ob.getBounds().toRectBounds())){
                x = ob.getX() + ob.getWidth();
                // xVel = 0;
            }

        }

        if(ob instanceof Exit && getBounds().toRectBounds().intersects(ob.getBounds().toRectBounds())){
            nearExit = true;
        }

        if(ob instanceof Packs){
            if(ob instanceof AmmoPack){
                if(getBounds().toRectBounds().intersects(ob.getBounds().toRectBounds())){
                    PlayerUI.getInstance().setAmmo(((Packs) ob).getBonus());
                    ((Packs) ob).setActivated(true);
                }
            }
            if(ob instanceof AidKit){
                if(getBounds().toRectBounds().intersects(ob.getBounds().toRectBounds()) && life < 100){
                    PlayerUI.getInstance().setLife(((Packs) ob).getBonus());
                    life += ((AidKit) ob).getBonus();
                    if(life > 100){
                        life = 100;
                    }
                    ((Packs) ob).setActivated(true);
                }
            }

        }


        for(int i = 0; i < shoots.size(); i++){
            shoots.get(i).checkCollision(ob);
            if(shoots.get(i).isCol()){
                shoots.remove(i);
            }
        }*/
    }

    public boolean isFire() {
        return fire;
    }

    public void setFire(boolean fire) {
        this.fire = fire;
    }
}

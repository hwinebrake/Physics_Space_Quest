package edu.virginia.RocketGame;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import javafx.scene.layout.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Base64;


public class RocketGame2 extends Game {

    private int init_collision_good = 0;
    private int levelflag = 0;
    private int asteroidflag = 0; //1 if fail
    private int speedflag = 0; // 1 if fail, 2 if pass
    private double graphicstimer = 0; //timers so explosion gets shown before fail screen
    private double timerflag = 0; //flag for explosion fails
    AnimatedSprite blue_ship = new AnimatedSprite("BlueShip", "na", new Point(0, 250));
    AnimatedSprite stranded_ship = new AnimatedSprite("Stranded", "na", new Point(400, 150));
    Sprite background = new Sprite("Background", "Background/space.png");
    Sprite a1 = new Sprite("a1", "Aestroids/aestroid_brown.png");
    Sprite a2 = new Sprite("a1", "Aestroids/aestroid_grey.png");
    Sprite a3 = new Sprite("a1", "Aestroids/aestroid_brown.png");
    Sprite a4 = new Sprite("a1", "Aestroids/aestroid_grey.png");
    Sprite a5 = new Sprite("a1", "Aestroids/aestroid_brown.png");
    Sprite es = new Sprite("es", "Background/explosion_sign.jpeg");
    Sprite cb = new Sprite("cb", "Background/collision_badge.jpeg");
    Sprite cts = new Sprite("cts", "Background/collisions_teaching_slide.png");
    Sprite l2i = new Sprite("l1i", "Background/Level2Instructions.jpg");
    public boolean won = false;
    public boolean exited = false;

    public RocketGame2() {

        super("Rocket Game Level 2", 720, 480);

        //add Blueship animation and set animation speed
        ArrayList<String> burls = new ArrayList<String>();
        burls.add("blue_anim/1.png");
        burls.add("blue_anim/2.png");
        burls.add("blue_anim/3.png");
        burls.add("blue_anim/4.png");
        burls.add("blue_anim/5.png");

        ArrayList<String> eurls = new ArrayList<String>();
        eurls.add("BlueEffects/1_0.png");
        eurls.add("BlueEffects/1_1.png");
        eurls.add("BlueEffects/1_2.png");
        eurls.add("BlueEffects/1_3.png");
        eurls.add("BlueEffects/1_4.png");
        eurls.add("BlueEffects/1_5.png");
        eurls.add("BlueEffects/1_6.png");
        eurls.add("BlueEffects/1_7.png");
        eurls.add("BlueEffects/1_8.png");
        eurls.add("BlueEffects/1_9.png");
        eurls.add("BlueEffects/1_10.png");
        eurls.add("BlueEffects/1_11.png");
        eurls.add("BlueEffects/1_12.png");
        eurls.add("BlueEffects/1_13.png");

        blue_ship.setAnimationSpeed(50);
        //blue_ship.setPosition(new Point(0, 250));
        blue_ship.addAnimation(burls, "flight");
        blue_ship.addAnimation(eurls, "explosion");
        blue_ship.setAnimation("flight");
        blue_ship.setMass(100);

        stranded_ship.setAnimationSpeed(50);
        stranded_ship.setMass(100);
        stranded_ship.addAnimation(burls, "flight");
        stranded_ship.addAnimation(eurls, "explosion");
        stranded_ship.setAnimation("flight");
        //stranded_ship.setPosition(new Point());

        a1.setPosition(new Point(500, 0));
        a1.setMass(3000);

        a2.setPosition(new Point(250, 300));
        a2.setMass(6000);

        a3.setPosition(new Point(400, 400));
        a3.setMass(3000);

        a4.setPosition(new Point(50, 50));
        a4.setMass(6000);

        a5.setPosition(new Point( 100, 300));
        a4.setMass(3000);

        es.setPosition(new Point(110, 50));
        cb.setPosition(new Point(160,0));

        //add sprites to game
        this.addChild(background);
        this.addChild(blue_ship);
        this.addChild(stranded_ship);
        this.addChild(a1);
        this.addChild(a2);
        this.addChild(a3);
        this.addChild(a4);
        this.addChild(a5);

    }

    public static void main(String args[]) {
        RocketGame2 rg = new RocketGame2();
        rg.start();
    }

    public void draw(Graphics g) {
        if (levelflag == 2) {
            if (blue_ship.getPosition().x < 720) {
                //super.draw(g);
                if (asteroidflag == 1 & graphicstimer > 1000) {
                    es.draw(g);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setFont(new Font("MonospacedBold", Font.PLAIN, 20));
                    g2.drawString("Asteroid Collision.", 250, 422);
                    g2.drawString("Press Enter to try again", 250, 442);
                    //MAKE IMAGE FOR ASTEROID FAIL
                    //when asteroid fail
                } else if (init_collision_good != 2 & speedflag == 1 & graphicstimer > 500) {
                    blue_ship.setPosition(new Point(0, 250));
                    stranded_ship.setPosition(new Point(400, 150));
                    blue_ship.setDx(0);
                    blue_ship.setDy(0);
                    stranded_ship.setDx(0);
                    stranded_ship.setDy(0);
                    es.draw(g);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setFont(new Font("MonospacedBold", Font.PLAIN, 20));
                    g2.drawString("Too much velocity at time of impact!", 150, 422);
                    g2.drawString("Press Enter to try again", 150, 442);
                    //when speed fail
                } else {
                    super.draw(g); //if we've added the sprites to our RocketGame instance, this'll draw all of them automatically
                    //bc they all live in the children of the frame + DOC's draw iterates over all of them.
                }

            } else if (init_collision_good == 2){
                cb.draw(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setFont(new Font("MonospacedBold", Font.PLAIN, 20));
                g2.drawString("Congratulations!!! You have received your collisions badge.", 50, 380);
                won = true;
                g2.drawString("Press space to continue, or enter to restart level.", 50, 400);
            }else{
                super.draw(g);
            }

        }else if (levelflag == 1){
            l2i.draw(g);
        }else if (levelflag == 0){
            cts.draw(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setFont(new Font("MonospacedBold", Font.PLAIN, 20));
            g2.drawString("Press Space to test your collisions knowledge!", 110, 450);
        }
    }

    public void update(ArrayList<Integer> pressedKeys, double elapsedTime) {
        //instruction slide
        //accelerate or decelerate
        if (levelflag == 0){
            if (pressedKeys.contains(KeyEvent.VK_SPACE)){
                levelflag = 1;
                try {
                    // thread to sleep for 100 milliseconds
                    Thread.sleep(200);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }//game instructions
        else if (levelflag == 1){
            if (pressedKeys.contains(KeyEvent.VK_SPACE)) {
                levelflag = 2;
                try {
                    // thread to sleep for 100 milliseconds
                    Thread.sleep(200);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }else if (pressedKeys.contains(KeyEvent.VK_BACK_SPACE)){
                levelflag = 0;
                try {
                    // thread to sleep for 100 milliseconds
                    Thread.sleep(200);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }else if (levelflag == 2) {
            if (won) {
                if (pressedKeys.contains(KeyEvent.VK_SPACE)) {
                    exited = true;
                    exitGame();
                }
            }
            if (timerflag == 1){
                graphicstimer += elapsedTime;
            }if (pressedKeys.contains(KeyEvent.VK_ENTER)) {
                levelflag = 1;
                init_collision_good = 0;
                asteroidflag = 0; //1 if fail
                speedflag = 0; // 1 if fail, 2 if pass
                graphicstimer = 0; //timers so explosion gets shown before fail screen
                timerflag = 0; //flag for explosion fails
                blue_ship.setAnimation("flight");
                stranded_ship.setAnimation("flight");
                blue_ship.setPosition(new Point(0, 250));
                stranded_ship.setPosition(new Point(400, 150));
                blue_ship.setDx(0);
                blue_ship.setDy(0);
                stranded_ship.setDx(0);
                stranded_ship.setDy(0);
            }
            if (pressedKeys.contains(KeyEvent.VK_UP)) {
                blue_ship.setDvy(-1);
            }
            if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
                blue_ship.setDvy(1);
            }
            if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
                blue_ship.setDvx(1);
            }
            if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
                blue_ship.setDvx(-1);
            }
            if (blue_ship.collidesWith(stranded_ship)){

                if(blue_ship.getVelocity() > 13){
                    blue_ship.setAnimation("explosion");
                    stranded_ship.setAnimation("explosion");
                    if (init_collision_good != 2){
                        System.out.println("Loss! YOUR VELOCITY AT COLLISON:" + blue_ship.getVelocity());
                        //level fail
                        speedflag = 1;
                        timerflag = 1;
                    }
                }else{
                    System.out.println("WIN! YOUR VELOCITY AT COLLISON:" + blue_ship.getVelocity());
                    init_collision_good = 2;
                }
                inelastic_collision(blue_ship, stranded_ship);
            }
            if (blue_ship.collidesWith(a1)){
                blue_ship.setAnimation("explosion");
                inelastic_collision(blue_ship, a1);
                asteroidflag = 1;
                timerflag = 1;
            }
            if (blue_ship.collidesWith(a2)){
                blue_ship.setAnimation("explosion");
                inelastic_collision(blue_ship, a2);
                asteroidflag = 1;
                timerflag = 1;
            }
            if (blue_ship.collidesWith(a3)){
                blue_ship.setAnimation("explosion");
                inelastic_collision(blue_ship, a3);
                asteroidflag = 1;
                timerflag = 1;
            }
            if (blue_ship.collidesWith(a4)){
                blue_ship.setAnimation("explosion");
                inelastic_collision(blue_ship, a4);
                asteroidflag = 1;
                timerflag = 1;
            }
            if (blue_ship.collidesWith(a5)){
                blue_ship.setAnimation("explosion");
                inelastic_collision(blue_ship, a5);
                asteroidflag = 1;
                timerflag = 1;
            }

            blue_ship.update(pressedKeys, elapsedTime);
            stranded_ship.update(pressedKeys, elapsedTime);

            super.update(pressedKeys, elapsedTime);
        }
    }


    //function to handle collision between 2 objects using momentum
    //maybe keep like this and say some bullshit about connecting with forcefield
    //next level have them still connected and dump ship after loading passengers onto yours so
    //third ship doesnt get you
    public void inelastic_collision(DisplayObject objone, DisplayObject objtwo) {
        //going to assume mass
        double mass1 = objone.getMass();
        double mass2 = objtwo.getMass();
        double mom1x = mass1 * objone.getDx();
        double mom2x = mass2 * objtwo.getDx();
        double mom1y = mass1 * objone.getDy();
        double mom2y = mass2 * objtwo.getDy();
        double newdx = (mom1x + mom2x)/(mass1+mass2);
        double newdy = (mom1y + mom2y)/(mass1+mass2);
        objone.setDx(newdx);
        objtwo.setDx(newdx);
        objone.setDy(newdy);
        objtwo.setDy(newdy);
        if(newdx > 0){
            objtwo.setPosition(new Point(objtwo.getPosition().x + 1, objtwo.getPosition().y));
        }else if (newdx < 0){
            objtwo.setPosition(new Point(objtwo.getPosition().x - 1, objtwo.getPosition().y));

        }
        if (newdy > 0){
            objtwo.setPosition(new Point(objtwo.getPosition().x, objtwo.getPosition().y + 1));

        }else if (newdy < 0){
            objtwo.setPosition(new Point(objtwo.getPosition().x, objtwo.getPosition().y - 1));
        }
    }
}
package edu.virginia.RocketGame;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class RocketGame1 extends Game {

    private int levelflag = 0;
    AnimatedSprite blue_ship = new AnimatedSprite("BlueShip", "na", new Point(0,0));
    Sprite background = new Sprite("Background", "Background/space.png");
    Sprite cop = new Sprite("Cop", "cop_ship.png");
    Sprite a1 = new Sprite("a1", "Aestroids/aestroid_brown.png");
    Sprite a2 = new Sprite("a1", "Aestroids/aestroid_grey.png");
    Sprite a3 = new Sprite("a1", "Aestroids/aestroid_brown.png");
    Sprite copbackground = new Sprite("copbackground", "Background/police_car.png");
    Sprite sdb = new Sprite("sdb", "Background/safe_driver_badge.png");
    Sprite ats = new Sprite("ats", "Background/acceleration_teaching_slide.png");
    Sprite l1i = new Sprite("l1i", "Background/level_1_instructions2.jpg");
    public boolean exited = false;
    public boolean won = false;
    public RocketGame1() {

        super("Rocket Game Level 1", 720,  480);

        //add Blueship animation and set animation speed
        ArrayList<String> burls = new ArrayList<String>();
        burls.add("blue_anim/1.png");
        burls.add("blue_anim/2.png");
        burls.add("blue_anim/3.png");
        burls.add("blue_anim/4.png");
        burls.add("blue_anim/5.png");
        blue_ship.setAnimationSpeed(100);
        blue_ship.setPosition(new Point(0, 250));
        blue_ship.addAnimation(burls, "flight");
        blue_ship.setAnimation("flight");
        blue_ship.setDx(14);

        cop.setPosition(new Point(550, 30));

        sdb.setPosition(new Point(17, 0));

        a1.setPosition(new Point (450, 0));
        a2.setPosition(new Point (350, 60));
        a3.setPosition(new Point (400, -10));

        l1i.setPosition(new Point (0, -15));

        //add sprites to game
        this.addChild(background);
        this.addChild(blue_ship);
        this.addChild(cop);
        this.addChild(a1);
        this.addChild(a2);
        this.addChild(a3);

    }

    public static void main(String args[]) {
        RocketGame1 rg = new RocketGame1();
        rg.start();
    }

    public void draw(Graphics g) {
        if (levelflag == 2) {
            if (blue_ship.getPosition().x > 720) {
                if (blue_ship.getVelocity() < 6 | blue_ship.getVelocity() > 8) {
                    //too slow fail
                    copbackground.draw(g);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setFont(new Font("MonospacedBold", Font.PLAIN, 20));
                    g2.drawString("You were going " + (float) blue_ship.getVelocity() +
                            "km/s in a velocity zone of 6 km/s - 8 km/s!", 20, 400);
                    g2.drawString("I'm going to have to give you a ticket.", 20, 422);
                    g2.drawString("Press Enter to try again", 20, 442);
                } else {
                    sdb.draw(g);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setFont(new Font("MonospacedBold", Font.PLAIN, 20));
                    g2.drawString("You were going " + (float) blue_ship.getVelocity() +
                            "km/s in a velocity zone of 6 km/s - 8 km/s!", 20, 400);
                    g2.drawString("YOU HAVE RECEIVED AN INTERSTELLAR SAFE DRIVER BADGE!", 20, 422);

                    g2.drawString("Press space to continue or enter to go again.", 20, 444);

                    won = true;
                }
            } else {
                super.draw(g); //if we've added the sprites to our RocketGame instance, this'll draw all of them automatically
                //bc they all live in the children of the frame + DOC's draw iterates over all of them.
            }
        }else if (levelflag == 1){
            l1i.draw(g);
        } else if (levelflag == 0){
            if (ats == null) return;
            ats.draw(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setFont(new Font("MonospacedBold", Font.PLAIN, 20));
            g2.drawString("Press Space to test your acceleration knowledge!", 110, 450);
        }
    }

    public void update(ArrayList<Integer> pressedKeys, double elapsedTime) {
        //instruction slide
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
        }
        //level
        else if (levelflag == 2){
            if (won) {
                if (pressedKeys.contains(KeyEvent.VK_SPACE)) {
                    exited = true;
                    exitGame();
                }
            }
            //accelerate or decelerate
            if (pressedKeys.contains(KeyEvent.VK_UP)){
                blue_ship.setDvy(-1);
            }
            if (pressedKeys.contains(KeyEvent.VK_DOWN)){
                blue_ship.setDvy(1);
            }
            if (pressedKeys.contains(KeyEvent.VK_RIGHT)){
                blue_ship.setDvx(1);
            }
            if (pressedKeys.contains(KeyEvent.VK_LEFT)){
                blue_ship.setDvx(-1);
            }
            if (pressedKeys.contains(KeyEvent.VK_BACK_SPACE)){
                levelflag = 0;
            }if (pressedKeys.contains(KeyEvent.VK_ENTER)) {
                levelflag = 1;
                blue_ship.setDx(14);
                blue_ship.setDy(0);
                blue_ship.setPosition(new Point(0, 250));
                try {
                    // thread to sleep for 100 milliseconds
                    Thread.sleep(200);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            blue_ship.update(pressedKeys, elapsedTime);

            super.update(pressedKeys, elapsedTime);
        }
    }
}

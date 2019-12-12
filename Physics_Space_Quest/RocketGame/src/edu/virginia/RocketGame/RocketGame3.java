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


public class RocketGame3 extends Game {

    private int explosionflag = 0;
    private int levelflag = 0;
    private int asteroidflag = 0; //1 if fail
    private int stopflag = 0; //1 if fail
    private int speedflag = 0; // 1 if fail, 2 if pass
    private double graphicstimer = 0;
    private double timerflag = 0;
    AnimatedSprite blue_ship = new AnimatedSprite("BlueShip", "na", new Point(0, 100));
    Sprite supplies = new Sprite("supplies", "supplies.png");
    Sprite background = new Sprite("Background", "Background/space.png");
    Sprite a1 = new Sprite("a1", "Aestroids/aestroid_brown.png");
    Sprite a2 = new Sprite("a1", "Aestroids/aestroid_grey.png");
    Sprite a3 = new Sprite("a1", "Aestroids/aestroid_brown.png");
    Sprite a4 = new Sprite("a1", "Aestroids/aestroid_grey.png");
    Sprite a5 = new Sprite("a1", "Aestroids/aestroid_brown.png");
    Sprite a6 = new Sprite("a1", "Aestroids/aestroid_brown.png");
    Sprite sdb = new Sprite("sdb", "Background/safe_driver_badge.png");
    Sprite ets = new Sprite("ets", "Background/explosion_teaching_slide.png");
    Sprite l3i = new Sprite("l3i", "Background/Level3Instructions.jpg");
    Sprite es = new Sprite("es", "Background/explosion_sign.jpeg");
    Sprite eb = new Sprite("eb", "Background/explosion_badge.jpeg");
    public boolean won = false;
    public boolean exited = false;




    public RocketGame3() {

        super("Rocket Game Level 3", 720, 480);

        //add Blueship animation and set animation speed
        ArrayList<String> burls = new ArrayList<String>();
        burls.add("blue_anim/1.png");

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
        blue_ship.setDx(5);
        blue_ship.setMass(80);

        supplies.setDx(5);
        supplies.setPosition(new Point(12, 112));
        supplies.setMass(20);


        a1.setPosition(new Point(500, 0));
        a1.setMass(3000);

        a2.setPosition(new Point(450, 100));
        a2.setMass(6000);

        a3.setPosition(new Point(400, 200));
        a3.setMass(3000);

        a4.setPosition(new Point(650, 50));
        a4.setMass(6000);

        a5.setPosition(new Point( 550, 200));
        a5.setMass(3000);

        a6.setPosition(new Point( 375, 0));
        a6.setMass(3000);

        sdb.setPosition(new Point(0,0));
        //add sprites to game
        eb.setPosition(new Point(110, 0));
        es.setPosition(new Point(110, 50));


        this.addChild(background);
        this.addChild(supplies);
        this.addChild(blue_ship);
        this.addChild(a1);
        this.addChild(a2);
        this.addChild(a3);
        this.addChild(a4);
        this.addChild(a5);
        this.addChild(a6);


    }

    public static void main(String args[]) {
        RocketGame3 rg = new RocketGame3();
        rg.start();
    }

    public void draw(Graphics g) {
        if (levelflag == 2) {
            if (blue_ship.getPosition().x < 720 & blue_ship.getPosition().y < 480) {
                if (asteroidflag == 1 & graphicstimer > 1000) {
                    //when asteroid fail
                    es.draw(g);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setFont(new Font("MonospacedBold", Font.PLAIN, 20));
                    g2.drawString("Asteroid Collision.", 250, 422);
                    g2.drawString("Press Enter to try again", 250, 442);
                    //MAKE IMAGE FOR ASTEROID FAIL
                    //when asteroid fail
                } else if (stopflag == 1 & graphicstimer > 10000) {
                    es.draw(g);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setFont(new Font("MonospacedBold", Font.PLAIN, 20));
                    g2.drawString("You ship is Stranded!.", 250, 422);
                    g2.drawString("Press Enter to try again", 250, 442);
                    //no momentum fail
                } else if (blue_ship.getPosition().y < -50) {
                    es.draw(g);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setFont(new Font("MonospacedBold", Font.PLAIN, 20));
                    g2.drawString("Wrong Direction, avoid asteroids!", 250, 422);
                    g2.drawString("Press Enter to try again", 250, 442);
                } else {
                    super.draw(g);
                }
                //super.draw(g); //if we've added the sprites to our RocketGame instance, this'll draw all of them automatically
                //bc they all live in the children of the frame + DOC's draw iterates over all of them.


                //super.draw(g);
            } else {
                eb.draw(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setFont(new Font("MonospacedBold", Font.PLAIN, 20));

                g2.drawString("Congratulations!!! You have received your collisions badge.", 50, 420);
                g2.drawString("Press enter to restart level.", 50, 440);
                won = true;
            }

        }else if (levelflag == 1){
            if (l3i == null) return;
            l3i.draw(g);
        }else if (levelflag == 0){
            if (ets == null) return;
            ets.draw(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setFont(new Font("MonospacedBold", Font.PLAIN, 20));
            g2.drawString("Press Space to test your explosions knowledge!", 110, 450);
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
            if (pressedKeys.contains(KeyEvent.VK_ENTER)) {
                levelflag = 1;
                explosionflag = 0;
                asteroidflag = 0; //1 if fail
                stopflag = 0; //1 if fail
                speedflag = 0; // 1 if fail, 2 if pass
                graphicstimer = 0;
                timerflag = 0;
                blue_ship.setDx(5);
                blue_ship.setDy(0);
                supplies.setDx(5);
                supplies.setDy(0);
                supplies.setPosition(new Point(12, 112));
                blue_ship.setPosition(new Point(0, 100));
                blue_ship.setAnimation("flight");
                try {
                    // thread to sleep for 100 milliseconds
                    Thread.sleep(200);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            if (timerflag == 1){
                graphicstimer += elapsedTime;
            }

            if (explosionflag == 0) {
                supply_explosion(blue_ship, supplies, pressedKeys);
            }
            if (blue_ship.collidesWith(a1)) {
                blue_ship.setAnimation("explosion");
                inelastic_collision(blue_ship, a1);
                asteroidflag = 1;
                timerflag = 1;
            }
            if (blue_ship.collidesWith(a2)) {
                blue_ship.setAnimation("explosion");
                inelastic_collision(blue_ship, a2);
                asteroidflag = 1;
                timerflag = 1;

            }
            if (blue_ship.collidesWith(a3)) {
                blue_ship.setAnimation("explosion");
                inelastic_collision(blue_ship, a3);
                asteroidflag = 1;
                timerflag = 1;
            }
            if (blue_ship.collidesWith(a4)) {
                blue_ship.setAnimation("explosion");
                inelastic_collision(blue_ship, a4);
                asteroidflag = 1;
                timerflag = 1;

            }
            if (blue_ship.collidesWith(a5)) {
                blue_ship.setAnimation("explosion");
                inelastic_collision(blue_ship, a5);
                asteroidflag = 1;
                timerflag = 1;

            }
            if (blue_ship.collidesWith(a6)) {
                blue_ship.setAnimation("explosion");
                inelastic_collision(blue_ship, a6);
                asteroidflag = 1;
                timerflag = 1;

            }
            blue_ship.update(pressedKeys, elapsedTime);
            supplies.update(pressedKeys, elapsedTime);

            super.update(pressedKeys, elapsedTime);
        }
    }


        public void supply_explosion(DisplayObject ship, DisplayObject supplies, ArrayList<Integer> pressedKeys){
                supplies.setPosition(new Point(ship.getPosition().x + 12, ship.getPosition().y + 12));
                if (pressedKeys.contains(KeyEvent.VK_UP)) {
                    supplies.setDy(-15);
                    explosion_math(ship, supplies);
                    this.explosionflag = 1;
                } else if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
                    supplies.setDy(15);
                    explosion_math(ship, supplies);
                    this.explosionflag = 1;
                } else if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
                    supplies.setDx(20);
                    explosion_math(ship, supplies);
                    stopflag = 1;
                    System.out.println(ship.getDx());
                    this.explosionflag = 1;
                    this.timerflag = 1;
                } else if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
                    supplies.setDx(-15);
                    explosion_math(ship, supplies);
                    this.explosionflag = 1;
                }

    }

    public void explosion_math(DisplayObject objone, DisplayObject objtwo){
        //velocity of obj2 is hardcoded
        double m1 = objone.getMass();
        double m2 = objtwo.getMass();
        //momentum of system
        double pinitx = (m1 + m2) * objone.getDx();
        double pinity = (m1 + m2) * objone.getDy();//0
        //velocity of ship after ejecting supplies
        //vf2 = (mv - m1v1)/m2
        double vfx = (pinitx - (objtwo.getDx() * m2))/m1;
        double vfy = (pinity - (objtwo.getDy() * m2))/m1;
        objone.setDx(vfx);
        objone.setDy(vfy);
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
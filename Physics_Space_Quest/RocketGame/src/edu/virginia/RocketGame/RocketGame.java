package edu.virginia.RocketGame;


import edu.virginia.engine.display.Game;

public class RocketGame extends Game {

    public RocketGame(String gameId, int width, int height) {
        super(gameId, width, height);
    }

    public static void main(String args[]) {
        RocketGame1 rg1 = new RocketGame1();
        rg1.start();
        while (rg1.exited == false) {
            System.out.println(" ");
        }
        RocketGame2 rg2 = new RocketGame2();
        rg2.start();
        while (rg2.exited == false) {
            System.out.println(" ");
        }
        RocketGame3 rg3 = new RocketGame3();
        rg3.start();

    }

}

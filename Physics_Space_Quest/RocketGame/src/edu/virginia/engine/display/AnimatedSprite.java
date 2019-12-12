package edu.virginia.engine.display;

import edu.virginia.engine.util.GameClock;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;



public class AnimatedSprite extends Sprite {

    ArrayList<Animation> animations = new ArrayList<Animation>();
    String curr_anim_name;
    ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();
    static final int DEFAULT_ANIMATION_SPEED = 1;
    boolean playing;
    int currentFrame, startFrame, endFrame;
    GameClock gameClock;
    private int animationSpeed;
    public AnimatedSprite(String id, String curr_anim_name, Point pos) {
        super(id);
        this.curr_anim_name = curr_anim_name;
        this.setPosition(pos);
        initGameClock();
        this.animationSpeed = DEFAULT_ANIMATION_SPEED;
    }

    public void initGameClock() {
        if (gameClock == null) {
            gameClock = new GameClock();
        }
    }

    public Animation getAnimation(String id) {
        if (animations == null) return null;
        for (Animation an : animations) {
            if (an.getId().equals(id)) {
                return an;
            }
        }
        return null;
    }

    public void setStartEndFrame(int startFrame, int endFrame) {
        this.startFrame = startFrame;
        this.endFrame = endFrame;
        currentFrame = startFrame;
        setImage(frames.get(currentFrame));
    }

    public void setAnimation(Animation anim) {
        setStartEndFrame(anim.getStartFrame(), anim.getEndFrame());
    }

    public void setAnimation(String id) {
        setAnimation(getAnimation(id));
        playing = true;
    }

    public void initializeFrames(ArrayList<BufferedImage> frames) {
        this.frames = frames;
    }

    public void setAnimationSpeed(int animationSpeed){
        this.animationSpeed = animationSpeed;
    }

    public int getAnimationSpeed(){return this.animationSpeed;}

    public void setAnimations(ArrayList<Animation> animations) {
        this.animations = animations;
    }

    public void addAnimation(ArrayList<String> urls, String id) {
        if (getAnimation(id) != null) {
            System.err.println("Animation with this ID already exists in this AnimatedSprite");
            return;
        }
        int nframes = 0;
        int alreadyHadFrames = frames.size();
        for (String url : urls) {
            BufferedImage bi = readImage(url);
            frames.add(bi);
            nframes++;
        }
        Animation newAnim = new Animation(id, alreadyHadFrames, alreadyHadFrames + nframes - 1);
        animations.add(newAnim);
    }

    @Override
    public void draw(Graphics g){
        if (playing) {
            if (gameClock.getElapsedTime() > this.animationSpeed) {
                currentFrame++;
                if (currentFrame > endFrame) {
                    //if the current frame is the last frame of that animation then get a new animation
                    currentFrame = startFrame;

                }
                gameClock.resetGameClock();
                setImage(frames.get(currentFrame));
            }
        }
        super.draw(g);

    }



}

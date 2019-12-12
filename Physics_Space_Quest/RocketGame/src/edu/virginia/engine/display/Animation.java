package edu.virginia.engine.display;

public class Animation {
    //name of animation
    private String id;
    //index of start frame in list of images
    private int startFrame;
    //index of end frame in list of images
    private int endFrame;

    public Animation(String id, int startFrame, int endFrame){
        this.id = id;
        this.startFrame = startFrame;
        this.endFrame = endFrame;
    }

    //getters and setters for variables
    public void setId(String id){
        this.id = id;
    }
    public String getId() {
        return this.id;
    }

    public void setStartFrame(int startFrame){
        this.startFrame = startFrame;
    }
    public int getStartFrame() {
        return startFrame;
    }

    public void setEndFrame(int endFrame){
        this.endFrame = endFrame;
    }
    public int getEndFrame(){
        return endFrame;
    }
}

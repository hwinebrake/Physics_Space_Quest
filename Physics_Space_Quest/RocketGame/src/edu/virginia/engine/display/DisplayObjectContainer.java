package edu.virginia.engine.display;

import java.awt.*;
import java.util.ArrayList;

public class DisplayObjectContainer extends DisplayObject {

    private ArrayList<DisplayObject> children;

    public DisplayObjectContainer(String id) {
        super(id);
        children = new ArrayList<DisplayObject>();
    }

    public DisplayObjectContainer(String id, String fileName) {
        super(id, fileName);
        children = new ArrayList<DisplayObject>();
    }

    public void addChild(DisplayObject child) {
        child.setParent(this);
        children.add(child);
    }


    //unsafe
    public void addChildAtIndex(DisplayObject child, int i) {
        children.add(i, child);
    }

    public void removeChild(String id) {
        for (DisplayObject d : children) {
            if (d.getId().equals(id)) {
                children.remove(d);
            }
        }
    }

    //unsafe
    public void removeByIndex(int i) {
        children.remove(i);
    }

    public void removeAll() {
        children = new ArrayList<DisplayObject>();
    }

    public boolean contains(DisplayObject o) {
        for (DisplayObject d : children) {
            if (d.getId().equals(o.getId())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<DisplayObject> getChildren() {
        return children;
    }

    public void draw(Graphics g){
        super.draw(g);
        applyTransformations((Graphics2D) g);
        for (DisplayObject d : children) {
            d.draw(g);
        }
        reverseTransformations((Graphics2D) g);
    }
}

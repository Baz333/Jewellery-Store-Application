package com.example.demo;

import java.io.Serializable;

public class DisplayTray implements Serializable {

    //fields
    private JewelleryItem head;
    private DisplayTray nextTray;
    private DisplayCase parent;
    private String uid;
    private String inlayColour;
    private int[] dimensions = new int[3];



    //constructor
    public DisplayTray(JewelleryItem head, DisplayTray nextTray, DisplayCase parent, String uid, String inlayColour, int length, int width, int height) {
        setHead(head);
        setNextTray(nextTray);
        setParent(parent);
        setUid(uid);
        setInlayColour(inlayColour);
        setDimensions(length, width, height);
    }



    //getters
    public JewelleryItem getHead() {
        return head;
    }

    public DisplayTray getNextTray() {
        return nextTray;
    }

    public DisplayCase getParent() {
        return parent;
    }

    public String getUid() {
        return uid;
    }

    public String getInlayColour() {
        return inlayColour;
    }

    public int[] getDimensions() {
        return dimensions;
    }



    //setters
    public void setHead(JewelleryItem head) {
        this.head = head;
    }

    public void setNextTray(DisplayTray nextTray) {
        this.nextTray = nextTray;
    }

    public void setParent(DisplayCase parent) {
        this.parent = parent;
    }

    public void setUid(String uid) {
        if(uid.length() == 3) {
            this.uid = uid;
        } else if(uid.length() > 3) {
            this.uid = uid.substring(0, 3);
        }
    }

    public void setInlayColour(String inlayColour) {
        if(inlayColour.equals("black") || inlayColour.equals("white") || inlayColour.equals("red") || inlayColour.equals("yellow") || inlayColour.equals("green") || inlayColour.equals("blue") || inlayColour.equals("purple") || inlayColour.equals("orange") || inlayColour.equals("other")) {
            this.inlayColour = inlayColour;
        }
    }

    public void setDimensions(int length, int width, int height) {
        if(length > 0) {
            this.dimensions[0] = length;
        }
        if(width > 0) {
            this.dimensions[1] = width;
        }
        if(height > 0) {
            this.dimensions[2] = height;
        }
    }

}

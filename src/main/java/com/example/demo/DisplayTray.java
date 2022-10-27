package com.example.demo;

import java.io.Serializable;

public class DisplayTray implements Serializable {
    private JewelleryItem head;
    private DisplayTray nextTray;
    private DisplayCase parent;
    private String uid;
    private String inlayColour;
    private int[] dimensions = new int[3];



    public DisplayTray(JewelleryItem head, DisplayTray nextTray, DisplayCase parent, String uid, String inlayColour, int length, int width, int height) {
        setHead(head);
        setNextTray(nextTray);
        setParent(parent);
        setUid(uid);
        setInlayColour(inlayColour);
        setDimensions(length, width, height);
    }


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
        }
    }

    public void setInlayColour(String inlayColour) {
        this.inlayColour = inlayColour;
    }

    public void setDimensions(int length, int width, int height) {
        this.dimensions[0] = length;
        this.dimensions[1] = width;
        this.dimensions[2] = height;
    }
}

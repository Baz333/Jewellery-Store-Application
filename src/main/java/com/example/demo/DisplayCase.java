package com.example.demo;

import java.io.Serializable;

public class DisplayCase implements Serializable {

    //fields
    private DisplayTray head;
    private DisplayCase nextCase;
    private String uid;
    private String type;
    private boolean lit;



    //constructor
    public DisplayCase(DisplayTray head, DisplayCase nextCase, String uid, String type, boolean lit) {
        setHead(head);
        setNextCase(nextCase);
        setUid(uid);
        setType(type);
        setLit(lit);
    }


    //returns number of display trays in case
    public int numberOfDisplayTrays() {
        DisplayTray temp = head;
        int i = 0;
        while(temp != null) {
            i++;
            temp = temp.getNextTray();
        }
        return i;
    }



    //getters
    public DisplayTray getHead() {
        return head;
    }

    public DisplayCase getNextCase() {
        return nextCase;
    }

    public String getUid() {
        return uid;
    }

    public String getType() {
        return type;
    }

    public boolean isLit() {
        return lit;
    }



    //setters
    public void setHead(DisplayTray head) {
        this.head = head;
    }

    public void setNextCase(DisplayCase nextCase) {
        this.nextCase = nextCase;
    }

    public void setUid(String uid) {
        if(uid.length() == 3) {
            this.uid = uid;
        } else if(uid.length() > 3) {
            this.uid = uid.substring(0, 3);
        }
    }

    public void setType(String type) {
        if(type.equals("freestanding") || type.equals("wall-mounted")) {
            this.type = type;
        }
    }

    public void setLit(boolean lit) {
        this.lit = lit;
    }

}

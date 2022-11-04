package com.example.demo;

import java.io.Serializable;

public class JewelleryItem implements Serializable {



    //fields
    private MaterialComponent head;
    private JewelleryItem nextItem;
    private DisplayTray parent;
    private String itemDescription;
    private String type;
    private String targetGender;
    private String imageURL;
    private int retailPrice = 0;



    //constructor
    public JewelleryItem(MaterialComponent head, JewelleryItem nextItem, DisplayTray parent, String itemDescription, String type, String targetGender, String imageURL, int retailPrice) {
        setHead(head);
        setNextItem(nextItem);
        setParent(parent);
        setItemDescription(itemDescription);
        setType(type);
        setTargetGender(targetGender);
        setImageURL(imageURL);
        setRetailPrice(retailPrice);
    }



    //lists all materials associated with the jewellery item
    public String materialsToString() {
        String str = "";
        MaterialComponent temp = head;
        while(temp != null) {
            str += temp.getName() + " (" + temp.getDesc() + "), " + temp.getWeight() + " carats, " + temp.getQuality() + "\n";
            temp = temp.getNextComponent();
        }
        return str;
    }



    //getters
    public MaterialComponent getHead() {
        return head;
    }

    public JewelleryItem getNextItem() {
        return nextItem;
    }

    public DisplayTray getParent() {
        return parent;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getType() {
        return type;
    }

    public String getTargetGender() {
        return targetGender;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getRetailPrice() {
        return retailPrice;
    }



    //setters
    public void setHead(MaterialComponent head) {
        this.head = head;
    }

    public void setNextItem(JewelleryItem nextItem) {
        this.nextItem = nextItem;
    }

    public void setParent(DisplayTray parent) {
        this.parent = parent;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setType(String type) {
        if(type.equals("ring") || type.equals("watch") || type.equals("necklace") || type.equals("pendant") || type.equals("bracelet") || type.equals("earring") || type.equals("other")) {
            this.type = type;
        }
    }

    public void setTargetGender(String targetGender) {
        if(targetGender.equals("male") || targetGender.equals("female") || targetGender.equals("unisex"))
        this.targetGender = targetGender;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setRetailPrice(int retailPrice) {
        if(retailPrice > 0) {
            this.retailPrice = retailPrice;
        }
    }



}

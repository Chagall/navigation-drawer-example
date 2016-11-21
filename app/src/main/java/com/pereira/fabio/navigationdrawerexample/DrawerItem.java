package com.pereira.fabio.navigationdrawerexample;

/**
 * Created by fabiopereira on 11/19/16.
 */

public class DrawerItem {

    private String itemName;
    private int imageResID;

    public DrawerItem(String itemName, int imageResID) {
        this.itemName = itemName;
        this.imageResID = imageResID;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public void setImageResID(int imageResID) {
        this.imageResID = imageResID;
    }
    public String getItemName() {
        return itemName;
    }
    public int getImageResID() {
        return imageResID;
    }

}

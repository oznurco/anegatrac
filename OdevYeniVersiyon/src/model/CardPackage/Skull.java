package model.CardPackage;

import java.awt.*;

public class Skull implements Card{
    private String skullImg;
    private String cardName;

    public Skull(String img, String name)

    {
        this.skullImg = img;
        this.cardName = name;
    }
    public String getCardName(){
        return this.cardName;
    }
    public String getImg() {
        return skullImg;
    }
}

package model.CardPackage;

import java.awt.*;

public class Sword implements Card {
    private String swordImg;
    private String cardName;

    public Sword(String img, String name)
    {
        this.swordImg = img;
        this.cardName = name;
    }
    public String getCardName(){
        return this.cardName;
    }
    public String getImg() {
        return swordImg;
    }
}

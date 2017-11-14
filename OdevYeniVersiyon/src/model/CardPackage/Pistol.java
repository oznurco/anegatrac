package model.CardPackage;

import java.awt.*;

public class Pistol implements Card {
    private String pistolImg;
    private String cardName;

    public Pistol(String img, String name)
    {
        this.pistolImg = img;
        this.cardName = name;
    }
    public String getCardName(){
        return this.cardName;
    }
    public String getImg() {
        return pistolImg;
    }
}

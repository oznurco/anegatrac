package model.CardPackage;

import java.awt.*;

public class Key implements Card{
    private String keyImg;
    private String cardName;

    public Key(String img, String name)
    {
        this.keyImg = img;
        this.cardName = name;
    }

    public String getCardName(){
        return this.cardName;
    }
    public String getImg() {
        return keyImg;
    }

}

package model.CardPackage;

import java.awt.*;

public class Hat implements Card {
    private String hatImg;
    private String cardName;

    public Hat(String img, String name)
    {
        this.hatImg = img;
        this.cardName = name;
    }
    public String getCardName(){
        return this.cardName;
    }
    public String getImg() {
        return hatImg;
    }
}

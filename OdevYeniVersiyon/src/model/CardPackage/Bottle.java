package model.CardPackage;

import javax.swing.*;
import java.awt.*;

public class Bottle implements Card {
    private String bottleImg;
    private String cardName;

    public Bottle(String img, String name)
    {
        this.bottleImg = img;
        this.cardName = name;
    }

    public String getCardName(){
        return this.cardName;
    }

    public String getImg() {
        return bottleImg;
    }
}


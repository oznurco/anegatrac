package model;

import model.CardPackage.Card;

import java.util.ArrayList;

public class Cell {
    private Card card;
    private ArrayList<Pirate>pirates;

    public Cell(Card card)
    {
        this.card = card;
        pirates = new ArrayList<Pirate>();
    }

    public boolean addPirateInCell(Pirate pirate)
    {
        if(this.pirates.size() < 1)
        {
            this.pirates.add(pirate);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void removePirateInCell()
    {
        System.out.println("Pirates count in cell : " + pirates.size());
        if(pirates.size() > 0)
        {
            System.out.println("Bu hücredeki pirate başka hücreye geçti.");
            pirates.remove(pirates.size() -1);
        }
    }

    public int pirateCountInCell()
    {
        return pirates.size();
    }

    public Card getCardInCell()
    {
        return this.card;
    }
}

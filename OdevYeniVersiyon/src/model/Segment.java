package model;

import model.CardPackage.Card;

import java.util.ArrayList;

public class Segment {
    private ArrayList<Cell> cells = new ArrayList<Cell>();

    public Segment(ArrayList<Cell> cells)
    {
        this.cells = cells;
    }

    public Cell getSegmentCell(int index)
    {
        return this.cells.get(index);
    }

    public int isSuitableCell(int cellNo, Card card)
    {
        int founded_index = -1;
       for(int i = cellNo; i < this.cells.size(); i++)
        {
            if(card.getCardName().equals(this.cells.get(i).getCardInCell().getCardName()))
            {
                if(this.cells.get(i).pirateCountInCell() == 0)
                {
                    founded_index = i;
                    break;
                }
                else
                {
                    System.out.println("Bu hücrede zaten bi korsan var !");
                }
            }
        }

        return founded_index;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }
}

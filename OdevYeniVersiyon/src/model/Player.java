package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import model.CardPackage.*;
import model.*;

public class Player {
    private ArrayList<Pirate> pirates;
    private ArrayList<Point> moveArray;
    private ArrayList<Card> cards;
    private String playerImg;

    public Player(ArrayList<Pirate> pirates, ArrayList<Card> cards, String playerImg) {
        this.pirates = pirates;
        this.cards = cards;
        moveArray = new ArrayList<Point>();
        this.playerImg = playerImg;
    }

    public void AddMove(Point point) {
        this.moveArray.add(point);
    }

    public void pullCard(Card card) {
        this.cards.add(card);
    }

    public void pushCard(Card card) {
        this.cards.remove(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Pirate> getPirates() {
        return pirates;
    }

    public Card selectCard(String cardName) {
        Card card = null;
        if (this.cards.size() != 0) {
            for (int i = 0; i < this.cards.size(); i++) {
                if (this.cards.get(i).getCardName().equals(cardName)) {
                    card = this.cards.get(i);
                    this.cards.remove(i);
                    return card;
                }
            }
            System.out.println("Elinizdeki kağıtlar arasında " + cardName + "yok");
        } else {
            System.out.println("Your cards are finished !");
        }
        return null;
    }

    public Pirate selectedPirate(int pirateNo) {
        Pirate pirate = null;
        for (int i = 0; i < this.getPirates().size(); i++) {
            if (this.getPirates().get(i).getPirateNumber() == pirateNo) {
                return this.getPirates().get(i);

            }
        }
        return pirate;
    }

    public boolean areReachAllPiratesToBoat() {
        int count = 0;
        for (Pirate p : this.pirates) {
            if (p.getIsReachToBoat() == true) {
                count++;
            }
        }
        if (count == 6) {
            return true;
        } else
            return false;
    }

    public boolean isFinishedGameForPlayer() {
        int count = 0;
        for (Pirate p : this.pirates) {
            if (p.getIsReachToBoat() == true) {
                count++;
            }
        }
        if (count == 6) {
            return true;
        } else {
            return false;
        }
    }

    public String getPlayerImg() {
        return playerImg;
    }
}

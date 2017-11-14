package model;

import model.CardPackage.*;
import model.Images.ImagePath;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.awt.Color.*;
import static java.awt.Color.BLACK;

public class CarteganaPlayModel {

    public static final int EACH_CARD_COUNT = 30;
    public static final int SEGMENT_COUNT = 6;
    public static final int CELL_COUNT = 6;
    public static final int PIRATE_COUNT = 6;
    public static final int POINTS_ARRAY_LENGTH = 2;
    public static final int BOAT_X_COORDINATE = 7;
    public static final int BOAT_Y_COORDINATE = 12;
    public static final int MAP_POINT_INITIAL_CAPACITY = 36;

    private ArrayList<Card> cards;
    private ArrayList<Segment> playPath;
    private ArrayList<Card> pathCard;
    ArrayList<Player> players;
    private SingletonBoat boat;

    int player_count;

    public CarteganaPlayModel(int player_count) {
        this.cards = new ArrayList<Card>();
        this.playPath = new ArrayList<Segment>();
        this.pathCard = new ArrayList<Card>();
        this.players = new ArrayList<Player>();
        this.player_count = player_count;
        this.boat = SingletonBoat.getInstance();
    }

    public ArrayList<Card> createCards() {
        int i = 0;

        //Read image for cards.
        String imgBottle = ImagePath.bottleImg;
        String imgKey = ImagePath.keyImg;
        String imgSword = ImagePath.swordImg;
        String imgSkull = ImagePath.skullImg;
        String imgHat = ImagePath.hatImg;
        String imgPistol = ImagePath.pistolImg;

        //30 u const olarak tanımla.
        for (i = 0; i < EACH_CARD_COUNT; i++) {
            Card bottle = new Bottle(imgBottle, MessageDictionary.bottle);
            cards.add(bottle);
            if (i == 0) {
                pathCard.add(bottle);
            }
        }

        for (i = 0; i < EACH_CARD_COUNT; i++) {
            Card key = new Key(imgKey, MessageDictionary.key);
            cards.add(key);
            if (i == 0) {
                pathCard.add(key);
            }
        }

        for (i = 0; i < EACH_CARD_COUNT; i++) {
            Card sword = new Sword(imgSword, MessageDictionary.sword);
            cards.add(sword);
            if (i == 0) {
                pathCard.add(sword);
            }
        }

        for (i = 0; i < EACH_CARD_COUNT; i++) {
            Card skull = new Skull(imgSkull, MessageDictionary.skull);
            cards.add(skull);
            if (i == 0) {
                pathCard.add(skull);
            }
        }

        for (i = 0; i < EACH_CARD_COUNT; i++) {
            Card hat = new Hat(imgHat, MessageDictionary.hat);
            cards.add(hat);
            if (i == 0) {
                pathCard.add(hat);
            }
        }

        for (i = 0; i < EACH_CARD_COUNT; i++) {
            Card pistol = new Pistol(imgPistol, MessageDictionary.pistol);
            cards.add(pistol);
            if (i == 0) {
                pathCard.add(pistol);
            }
        }

        Collections.shuffle(cards);
        return this.cards;
    }

    public ArrayList<Segment> createPlayPath() {

        for (int m = 0; m < SEGMENT_COUNT; m++) {
            Collections.shuffle(pathCard);
            ArrayList<Cell> cells = new ArrayList<Cell>();

            for (int i = 0; i < SEGMENT_COUNT; i++) {
                Cell cell = new Cell(pathCard.get(i));
                cells.add(cell);
            }
            Segment segment = new Segment(cells);
            playPath.add(segment);
        }

        return this.playPath;
    }

    public ArrayList<Player> createPlayer() {

        String[] playerImgPath = {ImagePath.player1, ImagePath.player2, ImagePath.player3, ImagePath.player4, ImagePath.player5};

        String[] pirateImgPath = {ImagePath.bluePirate, ImagePath.yellowPirate, ImagePath.redPirate, ImagePath.greenPirate, ImagePath.brownPirate};

        Color[] colors = {BLUE, YELLOW, RED, GREEN, BLACK};
        Color color;
        int j = 0;
        int index_pirate_img = 0;
        for (int i = 0; i < player_count; i++) {
            ArrayList<Pirate> pirates = new ArrayList<Pirate>();
            ArrayList<Card> playerCard = new ArrayList<Card>();
            String playerImg = playerImgPath[i];

            color = colors[i];
            for (j = 0; j < PIRATE_COUNT; j++) {
                Pirate pirate = new Pirate(color, j, pirateImgPath[index_pirate_img]);
                pirates.add(pirate);
                playerCard.add(cards.get(i));
                cards.remove(i);
            }
            Player player = new Player(pirates, playerCard, playerImg);
            players.add(player);
            index_pirate_img++;
        }
        return players;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Card> getPathCard() {
        return pathCard;
    }

    public ArrayList<Segment> getPlayPath() {
        return playPath;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Point[] playCard(int playerNo, Card selectedCard, Pirate selectedPirate) {

        Point newPoint = new Point();
        Point oldPoint = new Point();
        Point[] points = new Point[POINTS_ARRAY_LENGTH];
        boolean isFindSuitableCell = false;
        int startSegmentIndex = 0;
        int startCellIndex = 0;
        if (selectedPirate.getPoint().x != -1 && selectedPirate.getPoint().y != -1) {
            oldPoint = selectedPirate.getPoint();
            points[0] = oldPoint;
            startSegmentIndex = selectedPirate.getPoint().x;
            startCellIndex = selectedPirate.getPoint().y;
        }
        for (int i = startSegmentIndex; i < playPath.size(); i++) {

            int suitable_cell_index;
            Segment segment = playPath.get(i);
            if(i == startSegmentIndex)
            {
                suitable_cell_index = segment.isSuitableCell(startCellIndex, selectedCard);
            }
            else
            {
                suitable_cell_index = segment.isSuitableCell(0, selectedCard);
            }

            // korsanın yer aldığı bi önceki yer boşaltılmalı.
            if (suitable_cell_index != -1)//ilgili boş hücre bulundu
            {
                playPath.get(startSegmentIndex).getSegmentCell(startCellIndex).removePirateInCell();

                //korsanı yeni yerine taşıyoruz.
                selectedPirate.setPoint(i, suitable_cell_index);
                selectedPirate.addMoveToList(selectedPirate.getPoint());
                newPoint = selectedPirate.getPoint();
                points[1] = newPoint;
                isFindSuitableCell = true;

                //ilgili cell e korsanı ekliyoruz.
                segment.getSegmentCell(suitable_cell_index).addPirateInCell(selectedPirate);

                //oyuncunun yaptığı hamleler dizisine bu hamlesini de ekliyoruz.
                System.out.println(1 + ". oyuncunun " + selectedPirate.getPirateNumber() + " . korsanı şuraya geçti " + "selected pirate get point : " + selectedPirate.getPoint().x + " " + selectedPirate.getPoint().y);
                players.get(playerNo).AddMove(selectedPirate.getPoint());

                //eğer oyun bitmediyse oyuncu yeni kartlar çekecek.
                players.get(playerNo).pushCard(selectedCard);
                players.get(playerNo).pullCard(getCard(1));
                break;
            }
        }
        if (isFindSuitableCell == false) {
            selectedPirate.setIsReachToBoat(true);
            boat.setPrite_in_boat();
            selectedPirate.setPoint(BOAT_X_COORDINATE, BOAT_Y_COORDINATE);
            boat.comePirateToBoat(selectedPirate);
        }
        return points;
    }

    public Card getCard(int step_count) {
        Card card = null;
        if (step_count == 1) {
            card = getCards().get(getCards().size() - 1);
            cards.remove(getCards().size() - 1);
        }
        return card;
    }

    public boolean isFinishedGame() {
        if (boat.isFinishGame() == true) {
            return true;
        }
        return false;
    }

    public SingletonBoat getBoat() {
        return boat;
    }

    public Map<Point, Point> mappingPoint() {
        Map<Point, Point> mapPoint = new HashMap<Point, Point>(MAP_POINT_INITIAL_CAPACITY);

        int[] points = {9, 1, 10, 2, 8, 3, 7, 3, 7, 0, 6, 0, 5, 0, 5, 3, 4, 3, 3, 3, 3, 0, 2, 0, 0, 1, 0, 2, 0, 3, 0,
                4, 0, 5, 0, 6, 2, 7, 3, 7, 3, 4, 4, 4, 5, 4, 5, 7, 6, 7, 7, 7, 7, 4, 8, 4, 10, 5, 10, 6, 10, 7, 10, 8,
                7, 8, 7, 9, 7, 10, 7, 11};
        int k = 0;
        for(int i = 0; i < SEGMENT_COUNT; i ++)
        {
            for(int j = 0; j < CELL_COUNT; j ++)
            {
                mapPoint.put(new Point(i, j), new Point(points[k],points[k+1]));
                k = k + 2;
            }
        }
        return mapPoint;
    }
}

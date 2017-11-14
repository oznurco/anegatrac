package model;

import model.Images.ImagePath;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.awt.Color.*;

public class SingletonBoat {

    public static final int MAP_PRITE_INITIAL_CAPACITY = 36;
    public static final int COUNT_PIRATES_MAX = 6;
    public static final int COUNT_COLOR_MAX = 5;

    private static SingletonBoat instance = null;

    private int prite_in_boat = 0;
    private ArrayList<Pirate> comingPirates;
    private String boatImg;

    private SingletonBoat() {
        this.boatImg = ImagePath.boatImg;
        comingPirates = new ArrayList<Pirate>();
    }

    public static SingletonBoat getInstance()
    {
        if(instance == null)
        {
            instance  = new SingletonBoat();
        }
        return instance;
    }

    public String getBoatImg() {
        return boatImg;
    }

    public void setPrite_in_boat() {
        System.out.println("Pirate is coming to boat !");
        prite_in_boat++;
    }

    public int getPrite_in_boat() {
        return prite_in_boat;
    }

    public Map<Color, Integer> getPirateCountInBoat() {

        int countBluePirate = 0, countYellowPirate = 0, countRedPirate = 0, countGreenPirate = 0, countBrownPirate = 0;

        Map<Color, Integer> mapPirate = new HashMap<Color, Integer>(MAP_PRITE_INITIAL_CAPACITY);
        for (int i = 0; i < this.comingPirates.size(); i++) {
            if (this.comingPirates.get(i).getColorPirate() == BLUE)
                countBluePirate++;
            if (this.comingPirates.get(i).getColorPirate() == YELLOW)
                countYellowPirate++;
            if (this.comingPirates.get(i).getColorPirate() == RED)
                countRedPirate++;
            if (this.comingPirates.get(i).getColorPirate() == GREEN)
                countGreenPirate++;
            if (this.comingPirates.get(i).getColorPirate() == BLACK)
                countBrownPirate++;
        }

        mapPirate.put(BLUE, countBluePirate);
        mapPirate.put(YELLOW, countYellowPirate);
        mapPirate.put(RED, countRedPirate);
        mapPirate.put(GREEN, countGreenPirate);
        mapPirate.put(BLACK, countBrownPirate);
        return mapPirate;
    }

    public void comePirateToBoat(Pirate pirate) {
        this.comingPirates.add(pirate);
    }

    public boolean isFinishGame() {

        Map<Color, Integer> hashMap = getPirateCountInBoat();
        for (int i = 0; i < COUNT_COLOR_MAX; i++) {
            if (hashMap.containsValue(COUNT_PIRATES_MAX))
                return true;
        }

        return false;
    }
}

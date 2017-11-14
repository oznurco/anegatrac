package model;

import java.awt.*;
import java.util.ArrayList;

public class Pirate {
    public static final int INITIAL_X_COORDINATE = -1;
    public static final int INITIAL_Y_COORDINATE = -1;
    private Color color;
    private Point point;
    private ArrayList<Point> moveList;
    private boolean isReachToBoat;
    private int pirateNumber;
    private String pirateImg;

    public Pirate(Color color, int pirateNumber, String img) {
        this.color = color;
        this.moveList = new ArrayList<Point>();
        this.isReachToBoat = false;
        point = new Point(INITIAL_X_COORDINATE, INITIAL_Y_COORDINATE);
        this.pirateNumber = pirateNumber;
        this.pirateImg = img;

    }

    public Color getColorPirate()
    {
        return this.color;
    }

    public void setPoint(int x, int y) {
        this.point.setLocation(x, y);
    }

    public Point getPoint() {
        return this.point.getLocation();
    }

    public void addMoveToList(Point point)
    {
        this.moveList.add(point);
    }

    public boolean getIsReachToBoat()
    {
        return this.isReachToBoat;
    }

    public void setIsReachToBoat(boolean reach_value)
    {
        this.isReachToBoat = reach_value;
    }

    public int getPirateNumber()
    {
        return this.pirateNumber;
    }

    public String getPirateImg() {
        return pirateImg;
    }
}
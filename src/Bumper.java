// Computer Math - Unit 2 - Lab 17 - Keyboard Input

import java.awt.Color;
import java.awt.Graphics;

public class Bumper {
    // private fields, all ints, for a Bumper
    // hint: the "location" of the bumper begins at its top left corner.
    private int myX;
    private int myY;
    private int myXWidth;
    private int myYWidth;
    private Color myColor;

    // constructors
    public Bumper() // default constructor
    {
        /*
         * myX = 200; myY = 200; myXWidth = 20; myYWidth = 40; myColor = Color.BLUE;
         */
        this(200, 200, 20, 40, Color.BLUE);
    }

    public Bumper(int x, int y, int xWidth, int yWidth, Color c) {
        myX = x;
        myY = y;
        myXWidth = xWidth;
        myYWidth = yWidth;
        myColor = c;
    }

    // accessor methods (one for each field)
    public int getX() {
        return myX;
    }

    // modifier methods (one for each field)
    public void setX(int x) {
        myX = x;
    }

    public int getY() {
        return myY;
    }

    public void setY(int y) {
        myY = y;
    }

    public int getXWidth() {
        return myXWidth;
    }

    public void setXWidth(int xWidth) {
        myXWidth = xWidth;
    }

    public int getYWidth() {
        return myYWidth;
    }

    public Color getColor() {
        return myColor;
    }

    public void setColor(Color c) {
        myColor = c;
    }

    public void setyWidth(int yWidth) {
        myYWidth = yWidth;
    }

    // instance methods

    // chooses a random (x,y) location. Bumper stays entirely in the window.
    public void jump(int rightEdge, int bottomEdge) {
        System.out.format("Bumper: width=%d, height=%d %n", rightEdge, bottomEdge);

        // make sure there is a minimum amount of room between bumper and left/right edges of screen.
        int minHorizontalGap = rightEdge * 12 / 100;
        System.out.format("minimum horizontal gap = %d%n", minHorizontalGap);
        int rightGap = 0;
        do {
            myX = (int) (Math.random() * (rightEdge - myXWidth));
            rightGap = rightEdge - (myX + myXWidth);
            System.out.format("myX = %d, rightGap = %d%n", myX, rightGap);
        } while (myX < minHorizontalGap || rightGap < minHorizontalGap);

        // make sure there is a minimum amount of room between top/bottom of bumper and top/bottom of screen
        int bottomGap = 0;
        int minVerticalGap = bottomEdge * 12 / 100;
        System.out.format("minimum vertical gap = %d%n", minVerticalGap);
        do {
            myY = (int) (Math.random() * (bottomEdge - myYWidth) );
            System.out.format("myY = %d%n", myY);
            bottomGap = bottomEdge - (myY + myYWidth);
            System.out.format("bottomGap = %d%n", bottomGap);
        } while (myY < minVerticalGap || bottomGap < minVerticalGap);

    }

    // draws a rectangular bumper on the buffer
    public void draw(Graphics myBuffer) {
        myBuffer.setColor(getColor());
        myBuffer.fillRect(getX(), getY(), getXWidth(), getYWidth());
    }

    // returns true if any part of the polkadot is inside the bumper
    public boolean inBumper(Polkadot dot) {
        for (int x = getX(); x <= getX() + getXWidth(); x++) // starts at upper left corner(x,y)
            for (int y = getY(); y <= getY() + getYWidth(); y++)
                if (distance(x, y, dot.getX(), dot.getY()) <= dot.getRadius()) // checks every point on the bumper
                    return true;
        return false;
    }

    // returns distance between (x1, y1) and (x2, y2)
    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}

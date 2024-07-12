// Computer Math - Unit 2 - Lab 17 - Keyboard Input

import java.awt.*;

public class Polkadot {
    private double myX;   // x and y coordinates of center
    private double myY;
    private double myDiameter;
    private Color myColor;
    private double myRadius;

    // constructors
    public Polkadot()     //default constructor
    {
        this(200, 200, 25, Color.RED);
    }

    public Polkadot(double x, double y, double d, Color c) {
        myX = x;
        myY = y;
        myDiameter = d;
        myColor = c;
        myRadius = d / 2;
    }

    // accessor methods
    public double getX() {
        return myX;
    }

    // modifier methods
    public void setX(double x) {
        myX = x;
    }

    public double getY() {
        return myY;
    }

    public void setY(double y) {
        myY = y;
    }

    public double getDiameter() {
        return myDiameter;
    }

    public void setDiameter(double d) {
        myDiameter = d;
        myRadius = d / 2;
    }

    public Color getColor() {
        return myColor;
    }

    /**
     * Sets the new color to c.
     *
     * @param c the color
     */
    public void setColor(Color c) {
        myColor = c;
    }

    public double getRadius() {
        return myRadius;
    }

    public void setRadius(double r) {
        myRadius = r;
        myDiameter = 2 * r;
    }


    //	 instance methods
    public void jump(int rightEdge, int bottomEdge) {
        // moves location to random (x, y) within the edges
        myX = (Math.random() * (rightEdge - myDiameter) + myRadius);
        myY = (Math.random() * (bottomEdge - myDiameter) + myRadius);
    }


    public void draw(Graphics myBuffer) {
        myBuffer.setColor(myColor);
        myBuffer.fillOval((int) (myX - myRadius), (int) (myY - myRadius), (int) myDiameter, (int) myDiameter);
    }


}
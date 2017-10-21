package com.jacobzipper.lockscreenroulette;


/**
 * This class handles the storage of a piece coordinates.
 *
 * @author tiphedor
 */

public class Coordinate {

    /**
     * x coordinate of piece 1
     */
    private int x1;

    /**
     * y coordinate of piece 1
     */
    private int y1;

    /**
     * x coordinate of piece 2
     */
    private int x2;

    /**
     * y coordinate of piece 2
     */
    private int y2;

    /**
     * x coordinate of piece 3
     */
    private int x3;

    /**
     * y coordinate of piece 3
     */
    private int y3;

    /**
     * x coordinate of piece 4
     */
    private int x4;

    /**
     * y coordinate of piece 4
     */
    private int y4;


    /**
     * Main class constructor.
     *
     * @param x1 x coordinate of piece 1
     * @param y1 y coordinate of piece 1
     * @param x2 x coordinate of piece 2
     * @param y2 y coordinate of piece 2
     * @param x3 x coordinate of piece 3
     * @param y3 y coordinate of piece 3
     * @param x4 x coordinate of piece 4
     * @param y4 y coordinate of piece 4
     */
    public Coordinate(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.x4 = x4;
        this.y4 = y4;
    }

    /**
     * Returns the x coordinate of piece 1
     * @return x coordinate of piece 1
     */
    public int getX1() {
        return x1;
    }

    /**
     * Returns the y coordinate of piece 1
     * @return y coordinate of piece 1
     */
    public int getY1() {
        return y1;
    }

    /**
     * Returns the x coordinate of piece 2
     * @return x coordinate of piece 2
     */
    public int getX2() {
        return x2;
    }

    /**
     * Returns the y coordinate of piece 2
     * @return y coordinate of piece 2
     */
    public int getY2() {
        return y2;
    }

    /**
     * Returns the x coordinate of piece 3
     * @return x coordinate of piece 3
     */
    public int getX3() {
        return x3;
    }

    /**
     * Returns the y coordinate of piece 3
     * @return y coordinate of piece 3
     */
    public int getY3() {
        return y3;
    }

    /**
     * Returns the x coordinate of piece 4
     * @return x coordinate of piece 4
     */
    public int getX4() {
        return x4;
    }

    /**
     * Returns the y coordinate of piece 4
     * @return y coordinate of piece 4
     */
    public int getY4() {
        return y4;
    }


    /**
     * Sets the value of piece 1 x coordinate
     * @param x1 Piece 1 x coordinate
     */
    public void setX1(int x1) {
        this.x1 = x1;
    }

    /**
     * Sets the value of piece 1 y coordinate
     * @param y1 Piece 1 y coordinate
     */
    public void setY1(int y1) {
        this.y1 = y1;
    }

    /**
     * Sets the value of piece 2 x coordinate
     * @param x2 Piece 2 x coordinate
     */
    public void setX2(int x2) {
        this.x2 = x2;
    }

    /**
     * Sets the value of piece 2 y coordinate
     * @param y2 Piece 2 y coordinate
     */
    public void setY2(int y2) {
        this.y2 = y2;
    }

    /**
     * Sets the value of piece 3 x coordinate
     * @param x3 Piece 3 x coordinate
     */
    public void setX3(int x3) {
        this.x3 = x3;
    }

    /**
     * Sets the value of piece 3 y coordinate
     * @param y3 Piece 3 y coordinate
     */
    public void setY3(int y3) {
        this.y3 = y3;
    }

    /**
     * Sets the value of piece 4 x coordinate
     * @param x4 Piece 4 x coordinate
     */
    public void setX4(int x4) {
        this.x4 = x4;
    }

    /**
     * Sets the value of piece 4 y coordinate
     * @param y4 Piece 4 y coordinate
     */
    public void setY4(int y4) {
        this.y4 = y4;
    }

}

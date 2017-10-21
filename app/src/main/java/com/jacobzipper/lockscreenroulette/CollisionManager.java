package com.jacobzipper.lockscreenroulette;

/**
 * Handle the collision detection.
 *
 * @author tiphedor
 */

public class CollisionManager {

    /**
     * Theses constants defines the orientation of a piece
     */

    public final int ORIENTATION_UP = 1;
    public final int ORIENTATION_DOWN = 2;
    public final int ORIENTATION_LEFT = 3;
    public final int ORIENTATION_RIGHT = 4;

    /**
     * The moving piece
     */
    private Piece movingPiece;

    /**
     * Destination of the piece
     */
    private Coordinate destination;

    /**
     * Class constructor
     * @param mMovingPiece The moving piece
     * @param mDestination Destination of the piece
     */
    public CollisionManager(Piece mMovingPiece, Coordinate mDestination) {
        this.movingPiece = mMovingPiece;
        this.destination = mDestination;
    }

    /**
     * Check for collision at the left of a piece
     * @return Possibility of going left
     */
    public boolean checkLeft() {
        // First, we check if piece is going off the game board
        if(this.destination.getX1() < 0 || this.destination.getX2() < 0 || this.destination.getX3() < 0 || this.destination.getX4() < 0) {
            return false;
        }

        switch(this.movingPiece.getPieceType()) {
            // T Block
            case 1:
                switch (this.movingPiece.getOrientation()) {
                    case ORIENTATION_UP:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_RIGHT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_LEFT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_DOWN:
                        if(this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                }
                break;
            // I Block
            case 2:
                switch (this.movingPiece.getOrientation()) {
                    case ORIENTATION_UP:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_RIGHT:case ORIENTATION_LEFT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_DOWN:
                        if(this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                }
                break;
            // O block
            case 3:
                if(this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                        || this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0) {
                    return false;
                }
                break;
            // S Block
            case 4:
                switch (this.movingPiece.getOrientation()) {
                    case ORIENTATION_UP:
                        if(this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_RIGHT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_DOWN:
                        if(this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_LEFT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0) {
                            return false;
                        }
                        break;
                }
                break;
            // Z Block
            case 5:
                switch (this.movingPiece.getOrientation()) {
                    case ORIENTATION_UP:
                        if(this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_RIGHT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_DOWN:
                        if(this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_LEFT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0) {
                            return false;
                        }
                        break;
                }
                break;
            // J Block
            case 6:
                switch (this.movingPiece.getOrientation()) {
                    case ORIENTATION_UP:
                        if(this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_RIGHT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_DOWN:
                        if(this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_LEFT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0) {
                            return false;
                        }
                        break;
                }
                break;
            // L Block
            case 7:
                switch (this.movingPiece.getOrientation()) {
                    case ORIENTATION_UP:
                        if(this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_RIGHT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_DOWN:
                        if(this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_LEFT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0) {
                            return false;
                        }
                        break;
                }
                break;
        }
        return true;
    }

    /**
     * Check for collision at the left of the piece
     * @return Possibility of going right
     */
    public boolean checkRight() {
        // First, we check if piece is going off the game board
        if(this.destination.getX1() > 9 || this.destination.getX2() > 9 || this.destination.getX3() > 9 || this.destination.getX4() > 9) {
            return false;
        }

        switch(this.movingPiece.getPieceType()) {
            // T Block
            case 1:
                switch (this.movingPiece.getOrientation()) {
                    case ORIENTATION_UP:
                        if(this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_RIGHT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_LEFT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_DOWN:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                }
                break;
            // O block
            case 3:
                if(this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                        || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                    return false;
                }
                break;
            // S Block
            case 4:
                switch(this.movingPiece.getOrientation()) {
                    case ORIENTATION_UP:
                        if(this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_RIGHT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_DOWN:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_LEFT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                }
                break;
            // Z block
            case 5:
                switch(this.movingPiece.getOrientation()) {
                    case ORIENTATION_UP:
                        if(this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_RIGHT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_DOWN:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_LEFT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                }
                break;
            // J block
            case 6:
                switch(this.movingPiece.getOrientation()) {
                    case ORIENTATION_UP:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_RIGHT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_DOWN:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_LEFT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                }
                break;
            // L block
            case 7:
                switch(this.movingPiece.getOrientation()) {
                    case ORIENTATION_UP:
                        if(this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_RIGHT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_DOWN:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_LEFT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0) {
                            return false;
                        }
                        break;
                }
                break;
        }
        return true;
    }

    /**
     * Check for collision at the bottom of a piece
     * @return Possibility of going down
     */
    public boolean checkDown() {
        // First, we check if piece is going off the game board
        if(this.destination.getY1() < 0 || this.destination.getY2() < 0 || this.destination.getY3() < 0 || this.destination.getY4() < 0) {
            return false;
        }
        switch(this.movingPiece.getPieceType()) {
            // T Block
            case 1:
                switch(this.movingPiece.getOrientation()) {
                    case ORIENTATION_UP:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_RIGHT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_LEFT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_DOWN:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                }
                break;

            // I Block
            case 2:
                switch(this.movingPiece.getOrientation()) {
                    case ORIENTATION_UP: case ORIENTATION_DOWN:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_RIGHT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_LEFT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0) {
                            return false;
                        }
                        break;
                }
                break;
            // O block
            case 3:
                if(this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                        || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                    return false;
                }
                break;
            // S Block
            case 4:
                switch (this.movingPiece.getOrientation()) {
                    case ORIENTATION_UP:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0 ) {
                            return false;
                        }
                        break;
                    case ORIENTATION_RIGHT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_DOWN:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0 ) {
                            return false;
                        }
                        break;
                    case ORIENTATION_LEFT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0) {
                            return false;
                        }
                        break;
                }
                break;
            // Z Block
            case 5:
                switch (this.movingPiece.getOrientation()) {
                    case ORIENTATION_UP:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0 ) {
                            return false;
                        }
                        break;
                    case ORIENTATION_RIGHT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_DOWN:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0 ) {
                            return false;
                        }
                        break;
                    case ORIENTATION_LEFT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0) {
                            return false;
                        }
                        break;
                }
                break;
            // J Block
            case 6:
                switch (this.movingPiece.getOrientation()) {
                    case ORIENTATION_UP:
                        if(this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0 ) {
                            return false;
                        }
                        break;
                    case ORIENTATION_RIGHT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_DOWN:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0 ) {
                            return false;
                        }
                        break;
                    case ORIENTATION_LEFT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0) {
                            return false;
                        }
                        break;
                }
                break;
            // L Block
            case 7:
                switch (this.movingPiece.getOrientation()) {
                    case ORIENTATION_UP:
                        if(this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0 ) {
                            return false;
                        }
                        break;
                    case ORIENTATION_RIGHT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX3()][this.destination.getY3()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0) {
                            return false;
                        }
                        break;
                    case ORIENTATION_DOWN:
                        if(this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX2()][this.destination.getY2()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0 ) {
                            return false;
                        }
                        break;
                    case ORIENTATION_LEFT:
                        if(this.movingPiece.getGameBoard()[this.destination.getX4()][this.destination.getY4()] != 0
                                || this.movingPiece.getGameBoard()[this.destination.getX1()][this.destination.getY1()] != 0) {
                            return false;
                        }
                        break;
                }
                break;
        }
        return true;
    }
}

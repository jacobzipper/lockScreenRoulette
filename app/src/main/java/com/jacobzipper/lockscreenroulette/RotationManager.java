package com.jacobzipper.lockscreenroulette;


/**
 * Detection of the validity of rotations
 *
 * @author tiphedor
 */

public class RotationManager {

    /**
     * Destination of the rotating piece
     */
    private Coordinate pieceDestination;

    /**
     * Constructor of the class
     * @param mPieceDestination destination of the piece
     */
    public RotationManager(Coordinate mPieceDestination) {
        this.pieceDestination = mPieceDestination;
    }

    /**
     * Check if a rotation is possible
     * @return Possibility of rotation
     */
    public boolean checkRotationValidity() {
        // Detect if rotation will put a part of the piece out of the board
        if(pieceDestination.getX1() < 0 || pieceDestination.getX1() > 9
                || pieceDestination.getX2() < 0 || pieceDestination.getX2() > 9
                || pieceDestination.getX3() < 0 || pieceDestination.getX3() > 9
                || pieceDestination.getX4() < 0 || pieceDestination.getX4() > 9

                || pieceDestination.getY1() < 0 || pieceDestination.getY1() > 21
                || pieceDestination.getY2() < 0 || pieceDestination.getY2() > 21
                || pieceDestination.getY3() < 0 || pieceDestination.getY3() > 21
                || pieceDestination.getY4() < 0 || pieceDestination.getY4() > 21) {
            return false;
        }

        // Detect if rotation don't make the piece go on other pieces
        // @todo

        return true;
    }
}

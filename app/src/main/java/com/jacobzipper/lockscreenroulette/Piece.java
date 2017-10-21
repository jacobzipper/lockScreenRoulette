package com.jacobzipper.lockscreenroulette;


import java.util.Random;

/**
 * Handle the pieces management, ie the movement and rotation of the currently playing piece.
 *
 * @author tiphedor
 */

public class Piece {

    /**
     * Theses constants defines the orientation of a piece
     */

    public final int ORIENTATION_UP = 1;
    public final int ORIENTATION_DOWN = 2;
    public final int ORIENTATION_LEFT = 3;
    public final int ORIENTATION_RIGHT = 4;

    /**
     * Player score
     */
    private int score;

    private boolean gameOver;

    /**
     * The type of the piece
     */
    private int pieceType;

    /**
     * The type of the next piece
     */
    public int nextPiece;

    /**
     * Current piece orientation
     */
    private int orientation;

    /**
     * Game board array
     */
    private int[][] gameBoard;

    /**
     * Coordinates of the piece
     */
    private Coordinate pieceLocation;

    /**
     * Main class constructor.
     */
    public Piece(int[][] mGameBoard) {
        Random rand = new Random();
        this.pieceType = rand.nextInt(7) + 1;
        this.nextPiece = rand.nextInt(7) + 1;
        this.gameBoard = mGameBoard;
        this.orientation = ORIENTATION_UP;
        this.setInitialPosition();
    }

    /**
     * Returns the next piece type
     * @return Next piece type
     */
    public int getNextPiece() {
        return nextPiece;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Returns the type of the currently played piece
     * @return Piece type
     */
    public int getPieceType() {
        return pieceType;
    }

    /**
     * Returns the current game board array
     * @return Game board
     */
    public int[][] getGameBoard() {
        return this.gameBoard;
    }

    /**
     * Sets the score
     * @return Score
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the current piece orientation
     * @return Piece orientation
     */
    public int getOrientation() {
        return this.orientation;
    }

    /**
     * Rotate the current piece
     * @return Game board
     */
    int[][] rotateClockwise() {
        Coordinate destination = this.pieceLocation;
        RotationManager rManager;
        int previousOrientation = this.orientation;

        switch(this.pieceType) {
            // T block
            case 1:
                switch (this.orientation) {
                    case ORIENTATION_UP:
                        destination = new Coordinate(this.pieceLocation.getX4(), this.pieceLocation.getY4(), this.pieceLocation.getX2(), this.pieceLocation.getY2(), this.pieceLocation.getX2(), this.pieceLocation.getY2() - 1, this.pieceLocation.getX3(), this.pieceLocation.getY3());
                        this.orientation = ORIENTATION_RIGHT;
                        break;
                    case ORIENTATION_RIGHT:
                        destination = new Coordinate(this.pieceLocation.getX4(), this.pieceLocation.getY4(), this.pieceLocation.getX2(), this.pieceLocation.getY2(), this.pieceLocation.getX2() - 1, this.pieceLocation.getY2(), this.pieceLocation.getX3(), this.pieceLocation.getY3());
                        this.orientation = ORIENTATION_DOWN;
                        break;
                    case ORIENTATION_DOWN:
                        destination = new Coordinate(this.pieceLocation.getX4(), this.pieceLocation.getY4(), this.pieceLocation.getX2(), this.pieceLocation.getY2(), this.pieceLocation.getX2(), this.pieceLocation.getY2() + 1, this.pieceLocation.getX3(), this.pieceLocation.getY3());
                        this.orientation = ORIENTATION_LEFT;
                        break;
                    case ORIENTATION_LEFT:
                        destination = new Coordinate(this.pieceLocation.getX4(), this.pieceLocation.getY4(), this.pieceLocation.getX2(), this.pieceLocation.getY2(), this.pieceLocation.getX2() + 1, this.pieceLocation.getY2(), this.pieceLocation.getX3(), this.pieceLocation.getY3());
                        this.orientation = ORIENTATION_UP;
                        break;

                }
                break;
            // I block
            case 2:
                switch(this.orientation) {
                    case ORIENTATION_UP:
                        destination = new Coordinate(this.pieceLocation.getX3(), this.pieceLocation.getY3() + 1, this.pieceLocation.getX3(), this.pieceLocation.getY3(), this.pieceLocation.getX3(), this.pieceLocation.getY3() - 1, this.pieceLocation.getX3(), this.pieceLocation.getY3() - 2);
                        this.orientation = ORIENTATION_RIGHT;
                        break;
                    case ORIENTATION_RIGHT:
                        destination = new Coordinate(this.pieceLocation.getX3() + 1, this.pieceLocation.getY3(), this.pieceLocation.getX3(), this.pieceLocation.getY3(), this.pieceLocation.getX3() - 1, this.pieceLocation.getY3(), this.pieceLocation.getX3() - 2, this.pieceLocation.getY3());
                        this.orientation = ORIENTATION_DOWN;
                        break;
                    case ORIENTATION_DOWN:
                        destination = new Coordinate(this.pieceLocation.getX3(), this.pieceLocation.getY3() - 1, this.pieceLocation.getX3(), this.pieceLocation.getY3(), this.pieceLocation.getX3(), this.pieceLocation.getY3() + 1, this.pieceLocation.getX3(), this.pieceLocation.getY3() + 2);
                        this.orientation = ORIENTATION_LEFT;
                        break;
                    case ORIENTATION_LEFT:
                        destination = new Coordinate(this.pieceLocation.getX3() - 1, this.pieceLocation.getY3(), this.pieceLocation.getX3(), this.pieceLocation.getY3(), this.pieceLocation.getX3() + 1, this.pieceLocation.getY3(), this.pieceLocation.getX3() + 2, this.pieceLocation.getY3());
                        this.orientation = ORIENTATION_UP;
                        break;
                }
                break;
            // No case 3 because piece 3 is the square, and square can't rotate
            // S block
            case 4:
                switch (this.orientation) {
                    case ORIENTATION_UP:
                        destination = new Coordinate(this.pieceLocation.getX3(), this.pieceLocation.getY3(), this.pieceLocation.getX2(), this.pieceLocation.getY2(), this.pieceLocation.getX2() + 1, this.pieceLocation.getY2(), this.pieceLocation.getX2() + 1, this.pieceLocation.getY2() - 1);
                        this.orientation = ORIENTATION_RIGHT;
                        break;
                    case ORIENTATION_RIGHT:
                        destination = new Coordinate(this.pieceLocation.getX3(), this.pieceLocation.getY3(), this.pieceLocation.getX2(), this.pieceLocation.getY2(), this.pieceLocation.getX2(), this.pieceLocation.getY2() - 1, this.pieceLocation.getX2() - 1, this.pieceLocation.getY2() - 1);
                        this.orientation = ORIENTATION_DOWN;
                        break;
                    case ORIENTATION_DOWN:
                        destination = new Coordinate(this.pieceLocation.getX3(), this.pieceLocation.getY3(), this.pieceLocation.getX2(), this.pieceLocation.getY2(), this.pieceLocation.getX2() - 1, this.pieceLocation.getY2(), this.pieceLocation.getX2() - 1, this.pieceLocation.getY2() + 1);
                        this.orientation = ORIENTATION_LEFT;
                        break;
                    case ORIENTATION_LEFT:
                        destination = new Coordinate(this.pieceLocation.getX3(), this.pieceLocation.getY3(), this.pieceLocation.getX2(), this.pieceLocation.getY2(), this.pieceLocation.getX2(), this.pieceLocation.getY2() + 1, this.pieceLocation.getX2() + 1, this.pieceLocation.getY2() + 1);
                        this.orientation = ORIENTATION_UP;
                        break;
                }
                break;

            // Z Block
            case 5:
                switch (this.orientation) {
                    case ORIENTATION_UP:
                        destination = new Coordinate(this.pieceLocation.getX2() + 1, this.pieceLocation.getY2(), this.pieceLocation.getX4(), this.pieceLocation.getY4(), this.pieceLocation.getX3(), this.pieceLocation.getY3(), this.pieceLocation.getX3(), this.pieceLocation.getY3() - 1);
                        this.orientation = ORIENTATION_RIGHT;
                        break;
                    case  ORIENTATION_RIGHT:
                        destination = new Coordinate(this.pieceLocation.getX2(), this.pieceLocation.getY2() - 1, this.pieceLocation.getX4(), this.pieceLocation.getY4(), this.pieceLocation.getX3(), this.pieceLocation.getY3(), this.pieceLocation.getX3() - 1, this.pieceLocation.getY3());
                        this.orientation = ORIENTATION_DOWN;
                        break;
                    case ORIENTATION_DOWN:
                        destination = new Coordinate(this.pieceLocation.getX4(), this.pieceLocation.getY4() - 1, this.pieceLocation.getX4(), this.pieceLocation.getY4(), this.pieceLocation.getX3(), this.pieceLocation.getY3(), this.pieceLocation.getX3(), this.pieceLocation.getY3() + 1);
                        this.orientation = ORIENTATION_LEFT;
                        break;
                    case ORIENTATION_LEFT:
                        destination = new Coordinate(this.pieceLocation.getX2(), this.pieceLocation.getY2(), this.pieceLocation.getX3(), this.pieceLocation.getY3(), this.pieceLocation.getX1() + 1, this.pieceLocation.getY1(), this.pieceLocation.getX1() + 2, this.pieceLocation.getY1());
                        this.orientation = ORIENTATION_UP;
                        break;
                }
                break;
            // J Block
            case 6:
                switch (this.orientation) {
                    case ORIENTATION_UP:
                        destination = new Coordinate(this.pieceLocation.getX4(), this.pieceLocation.getY4() + 1, this.pieceLocation.getX3(), this.pieceLocation.getY3() + 1, this.pieceLocation.getX3(), this.pieceLocation.getY3(), this.pieceLocation.getX3(), this.pieceLocation.getY3() - 1);
                        this.orientation = ORIENTATION_RIGHT;
                        break;
                    case  ORIENTATION_RIGHT:
                        destination = new Coordinate(this.pieceLocation.getX4() + 1, this.pieceLocation.getY4(), this.pieceLocation.getX3() + 1, this.pieceLocation.getY3(), this.pieceLocation.getX3(), this.pieceLocation.getY3(), this.pieceLocation.getX3() - 1, this.pieceLocation.getY3());
                        this.orientation = ORIENTATION_DOWN;
                        break;
                    case ORIENTATION_DOWN:
                        destination = new Coordinate(this.pieceLocation.getX4(), this.pieceLocation.getY4() - 1, this.pieceLocation.getX3(), this.pieceLocation.getY3() - 1, this.pieceLocation.getX3(), this.pieceLocation.getY3(), this.pieceLocation.getX3(), this.pieceLocation.getY3() + 1);
                        this.orientation = ORIENTATION_LEFT;
                        break;
                    case ORIENTATION_LEFT:
                        destination = new Coordinate(this.pieceLocation.getX4() - 1, this.pieceLocation.getY4(), this.pieceLocation.getX3() - 1, this.pieceLocation.getY3(), this.pieceLocation.getX3(), this.pieceLocation.getY3(), this.pieceLocation.getX3() + 1, this.pieceLocation.getY3());
                        this.orientation = ORIENTATION_UP;
                        break;
                }
                break;
            // L Block
            case 7:
                switch (this.orientation) {
                    case ORIENTATION_UP:
                        destination = new Coordinate(this.pieceLocation.getX2(), this.pieceLocation.getY2() + 1, this.pieceLocation.getX2(), this.pieceLocation.getY2(), this.pieceLocation.getX2(), this.pieceLocation.getY2() - 1, this.pieceLocation.getX3(), this.pieceLocation.getY3() - 1);
                        this.orientation = ORIENTATION_RIGHT;
                        break;
                    case  ORIENTATION_RIGHT:
                        destination = new Coordinate(this.pieceLocation.getX4(), this.pieceLocation.getY4() + 1, this.pieceLocation.getX2(), this.pieceLocation.getY2(), this.pieceLocation.getX2() - 1, this.pieceLocation.getY2(), this.pieceLocation.getX3() - 1, this.pieceLocation.getY3());
                        this.orientation = ORIENTATION_DOWN;
                        break;
                    case ORIENTATION_DOWN:
                        destination = new Coordinate(this.pieceLocation.getX2(), this.pieceLocation.getY2() - 1, this.pieceLocation.getX2(), this.pieceLocation.getY2(), this.pieceLocation.getX2(), this.pieceLocation.getY2() + 1, this.pieceLocation.getX3(), this.pieceLocation.getY3() + 1);
                        this.orientation = ORIENTATION_LEFT;
                        break;
                    case ORIENTATION_LEFT:
                        destination = new Coordinate(this.pieceLocation.getX4(), this.pieceLocation.getY4() - 1, this.pieceLocation.getX2(), this.pieceLocation.getY2(), this.pieceLocation.getX2() + 1, this.pieceLocation.getY2(), this.pieceLocation.getX3() + 1, this.pieceLocation.getY3());
                        this.orientation = ORIENTATION_UP;
                        break;
                }
                break;

        }

        // Check if rotation is possible

        rManager = new RotationManager(destination);
        if(!rManager.checkRotationValidity()) {
            this.orientation = previousOrientation;
            return null;
        }

        // rotation is possible, we delete the previous piece
        this.gameBoard[this.pieceLocation.getX1()][this.pieceLocation.getY1()] = 0;
        this.gameBoard[this.pieceLocation.getX2()][this.pieceLocation.getY2()] = 0;
        this.gameBoard[this.pieceLocation.getX3()][this.pieceLocation.getY3()] = 0;
        this.gameBoard[this.pieceLocation.getX4()][this.pieceLocation.getY4()] = 0;

        this.gameBoard[destination.getX1()][destination.getY1()] = 1;
        this.gameBoard[destination.getX2()][destination.getY2()] = 1;
        this.gameBoard[destination.getX3()][destination.getY3()] = 1;
        this.gameBoard[destination.getX4()][destination.getY4()] = 1;

        this.pieceLocation.setX1(destination.getX1());
        this.pieceLocation.setX2(destination.getX2());
        this.pieceLocation.setX3(destination.getX3());
        this.pieceLocation.setX4(destination.getX4());

        this.pieceLocation.setY1(destination.getY1());
        this.pieceLocation.setY2(destination.getY2());
        this.pieceLocation.setY3(destination.getY3());
        this.pieceLocation.setY4(destination.getY4());

        return this.gameBoard;
    }

    /**
     * Move the current piece one cell left and return the modified game board
     * @return Game board
     */
    int[][] moveLeft() {
        // Destination of the piece
        Coordinate destination = new Coordinate(this.pieceLocation.getX1() - 1, this.pieceLocation.getY1(), this.pieceLocation.getX2() - 1, this.pieceLocation.getY2(), this.pieceLocation.getX3() - 1, this.pieceLocation.getY3(), this.pieceLocation.getX4() - 1, this.pieceLocation.getY4());

        // Check if movement is possible
        CollisionManager collManager = new CollisionManager(this, destination);
        if(!collManager.checkLeft()) { // Movement is NOT possible
            return this.gameBoard;
        }

        // Movement is possible, we move the piece

        this.gameBoard[this.pieceLocation.getX1()][this.pieceLocation.getY1()] = 0;
        this.gameBoard[this.pieceLocation.getX2()][this.pieceLocation.getY2()] = 0;
        this.gameBoard[this.pieceLocation.getX3()][this.pieceLocation.getY3()] = 0;
        this.gameBoard[this.pieceLocation.getX4()][this.pieceLocation.getY4()] = 0;

        this.gameBoard[this.pieceLocation.getX1() - 1][this.pieceLocation.getY1()] = 1;
        this.gameBoard[this.pieceLocation.getX2() - 1][this.pieceLocation.getY2()] = 1;
        this.gameBoard[this.pieceLocation.getX3() - 1][this.pieceLocation.getY3()] = 1;
        this.gameBoard[this.pieceLocation.getX4() - 1][this.pieceLocation.getY4()] = 1;

        this.pieceLocation.setX1(this.pieceLocation.getX1() - 1);
        this.pieceLocation.setX2(this.pieceLocation.getX2() - 1);
        this.pieceLocation.setX3(this.pieceLocation.getX3() - 1);
        this.pieceLocation.setX4(this.pieceLocation.getX4() - 1);

        return this.gameBoard;
    }

    /**
     * Move the current piece one cell right and return the modified game board
     * @return Game board
     */
    int[][] moveRight() {
        // Destination of the piece
        Coordinate destination = new Coordinate(this.pieceLocation.getX1() + 1, this.pieceLocation.getY1(), this.pieceLocation.getX2() + 1, this.pieceLocation.getY2(), this.pieceLocation.getX3() + 1, this.pieceLocation.getY3(), this.pieceLocation.getX4() + 1, this.pieceLocation.getY4());

        // Check if movement is possible
        CollisionManager collManager = new CollisionManager(this, destination);
        if(!collManager.checkRight()) { // Movement is NOT possible
            return this.gameBoard;
        }

        this.gameBoard[this.pieceLocation.getX1()][this.pieceLocation.getY1()] = 0;
        this.gameBoard[this.pieceLocation.getX2()][this.pieceLocation.getY2()] = 0;
        this.gameBoard[this.pieceLocation.getX3()][this.pieceLocation.getY3()] = 0;
        this.gameBoard[this.pieceLocation.getX4()][this.pieceLocation.getY4()] = 0;

        this.gameBoard[this.pieceLocation.getX1() + 1][this.pieceLocation.getY1()] = 1;
        this.gameBoard[this.pieceLocation.getX2() + 1][this.pieceLocation.getY2()] = 1;
        this.gameBoard[this.pieceLocation.getX3() + 1][this.pieceLocation.getY3()] = 1;
        this.gameBoard[this.pieceLocation.getX4() + 1][this.pieceLocation.getY4()] = 1;

        this.pieceLocation.setX1(this.pieceLocation.getX1() + 1);
        this.pieceLocation.setX2(this.pieceLocation.getX2() + 1);
        this.pieceLocation.setX3(this.pieceLocation.getX3() + 1);
        this.pieceLocation.setX4(this.pieceLocation.getX4() + 1);

        return this.gameBoard;

    }

    /**
     * Move the current piece one cell down and return the modified game board
     * @return Game board
     */
    int[][] moveDown() {
        // Destination of the piece
        Coordinate destination = new Coordinate(this.pieceLocation.getX1(), this.pieceLocation.getY1() - 1, this.pieceLocation.getX2(), this.pieceLocation.getY2() - 1, this.pieceLocation.getX3(), this.pieceLocation.getY3() - 1, this.pieceLocation.getX4(), this.pieceLocation.getY4() - 1);

        // Check if movement is possible
        CollisionManager collManager = new CollisionManager(this, destination);
        if(!collManager.checkDown()) { // Movement is NOT possible, we need to generate a new piece.
            this.deattachPiece();
            return this.gameBoard;
        }

        // Movement IS possible, delete the piece

        this.gameBoard[this.pieceLocation.getX1()][this.pieceLocation.getY1()] = 0;
        this.gameBoard[this.pieceLocation.getX2()][this.pieceLocation.getY2()] = 0;
        this.gameBoard[this.pieceLocation.getX3()][this.pieceLocation.getY3()] = 0;
        this.gameBoard[this.pieceLocation.getX4()][this.pieceLocation.getY4()] = 0;

        // Re-add the piece one pixel lower

        this.gameBoard[this.pieceLocation.getX1()][this.pieceLocation.getY1() - 1] = 1;
        this.gameBoard[this.pieceLocation.getX2()][this.pieceLocation.getY2() - 1] = 1;
        this.gameBoard[this.pieceLocation.getX3()][this.pieceLocation.getY3() - 1] = 1;
        this.gameBoard[this.pieceLocation.getX4()][this.pieceLocation.getY4() - 1] = 1;

        // Adjust the coordinates to match new values

        this.pieceLocation.setY1(this.pieceLocation.getY1() - 1);
        this.pieceLocation.setY2(this.pieceLocation.getY2() - 1);
        this.pieceLocation.setY3(this.pieceLocation.getY3() - 1);
        this.pieceLocation.setY4(this.pieceLocation.getY4() - 1);


        return this.gameBoard;
    }

    /**
     * When a piece reach the bottom of the board, a new piece needs to be generated.
     */
    public void deattachPiece() {
        this.pieceType =  this.nextPiece;
        Random rand = new Random();
        this.nextPiece = rand.nextInt(7) + 1;
        this.orientation = ORIENTATION_UP;
        this.checkForLoose();
        this.checkForLines(this.gameBoard);
        this.setInitialPosition();

    }

    public void checkForLoose() {
        for(int i = 20; i < 22; i++) {
            for(int y = 0; y != 10; y++) {
                if(this.gameBoard[y][i] != 0) {
                    this.gameOver = true;
                    return;
                }
            }
        }
    }

    /**
     * Check for completed lines on the game board. If a line is completed, the others lines at the top of the completed one must go 1 cell down.
     */
    public void checkForLines(int[][] iGameBoard) {
        boolean completedLine;
        int lowerLine = -1;
        int scoredLines = 0;

        for(int i = 0; i != 22; i++) {
            completedLine = true;
            for(int y = 0; y != 10; y++) {
                if(iGameBoard[y][i] == 0) {
                    completedLine = false;
                }
            }
            if(completedLine) { // If the current line (line nb. i), delete it.
                scoredLines++;
                for(int a = 0; a != 10; a++) {
                    iGameBoard[a][i] = 0;
                }
                if(lowerLine < 0) {
                    lowerLine = i;
                }
            }
        }

        if(lowerLine != -1) {
            for(int i = lowerLine + 1; i != 22; i++) {
                for (int y = 0; y != 10; y++) {
                    iGameBoard[y][i - 1] = iGameBoard[y][i];
                }
            }
        }

        // Add the score

        if(scoredLines == 1) {
            this.score += 40;
        }
        if(scoredLines == 2) {
            this.score += 100;
        }
        if(scoredLines == 3) {
            this.score += 300;
        }
        if(scoredLines == 4) {
            this.score += 1200;
        }

    }

    /**
     * For a given type of piece, sets the original location of piece and its orientation
     */
    public void setInitialPosition() {
        // Initializing start location depending of the piece type
        this.orientation = ORIENTATION_UP;

        switch(this.pieceType) {
            // T block
            case 1:
                this.pieceLocation = new Coordinate(3, 19, 4, 19, 5, 19, 4, 20);
                this.gameBoard[3][19] = 1;
                this.gameBoard[4][19] = 1;
                this.gameBoard[5][19] = 1;
                this.gameBoard[4][20] = 1;

                break;
            // I block
            case 2:
                this.pieceLocation = new Coordinate(3, 19, 4, 19, 5, 19, 6, 19);
                this.gameBoard[3][19] = 1;
                this.gameBoard[4][19] = 1;
                this.gameBoard[5][19] = 1;
                this.gameBoard[6][19] = 1;
                break;
            // O block
            case 3:
                this.pieceLocation = new Coordinate(4, 19, 5, 19, 4, 18, 5, 18);
                this.gameBoard[4][19] = 1;
                this.gameBoard[5][19] = 1;
                this.gameBoard[4][18] = 1;
                this.gameBoard[5][18] = 1;
                break;
            // S block
            case 4:
                this.pieceLocation = new Coordinate(3, 18, 4, 18, 4, 19, 5, 19);

                this.gameBoard[3][18] = 1;
                this.gameBoard[4][18] = 1;
                this.gameBoard[4][19] = 1;
                this.gameBoard[5][19] = 1;
                break;
            // Z block
            case 5:
                this.pieceLocation = new Coordinate(3, 19, 4, 19, 4, 18, 5, 18);

                this.gameBoard[3][19] = 1;
                this.gameBoard[4][19] = 1;
                this.gameBoard[4][18] = 1;
                this.gameBoard[5][18] = 1;
                break;
            // J block
            case 6:
                this.pieceLocation = new Coordinate(3, 19, 3, 18, 4, 18, 5, 18);

                this.gameBoard[3][19] = 1;
                this.gameBoard[3][18] = 1;
                this.gameBoard[4][18] = 1;
                this.gameBoard[5][18] = 1;
                break;
            // L block
            case 7:
                this.pieceLocation = new Coordinate(3, 18, 4, 18, 5, 18, 5, 19);

                this.gameBoard[3][18] = 1;
                this.gameBoard[4][18] = 1;
                this.gameBoard[5][18] = 1;
                this.gameBoard[5][19] = 1;
                break;
        }
    }
}

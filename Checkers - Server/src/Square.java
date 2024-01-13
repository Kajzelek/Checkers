import java.util.PrimitiveIterator;

public class Square {
    private int squareID;
    private int squareRow;
    private int squareCol;
    private boolean filled;
    private int playerID;

    public Square(int squareID, int squareRow, int squareCol, boolean isFilled) {
        this.squareID = squareID;
        this.squareRow = squareRow;
        this.squareCol = squareCol;
        this.setFilled(isFilled);

        if(this.filled){
            this.playerID = CheckersConstants.EMPTY_SQUARES.getValue();
        }

    }

    public int getSquareID() {
        return squareID;
    }

    public void setSquareID(int squareID) {
        this.squareID = squareID;
    }

    public int getSquareRow() {
        return squareRow;
    }

    public void setSquareRow(int squareRow) {
        this.squareRow = squareRow;
    }

    public int getSquareCol() {
        return squareCol;
    }

    public void setSquareCol(int squareCol) {
        this.squareCol = squareCol;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

}

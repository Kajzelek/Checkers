public class Square{
    private int SquareID;
    private int SquareRow;
    private int SquareCol;
    private boolean isKing;
    private boolean filled;
    private boolean selected;
    private boolean isPossibleToMove;
    private int playerID;

    public Square(int var1, int var2, int var3, boolean var4) {
        this.SquareID = var1;
        this.SquareRow = var2;
        this.SquareCol = var3;
        this.setFilled(var4);
        if (this.filled) {
            this.playerID = CheckersConstants.EMPTY_SQUARES.getValue();
        }

        this.isKing = false;
        this.selected = false;
        this.isPossibleToMove = false;
    }

    public boolean getIsFilled() {
        return this.filled;
    }

    private void setFilled(boolean var1) {
        this.filled = var1;
    }

    public void setPlayerID(int var1) {
        this.playerID = var1;
    }

    public int getPlayerID() {
        return this.playerID;
    }

    public int getSquareID() {
        return this.SquareID;
    }

    public int getSquareRow() {
        return this.SquareRow;
    }

    public int getSquareCol() {
        return this.SquareCol;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean var1) {
        this.selected = var1;
    }

    public boolean isPossibleToMove() {
        return this.isPossibleToMove;
    }

    public void setPossibleToMove(boolean var1) {
        this.isPossibleToMove = var1;
    }

    public boolean isOpponentSquare() {
        return this.playerID != SessionVariable.myID.getValue() && this.playerID != CheckersConstants.EMPTY_SQUARES.getValue();
    }

    public boolean isKing() {
        return this.isKing;
    }

    public void setKing() {
        this.isKing = true;
    }

    public void removeKing() {
        this.isKing = false;
    }
}

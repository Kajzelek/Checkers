import javax.swing.plaf.PanelUI;

public class Game {
    private Square[][] board = new Square[8][8];

    public Game() {

    }

    private void initializeSquares() {
        int x = 0;
        for (int i = 0; i < CheckersConstants.NUM_ROWS.getValue(); i++) {
            boolean area = i % 2 == 1;

            for (int j = 0; j < CheckersConstants.NUM_COLS.getValue(); j++) {
                boolean area2 = area && j % 2 == 0 ? true : !area && j % 2 == 1;
                ++x;
                this.board[i][j] = new Square(x, i, j, area2);
            }
        }
    }

    private void assaignPlayerIDs() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < CheckersConstants.NUM_ROWS.getValue(); j++) {
                if (this.board[i][j].isFilled()) {
                    this.board[i][j].setPlayerID(CheckersConstants.PLAYER_ONE.getValue());
                }
            }
        }

        for (int i = 5; i < 8; i++) {
            for (int j = 0; j < CheckersConstants.NUM_ROWS.getValue(); j++) {
                if (this.board[i][j].isFilled()) {
                    this.board[i][j].setPlayerID(CheckersConstants.PLAYER_TWO.getValue());
                }
            }
        }
    }


    public void printSquareDetails(){
        for (int i = 0; i < CheckersConstants.NUM_ROWS.getValue(); i++) {
            for (int j = 0; j < CheckersConstants.NUM_COLS.getValue(); j++) {
                int square = this.board[i][j].getSquareID();
                System.out.println("" + square + "--" + this.board[i][j].getSquareRow() + "--" + this.board[i][j].getSquareCol() + this.board[i][j].getPlayerID());
            }
        }
    }

    public Square getSquare(int squareID){
        Square[][] board = this.board;
        int boardLength = board.length;

        for(int row=0; row<boardLength; row++){
            Square[] CurrentRow = board[row];
            Square[] y = CurrentRow;
            int CurrentRowLength = CurrentRow.length;

            for(int i=0; i<CurrentRowLength; i++){
                Square square = y[i];
                if(square.getSquareID() == squareID){
                    return square;
                }
            }
        }
        return null;
    }

    public void printAvailableSquareDetails() {
        int PlayerOneAvailableSquares = 0;
        int PlayerTwoAvailableSquares = 0;

        for(int i = 0; i < CheckersConstants.NUM_ROWS.getValue(); i++) {
            for(int j = 0; j < CheckersConstants.NUM_COLS.getValue(); j++) {
                if (this.board[i][j].getPlayerID() == 1) {
                    PlayerOneAvailableSquares++;
                }

                if (this.board[i][j].getPlayerID() == 2) {
                    PlayerTwoAvailableSquares++;
                }
            }
        }

        System.out.println("Player 1 has " + PlayerOneAvailableSquares);
        System.out.println("Player 2 has " + PlayerTwoAvailableSquares);
    }

    public boolean isOver() {
        int PlayerOneAvailableSquares = 0;
        int PlayerTwoAvailableSquares = 0;

        for(int i = 0; i < CheckersConstants.NUM_ROWS.getValue(); i++) {
            for(int j = 0; j < CheckersConstants.NUM_COLS.getValue(); j++) {
                if (this.board[i][j].getPlayerID() == 1) {
                    PlayerOneAvailableSquares++;
                }

                if (this.board[i][j].getPlayerID() == 2) {
                    PlayerTwoAvailableSquares++;
                }
            }
        }

        if (PlayerOneAvailableSquares != 0 && PlayerTwoAvailableSquares != 0) {
            return false;
        } else {
            return true;
        }
    }




}




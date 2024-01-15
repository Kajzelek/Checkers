import java.io.PrintStream;
import java.util.LinkedList;

public class Board{
    private Square[][] squares = new Square[8][8];

    public Board() {
        this.setSquares();
        this.assignPlayerIDs();
    }

    private void setSquares() {
        int var3 = 0;

        for(int var4 = 0; var4 < CheckersConstants.NUM_ROWS.getValue(); ++var4) {
            boolean var1 = var4 % 2 == 1;

            for(int var5 = 0; var5 < CheckersConstants.NUM_COLS.getValue(); ++var5) {
                boolean var2 = var1 && var5 % 2 == 0 ? true : !var1 && var5 % 2 == 1;
                ++var3;
                this.squares[var4][var5] = new Square(var3, var4, var5, var2);
            }
        }

    }

    public Square[][] getSquares() {
        return this.squares;
    }

    public int getTotlaSquares() {
        return this.squares.length;
    }

    public void printSquareDetails() {
        for(int var1 = 0; var1 < CheckersConstants.NUM_ROWS.getValue(); ++var1) {
            for(int var2 = 0; var2 < CheckersConstants.NUM_COLS.getValue(); ++var2) {
                PrintStream var10000 = System.out;
                int var10001 = this.squares[var1][var2].getSquareID();
                var10000.println("" + var10001 + " --" + this.squares[var1][var2].isPossibleToMove());
            }
        }

    }

    private void assignPlayerIDs() {
        int var1;
        int var2;
        for(var1 = 0; var1 < 3; ++var1) {
            for(var2 = 0; var2 < CheckersConstants.NUM_COLS.getValue(); ++var2) {
                if (this.squares[var1][var2].getIsFilled()) {
                    this.squares[var1][var2].setPlayerID(CheckersConstants.PLAYER_ONE.getValue());
                }
            }
        }

        for(var1 = 5; var1 < 8; ++var1) {
            for(var2 = 0; var2 < CheckersConstants.NUM_COLS.getValue(); ++var2) {
                if (this.squares[var1][var2].getIsFilled()) {
                    this.squares[var1][var2].setPlayerID(CheckersConstants.PLAYER_TWO.getValue());
                }
            }
        }

    }

    public LinkedList<Square> findPlayableSquares(Square var1) {
        LinkedList var2 = new LinkedList();
        int var3 = var1.getSquareRow();
        int var4 = var1.getSquareCol();
        int var5 = var1.getPlayerID() == 1 ? var3 + 1 : var3 - 1;
        this.twoFrontSquares(var2, var5, var4);
        this.crossJumpFront(var2, var1.getPlayerID() == 1 ? var5 + 1 : var5 - 1, var4, var5);
        if (var1.isKing()) {
            var5 = var1.getPlayerID() == 1 ? var3 - 1 : var3 + 1;
            this.twoFrontSquares(var2, var5, var4);
            this.crossJumpFront(var2, var1.getPlayerID() == 1 ? var5 - 1 : var5 + 1, var4, var5);
        }

        return var2;
    }

    private void twoFrontSquares(LinkedList<Square> var1, int var2, int var3) {
        if (var2 >= 0 && var2 < 8) {
            Square var4;
            if (var3 >= 0 && var3 < 7) {
                var4 = this.squares[var2][var3 + 1];
                if (var4.getPlayerID() == 0) {
                    var4.setPossibleToMove(true);
                    var1.add(var4);
                }
            }

            if (var3 > 0 && var3 <= 8) {
                var4 = this.squares[var2][var3 - 1];
                if (var4.getPlayerID() == 0) {
                    var4.setPossibleToMove(true);
                    var1.add(var4);
                }
            }
        }

    }

    private void crossJumpFront(LinkedList<Square> var1, int var2, int var3, int var4) {
        if (var2 >= 0 && var2 < 8) {
            int var5;
            Square var6;
            if (var3 >= 0 && var3 < 6) {
                var6 = this.squares[var2][var3 + 2];
                var5 = (var3 + var3 + 2) / 2;
                if (var6.getPlayerID() == 0 && this.isOpponentInbetween(var4, var5)) {
                    var6.setPossibleToMove(true);
                    var1.add(var6);
                }
            }

            if (var3 > 1 && var3 <= 7) {
                var6 = this.squares[var2][var3 - 2];
                var5 = (var3 + var3 - 2) / 2;
                if (var6.getPlayerID() == 0 && this.isOpponentInbetween(var4, var5)) {
                    var6.setPossibleToMove(true);
                    var1.add(var6);
                }
            }
        }

    }

    private boolean isOpponentInbetween(int var1, int var2) {
        return this.squares[var1][var2].isOpponentSquare();
    }
}

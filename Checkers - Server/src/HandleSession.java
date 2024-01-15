import java.net.Socket;

public class HandleSession implements Runnable{
    private Game checkers;
    private Player player1;
    private Player player2;
    private boolean continueToPlay = true;

    public HandleSession(Socket playerOneSocket, Socket playerTwoSocket) {
        this.player1 = new Player(CheckersConstants.PLAYER_ONE.getValue(), playerOneSocket);
        this.player2 = new Player(CheckersConstants.PLAYER_TWO.getValue(), playerTwoSocket);
        this.checkers = new Game();
    }

    public void run(){
        try {
            this.player1.sendData(1);

            while(this.continueToPlay) {
                int var1 = this.player1.receiveData();
                int var2 = this.player1.receiveData();
                this.checkStatus(var1, var2);
                this.updateGameModel(var1, var2);
                if (this.checkers.isOver()) {
                    this.player2.sendData(CheckersConstants.LOSE.getValue());
                }

                int var3 = this.player2.sendData(var1);
                int var4 = this.player2.sendData(var2);
                this.checkStatus(var3, var4);
                if (this.checkers.isOver()) {
                    this.player1.sendData(CheckersConstants.WIN.getValue());
                    this.continueToPlay = false;
                    break;
                }

                System.out.println("after break");
                var1 = this.player2.receiveData();
                var2 = this.player2.receiveData();
                this.checkStatus(var1, var2);
                this.updateGameModel(var1, var2);
                if (this.checkers.isOver()) {
                    this.player1.sendData(CheckersConstants.LOSE.getValue());
                }

                var3 = this.player1.sendData(var1);
                var4 = this.player1.sendData(var2);
                this.checkStatus(var3, var4);
                if (this.checkers.isOver()) {
                    this.player2.sendData(CheckersConstants.WIN.getValue());
                    this.continueToPlay = false;
                    break;
                }

                System.out.println("second break");
            }

        } catch (Exception var5) {
            System.out.println("Connection is being closed");
            if (this.player1.isOnline()) {
                this.player1.closeConnection();
            }

            if (this.player2.isOnline()) {
                this.player2.closeConnection();
            }

        }
    }

    private void checkStatus(int var1, int var2) throws Exception {
        if (var1 == 99 || var2 == 99) {
            throw new Exception("Connection is lost");
        }
    }


    private void updateGameModel(int var1, int var2) {
        Square var3 = this.checkers.getSquare(var1);
        Square var4 = this.checkers.getSquare(var2);
        var4.setPlayerID(var3.getPlayerID());
        var3.setPlayerID(CheckersConstants.EMPTY_SQUARES.getValue());
        this.checkCrossJump(var3, var4);
    }

    private void checkCrossJump(Square square1, Square square2) {
        if (Math.abs(square1.getSquareRow() - square2.getSquareRow()) == 2) {
            int var3 = (square1.getSquareRow() + square2.getSquareRow()) / 2;
            int var4 = (square1.getSquareCol() + square2.getSquareCol()) / 2;
            Square var5 = this.checkers.getSquare(var3 * 8 + var4 + 1);
            var5.setPlayerID(CheckersConstants.EMPTY_SQUARES.getValue());
        }

    }


}

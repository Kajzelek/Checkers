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
        try{
            this.player1.sendData(1);

            while(this.continueToPlay){

            }
        }
    }



}

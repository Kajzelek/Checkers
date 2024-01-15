import java.awt.Component;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.JOptionPane;

public class Controller implements Runnable {
    private boolean continueToPlay;
    private boolean waitingForAction;
    private boolean isOver;
    private DataInputStream fromServer;
    private DataOutputStream toServer;
    private BoardPanel boardPanel;
    private Player player;
    private LinkedList<Square> selectedSquares;
    private LinkedList<Square> playableSquares;

    public Controller(Player var1, DataInputStream var2, DataOutputStream var3) {
        this.player = var1;
        this.fromServer = var2;
        this.toServer = var3;
        this.selectedSquares = new LinkedList();
        this.playableSquares = new LinkedList();
    }

    public void setBoardPanel(BoardPanel var1) {
        this.boardPanel = var1;
    }

    public void run() {
        this.continueToPlay = true;
        this.waitingForAction = true;
        this.isOver = false;

        try {
            if (this.player.getPlayerID() == CheckersConstants.PLAYER_ONE.getValue()) {
                this.fromServer.readInt();
                this.player.setMyTurn(true);
            }

            while(this.continueToPlay && !this.isOver) {
                if (this.player.getPlayerID() == CheckersConstants.PLAYER_ONE.getValue()) {
                    this.waitForPlayerAction();
                    if (!this.isOver) {
                        this.receiveInfoFromServer();

                    }
                } else if (this.player.getPlayerID() == CheckersConstants.PLAYER_TWO.getValue()) {
                    this.receiveInfoFromServer();
                    if (!this.isOver) {
                        this.waitForPlayerAction();
                    }
                }
            }

            if (this.isOver) {
                JOptionPane.showMessageDialog((Component)null, "Game is over", "Information", 1, (Icon)null);
                System.exit(0);
            }
        } catch (IOException var2) {
            JOptionPane.showMessageDialog((Component)null, "Connection lost", "Error", 0, (Icon)null);
            System.exit(0);
        } catch (InterruptedException var3) {
            JOptionPane.showMessageDialog((Component)null, "Connection interrupted", "Error", 0, (Icon)null);
        }

    }

    private void receiveInfoFromServer() throws IOException {
        this.player.setMyTurn(false);
        int var1 = this.fromServer.readInt();
        int var2;
        if (var1 == CheckersConstants.LOSE.getValue()) {
            var1 = this.fromServer.readInt();
            var2 = this.fromServer.readInt();
            this.updateReceivedInfo(var1, var2);
            this.isOver = true;
        } else if (var1 == CheckersConstants.WIN.getValue()) {
            this.isOver = true;
            this.continueToPlay = false;
        } else {
            var2 = this.fromServer.readInt();
            this.updateReceivedInfo(var1, var2);
        }

    }

    private void sendMove(Square var1, Square var2) throws IOException {
        this.toServer.writeInt(var1.getSquareID());
        this.toServer.writeInt(var2.getSquareID());
    }

    private void waitForPlayerAction() throws InterruptedException {
        this.player.setMyTurn(true);

        while(this.waitingForAction) {
            Thread.sleep(100L);
        }

        this.waitingForAction = true;
    }

    public void move(Square var1, Square var2) {
        var2.setPlayerID(var1.getPlayerID());
        var1.setPlayerID(CheckersConstants.EMPTY_SQUARES.getValue());
        this.checkCrossJump(var1, var2);
        this.checkKing(var1, var2);
        this.squareDeselected();
        this.waitingForAction = false;

        try {
            this.sendMove(var1, var2);
        } catch (IOException var4) {
            System.out.println("Sending failed");
        }

    }

    public void squareSelected(Square var1) {
        if (this.selectedSquares.isEmpty()) {
            this.addToSelected(var1);
        } else if (this.selectedSquares.size() >= 1) {
            if (this.playableSquares.contains(var1)) {
                this.move((Square)this.selectedSquares.getFirst(), var1);
            } else {
                this.squareDeselected();
                this.addToSelected(var1);
            }
        }

    }

    private void addToSelected(Square var1) {
        var1.setSelected(true);
        this.selectedSquares.add(var1);
        this.getPlayableSquares(var1);
    }

    public void squareDeselected() {
        Iterator var1 = this.selectedSquares.iterator();

        Square var2;
        while(var1.hasNext()) {
            var2 = (Square)var1.next();
            var2.setSelected(false);
        }

        this.selectedSquares.clear();
        var1 = this.playableSquares.iterator();

        while(var1.hasNext()) {
            var2 = (Square)var1.next();
            var2.setPossibleToMove(false);
        }

        this.playableSquares.clear();
        this.boardPanel.repaintPanels();
    }

    private void getPlayableSquares(Square var1) {
        this.playableSquares.clear();
        this.playableSquares = this.boardPanel.getPlayableSquares(var1);
        Iterator var2 = this.playableSquares.iterator();

        while(var2.hasNext()) {
            Square var3 = (Square)var2.next();
            System.out.println(var3.getSquareID());
        }

        this.boardPanel.repaintPanels();
    }

    public boolean isHisTurn() {
        return this.player.isMyTurn();
    }

    private void checkCrossJump(Square var1, Square var2) {
        if (Math.abs(var1.getSquareRow() - var2.getSquareRow()) == 2) {
            int var3 = (var1.getSquareRow() + var2.getSquareRow()) / 2;
            int var4 = (var1.getSquareCol() + var2.getSquareCol()) / 2;
            Square var5 = this.boardPanel.getSquare(var3 * 8 + var4 + 1);
            var5.setPlayerID(CheckersConstants.EMPTY_SQUARES.getValue());
            var5.removeKing();
        }

    }

    private void checkKing(Square var1, Square var2) {
        if (var1.isKing()) {
            var2.setKing();
            var1.removeKing();
        } else if (var2.getSquareRow() == 7 && var2.getPlayerID() == CheckersConstants.PLAYER_ONE.getValue()) {
            var2.setKing();
        } else if (var2.getSquareRow() == 0 && var2.getPlayerID() == CheckersConstants.PLAYER_TWO.getValue()) {
            var2.setKing();
        }

    }

    private void updateReceivedInfo(int var1, int var2) {
        Square var3 = this.boardPanel.getSquare(var1);
        Square var4 = this.boardPanel.getSquare(var2);
        var4.setPlayerID(var3.getPlayerID());
        var3.setPlayerID(CheckersConstants.EMPTY_SQUARES.getValue());
        this.checkCrossJump(var3, var4);
        this.checkKing(var3, var4);
        this.boardPanel.repaintPanels();
    }
}

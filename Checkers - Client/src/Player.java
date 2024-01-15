public class Player {

    private String name;
    private int playerID;
    private boolean myTurn;

    public Player(String var1) {
        this.name = var1;
        this.setMyTurn(false);
    }

    public String getName() {
        return this.name;
    }

    public int getPlayerID() {
        return this.playerID;
    }

    public void setPlayerID(int var1) {
        this.playerID = var1;
        SessionVariable.myID.setValue(var1);
    }

    public boolean isMyTurn() {
        return this.myTurn;
    }

    public void setMyTurn(boolean var1) {
        this.myTurn = var1;
    }

}

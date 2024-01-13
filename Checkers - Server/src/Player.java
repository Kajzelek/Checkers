import java.util.PropertyResourceBundle;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
public class Player {

    private int PlayerID;
    private Socket socket;
    private DataInputStream fromPlayer;
    private DataOutputStream toPlayer;

    public Player(int PlayerID, Socket socket){
        this.PlayerID = PlayerID;
        this.socket = socket;

        try {
            this.fromPlayer = new DataInputStream(this.socket.getInputStream());
            this.toPlayer = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int sendData(int playerSocket) {
        try {
            this.toPlayer.writeInt(playerSocket);
            return 1;
        } catch (IOException e) {
            System.out.println("sending: Player not found");
            return 99;
        }
    }

    public int receiveData() {
        boolean var1 = false;

        try {
            int data = this.fromPlayer.readInt();
            return data;
        } catch (IOException e) {
            System.out.println("Waiting: No respond from Player");
            return 99;
        }
    }

    public void closeConnection() {
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isOnline() {
        return this.socket.isConnected();
    }




}

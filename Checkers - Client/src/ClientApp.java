import java.awt.Component;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class ClientApp extends JFrame{
    private static final long serialVersionUID = 1L;
    private String address;
    private int port;
    private Player player;
    private BoardPanel boardPanel;
    private Socket connection;
    private DataInputStream fromServer;
    private DataOutputStream toServer;

    public ClientApp() {
        try {
            PropertyMenager var1 = PropertyMenager.getInstance();
            this.address = var1.getAddress();
            this.port = var1.getPort();
            String var2 = JOptionPane.showInputDialog((Component)null, "Enter your name to Connect", "Connect to Server", 2);
            if (var2 != null && var2.length() > 0) {
                this.player = new Player(var2);
                this.connect();
            } else {
                JOptionPane.showMessageDialog((Component)null, "Please enter valid name", "Error", 0, (Icon)null);
                System.exit(0);
            }
        } catch (Exception var3) {
            JOptionPane.showMessageDialog((Component)null, "Please enter valid IPv4-Address", "Error", 0, (Icon)null);
            System.exit(0);
        }

    }

    private void connect() {
        try {
            this.connection = new Socket(this.address, this.port);
            this.fromServer = new DataInputStream(this.connection.getInputStream());
            this.toServer = new DataOutputStream(this.connection.getOutputStream());
            this.player.setPlayerID(this.fromServer.readInt());
            Controller var1 = new Controller(this.player, this.fromServer, this.toServer);
            this.setup(var1);
            (new Thread(var1)).start();
        } catch (UnknownHostException var2) {
            JOptionPane.showMessageDialog((Component)null, "Internal Server Error - Check your IPv4-Address", "Error", 0, (Icon)null);
            System.exit(0);
        } catch (IOException var3) {
            JOptionPane.showMessageDialog((Component)null, "Couldn't connect to Server. Please try again", "Error", 0, (Icon)null);
            System.exit(0);
        }

    }

    private void setup(Controller var1) {
        MyMouseListener var2 = new MyMouseListener();
        var2.setController(var1);
        this.boardPanel = new BoardPanel(var2);
        var1.setBoardPanel(this.boardPanel);
        this.add(this.boardPanel);
    }
}

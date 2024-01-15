import java.awt.BorderLayout;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class ServerApp extends JFrame{
    private static final long serialVersionUID = 1L;
    private JScrollPane scroll;
    private JTextArea information;
    private JLabel title;
    private ServerSocket serverSocket;
    int sessionNo;

    public ServerApp() {
        BorderLayout var1 = new BorderLayout();
        this.setLayout(var1);
        this.title = new JLabel("Server");
        this.information = new JTextArea();
        this.scroll = new JScrollPane(this.information);
        this.add(this.title, "North");
        this.add(this.scroll, "Center");
    }

    public void startRunning() {
        try {
            PropertyMenager var1 = PropertyMenager.getInstance();
            int var2 = var1.getPort();
            this.serverSocket = new ServerSocket(var2);
            this.information.append(this.serverSocket.getInetAddress().getHostAddress());
            JTextArea var10000 = this.information;
            String var10001 = String.valueOf(new Date());
            var10000.append(var10001 + ":- Server start at port " + var2 + " \n");
            this.sessionNo = 1;

            while(true) {
                var10000 = this.information;
                var10001 = String.valueOf(new Date());
                var10000.append(var10001 + ":- Session " + this.sessionNo + " is started\n");
                Socket var3 = this.serverSocket.accept();
                var10000 = this.information;
                Date var7 = new Date();
                var10000.append(String.valueOf(var7) + ":- player1 joined at");
                this.information.append(var3.getInetAddress().getHostAddress() + "\n");
                (new DataOutputStream(var3.getOutputStream())).writeInt(CheckersConstants.PLAYER_ONE.getValue());
                Socket var4 = this.serverSocket.accept();
                var10000 = this.information;
                var7 = new Date();
                var10000.append(String.valueOf(var7) + ":- player2 joined at");
                this.information.append(var4.getInetAddress().getHostAddress() + "\n");
                (new DataOutputStream(var4.getOutputStream())).writeInt(CheckersConstants.PLAYER_TWO.getValue());
                ++this.sessionNo;
                HandleSession var5 = new HandleSession(var3, var4);
                (new Thread(var5)).start();
            }
        } catch (Exception var6) {
            var6.printStackTrace();
            System.exit(0);
        }
    }



}



public class ServerMain {
    public ServerMain() {
    }

    public static void main(String[] var0) {
        ServerApp var1 = new ServerApp();
        var1.setSize(400, 250);
        var1.setVisible(true);
        var1.setTitle("Checkers Server");
        var1.setDefaultCloseOperation(3);
        var1.startRunning();
    }
}

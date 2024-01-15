public class ClientMain {
    public ClientMain() {
    }

    public static void main(String[] var0) {
        ClientApp var1 = new ClientApp();
        var1.setTitle("Checkers");
        var1.pack();
        var1.setVisible(true);
        var1.setLocation(250, 150);
        var1.setDefaultCloseOperation(3);
    }
}

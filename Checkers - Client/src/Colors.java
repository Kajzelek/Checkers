import java.awt.Color;

public enum Colors {
    BLACK(Color.BLACK),
    WHITE(Color.WHITE),
    RED(Color.RED),
    ORANGE(new Color(255, 144, 0)),
    PURPLE(new Color(128, 100, 162)),
    YELLOW(Color.YELLOW);

    private Color color;

    private Colors(Color var3) {
        this.color = var3;
    }

    public Color getColor() {
        return this.color;
    }

    public static Color getMyDefaultColor(int var0) {
        if (var0 == 1) {
            return RED.getColor();
        } else {
            return var0 == 2 ? ORANGE.getColor() : null;
        }
    }

    public static Color getFocusedColor(int var0) {
        if (var0 == 1) {
            return PURPLE.getColor();
        } else {
            return var0 == 2 ? YELLOW.getColor() : null;
        }
    }
}

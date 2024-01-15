import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class SquarePanel extends JPanel{
    private static final long serialVersionUID = 1L;
    private Square square;
    private Border defaultBorder;
    private Border thickBorder;
    private boolean focused;
    private MouseHandler handler;

    public SquarePanel(Square var1) {
        this.defaultBorder = BorderFactory.createEtchedBorder(0, Color.black, Color.gray);
        this.thickBorder = BorderFactory.createLineBorder(Colors.PURPLE.getColor(), 5);
        this.square = var1;
        this.focused = false;
        this.handler = new MouseHandler();
        this.setListener();
    }

    protected void paintComponent(Graphics var1) {
        Graphics2D var2 = (Graphics2D)var1;
        super.paintComponents(var2);
        var2.setColor(Colors.BLACK.getColor());
        if (this.square.getIsFilled()) {
            var2.fillRect(0, 0, this.getWidth(), this.getHeight());
        }

        int var3 = this.square.getPlayerID();
        if (this.isClicked()) {
            var2.setColor(Colors.getFocusedColor(var3));
            this.paint(var2);
        } else if (var3 == 1 || var3 == 2) {
            if (this.focused) {
                var2.setColor(Colors.getFocusedColor(var3));
            } else {
                var2.setColor(Colors.getMyDefaultColor(var3));
            }

            this.paint(var2);
        }

        if (this.square.isPossibleToMove()) {
            if (this.focused) {
                this.setBorder(this.thickBorder);
            } else {
                this.setBorder((Border)null);
            }
        } else {
            this.setBorder((Border)null);
        }

        if (this.square.isKing() && this.square.getIsFilled()) {
            var2.setColor(Color.WHITE);
            var2.setFont(new Font("Arial", 1, 25));
            var2.drawString("K", this.getWidth() / 2 - 8, this.getHeight() / 2 + 8);
        }

    }

    public void setListener() {
        if (!this.square.isPossibleToMove() && this.square.getPlayerID() != SessionVariable.myID.getValue()) {
            this.removeMouseListener(this.handler);
        } else {
            this.removeMouseListener(this.handler);
            this.addMouseListener(this.handler);
        }

    }

    public void setListner(MyMouseListener var1) {
        this.setListener();
        if (!this.square.isPossibleToMove() && this.square.getPlayerID() != SessionVariable.myID.getValue()) {
            this.removeMouseListener(var1);
        } else {
            this.removeMouseListener(var1);
            this.addMouseListener(var1);
        }

    }

    public Square getSquare() {
        return this.square;
    }

    public boolean isClicked() {
        return this.square.isSelected();
    }

    public void resetClicked() {
        this.square.setSelected(false);
    }

    public void setClicked() {
        this.square.setSelected(true);
    }

    private void paint(Graphics2D var1) {
        byte var2 = 24;
        var1.fillOval(var2 / 2, var2 / 2, this.getWidth() - var2, this.getHeight() - var2);
    }

    class MouseHandler extends MouseAdapter {
        MouseHandler() {
        }

        public void mouseEntered(MouseEvent var1) {
            super.mouseEntered(var1);
            SquarePanel.this.focused = true;
            SquarePanel.this.repaint();
        }

        public void mouseExited(MouseEvent var1) {
            super.mouseExited(var1);
            SquarePanel.this.focused = false;
            SquarePanel.this.repaint();
        }

        public void mousePressed(MouseEvent var1) {
        }
    }
}
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.JOptionPane;
public class MyMouseListener extends MouseAdapter{
    private SquarePanel squarePanel;
    private Controller controller;

    public MyMouseListener() {
    }

    public void setController(Controller var1) {
        this.controller = var1;
    }

    public void mousePressed(MouseEvent var1) {
        super.mousePressed(var1);

        try {
            if (this.controller.isHisTurn()) {
                this.ToggleSelectPiece(var1);
            } else {
                JOptionPane.showMessageDialog((Component)null, "Not your turn", "Error", 0, (Icon)null);
            }
        } catch (Exception var3) {
            System.out.println("Error");
        }

    }

    private void ToggleSelectPiece(MouseEvent var1) {
        try {
            this.squarePanel = (SquarePanel)var1.getSource();
            Square var2 = this.squarePanel.getSquare();
            if (var2.isSelected()) {
                System.out.println("deselect - " + var2.getSquareID());
                this.controller.squareDeselected();
            } else {
                System.out.println("select - " + var2.getSquareID());
                this.controller.squareSelected(var2);
            }
        } catch (Exception var3) {
            System.out.println("error");
        }

    }
}

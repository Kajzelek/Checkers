import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JPanel;
public class BoardPanel extends JPanel{
    private static final long serialVersionUID = 1L;
    private Dimension panelSize = new Dimension(720, 720);
    private Board boardModel;
    private MyMouseListener listener;
    private LinkedList<SquarePanel> panels;
    private Square[][] squares;

    public BoardPanel(MyMouseListener var1) {
        this.setPreferredSize(this.panelSize);
        this.setLayout(new GridLayout(8, 8));
        this.boardModel = new Board();
        this.squares = this.boardModel.getSquares();
        this.listener = var1;
        this.panels = new LinkedList();
        this.initializeSquarePanels();
        System.out.println(this.boardModel.getTotlaSquares());
    }

    private void initializeSquarePanels() {
        for(int var1 = 0; var1 < 8; ++var1) {
            for(int var2 = 0; var2 < 8; ++var2) {
                SquarePanel var3 = new SquarePanel(this.squares[var1][var2]);
                if (var3.getSquare().isPossibleToMove() || var3.getSquare().getPlayerID() == SessionVariable.myID.getValue()) {
                    var3.addMouseListener(this.listener);
                }

                this.panels.add(var3);
                this.add(var3);
            }
        }

    }

    public void repaintPanels() {
        Iterator var1 = this.panels.iterator();

        while(var1.hasNext()) {
            SquarePanel var2 = (SquarePanel)var1.next();
            var2.setListner(this.listener);
        }

        this.repaint();
    }

    public LinkedList<Square> getPlayableSquares(Square var1) {
        return this.boardModel.findPlayableSquares(var1);
    }

    public Square getSquare(int var1) {
        return ((SquarePanel)this.panels.get(var1 - 1)).getSquare();
    }
}

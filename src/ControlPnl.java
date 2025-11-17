import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPnl extends JPanel
{
    JButton newFileBtn;
    JButton addProdBtn;
    JButton quitBtn;

    public ControlPnl()
    {
        setLayout(new GridLayout(1, 3));
        setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));

        newFileBtn = new JButton("New File");
        addProdBtn = new JButton("Add Product");
        quitBtn = new JButton("Quit");

        add(newFileBtn);

        add(addProdBtn);

        add(quitBtn);
    }

    public JButton getNewFileBtn() {
        return newFileBtn;
    }

    public JButton getAddProdBtn() {
        return addProdBtn;
    }

    public JButton getQuitBtn() {
        return quitBtn;
    }
}

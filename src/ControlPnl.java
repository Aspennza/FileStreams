import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Allows the creation of pre-designed JPanel objects with JButton
 * controls for selecting a file path, adding products, and quitting
 * the program (though the ActionListeners are not in this file).
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class ControlPnl extends JPanel
{
    //This JButton is used to switch to a new file path
    JButton newFileBtn;

    //This JButton is used to write products to a file
    JButton addProdBtn;

    //This JButton is used to quit the program
    JButton quitBtn;

    //This JButton instantiates the buttons and gives the panel a layout
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

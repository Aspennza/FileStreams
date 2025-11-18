import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Allows the creation of pre-designed JPanel objects with JButton controls
 * for resetting the RandProductSearch program, searching a selected file,
 * and quitting the program (though the ActionListeners are not
 * in this file).
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class ControlPnl2 extends JPanel
{
    //This JButton is used to reset the program state
    JButton newFileBtn;

    //This JButton is used to search the file for product names
    JButton searchBtn;

    //This JButton is used to quit the program
    JButton quitBtn;

    //This constructor sets the layout and establishes the buttons
    public ControlPnl2()
    {
        setLayout(new GridLayout(1, 3));
        setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));

        newFileBtn = new JButton("Reset Program");
        searchBtn = new JButton("Search File");
        quitBtn = new JButton("Quit");

        add(newFileBtn);

        add(searchBtn);

        add(quitBtn);
    }

    public JButton getNewFileBtn() {
        return newFileBtn;
    }

    public JButton getSearchBtn() {
        return searchBtn;
    }

    public JButton getQuitBtn() {
        return quitBtn;
    }
}

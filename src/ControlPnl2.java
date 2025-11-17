import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPnl2 extends JPanel
{
    JButton newFileBtn;
    JButton searchBtn;
    JButton quitBtn;

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

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
    RandProductSearch searcher;

    public ControlPnl2(RandProductSearch searcher)
    {
        this.searcher = searcher;
        setLayout(new GridLayout(1, 3));
        setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));

        newFileBtn = new JButton("Reset Program");
        searchBtn = new JButton("Search File");
        quitBtn = new JButton("Quit");

        add(newFileBtn);

        newFileBtn.addActionListener((ActionEvent ae) -> {
            //This int tracks whether the user confirmed or denied they wanted to quit the program
            int selection = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the program and choose a new file?", "Reset Program", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            //This algorithm determines whether to quit the program based on the user's input
            if(selection == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Resetting...");
                searcher.resetProgram();
            } else
            {
                JOptionPane.showMessageDialog(null, "The existing file will remain open.");
            }
        });

        add(searchBtn);

        searchBtn.addActionListener((ActionEvent ae) -> {
            searcher.searchFile();
        });

        add(quitBtn);

        quitBtn.addActionListener((ActionEvent ae) -> {
            //This int tracks whether the user confirmed or denied they wanted to quit the program
            int selection = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit? You can press Re-run Program to reset the program.", "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            //This algorithm determines whether to quit the program based on the user's input
            if(selection == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Quitting the program...");
                System.exit(0);
            } else
            {
                JOptionPane.showMessageDialog(null, "The program will remain open.");
            }
        });
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

    public RandProductSearch getSearcher() {
        return searcher;
    }
}

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
    RandProductMaker maker;

    public ControlPnl(RandProductMaker maker)
    {
        this.maker = maker;
        setLayout(new GridLayout(1, 3));
        setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));

        newFileBtn = new JButton("New File");
        addProdBtn = new JButton("Add Product");
        quitBtn = new JButton("Quit");

        add(newFileBtn);

        newFileBtn.addActionListener((ActionEvent ae) -> {
            maker.createNewFile();
        });

        add(addProdBtn);

        addProdBtn.addActionListener((ActionEvent ae) -> {
            maker.addProduct();
        });

        add(quitBtn);

        quitBtn.addActionListener((ActionEvent ae) -> {
            //This int tracks whether the user confirmed or denied they wanted to quit the program
            int selection = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

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

    public JButton getAddProdBtn() {
        return addProdBtn;
    }

    public JButton getQuitBtn() {
        return quitBtn;
    }
}

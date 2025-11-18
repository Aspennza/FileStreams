import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * Allows the creation of pre-designed JPanels with a JLabel
 * and JTextField for counting how many records have been written
 * to a product file.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class CounterPnl extends JPanel
{
    //A JLabel for the countTF
    JLabel countLbl;

    //A JTextField for displaying the number of records entered
    JTextField countTF;

    //This constructor sets the layout and initializes the components
    public CounterPnl()
    {
        setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));

        //These GridBagConstraints are changed only slightly and shared among the JLabel and JTextField
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 10, 10, 10);

        countLbl = new JLabel("# of records entered: ");
        add(countLbl, gbc);

        countTF = new JTextField(15);
        countTF.setEditable(false);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(countTF, gbc);
    }

    /**
     * This method updates the in the countTF by accepting an int
     * @param count the number to be put in the countTF
     */
    public void updateCount(int count) {
        countTF.setText("" + count);
    }

    public JLabel getCountLbl() {
        return countLbl;
    }

    public JTextField getCountTF() {
        return countTF;
    }
}

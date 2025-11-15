import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class CounterPnl extends JPanel
{
    JLabel countLbl;
    JTextField countTF;

    public CounterPnl()
    {
        setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));

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

    public JLabel getCountLbl() {
        return countLbl;
    }

    public JTextField getCountTF() {
        return countTF;
    }
}

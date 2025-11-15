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
        setLayout(new GridLayout(1, 2));
        setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));

        countLbl = new JLabel("# of records entered: ");
        add(countLbl);

        countTF = new JTextField(15);
        add(countTF);
    }

    public JLabel getCountLbl() {
        return countLbl;
    }

    public JTextField getCountTF() {
        return countTF;
    }
}

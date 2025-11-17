import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class ProductDataPnl extends JPanel
{
    JLabel IDLbl;
    JTextField IDTF;
    JLabel nameLbl;
    JTextField nameTF;
    JLabel descripLbl;
    JTextField descripTF;
    JLabel costLbl;
    JTextField costTF;

    public ProductDataPnl()
    {
        setBorder(new CompoundBorder((new EtchedBorder()), new EmptyBorder(10, 10, 10, 10)));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.gridwidth = 1;
        gbc1.gridheight = 1;
        gbc1.anchor = GridBagConstraints.EAST;
        gbc1.insets = new Insets(10, 10, 10, 10);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        gbc2.gridwidth = 1;
        gbc2.gridheight = 1;
        gbc2.anchor = GridBagConstraints.WEST;
        gbc2.insets = new Insets(10, 10, 10, 10);

        IDLbl = new JLabel("Product ID: ");
        add(IDLbl, gbc1);
        IDTF = new JTextField(30);
        add(IDTF, gbc2);
        nameLbl = new JLabel("Product Name: ");
        gbc1.gridy = 1;
        add(nameLbl, gbc1);
        nameTF = new JTextField(30);
        gbc2.gridy = 1;
        add(nameTF, gbc2);
        descripLbl = new JLabel("Product Description: ");
        gbc1.gridy = 2;
        add(descripLbl, gbc1);
        descripTF = new JTextField(30);
        gbc2.gridy = 2;
        add(descripTF, gbc2);
        costLbl = new JLabel("Product Cost: ");
        gbc1.gridy = 3;
        add(costLbl, gbc1);
        costTF = new JTextField(30);
        gbc2.gridy = 3;
        add(costTF, gbc2);
    }

    public JLabel getIDLbl() {
        return IDLbl;
    }

    public JTextField getIDTF() {
        return IDTF;
    }

    public JLabel getNameLbl() {
        return nameLbl;
    }

    public JTextField getNameTF() {
        return nameTF;
    }

    public JLabel getDescripLbl() {
        return descripLbl;
    }

    public JTextField getDescripTF() {
        return descripTF;
    }

    public JLabel getCostLbl() {
        return costLbl;
    }

    public JTextField getCostTF() {
        return costTF;
    }

    public void clearInputs() {
        IDTF.setText("");
        nameTF.setText("");
        descripTF.setText("");
        costTF.setText("");
    }
}

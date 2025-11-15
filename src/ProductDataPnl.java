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
        setLayout(new GridLayout(4, 2));
        IDLbl = new JLabel("Product ID: ");
        add(IDLbl);
        IDTF = new JTextField(15);
        add(IDTF);
        nameLbl = new JLabel("Product Name: ");
        add(nameLbl);
        nameTF = new JTextField(15);
        add(nameTF);
        descripLbl = new JLabel("Product Description: ");
        add(descripLbl);
        descripTF = new JTextField(30);
        add(descripTF);
        costLbl = new JLabel("Product Cost: ");
        add(costLbl);
        costTF = new JTextField(15);
        add(costTF);
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
}

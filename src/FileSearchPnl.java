import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Allows the creation of JPanels wih a JButton for choosing a file
 * and a text field for entering a search string.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class FileSearchPnl extends JPanel
{
    //A JLabel for the fileTF
    JLabel fileLbl;

    //A JTextField for displaying the filename
    JTextField fileTF;

    //A JButton for choosing a file
    JButton selectBtn;

    //A JLabel for the searchStringTF
    JLabel searchStringLbl;

    //A JTextField for entering a search string
    JTextField searchStringTF;

    //This constructor sets the layout for the panel and establishes the elements inside it
    public FileSearchPnl() {
        setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));

        //GridBagConstraints for the selectBtn
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.gridwidth = 1;
        gbc1.gridheight = 1;
        gbc1.fill = GridBagConstraints.BOTH;

        //GridBagConstraints for the fileLbl
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        gbc2.gridwidth = 1;
        gbc2.gridheight = 1;
        gbc2.fill = GridBagConstraints.NONE;
        gbc2.anchor = GridBagConstraints.EAST;
        gbc2.insets = new Insets(15, 15, 15, 15);

        //GridBagConstraints for the fileTF
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 2;
        gbc3.gridy = 0;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        gbc3.fill = GridBagConstraints.BOTH;
        gbc3.insets = new Insets(15, 15, 15, 15);

        //GridBagConstraints for the searchStringLbl
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 1;
        gbc4.gridy = 1;
        gbc4.gridwidth = 1;
        gbc4.gridheight = 1;
        gbc4.fill = GridBagConstraints.NONE;
        gbc4.anchor = GridBagConstraints.EAST;
        gbc4.insets = new Insets(15, 15, 15, 15);

        //GridBagConstraints for the searchStringTF
        GridBagConstraints gbc5 = new GridBagConstraints();
        gbc5.gridx = 2;
        gbc5.gridy = 1;
        gbc5.gridwidth = 1;
        gbc5.gridheight = 1;
        gbc5.fill = GridBagConstraints.BOTH;
        gbc5.insets = new Insets(15, 15, 15, 15);

        fileLbl = new JLabel("Chosen File:");
        fileTF = new JTextField(30);
        fileTF.setEditable(false);
        selectBtn = new JButton("Choose a file");

        searchStringLbl = new JLabel("Search String:");
        searchStringTF = new JTextField(30);

        add(selectBtn, gbc1);
        add(fileLbl, gbc2);
        add(fileTF, gbc3);
        add(searchStringLbl, gbc4);
        add(searchStringTF, gbc5);
    }

    public JLabel getFileLbl() {
        return fileLbl;
    }

    public JTextField getFileTF() {
        return fileTF;
    }

    public JButton getSelectBtn() {
        return selectBtn;
    }

    public JLabel getSearchStringLbl() {
        return searchStringLbl;
    }

    public JTextField getSearchStringTF() {
        return searchStringTF;
    }
}

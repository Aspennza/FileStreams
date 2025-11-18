import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * Allows the creation of pre-designed JPanel objects for the title panel
 * of a product searcher. Contains a JLabel and a Font.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class TitlePnl2 extends JPanel
{
    //This JLabel serves as the title for the title panel
    JLabel titleLbl;

    //This Font serves as the font of the titleLbl
    Font titleFont;

    //This constructor establishes the titleLbl and titleFont and adds them to the panel
    public TitlePnl2()
    {
        titleLbl = new JLabel("Product Searcher");
        setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));
        titleFont = new Font("Serif", Font.BOLD, 36);
        titleLbl.setFont(titleFont);
        add(titleLbl);
    }

    public JLabel getTitleLbl() {
        return titleLbl;
    }

    public Font getTitleFont() {
        return titleFont;
    }
}

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class TitlePnl2 extends JPanel
{
    JLabel titleLbl;
    Font titleFont;

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

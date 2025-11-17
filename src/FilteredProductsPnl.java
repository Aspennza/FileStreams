import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class FilteredProductsPnl extends JPanel
{
    JLabel filteredProductsLbl;
    JTextArea filteredProductsTA;
    JScrollPane scroller;

    public FilteredProductsPnl() {
        setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));
        setLayout(new BorderLayout());

        filteredProductsLbl = new JLabel("Matching Products:");
        filteredProductsTA = new JTextArea(10, 50);
        filteredProductsTA.setEditable(false);
        scroller = new JScrollPane(filteredProductsTA);

        add(filteredProductsLbl, BorderLayout.NORTH);
        add(scroller, BorderLayout.CENTER);
    }

    public JScrollPane getScroller() {
        return scroller;
    }

    public JTextArea getFilteredProductsTA() {
        return filteredProductsTA;
    }

    public JLabel getFilteredProductsLbl() {
        return filteredProductsLbl;
    }
}

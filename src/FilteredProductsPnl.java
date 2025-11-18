import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * Allows the creation of JPanel objects for displaying a
 * filtered list of products in a JTextArea.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class FilteredProductsPnl extends JPanel
{
    //A JLabel for the filteredProductsTA
    JLabel filteredProductsLbl;

    //A JTextArea for displaying the filtered list
    JTextArea filteredProductsTA;

    //A JScrollPane for scrolling the filteredProductsTA
    JScrollPane scroller;

    //This constructor sets up the layout and initializes the components
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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductDataPnlTest {

    ProductDataPnl pnl;

    @BeforeEach
    void setUp() {
        pnl = new ProductDataPnl();
    }

    @Test
    void testConstructor() {
        assertInstanceOf(GridBagLayout.class, pnl.getLayout());
        assertNotNull(pnl.getIDLbl());
        assertNotNull(pnl.getIDTF());
        assertNotNull(pnl.getNameLbl());
        assertNotNull(pnl.getNameTF());
        assertNotNull(pnl.getDescripLbl());
        assertNotNull(pnl.getDescripTF());
        assertNotNull(pnl.getCostLbl());
        assertNotNull(pnl.getCostTF());
        assertEquals("Product ID: ", pnl.getIDLbl().getText());
        assertEquals("Product Name: ", pnl.getNameLbl().getText());
        assertEquals("Product Description: ", pnl.getDescripLbl().getText());
        assertEquals("Product Cost: ", pnl.getCostLbl().getText());
    }

    @Test
    void clearInputs() {
        pnl.getIDTF().setText("lorem ipsum");
        pnl.getNameTF().setText("lorem ipsum");
        pnl.getDescripTF().setText("lorem ipsum");
        pnl.getCostTF().setText("lorem ipsum");
        pnl.clearInputs();
        assertEquals("", pnl.getIDTF().getText());
        assertEquals("", pnl.getNameTF().getText());
        assertEquals("", pnl.getDescripTF().getText());
        assertEquals("", pnl.getCostTF().getText());
    }
}
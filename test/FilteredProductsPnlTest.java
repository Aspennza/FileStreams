import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FilteredProductsPnlTest {

    FilteredProductsPnl pnl;

    @BeforeEach
    void setUp() {
        pnl = new FilteredProductsPnl();
    }

    @Test
    void testConstructor() {
        assertTrue(pnl.getLayout() instanceof BorderLayout);
        assertNotNull(pnl.getFilteredProductsLbl());
        assertNotNull(pnl.getFilteredProductsTA());
        assertNotNull(pnl.getScroller());
        assertEquals("Matching Products:", pnl.getFilteredProductsLbl().getText());
        assertFalse(pnl.getFilteredProductsTA().isEditable());
    }
}
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ControlPnlTest {

    ControlPnl pnl;

    @BeforeEach
    void setUp() {
        pnl = new ControlPnl();
    }

    @Test
    void testConstructor() {
        int rows = ((GridLayout) pnl.getLayout()).getRows();
        int cols = ((GridLayout) pnl.getLayout()).getColumns();
        assertEquals(1, rows);
        assertEquals(3, cols);
        assertNotNull(pnl.getNewFileBtn());
        assertNotNull(pnl.getAddProdBtn());
        assertNotNull(pnl.getQuitBtn());
        assertEquals("New File", pnl.getNewFileBtn().getText());
        assertEquals("Add Product", pnl.getAddProdBtn().getText());
        assertEquals("Quit", pnl.getQuitBtn().getText());
    }
}
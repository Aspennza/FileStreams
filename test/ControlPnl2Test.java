import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.ldap.Control;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ControlPnl2Test {

    ControlPnl2 pnl;

    @BeforeEach
    void setUp() {
        pnl = new ControlPnl2();
    }

    @Test
    void testConstructor() {
        assertNotNull(pnl.getNewFileBtn());
        assertNotNull(pnl.getSearchBtn());
        assertNotNull(pnl.getQuitBtn());
        assertEquals("Reset Program", pnl.getNewFileBtn().getText());
        assertEquals("Search File", pnl.getSearchBtn().getText());
        assertEquals("Quit", pnl.getQuitBtn().getText());
        int rows = ((GridLayout) pnl.getLayout()).getRows();
        int cols = ((GridLayout) pnl.getLayout()).getColumns();
        assertEquals(1, rows);
        assertEquals(3, cols);
    }
}
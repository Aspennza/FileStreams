import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CounterPnlTest {

    CounterPnl pnl;

    @BeforeEach
    void setUp() {
        pnl = new CounterPnl();
    }

    @Test
    void testConstructor() {
        assertInstanceOf(GridBagLayout.class, pnl.getLayout());
        assertNotNull(pnl.getCountLbl());
        assertEquals("# of records entered: ", pnl.getCountLbl().getText());
        assertNotNull(pnl.getCountTF());
        assertFalse(pnl.getCountTF().isEditable());
    }

    @Test
    void updateCount() {
        assertEquals("", pnl.getCountTF().getText());
        pnl.updateCount(3);
        assertEquals("3", pnl.getCountTF().getText());
    }
}
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FileSearchPnlTest {

    FileSearchPnl pnl;

    @BeforeEach
    void setUp() {
        pnl = new FileSearchPnl();
    }

    @Test
    void testConstructor() {
        assertTrue(pnl.getLayout() instanceof GridBagLayout);
        assertNotNull(pnl.getFileLbl());
        assertNotNull(pnl.getFileTF());
        assertNotNull(pnl.getSelectBtn());
        assertNotNull(pnl.getSearchStringLbl());
        assertNotNull(pnl.getSearchStringTF());
        assertEquals("Chosen File:", pnl.getFileLbl().getText());
        assertEquals("Choose a file", pnl.getSelectBtn().getText());
        assertFalse(pnl.getFileTF().isEditable());
        assertEquals("Search String:", pnl.getSearchStringLbl().getText());
    }
}
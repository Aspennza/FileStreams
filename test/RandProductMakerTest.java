import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class RandProductMakerTest {

    private RandProductMaker maker;
    private Path tempFile;

    @BeforeEach
    void setUp() {
        try {
            maker = new RandProductMaker();
            maker.setFilesSaved(0);

            tempFile = Files.createTempFile("testProduct", ".bin");

            maker.setWriter(new ProductWriter(tempFile));

            maker.setProductDataPnl(new ProductDataPnl());
            maker.setCounterPnl(new CounterPnl());

            maker.getProductDataPnl().getIDTF().setText("000001");
            maker.getProductDataPnl().getNameTF().setText("Test Product");
            maker.getProductDataPnl().getDescripTF().setText("A product desc");
            maker.getProductDataPnl().getCostTF().setText("20.25");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        try {
            Files.deleteIfExists(tempFile);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void start() {
        maker.start();
        maker.getFrame().setVisible(false);
        assertEquals(0, maker.getFilesSaved());
        assertNotNull(maker.getWriter());
        assertNotNull(maker.getValidator());
        assertNotNull(maker.getFrame());
        assertNotNull(maker.getTitlePnl());
        assertNotNull(maker.getProductDataPnl());
        assertNotNull(maker.getControlPnl());
        assertNotNull(maker.getCounterPnl());
    }

    @Test
    void addProduct() {
        maker.start();
        maker.getFrame().setVisible(false);
        maker.setWriter(new ProductWriter(tempFile));

        maker.getProductDataPnl().getIDTF().setText("000001");
        maker.getProductDataPnl().getNameTF().setText("name");
        maker.getProductDataPnl().getDescripTF().setText("descrip");
        maker.getProductDataPnl().getCostTF().setText("20.25");

        maker.addProduct();

        assertEquals("", maker.getProductDataPnl().getIDTF().getText());
        assertEquals("", maker.getProductDataPnl().getNameTF().getText());
        assertEquals("", maker.getProductDataPnl().getDescripTF().getText());
        assertEquals("", maker.getProductDataPnl().getCostTF().getText());

        assertEquals(1, maker.getWriter().getRecordsWritten());
    }

    @Test
    void testAddProductUpdatesWriterandCounter() {
        maker.start();
        maker.getFrame().setVisible(false);
        maker.setWriter(new ProductWriter(tempFile));
        maker.getProductDataPnl().getIDTF().setText("000001");
        maker.getProductDataPnl().getNameTF().setText("name");
        maker.getProductDataPnl().getDescripTF().setText("descrip");
        maker.getProductDataPnl().getCostTF().setText("20.25");
        int prevCount = maker.getWriter().getRecordsWritten();
        maker.addProduct();
        int afterCount = maker.getWriter().getRecordsWritten();

        assertEquals(prevCount + 1, afterCount);

        assertEquals(String.valueOf(afterCount), maker.getCounterPnl().getCountTF().getText());

        try {
            assertTrue(Files.size(tempFile) >= maker.getWriter().getRECORD_SIZE());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testResetProgramClearsCounterandWriter() {
        maker.start();
        maker.getFrame().setVisible(false);
        maker.addProduct();
        maker.resetProgram();

        assertEquals(0, maker.getWriter().getRecordsWritten());

        assertEquals("0", maker.getCounterPnl().getCountTF().getText());

        assertEquals("", maker.getProductDataPnl().getIDTF().getText());
        assertEquals("", maker.getProductDataPnl().getNameTF().getText());
        assertEquals("", maker.getProductDataPnl().getDescripTF().getText());
        assertEquals("", maker.getProductDataPnl().getCostTF().getText());
    }

    @Test
    void generateFrame() {
        maker.generateFrame();
        maker.getFrame().setVisible(false);

        assertNotNull(maker.getFrame());
        assertNotNull(maker.getTitlePnl());
        assertNotNull(maker.getProductDataPnl());
        assertNotNull(maker.getControlPnl());
        assertNotNull(maker.getCounterPnl());
        assertEquals(JFrame.EXIT_ON_CLOSE, maker.getFrame().getDefaultCloseOperation());
        assertEquals("Product Maker", maker.getFrame().getTitle());
    }

    @Test
    void testNameUnique() {
        maker.setFilesSaved(0);
        Path first = maker.nameFile();
        maker.setFilesSaved(1);
        Path second = maker.nameFile();

        assertNotEquals(first, second);
        assertTrue(first.toString().endsWith("RandProductData1.bin"));
        assertTrue(second.toString().endsWith("RandProductData2.bin"));
    }
}
import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RandProductSearchTest {

    private RandProductSearch search;

    @BeforeEach
    void setUp() {
        search = new RandProductSearch();
        search.start();
    }

    @Test
    public void searchProducts() {
        try {
            Path tempFile = Files.createTempFile("products", ".bin");

            ProductWriter writer = new ProductWriter(tempFile);
            writer.saveProduct("000001", "Test Product                       ", "A product desc                                                             ", 20.25);

            search.getChooser().resetChooser();
            search.getReader().setCurrentFile(tempFile);
            search.getReader().loadAllProducts(tempFile);

            List<Product> results = search.getReader().searchFile("Test Product");
            assertEquals(1, results.size());
            assertEquals("Test Product", results.get(0).getName());

            Files.deleteIfExists(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void displayResults() {
        Product p = new Product("000001", "Test Product                       ", "A product desc                                                             ", 20.25);
        List<Product> testList = List.of(p);

        search.displayResults(testList);

        String text = search.getFilteredProductsPnl().getFilteredProductsTA().getText();
        assertTrue(text.contains("ID: 000001"));
        assertTrue(text.contains("Name: Test Product"));
        assertTrue(text.contains("Description: A product desc"));
        assertTrue(text.contains("Cost: $20.25"));
    }

    @Test
    public void resetProgram() {
        search.getFileSearchPnl().getFileTF().setText("filler");
        search.getFileSearchPnl().getSearchStringTF().setText("filler");
        search.getFilteredProductsPnl().getFilteredProductsTA().setText("filler");

        search.resetProgram();

        assertEquals("", search.getFileSearchPnl().getFileTF().getText());
        assertEquals("", search.getFileSearchPnl().getSearchStringTF().getText());
        assertEquals("", search.getFilteredProductsPnl().getFilteredProductsTA().getText());

        assertTrue(search.getFileSearchPnl().getSelectBtn().isEnabled());
        assertFalse(search.getControlPnl().getSearchBtn().isEnabled());
        assertFalse(search.getControlPnl().getNewFileBtn().isEnabled());
    }

    @Test
    void start() {
        assertNotNull(search.getReader());
        assertNotNull(search.getChooser());
        assertNotNull(search.getFrame());
        assertNotNull(search.getTitlePnl());
        assertNotNull(search.getFileSearchPnl());
        assertNotNull(search.getFilteredProductsPnl());
        assertNotNull(search.getControlPnl());
        assertFalse(search.getControlPnl().getNewFileBtn().isEnabled());
        assertFalse(search.getControlPnl().getSearchBtn().isEnabled());
    }

    @Test
    void generateFrame() {
        search.generateFrame();
        search.getFrame().setVisible(false);

        assertNotNull(search.getFrame());
        assertNotNull(search.getTitlePnl());
        assertNotNull(search.getFileSearchPnl());
        assertNotNull(search.getFilteredProductsPnl());
        assertNotNull(search.getControlPnl());
        assertEquals(JFrame.EXIT_ON_CLOSE, search.getFrame().getDefaultCloseOperation());
        assertEquals("Product Searcher", search.getFrame().getTitle());
    }
}
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductReaderTest {

    private Path tempFile;

    @BeforeEach
    void setUp() {
        try {
            tempFile = Files.createTempFile("testProducts", ".bin");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(RandomAccessFile randFile = new RandomAccessFile(tempFile.toFile(), "rw")) {
            writeFixedLengthString(randFile, "000001", 6);
            writeFixedLengthString(randFile, "Test Product", 35);
            writeFixedLengthString(randFile, "A product desc", 75);
            randFile.writeDouble(20.25);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        try {
            Files.deleteIfExists(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void loadAllProducts() {
        ProductReader reader = new ProductReader();
        reader.loadAllProducts(tempFile);

        List<Product> products = reader.getProducts();
        assertEquals(1, products.size());

        Product p = products.get(0);
        assertEquals("000001", p.getID().trim());
        assertEquals("Test Product", p.getName().trim());
        assertEquals("A product desc", p.getDescription().trim());
        assertEquals(20.25, p.getCost(), 0.001);
    }

    @Test
    void searchFile()
    {
        ProductReader reader = new ProductReader();
        reader.loadAllProducts(tempFile);

        List<Product> result = reader.searchFile("Test");
        assertEquals(1, result.size());

        result = reader.searchFile("fake text");
        assertEquals(0, result.size());
    }

    private void writeFixedLengthString(RandomAccessFile randFile, String text, int length) {
        byte[] textBytes = text.getBytes(StandardCharsets.UTF_8);
        int i = 0;

        try {
            for (; i < textBytes.length && i < length; i++) {
                randFile.write(textBytes[i]);
            }

            for (; i < length; i++) {
                randFile.write(' ');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void reset() {
        ProductReader reader = new ProductReader();
        reader.loadAllProducts(tempFile);
        reader.reset();
        assertTrue(reader.getProducts().isEmpty());
        assertNull(reader.getCurrentFile());
    }

    @Test
    void setCurrentFile() {
        ProductReader reader = new ProductReader();
        reader.setCurrentFile(tempFile);
        assertEquals(tempFile, reader.getCurrentFile());
    }
}
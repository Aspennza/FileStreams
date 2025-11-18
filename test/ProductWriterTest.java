import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ProductWriterTest {

    private Path tempFile;
    private ProductWriter writer;

    @BeforeEach
    void setUp() {
        try {
            tempFile = Files.createTempFile("product_test", ".bin");
            writer = new ProductWriter(tempFile);
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
    void saveProduct() {
        writer.saveProduct("000001", "Test Product                       ", "A product desc                                                             ", 20.25);

        assertEquals(1, writer.getRecordsWritten());

        try (RandomAccessFile randFile = new RandomAccessFile(tempFile.toFile(), "r"))
        {
            byte[] idBytes = new byte[6];
            randFile.readFully(idBytes);
            String id = new String(idBytes, StandardCharsets.UTF_8);
            assertEquals("000001", id);

            byte[] nameBytes = new byte[35];
            randFile.readFully(nameBytes);
            String name = new String(nameBytes, StandardCharsets.UTF_8).trim();
            assertEquals("Test Product", name);

            byte[] descBytes = new byte[75];
            randFile.readFully(descBytes);
            String desc = new String(descBytes, StandardCharsets.UTF_8).trim();
            assertEquals("A product desc", desc);

            double cost = randFile.readDouble();
            assertEquals(20.25, cost, 0.001);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void reset() {
        writer.saveProduct("000001", "Test Product", "A product desc", 20.25);
        assertEquals(1, writer.getRecordsWritten());

        writer.reset();
        assertEquals(0, writer.getRecordsWritten());
    }
}
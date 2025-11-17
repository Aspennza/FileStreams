import javax.swing.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class ProductWriter
{
    private Path file;
    private int recordsWritten;
    private final int RECORD_SIZE = 124;

    public ProductWriter(Path file)
    {
        this.file = file;
        this.recordsWritten = 0;
    }

    public void saveProduct(String ID, String name, String description, double cost)
    {
        try(RandomAccessFile randFile = new RandomAccessFile(file.toFile(), "rw"))
        {
            randFile.seek(recordsWritten * RECORD_SIZE);
            randFile.write(ID.getBytes(StandardCharsets.UTF_8));
            randFile.write(name.getBytes(StandardCharsets.UTF_8));
            randFile.write(description.getBytes(StandardCharsets.UTF_8));
            randFile.writeDouble(cost);
            recordsWritten++;
        } catch (IOException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "Error writing product data:\n" + e.getMessage(),
                    "File Write Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void reset() {
        recordsWritten = 0;
    }

    public Path getFile() {
        return file;
    }

    public int getRecordsWritten() {
        return recordsWritten;
    }

    public int getRECORD_SIZE() {
        return RECORD_SIZE;
    }
}


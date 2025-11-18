import javax.swing.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

/**
 * Allows the creation of objects for writing Product data to a
 * RandomAccessFile.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class ProductWriter
{
    //This Path stores the location of the file to write to
    private Path file;

    //This int tracks how many records have been written
    private int recordsWritten;

    //This int permanently stores the maximum size of a record
    private final int RECORD_SIZE = 124;

    public ProductWriter(Path file)
    {
        this.file = file;
        this.recordsWritten = 0;
    }

    /**
     * This method writes Product data input by a user into a RandomAccessFile.
     * @param ID the user's Product ID
     * @param name the user's Product name
     * @param description the user's Product description
     * @param cost the user's Product cost
     */
    public void saveProduct(String ID, String name, String description, double cost)
    {
        //This algorithm checks for errors while writing to the file
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

    /**
     * This method resets the program state
     */
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


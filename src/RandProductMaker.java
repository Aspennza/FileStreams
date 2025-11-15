import javax.swing.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class RandProductMaker
{
    private JFrame frame;
    private int filesSaved;
    private ArrayList<Product> products;
    private File workingDirectory;
    private Path file;

    public RandProductMaker()
    {
        filesSaved = 0;
        products = new ArrayList<>();
        workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\RandProductData.bin");
    }


    private Path nameFile()
    {
        return Paths.get(workingDirectory.getPath() + "\\src\\RandProductData" + (filesSaved + 1) + ".bin");
    }
}

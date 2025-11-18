import javax.swing.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Allows the creation of objects for reading a RandomAccessFile,
 * adding product objects to an ArrayList, and searching a file,
 * among other functions.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class ProductReader
{
    //This List<Product> stores the products read from the file
    private List<Product> products;

    //This int tracks the size each record should be
    private final int RECORD_SIZE = 124;

    //This Path stores the location of the current file
    private Path currentFile;

    public ProductReader() {
        products = new ArrayList<>();
    }

    /**
     * This method reads products from a RandomAccessFile and adds them to the products List.
     * Also checks for errors.
     * @param file The Path of the file to be read
     */
    public void loadAllProducts(Path file) {
        products.clear();
        currentFile = file;

        //This algorithm reads the products into the list
        try(RandomAccessFile randFile = new RandomAccessFile(file.toFile(), "r")) {
            long position = 0;
            Product p;
            while ((p = readProductData(randFile, position)) != null) {
                products.add(p);
                position += RECORD_SIZE;
            }

            //This algorithm makes sure the file isn't empty
            if(products.isEmpty()) {
                JOptionPane.showMessageDialog(null, "This file does not appear to contain any products.");
            } else {
                JOptionPane.showMessageDialog(null, products.size() + " products were loaded from the file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "Error reading product data:\n" + e.getMessage(),
                    "File Read Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * This method reads individual data fields from a RandomAccessFile and
     * adds them to a Product object.
     * @param randFile The file to read
     * @param position the position of the cursor
     * @return a Product object containing the data from the file
     */
    private static Product readProductData(RandomAccessFile randFile, long position)
    {
        //This algorithm checks for exceptions while reading
        try {
            if (position >= randFile.length()) {
                return null; //This means there are no more records in the file
            }

            randFile.seek(position);
            byte[] IDBytes = new byte[6];
            randFile.readFully(IDBytes);
            String ID = new String(IDBytes, StandardCharsets.UTF_8).trim();

            byte[] nameBytes = new byte[35];
            randFile.readFully(nameBytes);
            String name = new String(nameBytes, StandardCharsets.UTF_8).trim();

            byte[] descripBytes = new byte[75];
            randFile.readFully(descripBytes);
            String description = new String(descripBytes, StandardCharsets.UTF_8).trim();

            double cost = randFile.readDouble();

            Product product = new Product(ID, name, description, cost);
            return product;
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "Error reading product data:\n" + e.getMessage(),
                    "File Read Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return null; //This means there was a corrupted record
        }
    }

    /**
     * This method accepts a search string and filters the products List
     * by it, returning a List of the Products that match the string.
     * @param searchString the String to search the List by
     * @return a List of filtered products
     */
    public List<Product> searchFile(String searchString)
    {
        List<Product> filteredProducts = new ArrayList<>();

        if(searchString == null || searchString.isEmpty()) return filteredProducts;

        String lowerString = searchString.toLowerCase();

        for (Product p : products)
        {
            if(p.getName().toLowerCase().contains(lowerString))
            {
                filteredProducts.add(p);
            }
        }

        return filteredProducts;
    }

    /**
     * This method resets the program to its original state
     */
    public void reset() {
        products.clear();
        currentFile = null;
    }

    public void setCurrentFile(Path currentFile) {
        this.currentFile = currentFile;
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getRECORD_SIZE() {
        return RECORD_SIZE;
    }

    public Path getCurrentFile() {
        return currentFile;
    }
}

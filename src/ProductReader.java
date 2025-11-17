import javax.swing.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;

public class ProductReader
{
    private ArrayList<Product> products;
    private Path file;
    private final int RECORD_SIZE = 124;

    public void loadAllProducts(Path file) {
        products.clear();

        try(RandomAccessFile randFile = new RandomAccessFile(file.toFile(), "r")) {
            long position = 0;
            Product p;
            while ((p = readProductData(randFile, position)) != null) {
                products.add(p);
                position += RECORD_SIZE;
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

    private static Product readProductData(RandomAccessFile randFile, long position)
    {
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

    public void searchFile()
    {
        if (products.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "You must select a non-empty file before searching.");
            return;
        }

        if(fileSearchPnl.getSearchStringTF().getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "You must enter a search string before searching the file.");
            return;
        }

        if(!filteredProductsPnl.getFilteredProductsTA().getText().isEmpty())
        {
            filteredProductsPnl.getFilteredProductsTA().setText("");
        }

        int productIndex = 1;

        for (Product p : products)
        {
            if(p.getName().toLowerCase().contains(fileSearchPnl.getSearchStringTF().getText().trim().toLowerCase()))
            {
                filteredProductsPnl.getFilteredProductsTA().append("Product " + productIndex + "\nID: " + p.getID() + "\nName: " + p.getName() + "\nDescription: " + p.getDescription() + "\nCost: $" +  String.format("%.2f", p.getCost()) + "\n-----------------------------\n");
                productIndex++;
            }
        }

        JOptionPane.showMessageDialog(null, "Search complete.");
    }
}

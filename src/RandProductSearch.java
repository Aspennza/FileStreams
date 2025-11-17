import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;

public class RandProductSearch
{
    private JFrame frame;
    private TitlePnl2 titlePnl;
    private FileSearchPnl fileSearchPnl;
    private FilteredProductsPnl filteredProductsPnl;
    private ControlPnl2 controlPnl;
    private FileChooserLauncher chooser;
    private Path file;
    private ArrayList<Product> products;
    private int recordsRead;
    private final int RECORD_SIZE = 124;

    public void start() {
        chooser = new FileChooserLauncher();
        products = new ArrayList<>();
        recordsRead = 0;
        generateFrame();
        controlPnl.getNewFileBtn().setEnabled(false);
        JOptionPane.showMessageDialog(null, "Welcome to the Product Searcher! First, pick a file, then enter a search string to filter the file for relevant product names.");
    }

    public void chooseFile()
    {
        Path selectedFile = chooser.chooseFile();

        if(selectedFile != null) {
            file = selectedFile;
            fileSearchPnl.getFileTF().setText(file.getFileName().toString());
            controlPnl.getNewFileBtn().setEnabled(true);
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

    public void loadAllProducts() {
        products.clear();
        recordsRead = 0;

        if(file == null) {
            JOptionPane.showMessageDialog(null, "No file was selected.");
            return;
        }

        try(RandomAccessFile randFile = new RandomAccessFile(file.toFile(), "r")) {
            Product p;
            while ((p = readProductData(randFile, recordsRead * RECORD_SIZE)) != null) {
                products.add(p);
                recordsRead++;
            }

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

    public void resetProgram()
    {
        file = null;
        chooser.resetChooser();
        products.clear();
        recordsRead = 0;
        fileSearchPnl.getFileTF().setText("");
        fileSearchPnl.getSelectBtn().setEnabled(true);
        filteredProductsPnl.getFilteredProductsTA().setText("");
        controlPnl.getNewFileBtn().setEnabled(false);
        fileSearchPnl.getSearchStringTF().setText("");
    }

    private void generateFrame()
    {
        frame = new JFrame();

        //GridBagConstraints for the titlePnl
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.gridwidth = 1;
        gbc1.gridheight = 1;
        gbc1.weightx = 1;
        gbc1.weighty = 0.25;
        gbc1.fill = GridBagConstraints.BOTH;

        //GridBagConstraints for the filePnl
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.gridwidth = 1;
        gbc2.gridheight = 1;
        gbc2.weightx = 1;
        gbc2.weighty = 0.25;
        gbc2.fill = GridBagConstraints.BOTH;

        //GridBagConstraints for the tagPnl
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 0;
        gbc3.gridy = 2;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        gbc3.weightx = 1;
        gbc3.weighty = 0.25;
        gbc3.fill = GridBagConstraints.BOTH;

        //GridBagConstraints for the controlPnl
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 0;
        gbc4.gridy = 3;
        gbc4.gridwidth = 1;
        gbc4.gridheight = 1;
        gbc4.weightx = 1;
        gbc4.weighty = 0.25;
        gbc4.fill = GridBagConstraints.BOTH;

        JPanel mainPnl = new JPanel();

        //This Toolkit is used to find the screen size of the computer running the GUI
        Toolkit kit = Toolkit.getDefaultToolkit();

        //This Dimension stores the screen size
        Dimension screenSize = kit.getScreenSize();

        //This int stores the height of the screen
        int screenHeight = screenSize.height;

        //This int stores the width of the screen
        int screenWidth = screenSize.width;

        mainPnl.setLayout(new GridBagLayout());
        frame.add(mainPnl);

        titlePnl = new TitlePnl2();
        mainPnl.add(titlePnl, gbc1);

        fileSearchPnl = new FileSearchPnl(this);
        mainPnl.add(fileSearchPnl, gbc2);

        filteredProductsPnl = new FilteredProductsPnl();
        mainPnl.add(filteredProductsPnl, gbc3);

        controlPnl = new ControlPnl2(this);
        mainPnl.add(controlPnl, gbc4);

        frame.setSize(screenWidth * 3 / 4, screenHeight * 3 / 4);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tag Extractor");
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public TitlePnl2 getTitlePnl() {
        return titlePnl;
    }

    public FileSearchPnl getFileSearchPnl() {
        return fileSearchPnl;
    }

    public FilteredProductsPnl getFilteredProductsPnl() {
        return filteredProductsPnl;
    }

    public ControlPnl2 getControlPnl() {
        return controlPnl;
    }

    public FileChooserLauncher getChooser() {
        return chooser;
    }

    public Path getFile() {
        return file;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public int getRecordsRead() {
        return recordsRead;
    }
}

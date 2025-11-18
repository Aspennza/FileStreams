import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.*;
import java.nio.file.Path;

/**
 * Allows the creation of objects for orchestrating a search
 * of a RandomAccessFile for products with specific names.
 * Starts the program, displays results, and builds the GUI.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class RandProductSearch
{
    //A JFrame for containing the other GUI elements in the program
    private JFrame frame;

    //A TitlePnl2 containing the logic for formatting the titlePnl
    private TitlePnl2 titlePnl;

    //A FileSearchPnl containing the logic for formatting the fileSearchPnl
    private FileSearchPnl fileSearchPnl;

    //A FilteredProductsPnl containing the logic for formatting the filteredProductsPnl
    private FilteredProductsPnl filteredProductsPnl;

    //A ControlPnl2 containing the logic for formatting the controlPnl
    private ControlPnl2 controlPnl;

    //A FileChooserLauncher for creating and managing the JFileChooser
    private FileChooserLauncher chooser;

    //A ProductReader for reading data from a RandomAccessFile
    private ProductReader reader;

    /**
     * This method launches the program, initializing critical components and establishing the GUI.
     */
    public void start() {
        reader = new ProductReader();
        chooser = new FileChooserLauncher();
        generateFrame();
        setUpButtonActions();
        controlPnl.getNewFileBtn().setEnabled(false);
        controlPnl.getSearchBtn().setEnabled(false);
        JOptionPane.showMessageDialog(null, "Welcome to the Product Searcher! First, pick a file, then enter a search string to filter the file for relevant product names.");
    }

    /**
     * This method resets the program to its original state
     */
    public void resetProgram() {
        reader.reset();
        chooser.resetChooser();
        fileSearchPnl.getFileTF().setText("");
        fileSearchPnl.getSearchStringTF().setText("");
        filteredProductsPnl.getFilteredProductsTA().setText("");
        controlPnl.getNewFileBtn().setEnabled(false);
        fileSearchPnl.getSelectBtn().setEnabled(true);
        controlPnl.getSearchBtn().setEnabled(false);
    }

    /**
     * This method prints the content of a List of Products to the filteredProductsTA.
     * @param results a List of Product objects
     */
    public void displayResults(List<Product> results) {
        filteredProductsPnl.getFilteredProductsTA().setText("");
        int index = 1;

        //This algorithm iterates through the results List and appends the data to the filteredProductsTA
        for(Product p : results) {
            filteredProductsPnl.getFilteredProductsTA().append("Product " + index + "\nID: " + p.getID()
                    + "\nName: " + p.getName()
                    + "\nDescription: " + p.getDescription()
                    + "\nCost: $" + String.format("%.2f", p.getCost())
                    + "\n-----------------------------\n");
            index++;
        }
    }

    /**
     * This method establishes the JFrame, its panels, and its settings.
     */
    public void generateFrame()
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

        //GridBagConstraints for the fileSearchPnl
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.gridwidth = 1;
        gbc2.gridheight = 1;
        gbc2.weightx = 1;
        gbc2.weighty = 0.25;
        gbc2.fill = GridBagConstraints.BOTH;

        //GridBagConstraints for the filteredProductsPnl
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

        fileSearchPnl = new FileSearchPnl();
        mainPnl.add(fileSearchPnl, gbc2);

        filteredProductsPnl = new FilteredProductsPnl();
        mainPnl.add(filteredProductsPnl, gbc3);

        controlPnl = new ControlPnl2();
        mainPnl.add(controlPnl, gbc4);

        frame.setSize(screenWidth * 3 / 4, screenHeight * 3 / 4);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Product Searcher");
        frame.setVisible(true);
    }

    /**
     * This method sets up the ActionListeners for the JButtons in the GUI
     * to maintain separation of concerns (model, view, controller).
     */
    private void setUpButtonActions()
    {
        fileSearchPnl.getSelectBtn().addActionListener((ActionEvent ae) -> {
            Path selectedFile = chooser.chooseFile();

            //This algorithm reads the products from the file
            if(selectedFile != null) {
                reader.setCurrentFile(selectedFile);
                fileSearchPnl.getFileTF().setText(selectedFile.getFileName().toString());
                reader.loadAllProducts(selectedFile);
                fileSearchPnl.getSelectBtn().setEnabled(false);
                controlPnl.getNewFileBtn().setEnabled(true);
                controlPnl.getSearchBtn().setEnabled(true);
            }
        });

        controlPnl.getSearchBtn().addActionListener((ActionEvent ae) -> {
            String searchString = fileSearchPnl.getSearchStringTF().getText().trim();

            //This algorithm checks if the file content is null before searching
            if(reader.getProducts().isEmpty()) {
                JOptionPane.showMessageDialog(null, "You must select a file before searching.");
                return;
            }

            //This algorithm checks if the search string is null before searching
            if (searchString.isEmpty()) {
                JOptionPane.showMessageDialog(null, "You must enter a search string.");
                return;
            }
            displayResults(reader.searchFile(searchString));

            JOptionPane.showMessageDialog(null, "Search complete.");
        });

        controlPnl.getNewFileBtn().addActionListener((ActionEvent ae) -> {
            //This int tracks whether the user confirmed or denied they wanted to pick a new file
            int selection = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the program?", "Reset", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            //This algorithm determines whether to pick a new file based on the user's input
            if(selection == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Resetting the program...");
                resetProgram();
            } else {
                JOptionPane.showMessageDialog(null, "The current file will remain open.");
            }
        });

        controlPnl.getQuitBtn().addActionListener((ActionEvent ae) -> {
            //This int tracks whether the user confirmed or denied they wanted to quit the program
            int selection = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit? You can press Re-run Program to reset the program.", "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            //This algorithm determines whether to quit the program based on the user's input
            if(selection == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Quitting the program...");
                System.exit(0);
            } else
            {
                JOptionPane.showMessageDialog(null, "The program will remain open.");
            }
        });
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

    public ProductReader getReader() {
        return reader;
    }
}

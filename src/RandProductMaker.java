import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Allows the creation of controller objects for orchestrating
 * the creation of RandomAccessFiles of Product objects. Sets up ActionListeners,
 * provides methods for adding products, resetting, etc.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class RandProductMaker
{
    //This JFrame stores the other GUI elements
    private JFrame frame;

    //A TitlePnl object containing the logic for formatting the titlePnl
    private TitlePnl titlePnl;

    //A ProductDataPnl object containing the logic for formatting the productDataPnl
    private ProductDataPnl productDataPnl;

    //A ControlPnl object containing the logic for formatting the controlPnl
    private ControlPnl controlPnl;

    //A CounterPnl object containing the logic for formatting the CounterPnl
    private CounterPnl counterPnl;

    //A ProductWriter for writing the data to the file
    private ProductWriter writer;

    //A ProductValidator for validating user input
    private ProductValidator validator;

    //An int for tracking how many files have been saved
    private int filesSaved;

    /**
     * This method sets up all the preliminary information for making the program run.
     * Creates GUI, sets up writer and validator.
     */
    public void start() {
        filesSaved = 0;
        writer = new ProductWriter(nameFile());
        validator = new ProductValidator();
        generateFrame();
        JOptionPane.showMessageDialog(null, "Welcome to the Product Maker. First, type some data into the product information fields, then click Add Product to add them to a binary file. You can make a new file by clicking New File.");
    }

    /**
     * This method dynamically names the next file based on how many have been saved.
     * @return a Path of where to save the file
     */
    public Path nameFile()
    {
        return Paths.get(System.getProperty("user.dir") + "\\src\\RandProductData" + (filesSaved + 1) + ".bin");
    }

    /**
     * This method takes the data from the productDataPnl,
     * checks it for validity, and saves it to the file.
     */
    public void addProduct()
    {
        String ID = productDataPnl.getIDTF().getText().trim();
        String name = productDataPnl.getNameTF().getText().trim();
        String description = productDataPnl.getDescripTF().getText().trim();

                //This algorithm checks for errors in double parsing and reading the file
                try {
                    String costStr = productDataPnl.getCostTF().getText().trim();
                    double cost = Double.parseDouble(costStr);

                    //This algorithm checks the validity of the input and writes it to a file
                    if(validator.checkValidInput(ID, name, description, costStr)) {
                        Product product = new Product(ID, name, description, cost);

                        ID = product.padID();
                        name = product.padName();
                        description = product.padDescrip();
                        writer.saveProduct(ID, name, description, cost);
                        JOptionPane.showMessageDialog(frame, "The product was saved.");

                        productDataPnl.clearInputs();

                        counterPnl.updateCount(writer.getRecordsWritten());
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "The input entered into the Product Cost field is not a valid number. Please try again.");
                }
    }

    /**
     * This method resets the program state
     */
    public void resetProgram()
    {
        productDataPnl.clearInputs();
        counterPnl.updateCount(0);
        if(writer != null) writer.reset();
    }

    /**
     * This method checks if the user wants to start saving to a new file.
     */
    public void createNewFile()
    {
            //This int tracks whether the user confirmed or denied they wanted to make a new file
            int selection = JOptionPane.showConfirmDialog(null, "Are you sure you want to create a new file?", "New File", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            //This algorithm determines whether to make a new file based on the user's input
            if(selection == JOptionPane.YES_OPTION) {
                filesSaved++;
                writer = new ProductWriter(nameFile());
                resetProgram();
                JOptionPane.showMessageDialog(null, "Creating a new file...");
            } else
            {
                JOptionPane.showMessageDialog(null, "The current file will remain open.");
            }
    }

    /**
     * This method sets up the ActionListeners for the panels to
     * maintain separation of concerns (model, view, controller).
     */
    private void setUpButtonActions()
    {
        controlPnl.getNewFileBtn().addActionListener((ActionEvent ae) -> {
            createNewFile();
        });

        controlPnl.getAddProdBtn().addActionListener((ActionEvent ae) -> {
            addProduct();
        });

        controlPnl.getQuitBtn().addActionListener((ActionEvent ae) -> {
            //This int tracks whether the user confirmed or denied they wanted to quit the program
            int selection = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

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

    /**
     * This method sets up the JFrame, JPanels, and all other relevant settings.
     */
    public void generateFrame() {
        frame = new JFrame();

        //GridBagConstraints for the titlePnl
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.gridwidth = 1;
        gbc1.gridheight = 1;
        gbc1.weightx = 1;
        gbc1.fill = GridBagConstraints.BOTH;

        //GridBagConstraints for the productDataPnl
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.gridwidth = 1;
        gbc2.gridheight = 1;
        gbc2.weightx = 1;
        gbc2.fill = GridBagConstraints.BOTH;

        //GridBagConstraints for the controlPnl
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 0;
        gbc3.gridy = 2;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        gbc3.weightx = 1;
        gbc3.fill = GridBagConstraints.BOTH;

        //GridBagConstraints for the counterPnl
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 0;
        gbc4.gridy = 3;
        gbc4.gridwidth = 1;
        gbc4.gridheight = 1;
        gbc4.weightx = 1;
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

        titlePnl = new TitlePnl();
        mainPnl.add(titlePnl, gbc1);

        productDataPnl = new ProductDataPnl();
        mainPnl.add(productDataPnl, gbc2);

        controlPnl = new ControlPnl();
        mainPnl.add(controlPnl, gbc3);

        counterPnl = new CounterPnl();
        mainPnl.add(counterPnl, gbc4);

        frame.setSize(screenWidth * 3 / 4, screenHeight * 3 / 4);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Product Maker");
        frame.setVisible(true);
        setUpButtonActions();
    }

    public JFrame getFrame() {
        return frame;
    }

    public TitlePnl getTitlePnl() {
        return titlePnl;
    }

    public ProductDataPnl getProductDataPnl() {
        return productDataPnl;
    }

    public ControlPnl getControlPnl() {
        return controlPnl;
    }

    public CounterPnl getCounterPnl() {
        return counterPnl;
    }

    public ProductWriter getWriter() {
        return writer;
    }

    public ProductValidator getValidator() {
        return validator;
    }

    public int getFilesSaved() {
        return filesSaved;
    }

    public void setFilesSaved(int filesSaved) {
        this.filesSaved = filesSaved;
    }

    public void setWriter(ProductWriter writer) {
        this.writer = writer;
    }

    public void setCounterPnl(CounterPnl counterPnl) {
        this.counterPnl = counterPnl;
    }

    public void setProductDataPnl(ProductDataPnl productDataPnl) {
        this.productDataPnl = productDataPnl;
    }
}

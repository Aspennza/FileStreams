import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

//add notification instructing how to use the program when it launches
//add notifications for when errors occur
//basically, just review his directions!!!
//make minor optimizations to the code through code review
//check if the program can be made more object-oriented
//create JUnit
//javadoc
//UML

public class RandProductMaker
{
    private JFrame frame;
    private TitlePnl titlePnl;
    private ProductDataPnl productDataPnl;
    private ControlPnl controlPnl;
    private CounterPnl counterPnl;
    private static int filesSaved;
    private File workingDirectory;
    private static Path file;
    private static int recordsWritten;
    private static final int RECORD_SIZE = 124;

    public void start() {
        filesSaved = 0;
        recordsWritten = 0;
        workingDirectory = new File(System.getProperty("user.dir"));
        nameFile();
        generateFrame();
    }

    public void nameFile()
    {
        file = Paths.get(workingDirectory.getPath() + "\\src\\RandProductData" + (filesSaved + 1) + ".bin");
    }

    public void addProduct()
    {
        boolean isValidInput;
        if (file != null)
        {
            if(checkValidInput())
            {
                String ID = productDataPnl.getIDTF().getText().trim();
                String name = productDataPnl.getNameTF().getText().trim();
                String description = productDataPnl.getDescripTF().getText().trim();
                try {
                    double cost = Double.parseDouble(productDataPnl.getCostTF().getText().trim());

                    Product product = new Product(ID, name, description, cost);

                    ID = product.padID();
                    name = product.padName();
                    description = product.padDescrip();

                    saveProductData(ID, name, description, cost);

                    JOptionPane.showMessageDialog(null, "The product was saved.");
                    recordsWritten++;

                    productDataPnl.getIDTF().setText("");
                    productDataPnl.getNameTF().setText("");
                    productDataPnl.getDescripTF().setText("");
                    productDataPnl.getCostTF().setText("");

                    counterPnl.getCountTF().setText("" + recordsWritten);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "The input entered into the Product Cost field is not a valid number. Please try again.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "You must select the New File button before writing products to a file.");
        }
    }

    private static void saveProductData(String ID, String name, String description, double cost)
    {
        try (RandomAccessFile randFile = new RandomAccessFile(file.toFile(), "rw")) {
            randFile.seek(findPosition());

            randFile.write(ID.getBytes(StandardCharsets.UTF_8));
            randFile.write(name.getBytes(StandardCharsets.UTF_8));
            randFile.write(description.getBytes(StandardCharsets.UTF_8));
            randFile.writeDouble(cost);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void resetProgram()
    {
        recordsWritten = 0;
        productDataPnl.getIDTF().setText("");
        productDataPnl.getNameTF().setText("");
        productDataPnl.getDescripTF().setText("");
        productDataPnl.getCostTF().setText("");
        counterPnl.getCountTF().setText("");
    }

    public void createNewFile()
    {
        if (file != null)
        {
            //This int tracks whether the user confirmed or denied they wanted to quit the program
            int selection = JOptionPane.showConfirmDialog(null, "Are you sure you want to create a new file?", "New File", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            //This algorithm determines whether to quit the program based on the user's input
            if(selection == JOptionPane.YES_OPTION) {
                resetProgram();
                filesSaved++;
                nameFile();
                JOptionPane.showMessageDialog(null, "Creating a new file...");
            } else
            {
                JOptionPane.showMessageDialog(null, "The current file will remain open.");
            }
        } else
        {
            nameFile();
        }
    }

    private static long findPosition()
    {
        return (long) recordsWritten * RECORD_SIZE;
    }

    private boolean checkValidInput()
    {
        boolean isIDValid = false;
        boolean isNameValid = false;
        boolean isDescripValid = false;
        boolean isCostValid = false;
        if(productDataPnl.getIDTF().getText().matches("[0-9][0-9][0-9][0-9][0-9][0-9]"))
        {
            isIDValid = true;

            if(!productDataPnl.getNameTF().getText().trim().isEmpty())
            {
                isNameValid = true;

                if(!productDataPnl.getDescripTF().getText().trim().isEmpty()) {
                    isDescripValid = true;

                    if(productDataPnl.getCostTF().getText().matches("^[0-9]+(\\.[0-9]+)?$"))
                    {
                        isCostValid = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "The cost field must contain only numeric characters and cannot be empty.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "The Product Description field cannot be empty.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "The Product Name field cannot be empty.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "The Product ID field must contain 6 digits and no non-numeric characters.");
        }

        if(isIDValid && isNameValid && isDescripValid && isCostValid) {
            return true;
        } else {
            return false;
        }
    }

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

        controlPnl = new ControlPnl(this);
        mainPnl.add(controlPnl, gbc3);

        counterPnl = new CounterPnl();
        mainPnl.add(counterPnl, gbc4);

        frame.setSize(screenWidth * 3 / 4, screenHeight * 3 / 4);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Product Maker");
        frame.setVisible(true);
    }
}

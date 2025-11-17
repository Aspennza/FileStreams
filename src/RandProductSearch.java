import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;


//LOOK AT THE EXAMPLE OF HOW TO ACTUALLY READ THE FILE THAT YOU SAVED IN ONENOTE

public class RandProductSearch
{
    private JFrame frame;
    private TitlePnl2 titlePnl;
    private FileSearchPnl fileSearchPnl;
    private FilteredProductsPnl filteredProductsPnl;
    private ControlPnl2 controlPnl;
    private FileChooserLauncher chooser;
    private static Path file;

    public void start() {
        chooser = new FileChooserLauncher();
        generateFrame();
    }

    public void chooseFile()
    {
        Path selectedFile = chooser.chooseFile();

        if(selectedFile != null) {
            file = selectedFile;
        }
    }

    private static Product readProductData()
    {
        if(file != null) {
            try (RandomAccessFile randFile = new RandomAccessFile(file.toFile(), "r"))
            {
                randFile.seek(0);
                byte[] bytes = new byte[6];
                randFile.readFully(bytes);

            } catch (IOException e){
                e.printStackTrace();
            }
        } else
        {
            JOptionPane.showMessageDialog(null, "You must select a file before searching.");
        }

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
        gbc1.fill = GridBagConstraints.BOTH;

        //GridBagConstraints for the filePnl
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.gridwidth = 1;
        gbc2.gridheight = 1;
        gbc2.weightx = 1;
        gbc2.fill = GridBagConstraints.BOTH;

        //GridBagConstraints for the tagPnl
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 0;
        gbc3.gridy = 2;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        gbc3.weightx = 1;
        gbc3.fill = GridBagConstraints.BOTH;

        //GridBagConstraints for the controlPnl
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
        frame.setTitle("Tag Extractor");
        frame.setVisible(true);
    }
}

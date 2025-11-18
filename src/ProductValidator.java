import javax.swing.*;

/**
 * Allows the creation of objects for validating users' input
 * of Product data.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class ProductValidator
{
    /**
     * This method parses the cost field into a double to
     * make sure it is valid.
     * @param costStr the String value of the cost entered
     * @return a boolean representing whether the cost is a valid double
     */
    private boolean validateCost(String costStr) {
        try {
            double cost = Double.parseDouble(costStr.trim());
            return cost >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method checks if all the fields entered by the user are
     * valid for creating a Product object.
     * @param ID the user's Product ID
     * @param name the user's Product name
     * @param description the user's Product description
     * @param costStr the user's Product cost
     * @return a boolean value representing whether the values are valid
     */
    public boolean checkValidInput(String ID, String name, String description, String costStr)
    {
        boolean isIDValid = false;
        boolean isNameValid = false;
        boolean isDescripValid = false;
        boolean isCostValid = false;

        //this algorithm checks if the ID is valid
        if(ID.matches("[0-9][0-9][0-9][0-9][0-9][0-9]"))
        {
            isIDValid = true;

            //this algorithm checks if the name is valid
            if(!name.trim().isEmpty())
            {
                isNameValid = true;

                //this algorithm checks if the description is valid
                if(!description.trim().isEmpty()) {
                    isDescripValid = true;

                    //this algorithm checks if the cost is valid
                    if(validateCost(costStr))
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
}

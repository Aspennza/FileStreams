import javax.swing.*;

public class ProductValidator
{
    private boolean validateCost(String costStr) {
        try {
            double cost = Double.parseDouble(costStr.trim());
            return cost >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean checkValidInput(String ID, String name, String description, String costStr)
    {
        boolean isIDValid = false;
        boolean isNameValid = false;
        boolean isDescripValid = false;
        boolean isCostValid = false;
        if(ID.matches("[0-9][0-9][0-9][0-9][0-9][0-9]"))
        {
            isIDValid = true;

            if(!name.trim().isEmpty())
            {
                isNameValid = true;

                if(!description.trim().isEmpty()) {
                    isDescripValid = true;

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

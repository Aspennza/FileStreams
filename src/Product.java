//Need to:
//write javadoc for all programs
//create UML diagrams
//take screenshots

import java.util.Objects;

/**
 * Allows the creation of objects representing product data, including an ID number, name,
 * description, and cost. Also includes functions for acting on said objects.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class Product
{
    //This String holds the unique ID for each Product object
    private String ID;

    //This String holds the name of each Product object
    private String name;

    //This String holds the description of each Product object
    private String description;

    //This double holds the cost of each Product object
    private double cost;

    private static final int ID_LENGTH = 6;
    private static final int NAME_LENGTH = 35;
    private static final int DESCRIPTION_LENGTH = 75;

    public Product(String ID, String name, String description, double cost)
    {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(cost, product.cost) == 0 && Objects.equals(ID, product.ID) && Objects.equals(name, product.name) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, description, cost);
    }

    public String padID()
    {
        String formatted = (ID.length() > ID_LENGTH) ? ID.substring(0, ID_LENGTH) : ID;
        return String.format("%-6s", formatted);
    }

    public String padName()
    {
        String formatted = (name.length() > NAME_LENGTH) ? name.substring(0, NAME_LENGTH) : name;
        return String.format("%-35s", formatted);
    }

    public String padDescrip()
    {
        String formatted = (description.length() > DESCRIPTION_LENGTH) ? description.substring(0, DESCRIPTION_LENGTH) : description;
        return String.format("%-75s", formatted);
    }

    /**
     * Takes the content of a Product object and converts it into a CSV format.
     * @return a String containing a comma-separated list of product qualities.
     */
    public String toCSV()
    {
        return ID + ", " + name + ", " + description + ", " + cost;
    }

    /**
     * Takes the content of a Product object and converts it into a JSON format.
     * @return a String containing product qualities listed in the JSON format.
     */
    public String toJSON()
    {
        String retString = "";
        char DQ = '\u0022';
        retString = "{" + DQ + "IDNum" + DQ + ":" + DQ + this.ID + DQ + ",";
        retString += " " + DQ + "name" + DQ + ":" + DQ + this.name + DQ + ",";
        retString += " " + DQ + "description" + DQ + ":" + DQ + this.description + DQ + ",";
        retString += " " + DQ + "cost" + DQ + ":" + this.cost + "}";

        return retString;
    }

    /**
     * Takes the content of a Product object and converts it into an XML format.
     * @return a String containing product qualities listed in the XML format.
     */
    public String toXML()
    {
        String retString = "";
        retString = "<Product>";
        retString += "<IDNum>" + ID + "</IDNum>";
        retString += "<name>" + this.name + "</name>";
        retString += "<description>" + this.description + "</description>";
        retString += "<cost>" + this.cost + "</cost></Product>";

        return retString;
    }
}

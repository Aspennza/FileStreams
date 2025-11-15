import java.util.Calendar;
import java.util.Objects;

//Need to:
//write javadoc for all programs
//create UML diagrams
//take screenshots

/**
 * Allows the creation of objects representing personal data, including an ID number,
 * first name, last name, title, and year of birth. Also includes functions for acting
 * on said objects.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class Person
{
    //This String holds the unique ID for each Person object
    private String ID;

    //This String holds the first name for each Person object
    private String firstName;

    //This String holds the last name for each Person object
    private String lastName;

    //This String holds the title for each Person object
    private String title;

    //This int holds the year of birth for each Person object
    private int YOB;

    public Person(String ID, String firstName, String lastName, String title, int YOB)
    {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.YOB = YOB;
    }

    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYOB() {
        return YOB;
    }

    public void setYOB(int YOB) {
        this.YOB = YOB;
    }

    @Override
    public String toString() {
        return "Person{" +
                "ID='" + ID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", YOB=" + YOB +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return YOB == person.YOB && Objects.equals(ID, person.ID) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(title, person.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, firstName, lastName, title, YOB);
    }

    /**
     * Concatenates a person's first and last names.
     * @return a String containing a person's full name.
     */
    public String fullName()
    {
        return firstName + " " + lastName;
    }

    /**
     * Concatenates a person's title, first name, and last name.
     * @return a String containing a person's title and full name.
     */
    public String formalName()
    {
        return title + " " + firstName + " " + lastName;
    }

    /**
     * Identifies the current year, then subtracts a person's year of birth to find their age.
     * @return a String containing a person's age.
     */
    public String getAge()
    {
        int currentYear = 0;
        int age = 0;
        String stringAge = "";
        Calendar calendar = Calendar.getInstance();

        currentYear = calendar.get(Calendar.YEAR);
        age = currentYear - YOB;

        stringAge = Integer.toString(age);

        return stringAge;
    }

    /**
     * Takes a year as a parameter, then subtracts a person's year of birth from the
     * given year to find their age during that year.
     * @param year   an int representing the selected year.
     * @return       a String containing the person's age.
     */
    public String getAge(int year)
    {
        int age = 0;
        String stringAge = "";
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, year);

        age = calendar.get(Calendar.YEAR) - YOB;
        stringAge = Integer.toString(age);

        return stringAge;
    }

    /**
     * Takes the content of a Person object and converts it into a CSV format.
     * @return a String containing a comma-separated list of personal traits.
     */
    public String toCSV()
    {
        return ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YOB;
    }

    /**
     * Takes the content of a Person object and converts it into a JSON format.
     * @return a String containing personal traits listed in the JSON format.
     */
    public String toJSON()
    {
        String retString = "";
        char DQ = '\u0022';
        retString = "{" + DQ + "IDNum" + DQ + ":" + DQ + this.ID + DQ + ",";
        retString += " " + DQ + "firstName" + DQ + ":" + DQ + this.firstName + DQ + ",";
        retString += " " + DQ + "lastName" + DQ + ":" + DQ + this.lastName + DQ + ",";
        retString += " " + DQ + "title" + DQ + ":" + DQ + this.title + DQ + ",";
        retString += " " + DQ + "YOB" + DQ + ":" + this.YOB + "}";

        return retString;
    }

    /**
     * Takes the content of a Person object and converts it into XML format.
     * @return a String containing personal traits listed in the XML format.
     */
    public String toXML()
    {
        String retString = "";
        retString = "<Person>";
        retString += "<IDNum>" + ID + "</IDNum>";
        retString += "<firstName>" + this.firstName + "</firstName>";
        retString += "<lastName>" + this.lastName + "</lastName>";
        retString += "<title>" + this.title + "</title>";
        retString += "<YOB>" + this.YOB + "</YOB></Person>";

        return retString;
    }
}
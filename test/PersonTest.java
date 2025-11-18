import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the basic functionality of the functions in the Person.java class.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
class PersonTest {

    //A Person object for testing the functions
    Person person1;

    //A Person object for testing the functions
    Person person2;

    @BeforeEach
    void setUp() {
        person1 = new Person("000001", "Bilbo", "Baggins", "Esq.", 1940);
        person2 = new Person("000002", "Frodo", "Baggins", "Esq.", 1960);
    }

    @Test
    void testPersonConstructor() {
        Person person2 = new Person("000002", "Frodo", "Baggins", "Esq.", 1960);
        assertEquals("000002", person2.getID());
        assertEquals("Frodo", person2.getFirstName());
        assertEquals("Baggins", person2.getLastName());
        assertEquals("Esq.", person2.getTitle());
        assertEquals(1960, person2.getYOB());
    }

    @Test
    void setFirstName() {
        person1.setFirstName("Frodo");
        assertEquals("Frodo", person1.getFirstName());
    }

    @Test
    void setLastName() {
        person1.setLastName("Gamgee");
        assertEquals("Gamgee", person1.getLastName());
    }

    @Test
    void setTitle() {
        person1.setTitle("Mr.");
        assertEquals("Mr.", person1.getTitle());
    }

    @Test
    void setYOB() {
        person1.setYOB(1975);
        assertEquals(1975, person1.getYOB());
    }

    @Test
    void testToString() {
        assertEquals("Person{ID='000001', firstName='Bilbo', lastName='Baggins', title='Esq.', YOB=1940}", person1.toString());
    }

    @Test
    void testEquals() {
        assertTrue(person1.equals(person1));
        assertFalse(person2.equals(person1));
    }

    @Test
    void testHashCode() {
        assertEquals(654671110, person1.hashCode());
    }

    @Test
    void fullName() {
        assertEquals("Bilbo Baggins", person1.fullName());
    }

    @Test
    void formalName() {
        assertEquals("Esq. Bilbo Baggins", person1.formalName());
    }

    @Test
    void getAge() {
        assertEquals("85", person1.getAge());
    }

    @Test
    void testGetAge() {
        assertEquals("20", person1.getAge(1960));
    }

    @Test
    void toCSV() {
        assertEquals("000001, Bilbo, Baggins, Esq., 1940", person1.toCSV());
    }

    @Test
    void toJSON() {
        assertEquals("{\"IDNum\":\"000001\", \"firstName\":\"Bilbo\", \"lastName\":\"Baggins\", \"title\":\"Esq.\", \"YOB\":1940}", person1.toJSON());
    }

    @Test
    void toXML() {
        assertEquals("<Person><IDNum>000001</IDNum><firstName>Bilbo</firstName><lastName>Baggins</lastName><title>Esq.</title><YOB>1940</YOB></Person>", person1.toXML());
    }
}
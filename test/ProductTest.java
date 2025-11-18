import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the basic functionality of the functions in the Product.java class.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
class ProductTest {

    //A Product object for testing the functions
    Product product1;

    //A Product object for testing the functions
    Product product2;

    Product product3;

    Product product4;

    @BeforeEach
    void setUp() {
        product1 = new Product("000001", "Computer", "Computer with mouse and keyboard", 500.00);
        product2 = new Product("000002", "Burger", "Burger with lettuce and tomato", 7.00);
        product3 = new Product("0003", "pencil", "a writing implement with a pointy tip", 3.00);
        product4 = new Product("000004", "supercalifragilisticexpialidocious", "supercalifragilisticexpialidocious", 5.00);
    }

    @Test
    void testProductConstructor()
    {
        Product product2 = new Product("000002", "Burger", "Burger with lettuce and tomato", 7.00);
        assertEquals("000002", product2.getID());
        assertEquals("Burger", product2.getName());
        assertEquals("Burger with lettuce and tomato", product2.getDescription());
        assertEquals(7.00, product2.getCost());
    }

    @Test
    void setName() {
        product1.setName("Laptop");
        assertEquals("Laptop", product1.getName());
    }

    @Test
    void setDescription() {
        product1.setDescription("Laptop computer with stylus pen");
        assertEquals("Laptop computer with stylus pen", product1.getDescription());
    }

    @Test
    void setCost() {
        product1.setCost(550.55);
        assertEquals(550.55, product1.getCost());
    }

    @Test
    void testToString() {
        assertEquals("Product{ID='000001', name='Computer', description='Computer with mouse and keyboard', cost=500.0}", product1.toString());
    }

    @Test
    void testEquals() {
        assertTrue(product1.equals(product1));
        assertFalse(product2.equals(product1));
    }

    @Test
    void testHashCode() {
        assertEquals(-1631514885, product1.hashCode());
    }

    @Test
    void toCSV() {
        assertEquals("000001, Computer, Computer with mouse and keyboard, 500.0", product1.toCSV());
    }

    @Test
    void toJSON() {
        assertEquals("{\"IDNum\":\"000001\", \"name\":\"Computer\", \"description\":\"Computer with mouse and keyboard\", \"cost\":500.0}", product1.toJSON());
    }

    @Test
    void toXML() {
        assertEquals("<Product><IDNum>000001</IDNum><name>Computer</name><description>Computer with mouse and keyboard</description><cost>500.0</cost></Product>", product1.toXML());
    }

    @Test
    void padID() {
        assertEquals("000001", product1.padID());
        assertEquals("0003  ", product3.padID());
    }

    @Test
    void padName() {
        assertEquals("Burger                             ", product2.padName());
        assertEquals("supercalifragilisticexpialidocious ", product4.padName());
    }

    @Test
    void padDescrip() {
        assertEquals("supercalifragilisticexpialidocious                                         ", product4.padDescrip());
        assertEquals("a writing implement with a pointy tip                                      ", product3.padDescrip());
    }
}
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductValidatorTest {

    ProductValidator validator;

    @BeforeEach
    void setUp() {
        validator = new ProductValidator();
    }

    @Test
    void checkValidInput() {
        assertFalse(validator.checkValidInput("001", "name", "descrip", "20.00"));
        assertFalse(validator.checkValidInput("000001", "", "descrip", "20.00"));
        assertFalse(validator.checkValidInput("000001", "name", "", "20.00"));
        assertFalse(validator.checkValidInput("000001", "name", "descrip", "word"));
        assertFalse(validator.checkValidInput("@", "name", "descrip", "@"));
        assertTrue(validator.checkValidInput("000001", "name", "descrip", "20.00"));
    }
}
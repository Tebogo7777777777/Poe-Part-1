package poe.part.pkg1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class POEPart1Test {

    @Test
    public void testValidUserName() {
        POEPart1 poepart1 = new POEPart1();
        assertTrue(poepart1.checkUserName("Kyl_l"));
    }

    @Test
    public void testInvalidUserName() {
        POEPart1 poepart1 = new POEPart1();
        assertFalse(poepart1.checkUserName("abcd")); // no underscore
        assertFalse(poepart1.checkUserName("abc_def")); // more than 5 characters
    }

    @Test
    public void testValidPassword() {
        POEPart1 poepart1 = new POEPart1();
        assertTrue(poepart1.checkPasswordComplexity("Pass@123"));
    }

    @Test
    public void testInvalidPassword() {
        POEPart1 poepart1 = new POEPart1();
        assertFalse(poepart1.checkPasswordComplexity("pass123"));     // no capital, no special
        assertFalse(poepart1.checkPasswordComplexity("Password"));    // no number, no special
        assertFalse(poepart1.checkPasswordComplexity("Pa@1"));        // too short
    }

    @Test
    public void testValidPhoneNumber() {
        POEPart1 poepart1 = new POEPart1();
        assertTrue(poepart1.checkCellPhoneNumber("+27123456789"));
    }

    @Test
    public void testInvalidPhoneNumber() {
        POEPart1 poepart1 = new POEPart1();
        assertFalse(poepart1.checkCellPhoneNumber("0123456789"));     // no +
        assertFalse(poepart1.checkCellPhoneNumber("+ABC123456"));     // letters inside
        assertFalse(poepart1.checkCellPhoneNumber("+271234567890123")); // too many digits
    }
}

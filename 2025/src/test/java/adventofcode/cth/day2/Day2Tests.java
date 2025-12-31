package adventofcode.cth.day2;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Day2Tests {

    @Test
    @DisplayName("ID validity test")
    public void isIdValid() {
        Day2 day2 = new Day2();
        assertTrue(day2.isIdValid(123456), "ID 123456 should be valid");
        assertFalse(day2.isIdValid(123123), "ID 123123 should be invalid");
    }

}

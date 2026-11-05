package me.alejandrokatz.codingassignment.unit;

import me.alejandrokatz.codingassignment.utils.NameUtility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NameUtilityTest {

    @Test
    @DisplayName("Should correctly process typical two-part names")
    public void testTwoWordsName() {
        assertEquals("B.M.", NameUtility.convertNameToInitials("Bruno Mars"));
    }

    @Test
    @DisplayName("Should correctly process names with middle initials or multiple parts")
    public void testThreeWordsName() {
        assertEquals("D.M.J.", NameUtility.convertNameToInitials("Dave M Jones"));
    }

    @Test
    @DisplayName("Should correctly process names with >3 parts")
    public void testManyWordsName() {
        assertEquals("C.E.J.E.S.B.C.", NameUtility.convertNameToInitials("Carmen Elizabeth Juanita Echo Sky Brava Cortez"));
    }

    @Test
    @DisplayName("Should correctly process a single word name with no spaces")
    public void testSingleWordName() {
        assertEquals("M.", NameUtility.convertNameToInitials("MichaelSmith"));
    }

    @Test
    @DisplayName("Should handle names with extra leading, trailing, or middle spaces gracefully")
    public void testIrregularSpacing() {
        assertEquals("J.D.", NameUtility.convertNameToInitials("   John    Doe   "));
    }

    @Test
    @DisplayName("Should handle empty, blank, or null strings gracefully")
    public void testEdgeCaseEmptyAndNull() {
        assertEquals("", NameUtility.convertNameToInitials(""));
        assertEquals("", NameUtility.convertNameToInitials("   "));
        assertEquals("", NameUtility.convertNameToInitials(null));
    }
}

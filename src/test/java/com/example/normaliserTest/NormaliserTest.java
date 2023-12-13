package com.example.normaliserTest;

import com.example.feefo.FeefoApplication;
import com.example.feefo.normaliser.Normaliser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {FeefoApplication.class})
public class NormaliserTest {

    @Autowired
    private Normaliser n;

    @Test
    public void testNormaliseJavaEngineer() {
        String result = n.normalise("Java engineer");
        assertEquals("Software engineer", result);
    }

    @Test
    public void testNormaliseCSharpEngineer() {
        String result = n.normalise("C# engineer");
        assertEquals("Software engineer", result);
    }

    @Test
    public void testNormaliseAccountant() {
        String result = n.normalise("Accountant");
        assertEquals("Accountant", result);
    }

    @Test
    public void testNormaliseChiefAccountant() {
        String result = n.normalise("Chief Accountant");
        assertEquals("Accountant", result);
    }

    @Test
    public void testNormaliseWithEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> {
            n.normalise("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            n.normalise(" ");
        });
    }

    @Test
    public void testNormaliseWithNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            // Call the method that is expected to throw IllegalArgumentException
            n.normalise("2");
        });
    }

    @Test
    public void testNormaliseWithRandomCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            n.normalise("/?");
        });
    }

    @Test
    public void testNormaliseWithValidInputAndSpecialCharacters() {
        String result = n.normalise("C?> engineer");
        assertEquals("Software engineer", result);
    }
}
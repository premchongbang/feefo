package com.example.normaliser;

import com.example.feefo.FeefoApplication;
import com.example.feefo.normaliser.factory.NormaliserAlgorithmType;
import com.example.feefo.normaliser.service.Normaliser;
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
    public void testNormaliseServiceJavaEngineer() {
        String result = n.normalise("Java engineer");
        assertEquals("Software engineer", result);
    }

    @Test
    public void testNormaliseServiceJavaEngineerWithQualityFactor() {
        String result = n.normalise("Java engineer", 0.2);
        assertEquals("Software engineer", result);
    }

    @Test
    public void testNormaliseServiceJavaEngineerWithQualityFactorAndType() {
        String result = n.normalise("Java engineer", 0.2, NormaliserAlgorithmType.JARO_WINKLER);
        assertEquals("Software engineer", result);
    }

    @Test
    public void testNormaliseServiceCSharpEngineer() {
        String result = n.normalise("C# engineer");
        assertEquals("Software engineer", result);
    }

    @Test
    public void testNormaliseServiceCSharpEngineerWithType() {
        String result = n.normalise("C# engineer", NormaliserAlgorithmType.JARO_WINKLER);
        assertEquals("Software engineer", result);
    }

    @Test
    public void testNormaliseServiceAccountant() {
        String result = n.normalise("Accountant");
        assertEquals("Accountant", result);
    }

    @Test
    public void testNormaliseServiceChiefAccountant() {
        String result = n.normalise("Chief Accountant");
        assertEquals("Accountant", result);
    }

    @Test
    public void testNormaliseServiceWithQualityFactor() {
        String result = n.normalise("Chief Accountant", 0.8);
        assertEquals("Accountant", result);
    }

    @Test
    public void testNormaliseServiceWithEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> {
            n.normalise("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            n.normalise(" ");
        });
    }

    @Test
    public void testNormaliseServiceWithNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            n.normalise("2");
        });
    }

    @Test
    public void testNormaliseServiceWithRandomCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            n.normalise("/?");
        });
    }
}

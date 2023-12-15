package com.example.normaliser;

import com.example.feefo.FeefoApplication;
import com.example.feefo.normaliser.algorithm.types.JaroWinklerNormaliserAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(classes = {FeefoApplication.class})
public class JaroWinklerNormaliserAlgorithmTest {

    @Autowired
    private JaroWinklerNormaliserAlgorithm jn;

    @Test
    public void testDefaultJaroWinklerNormaliserNormaliseMethod() {
        String result = jn.normalise("Java engineer");
        assertEquals("Software engineer", result);
    }

    @Test
    public void testDefaultJaroWinklerNormaliserNormaliseMethod2() {
        String result = jn.normalise("Java engineer", 0.2);
        assertEquals("Software engineer", result);
    }
}
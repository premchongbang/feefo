package com.example.normaliser.unitTest;

import com.example.feefo.FeefoApplication;
import com.example.feefo.normaliser.type.DefaultJaroWinklerNormaliserAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(classes = {FeefoApplication.class})
public class DefaultJaroWinklerNormaliserAlgorithmTest {

    @Autowired
    private DefaultJaroWinklerNormaliserAlgorithm jn;

    @Test
    public void testDefaultJaroWinklerNormaliserAddTitle() {
        jn.addNormalisedTitle("Manager");
        String result = jn.normalise("Food Manager");
        assertEquals("Manager", result);
    }

    @Test
    public void testDefaultJaroWinklerNormaliserRemoveTitle() {
        jn.addNormalisedTitle("Manager");
        String result = jn.normalise("Food Manager");
        assertEquals("Manager", result);

        jn.removeNormalisedTitle("Manager");
        result = jn.normalise("Food Manager");
        assertNotEquals("Manager", result);
    }
}
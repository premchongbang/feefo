package com.example.normaliser.unitTest;

import com.example.feefo.FeefoApplication;
import com.example.feefo.normaliser.factory.NormaliserAlgorithmType;
import com.example.feefo.normaliser.factory.NormaliserFactory;
import com.example.feefo.normaliser.type.DefaultJaroWinklerNormaliserAlgorithm;
import com.example.feefo.normaliser.type.NormaliserAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {FeefoApplication.class})
public class NormaliserFactoryTest {

    @Autowired
    NormaliserFactory nf;

    @Test
    public void testNormaliserFactoryGetDefaultMethod() {
        NormaliserAlgorithm result = nf.getDefaultNormalizer();
        assertEquals(result.getClass(), DefaultJaroWinklerNormaliserAlgorithm.class);
    }

    @Test
    public void testNormaliserFactoryGetNormaliserMethod() {
        NormaliserAlgorithm result = nf.getNormalizer(NormaliserAlgorithmType.DEFAULT_JARO_WINKLER);
        assertEquals(result.getClass(), DefaultJaroWinklerNormaliserAlgorithm.class);
    }

    @Test
    public void testNormaliserFactoryGetNormaliserMethodWithNull() {
        NormaliserAlgorithm result = nf.getNormalizer(null);
        assertEquals(result, null);
    }

    @Test
    public void testNormaliserFactoryRegisterNormaliserMethodNullValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            nf.registerNormalizer(null, null);
        });
    }
}

 class TestNormaliser implements NormaliserAlgorithm {

     @Override
     public String normalise(String input) {
         return null;
     }

     @Override
     public String normalise(String input, double qualityFactor) {
         return null;
     }
 }

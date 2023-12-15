package com.example.normaliser;

import com.example.feefo.FeefoApplication;
import com.example.feefo.normaliser.factory.NormaliserAlgorithmType;
import com.example.feefo.normaliser.factory.NormaliserFactory;
import com.example.feefo.normaliser.algorithm.types.JaroWinklerNormaliserAlgorithm;
import com.example.feefo.normaliser.algorithm.NormaliserAlgorithm;
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
    public void testNormaliserFactoryGetDefaultNormaliserMethod() {
        NormaliserAlgorithm result = nf.getDefaultNormalizer();
        assertEquals(result.getClass(), JaroWinklerNormaliserAlgorithm.class);
    }

    @Test
    public void testNormaliserFactoryGetNormaliserMethod() {
        NormaliserAlgorithm result = nf.getNormalizer(NormaliserAlgorithmType.JARO_WINKLER);
        assertEquals(result.getClass(), JaroWinklerNormaliserAlgorithm.class);
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

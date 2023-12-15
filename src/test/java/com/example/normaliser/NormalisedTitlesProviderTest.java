package com.example.normaliser;

import com.example.feefo.FeefoApplication;
import com.example.feefo.normaliser.algorithm.types.JaroWinklerNormaliserAlgorithm;
import com.example.feefo.normaliser.algorithm.NormalisedTitlesProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(classes = {FeefoApplication.class})
public class NormalisedTitlesProviderTest {

    @Test
    public void testDefaultJaroWinklerNormaliserGetTitles() {
        List<String> result = NormalisedTitlesProvider.getNormalisedTitles();
        assertEquals(result.size(), 4);
    }

    @Test
    public void testDefaultJaroWinklerNormaliserAddTitle() {
        NormalisedTitlesProvider.addNormalisedTitle("Manager");
        List<String> result = NormalisedTitlesProvider.getNormalisedTitles();
        assertEquals(result.size(), 5);
    }

    @Test
    public void testDefaultJaroWinklerNormaliserRemoveTitle() {
        NormalisedTitlesProvider.addNormalisedTitle("Manager");
        List<String> result = NormalisedTitlesProvider.getNormalisedTitles();
        assertEquals(result.size(), 5);

        NormalisedTitlesProvider.removeNormalisedTitle("Manager");
        result = NormalisedTitlesProvider.getNormalisedTitles();
        assertEquals(result.size(), 4);
    }
}
package com.example.feefo.normaliser;

import com.example.feefo.validator.InputValidator;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.similarity.JaroWinklerSimilarity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@Component
public class Normaliser {

    private static List<String> normalisedTitles;

    static {
        normalisedTitles = new ArrayList<>();
        normalisedTitles.add("Architect");
        normalisedTitles.add("Software engineer");
        normalisedTitles.add("Quantity surveyor");
        normalisedTitles.add("Accountant");
    }

    public String normalise(String input) {
        return normalise(input, 0);
    }

    public String normalise(String input, double qualityFactor) {
        String bestMatch = null;
        try {
            InputValidator.validateInput(input);

            JaroWinklerSimilarity similarity = new JaroWinklerSimilarity();

            double maxSimilarity = 0.0;

            for (String normalizedTitle : normalisedTitles) {
                double titleSimilarity = similarity.apply(normalizedTitle, input);

                double adjustedSimilarity = titleSimilarity + (qualityFactor * (1 - titleSimilarity));

                if (adjustedSimilarity > maxSimilarity) {
                    maxSimilarity = adjustedSimilarity;
                    bestMatch = normalizedTitle;
                }
            }
        } catch (IllegalArgumentException e) {
            log.error("Validation Error: " + e.getMessage());
            throw e;
        }

        return bestMatch;
    }
}

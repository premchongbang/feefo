package com.example.feefo.normaliser.algorithm.types;

import com.example.feefo.normaliser.algorithm.NormalisedTitlesProvider;
import com.example.feefo.normaliser.algorithm.NormaliserAlgorithm;
import com.example.feefo.normaliser.validator.InputValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.similarity.JaroWinklerSimilarity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JaroWinklerNormaliserAlgorithm implements NormaliserAlgorithm {

    /**
     * normalise method with string input
     * @param input
     * @return
     */
    public String normalise(String input) {
        return normalise(input, 0);
    }

    /**
     * normalise method with string input and quality factor
     * @param input
     * @param qualityFactor adjust similarity score in overall comparison. value should be within the range of 0 to 1.
     *                     Higher value indicates similarity score has more significant impact on the matching and vice-versa.
     * @return
     */
    public String normalise(String input, double qualityFactor) {
        String bestMatch = null;
        try {
            InputValidator.validateInput(input);
            InputValidator.validateQualityFactor(qualityFactor);

            JaroWinklerSimilarity similarity = new JaroWinklerSimilarity();

            double maxSimilarity = 0.0;

            for (String normalizedTitle : NormalisedTitlesProvider.getNormalisedTitles()) {
                double titleSimilarity = similarity.apply(normalizedTitle, input);

                double adjustedSimilarity = titleSimilarity + (qualityFactor * (1 - titleSimilarity));

                if (adjustedSimilarity > maxSimilarity) {
                    maxSimilarity = adjustedSimilarity;
                    bestMatch = normalizedTitle;
                }
            }

            return bestMatch;
        } catch (IllegalArgumentException e) {
            log.error("Validation Error: " + e.getMessage());
            throw e;
        }
    }
}

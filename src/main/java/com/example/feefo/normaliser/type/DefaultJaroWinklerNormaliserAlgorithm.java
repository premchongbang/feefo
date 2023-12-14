package com.example.feefo.normaliser.type;

import com.example.feefo.normaliser.validator.InputValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.JaroWinklerSimilarity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DefaultJaroWinklerNormaliserAlgorithm implements NormaliserAlgorithm {

    private static List<String> normalisedTitles;

    static {
        normalisedTitles = new ArrayList<>();
        normalisedTitles.add("Architect");
        normalisedTitles.add("Software engineer");
        normalisedTitles.add("Quantity surveyor");
        normalisedTitles.add("Accountant");
    }

    /**
     * normalise method with input
     * @param input
     * @return
     */
    public String normalise(String input) {
        return normalise(input, 0);
    }

    /**
     * normalise method with input and quality factor
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

            for (String normalizedTitle : normalisedTitles) {
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

    /**
     * add normalised titles
     * @param title
     */
    public void addNormalisedTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            log.error("Title cannot be empty or null");
            throw new IllegalArgumentException("Title cannot be empty or null");
        }

        normalisedTitles.add(title);
    }

    /**
     * remove normalised title
     * @param title
     */
    public void removeNormalisedTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            log.error("Title cannot be empty or null");
            throw new IllegalArgumentException("Title cannot be empty or null");
        }

        normalisedTitles.remove(title);
    }
}

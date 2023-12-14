package com.example.feefo.normaliser.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class InputValidator {

    public static boolean validateInput(String input) {
        if (StringUtils.isEmpty(input)) {
            throw new IllegalArgumentException("Input can't be empty string.");
        } else if (NumberUtils.isCreatable(input)) {
            throw new IllegalArgumentException("Input can't be a number.");
        } else if(!input.matches(".*[a-zA-Z].*")) {
            throw new IllegalArgumentException("Invalid input.");
        }
        return true;
    }

    public static boolean validateQualityFactor(double qualityFactor) {
        if (qualityFactor >= 0.0 && qualityFactor <= 1.0) {
            return true;
        }

        throw new IllegalArgumentException("Quality factor should be in-between 0 to 1.");
    }
}

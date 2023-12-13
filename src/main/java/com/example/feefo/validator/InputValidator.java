package com.example.feefo.validator;

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
}

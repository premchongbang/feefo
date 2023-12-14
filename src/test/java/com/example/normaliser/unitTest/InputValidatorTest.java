package com.example.normaliser.unitTest;


import com.example.feefo.FeefoApplication;
import com.example.feefo.normaliser.validator.InputValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {FeefoApplication.class})
public class InputValidatorTest {

    @Test
    public void testInputValidatorValidateInputMethod() {
        String input = "hello world";
        assertTrue(InputValidator.validateInput(input));

        input = "hello? world";
        assertTrue(InputValidator.validateInput(input));
    }

    @Test
    public void testInputValidatorValidateInputMethodWithEmptyValue() {
        String input = "";
        assertThrows(IllegalArgumentException.class, () -> {
            assertTrue(InputValidator.validateInput(input));
        });
    }

    @Test
    public void testInputValidatorValidateInputMethodWithNumericalValue() {
        String input = "2";
        assertThrows(IllegalArgumentException.class, () -> {
            assertTrue(InputValidator.validateInput(input));
        });
    }

    @Test
    public void testInputValidatorValidateInputMethodWithInvalidCharacter() {
        String input = ">?";
        assertThrows(IllegalArgumentException.class, () -> {
            assertTrue(InputValidator.validateInput(input));
        });
    }

    @Test
    public void testInputValidatorValidateQualityFactorMethod() {
        double qf = 0.5;
        assertTrue(InputValidator.validateQualityFactor(qf));
    }

    @Test
    public void testInputValidatorValidateQualityFactorMethodWithHigherThanOneValue() {
        double qf = 2;
        assertThrows(IllegalArgumentException.class, () -> {
            InputValidator.validateQualityFactor(qf);
        });
    }

    @Test
    public void testInputValidatorValidateQualityFactorMethodWithLowerThanOneValue() {
        double qf = -1;
        assertThrows(IllegalArgumentException.class, () -> {
            InputValidator.validateQualityFactor(qf);
        });
    }
}

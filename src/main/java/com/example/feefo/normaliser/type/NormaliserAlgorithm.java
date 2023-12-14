package com.example.feefo.normaliser.type;

public interface NormaliserAlgorithm {
    String normalise(String input);
    String normalise(String input, double qualityFactor);
}

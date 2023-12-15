package com.example.feefo.normaliser.algorithm;

public interface NormaliserAlgorithm {
    String normalise(String input);
    String normalise(String input, double qualityFactor);
}

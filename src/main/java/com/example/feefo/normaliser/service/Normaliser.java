package com.example.feefo.normaliser.service;

import com.example.feefo.normaliser.algorithm.NormaliserAlgorithm;
import com.example.feefo.normaliser.factory.NormaliserAlgorithmType;
import com.example.feefo.normaliser.factory.NormaliserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Normaliser {

    @Autowired
    private NormaliserFactory normaliserFactory;

    /**
     *
     * @param input
     * @return
     */
    public String normalise(String input) {
        return normalise(input, NormaliserAlgorithmType.JARO_WINKLER);
    }

    /**
     *
     * @param input
     * @param type
     * @return
     */
    public String normalise(String input, NormaliserAlgorithmType type) {
        return findNormaliserType(type).normalise(input);
    }

    /**
     * normalises using default normalisation algorithm i.e. Jaro Winkler algorithm
     * @param input
     * @param qualityFactor
     * @return
     */
    public String normalise(String input, double qualityFactor) {
        return normalise(input, qualityFactor, NormaliserAlgorithmType.JARO_WINKLER);
    }

    /**
     * @param input
     * @param qualityFactor
     * @param type
     * @return
     */
    public String normalise(String input, double qualityFactor, NormaliserAlgorithmType type) {
        return findNormaliserType(type).normalise(input, qualityFactor);
    }

    /**
     * @param type
     * @return
     */
    private NormaliserAlgorithm findNormaliserType(NormaliserAlgorithmType type) {
        if (type == null) {
            return normaliserFactory.getDefaultNormalizer();
        } else {
            return normaliserFactory.getNormalizer(type);
        }
    }
}

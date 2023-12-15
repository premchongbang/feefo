package com.example.feefo.normaliser.factory;

import com.example.feefo.normaliser.algorithm.types.JaroWinklerNormaliserAlgorithm;
import com.example.feefo.normaliser.algorithm.types.LevenshteinDistanceAlgorithm;
import com.example.feefo.normaliser.algorithm.NormaliserAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class NormaliserFactory {
    private NormaliserAlgorithm defaultNormalizer;

    private static Map<NormaliserAlgorithmType, NormaliserAlgorithm> normaliserMap = new HashMap<>();

    public NormaliserFactory(JaroWinklerNormaliserAlgorithm jaroWinklerNormalizer, LevenshteinDistanceAlgorithm levenshteinDistanceAlgorithm) {
        this.defaultNormalizer = jaroWinklerNormalizer;
        this.normaliserMap.put(NormaliserAlgorithmType.JARO_WINKLER, jaroWinklerNormalizer);
        this.normaliserMap.put(NormaliserAlgorithmType.LEVENSHTEIN_DISTANCE, levenshteinDistanceAlgorithm);
    }

    public NormaliserAlgorithm getNormalizer(NormaliserAlgorithmType type) {
        NormaliserAlgorithm result = normaliserMap.get(type);
        if (result == null) {
            log.error("No normalizer found for type: " + type);
        }

        return result;
    }

    public void registerNormalizer(NormaliserAlgorithmType type, NormaliserAlgorithm normaliser) {
        if (type == null || normaliser == null) {
            log.warn("Invalid type or normaliser provided");
            throw new IllegalArgumentException("Invalid type or normaliser provided");
        }

        synchronized (this) {
            normaliserMap.put(type, normaliser);
        }
    }

    public NormaliserAlgorithm getDefaultNormalizer() {
        return defaultNormalizer;
    }
}

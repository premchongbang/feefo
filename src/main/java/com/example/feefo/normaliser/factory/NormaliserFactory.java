package com.example.feefo.normaliser.factory;

import com.example.feefo.normaliser.type.DefaultJaroWinklerNormaliserAlgorithm;
import com.example.feefo.normaliser.type.NormaliserAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class NormaliserFactory {
    private NormaliserAlgorithm defaultNormalizer;

    private Map<NormaliserAlgorithmType, NormaliserAlgorithm> normaliserMap = new HashMap<>();
    public NormaliserFactory(DefaultJaroWinklerNormaliserAlgorithm defaultJaroWinklerNormalizer) {
        this.defaultNormalizer = defaultJaroWinklerNormalizer;
        this.normaliserMap.put(NormaliserAlgorithmType.DEFAULT_JARO_WINKLER, defaultJaroWinklerNormalizer);
    }

    public NormaliserAlgorithm getNormalizer(NormaliserAlgorithmType type) {
        NormaliserAlgorithm result = normaliserMap.get(type);
        if (result == null) {
            log.warn("No normalizer found for type: " + type);
        }

        return result;
    }

    public void registerNormalizer(NormaliserAlgorithmType type, NormaliserAlgorithm normaliser) {
        if (type == null || normaliser == null) {
            log.error("Invalid type or normaliser provided");
            throw new IllegalArgumentException("Invalid type or normaliser provided");
        }

        normaliserMap.put(type, normaliser);
    }

    public NormaliserAlgorithm getDefaultNormalizer() {
        return defaultNormalizer;
    }
}

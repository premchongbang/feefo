package com.example.feefo.normaliser.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class NormalisedTitlesProvider {

    private static List<String> normalisedTitles;

    static {
        normalisedTitles = new ArrayList<>();
        normalisedTitles.add("Architect");
        normalisedTitles.add("Software engineer");
        normalisedTitles.add("Quantity surveyor");
        normalisedTitles.add("Accountant");
    }

    public static List<String> getNormalisedTitles() {
        return new ArrayList<>(normalisedTitles);
    }

    public static synchronized  void addNormalisedTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            log.error("Title cannot be empty or null");
            throw new IllegalArgumentException("Title cannot be empty or null");
        }

        normalisedTitles.add(title);
    }

    public static synchronized void removeNormalisedTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            log.error("Title cannot be empty or null");
            throw new IllegalArgumentException("Title cannot be empty or null");
        }

        normalisedTitles.remove(title);
    }
}

package org.example.model;

import org.example.domain.City;

import java.util.Optional;

public record ContinentCityPair(String continent, City city) implements Comparable<ContinentCityPair> {

    @Override
    public int compareTo(ContinentCityPair continentCityPair) {
        return this.city.getPopulation() - continentCityPair.city.getPopulation();
    }

    public static void printEntry(String continent, Optional<ContinentCityPair> pair) {
        System.out.printf("%s: %s\n", continent, pair.get().city());
    }
}

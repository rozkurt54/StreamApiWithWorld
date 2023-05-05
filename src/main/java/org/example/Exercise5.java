package org.example;

import org.example.dao.InMemoryWorldDao;
import org.example.dao.WorldDao;
import org.example.domain.Country;

import java.util.Comparator;
import java.util.function.Predicate;

public class Exercise5 {
      private static final WorldDao WORLD_DAO = InMemoryWorldDao.getInstance();

      private static final Comparator<Country> populationDensity = Comparator.comparingDouble(country -> country.getPopulation() / country.getSurfaceAre());
      private static final Predicate<Country> noBodyLives = country -> country.getPopulation() == 0L;

      // Sort the countries by their population densities in descending order ignoring
      // zero population countries
      public static void main(String[] args) {
            var countries = WORLD_DAO.findAllCountries();
            var countriesDensitySortedByDesc = countries
                        .stream()
                        .filter(noBodyLives.negate())
                        .sorted(populationDensity.reversed())
                        .toList();

            countriesDensitySortedByDesc.forEach(System.out::println);
      }
}

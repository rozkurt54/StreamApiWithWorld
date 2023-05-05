package org.example;

import org.example.dao.InMemoryWorldDao;
import org.example.dao.WorldDao;
import org.example.domain.Country;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Exercise6 {

      private static final WorldDao WORLD_DAO = InMemoryWorldDao.getInstance();
      private static final BiConsumer<String, Optional<Country>> printEntry = ((continent, country) -> System.out.printf("%s: %s\n", continent, country.get()));

      public static void main(String[] args) {

            // Find the richest country of each continent with respect to their GNP (Gross National Product) values.

            var richestCountryByContinent = WORLD_DAO
                        .findAllCountries()
                        .stream()
                        .collect(Collectors.groupingBy(Country::getContinent, Collectors.maxBy(Comparator.comparing(Country::getGnp))));

            richestCountryByContinent.forEach(printEntry);
      }


}

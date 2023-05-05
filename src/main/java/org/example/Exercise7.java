package org.example;

import org.example.dao.InMemoryWorldDao;
import org.example.dao.WorldDao;
import org.example.domain.Country;

import java.util.stream.Collectors;

public class Exercise7 {
      private static final WorldDao WORLD_DAO = InMemoryWorldDao.getInstance();

      public static void main(String[] args) {

            // Find the minimum, the maximum and the average population of world countries

            var populationSummary = WORLD_DAO
                        .findAllCountries()
                        .stream()
                        .collect(Collectors.summarizingLong(Country::getPopulation));

            System.out.println(populationSummary);
      }
}

package org.example;

import org.example.dao.InMemoryWorldDao;
import org.example.dao.WorldDao;
import org.example.domain.Country;

import java.util.LongSummaryStatistics;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Exercise8 {

      private final static WorldDao WORLD_DAO = InMemoryWorldDao.getInstance();

      private final static BiConsumer<String, LongSummaryStatistics> printEntry = (continent, statistic) -> System.out.printf("%s: %s\n", continent, statistic);

      // Find the minimum, the maximum and the average population of each continent.
      public static void main(String[] args) {

            var populationSummaryByContinent = WORLD_DAO
                        .findAllCountries()
                        .stream()
                        .collect(Collectors.groupingBy(Country::getContinent, Collectors.summarizingLong(Country::getPopulation)));

            populationSummaryByContinent.forEach(printEntry);
      }

}

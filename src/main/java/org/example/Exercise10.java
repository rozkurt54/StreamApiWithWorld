package org.example;

import org.example.dao.InMemoryWorldDao;
import org.example.dao.WorldDao;
import org.example.domain.Country;
import org.example.model.CountrySummaryStatistics;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Exercise10 {

      private static final WorldDao WORLD_DAO = InMemoryWorldDao.getInstance();

      private static final BiConsumer<String, CountrySummaryStatistics> printEntry = (continent, statistic) -> System.out.printf("%s: %s\n", continent, statistic);

      private static final BiConsumer<CountrySummaryStatistics, Country> accumulator = CountrySummaryStatistics::accept;

      private static final BinaryOperator<CountrySummaryStatistics> combiner = (l, r) -> {
            l.combine(r);
            return l;
      };

      private static final Supplier<CountrySummaryStatistics> COUNTRY_SUMMARY_STATISTICS_SUPPLIER =
                  ()-> new CountrySummaryStatistics((l,r)-> Long.compare(l.getPopulation(), r.getPopulation()));

      // Find the countries of each continent with the minimum and the maximum population
      public static void main(String[] args) {
            var continentStatistic = WORLD_DAO.findAllCountries()
                        .stream()
                        .collect(Collectors.groupingBy(Country::getContinent, Collector.of(COUNTRY_SUMMARY_STATISTICS_SUPPLIER, accumulator, combiner)));

            continentStatistic.forEach(printEntry);
      }

}

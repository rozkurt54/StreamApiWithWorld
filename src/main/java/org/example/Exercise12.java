package org.example;

import org.example.dao.InMemoryWorldDao;
import org.example.dao.WorldDao;
import org.example.domain.Country;
import org.example.model.CountryCitySummaryStatistics;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Exercise12 {
      private static final WorldDao WORLD_DAO = InMemoryWorldDao.getInstance();

      private static final BiConsumer<CountryCitySummaryStatistics, Country> accumulator = (s,c) -> s.accept(c);
      private static final BinaryOperator<CountryCitySummaryStatistics> combiner = (l,r) -> {
            l.combine(r);
            return l;
      };
      private static final BiConsumer<String, CountryCitySummaryStatistics> printEntry =
                  (country, statistics) -> System.out.printf("%s: %s\n", country, statistics);

      public static void main(String[] args) {
            var countryStatistic = WORLD_DAO.findAllCountries()
                        .stream()
                        .collect(Collectors.groupingBy(Country::getCode, Collector.of(CountryCitySummaryStatistics::new, accumulator, combiner)));
            countryStatistic.forEach(printEntry);
      }

}

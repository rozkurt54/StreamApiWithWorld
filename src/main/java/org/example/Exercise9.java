package org.example;

import org.example.dao.InMemoryWorldDao;
import org.example.dao.WorldDao;
import org.example.model.CountrySummaryStatistics;

import java.util.Comparator;
import java.util.function.Supplier;

public class Exercise9 {
      private static final WorldDao WORLD_DAO = InMemoryWorldDao.getInstance();

      private static final Supplier<CountrySummaryStatistics> COUNTRY_SUMMARY_STATISTICS_SUPPLIER = ()-> new CountrySummaryStatistics((l,r) -> Long.compare(l.getPopulation(), r.getPopulation()));

      // Find the countries with the minimum and the maximum population
      public static void main(String[] args) {
            var countrySummaryStatistic = WORLD_DAO.findAllCountries()
                        .stream()
                        .collect(COUNTRY_SUMMARY_STATISTICS_SUPPLIER, CountrySummaryStatistics::accept, CountrySummaryStatistics::combine);

            System.out.println(countrySummaryStatistic);





      }
}

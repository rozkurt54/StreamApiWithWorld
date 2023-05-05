package org.example;

import org.example.dao.InMemoryWorldDao;
import org.example.dao.WorldDao;
import org.example.domain.Country;
import org.example.model.DoubleSummaryGaussianStatistics;

public class Exercise13 {
      private static final WorldDao WORLD_DAO = InMemoryWorldDao.getInstance();

      // Find the minimum, the maximum, the average, and the standard deviation of GNP values.
      public static void main(String[] args) {

            var gnpStatistics = WORLD_DAO
                        .findAllCountries()
                        .stream()
                        .mapToDouble(Country::getGnp)
                        .collect(
                                    DoubleSummaryGaussianStatistics::new,
                                    DoubleSummaryGaussianStatistics::accept,
                                    DoubleSummaryGaussianStatistics::combine
                        );
            System.out.println(gnpStatistics);

      }
}

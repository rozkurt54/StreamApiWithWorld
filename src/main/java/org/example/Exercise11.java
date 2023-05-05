package org.example;

import org.example.dao.CountryDao;
import org.example.dao.InMemoryWorldDao;
import org.example.domain.Country;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class Exercise11 {
      private static final CountryDao COUNTRY_DAO = InMemoryWorldDao.getInstance();
      private static final ToIntFunction<Country> compareByCityNumber = country -> country.getCities().size();
      private static final Consumer<Country> printCountry = country -> System.err.printf("%s: %d\n", country.getName(), country.getCities().size());

      private static final BiConsumer<String, List<Country>> printEntry = (continent, countries) -> {
            System.err.println(continent);
            System.err.println("=".repeat(continent.length()));
            countries.forEach(printCountry);
            System.err.println("\n");
      };

      // Group the countries by continent, and then sort the countries in each continent by number of cities in each country.
      public static void main(String[] args) {
            var countriesByContinent = COUNTRY_DAO
                        .findAllCountries()
                        .stream()
                        .sorted(Comparator.comparingInt(compareByCityNumber).reversed())
                        .collect(Collectors.groupingBy(Country::getContinent));

            countriesByContinent.forEach(printEntry);
      }
}

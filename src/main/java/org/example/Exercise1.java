package org.example;

import org.example.dao.CountryDao;
import org.example.dao.InMemoryWorldDao;
import org.example.model.ContinentCityPair;

import java.util.Collection;
import java.util.stream.Collectors;

public class Exercise1 {
      private static final CountryDao COUNTRY_DAO = InMemoryWorldDao.getInstance();

      //Find the most populated city of each continent.
      public static void main(String[] args) {

            var highPopulatedCities = COUNTRY_DAO
                        .findAllCountries()
                        .stream()
                        .map(country -> country.getCities().stream().map(city -> new ContinentCityPair(country.getContinent(), city)).toList())
                        .flatMap(Collection::stream)
                        .collect(Collectors.groupingBy(ContinentCityPair::continent, Collectors.maxBy(ContinentCityPair::compareTo)));

            highPopulatedCities.forEach(ContinentCityPair::printEntry);
      }
}

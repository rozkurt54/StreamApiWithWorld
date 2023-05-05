package org.example;

import org.example.dao.CityDao;
import org.example.dao.CountryDao;
import org.example.dao.InMemoryWorldDao;
import org.example.domain.City;
import org.example.model.ContinentCityPair;

import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Exercise3 {
    private static final CountryDao COUNTRY_DAO = InMemoryWorldDao.getInstance();
    private static final CityDao CITY_DAO = InMemoryWorldDao.getInstance();

    public static void main(String[] args) {

        //Find the highest populated capital city of each continent.

        Function<ContinentCityPair, City> extractCity = ContinentCityPair::city;
        var highPopulatesCapitalOfEachContinent = COUNTRY_DAO.findAllCountries()
                .stream()
                .map(country -> new ContinentCityPair(country.getContinent(), CITY_DAO.findCityById(country.getCapital())))
                .filter(pair -> Objects.nonNull(pair.city()))
                .collect(Collectors.groupingBy(ContinentCityPair::continent, Collectors.maxBy(Comparator.comparing(extractCity.andThen(City::getPopulation)))));

        highPopulatesCapitalOfEachContinent.forEach((continent, pair) -> System.out.printf("%s: %s\n", continent, pair.get().city()));

    }
}

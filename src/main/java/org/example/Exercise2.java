package org.example;

import org.example.dao.CityDao;
import org.example.dao.CountryDao;
import org.example.dao.InMemoryWorldDao;
import org.example.domain.City;
import org.example.domain.Country;

import java.util.Comparator;
import java.util.Objects;

public class Exercise2 {

    private static final CountryDao COUNTRY_DAO = InMemoryWorldDao.getInstance();

    private static final CityDao CITY_DAO = InMemoryWorldDao.getInstance();

    public static void main(String[] args) {

        var highPopulatedCapitalCities = COUNTRY_DAO.findAllCountries()
                .stream()
                .map(Country::getCapital)
                .filter(Objects::nonNull)
                .map(CITY_DAO::findCityById)
                .filter(Objects::nonNull)
                .max(Comparator.comparing(City::getPopulation));

        highPopulatedCapitalCities.ifPresent(System.out::println);

    }
}

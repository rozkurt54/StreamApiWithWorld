package org.example;

import org.example.dao.CountryDao;
import org.example.dao.InMemoryWorldDao;
import org.example.model.CountryCityCountPair;

import java.util.Comparator;
import java.util.function.Function;

public class Exercise4 {

    private static final CountryDao COUNTRY_DAO = InMemoryWorldDao.getInstance();

    public static void main(String[] args) {

        // Sort the countries by number of their cities in descending order

        Function<CountryCityCountPair, Integer> countExtract = CountryCityCountPair::count;

        var countriesWithCityCountInDescOrder = COUNTRY_DAO.findAllCountries()
                .stream()
                .map(country -> new CountryCityCountPair(country, country.getCities().size()))
                .sorted(Comparator.comparing(countExtract).reversed())
                .toList();

        countriesWithCityCountInDescOrder.forEach(System.out::println);
    }

}

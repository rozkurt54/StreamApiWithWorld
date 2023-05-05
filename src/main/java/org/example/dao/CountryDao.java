package org.example.dao;

import org.example.domain.Country;

import java.util.List;
import java.util.Set;

public interface CountryDao {
    Country findCountryByCode(String code);

    Country removeCountry(Country country);

    Country addCountry(Country country);

    Country updateCountry(Country country);

    List<Country> findAllCountries();

    List<Country> findCountriesByContinent(String continent);

    Set<String> getAllContinents();
}

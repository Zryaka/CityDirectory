package ru.sber.directory;

import main.java.ru.sber.directory.dao.CityDao;
import main.java.ru.sber.directory.models.City;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestCityDao {

    @Test
    public void readCityTest() {
        CityDao cityDao = new CityDao();
        List<City> cities = cityDao.readCity();

        Assert.assertFalse(cities.isEmpty());

        City city = cities.get(0);
        Assert.assertEquals("Адыгейск", city.getName());
    }
}

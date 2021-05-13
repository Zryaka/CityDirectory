package ru.sber.directory;

import main.java.ru.sber.directory.models.City;
import main.java.ru.sber.directory.service.CityService;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestService {

    @Test
    public void sortName() {
        CityService cityService = new CityService();
        List<City> cities = cityService.sortName();

        Assert.assertFalse(cities.isEmpty());

        City city = cities.get(0);
        Assert.assertEquals("Абаза", city.getName());
        City city2 = cities.get(cities.size()-1);
        Assert.assertEquals("Майкоп", city2.getName());

    }

    @Test
    public void sortNameAndDistrict() {
        CityService cityService = new CityService();
        List<City> cities = cityService.sortNameAndDistrict();

        Assert.assertFalse(cities.isEmpty());

        City city = cities.get(0);
        Assert.assertEquals("Дальневосточный", city.getDistrict());
        Assert.assertEquals("Алдан", city.getName());
        City city2 = cities.get(cities.size()-1);
        Assert.assertEquals("Майкоп", city2.getName());
        Assert.assertEquals("Южный", city2.getDistrict());
    }

    @Test
    public void populationSize(){
        CityService cityService = new CityService();
        String population = cityService.populationSize();

        Assert.assertFalse(population.isEmpty());

        Assert.assertEquals("[4] = 165183", population);
    }

    @Test
    public void sizeOfDistrict(){
        CityService cityService = new CityService();
        Map<String,Integer> cities = cityService.sizeOfDistrict();

        Assert.assertFalse(cities.isEmpty());

        Map<String,Integer> cities2 = new HashMap<>();
        cities2.put("Южный",2);
        cities2.put("Сибирский",3);
        cities2.put("Приволжский",3);
        cities2.put("Дальневосточный",3);

        Assert.assertEquals(cities, cities2);
    }
}

package ru.sber.directory.service;

import ru.sber.directory.dao.CityDao;
import ru.sber.directory.models.City;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CityService {

    private CityDao cityDao;

    Printer print = new Printer();

    public CityService() {
        cityDao = new CityDao();
    }

    public List<City> readCity() {
        return cityDao.readCity();

    }

    public List<City> sortName() {
        return cityDao.readCity().stream().sorted(Comparator.comparing(City::getName)).collect(Collectors.toList());

    }

    public List<City> sortNameAndDistrict() {

        return cityDao.readCity().stream().sorted(Comparator.comparing(City::getDistrict)
                .thenComparing(City::getName)).collect(Collectors.toList());
    }

    public String populationSize() {

        City[] cityPop = cityDao.readCity().toArray(new City[0]);

        int max = 0;
        int index = 0;
        for (int i = 0; i < cityPop.length; i++) {
            if (cityPop[i].getPopulation() > max) {
                max = cityPop[i].getPopulation();
                index = i;
            }
        }
        return "[" + index + "]" + " = " + max;

    }

    public Map<String,Integer> sizeOfDistrict() {

        Map<String,Integer> cities = new HashMap<>();

        Map<String, List<City>> district = cityDao.readCity().stream().collect(
                Collectors.groupingBy(City::getDistrict));

        for (Map.Entry<String, List<City>> item : district.entrySet()) {

            cities.put(item.getKey(),item.getValue().size());
        }
        return cities;
    }

    public void startSystem() throws IOException {

        char choice;
        char ignore;
        do {
            System.out.println("Добро пожаловать в справочник по городам");
            System.out.println("Выберите раздел");
            System.out.println("1 - все города");
            System.out.println("2 - сортировка городов по наименованию");
            System.out.println("3 - сортировка города по наименованию и федеральному округу");
            System.out.println("4 - сортировка по населению");
            System.out.println("5 - кол - во городов  в регионах");

            System.out.println("Выберите");

            choice = (char) System.in.read();
            do {
                ignore = (char) System.in.read();
            } while (ignore != '\n');
        } while (choice < '1' | choice > '5');
        System.out.println("\n");
        switch (choice) {
            case '1':
                print.printer(readCity());
                break;
            case '2':
                print.printer(sortName());
                break;
            case '3':
                print.printer(sortNameAndDistrict());
                break;
            case '4':
                print.printer(populationSize());
                break;
            case '5':
                print.printer(sizeOfDistrict());
                break;

        }


    }

}
package ru.sber.directory.dao;

import ru.sber.directory.models.City;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CityDao {

    private static final String FILE_DIRECTORY = "src/main/resources/City.txt";

    private List<City> cities = new ArrayList<>();

    public List<City> readCity() {
        if (cities != null && !cities.isEmpty()) {
            return cities;
        } else {
            try {
                Scanner scanner = new Scanner(new File(FILE_DIRECTORY));

                while (scanner.hasNext()) {
                    City city = new City();
                    String nextLine = scanner.nextLine();
                    String[] data = nextLine.split(";");
                    city.setName(data[1]);
                    city.setRegion(data[2]);
                    city.setDistrict(data[3]);
                    city.setPopulation(Integer.parseInt(data[4]));
                    city.setFoundation(Integer.parseInt(data[5]));

                    cities.add(city);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Возникла ошибка при добавлении файла");
            }
            return cities;
        }

    }
}

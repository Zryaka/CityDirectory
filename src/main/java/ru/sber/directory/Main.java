package ru.sber.directory;

import ru.sber.directory.service.CityService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        CityService cityService = new CityService();
        cityService.startSystem();
    }
}
package main.java.ru.sber.directory.service;

import java.util.List;
import java.util.Map;

public class Printer {

    public void printer(Object objectToPrint){
        if(objectToPrint instanceof List ){
            ((List<?>)objectToPrint).forEach(item -> System.out.println(item.toString()));
        }else{
            System.out.println(objectToPrint);
        }

    }
}

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CityService {

    private CityDao cityDao;



    public CityService(){
        cityDao = new CityDao();
    }

    public void readCity(){
        cityDao.readCity().stream()
                .forEach(System.out::println);
    }

    public void SortName() {
        cityDao.readCity().stream().sorted(Comparator.comparing(City::getName))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public void SortNameAndDistrict() {

        cityDao.readCity().stream().sorted(Comparator.comparing(City::getDistrict)
                .thenComparing(City::getName)).collect(Collectors.toList())
                .forEach(System.out::println);


    }

    public void populationSize() {

        City[] cityPop = cityDao.readCity().toArray(new City[0]);

        int max = 0;
        int index = 0;
        for (int i = 0; i < cityPop.length; i++) {
            if (cityPop[i].getPopulation() > max) {
                max = cityPop[i].getPopulation();
                index = i;
            }
        }
        System.out.println("[" + index + "]" + " = " + max);

    }

    public void sizeOfDistrict() {




        Map<String, List<City>> district = cityDao.readCity().stream().collect(
                Collectors.groupingBy(City::getDistrict));

        for (Map.Entry<String, List<City>> item : district.entrySet()) {

            System.out.println(item.getKey() + " - " + item.getValue().size());


        }
    }

    public void startSystem () throws IOException {

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
                readCity();
                break;
            case '2':
                SortName();
                break;
            case '3':
                SortNameAndDistrict();
                break;
            case '4':
                populationSize();
                break;
            case '5':
                sizeOfDistrict();
                break;

        }


    }

}
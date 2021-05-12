import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class Directory {

    private static String FILE_DIRECTORY = "/Users/a191881444/IdeaProjects/CityDirectory/.idea/City.txt";

    public static void main(String[] args) throws IOException {

        StartSystem();
    }

    public static List<City> readCity() {

        List<City> cities = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(FILE_DIRECTORY));

            while (scanner.hasNext()) {

                City city = new City();

                String nextLine = scanner.nextLine();

                String[] Data = nextLine.split(";");

                city.setName(Data[1]);

                city.setRegion(Data[2]);

                city.setDistrict(Data[3]);

                city.setPopulation(Integer.parseInt(Data[4]));

                city.setFoundation(Integer.parseInt(Data[5]));

                cities.add(city);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return cities;
    }

    public static void SortName() {
        List<City> cities = readCity();
        cities.stream().sorted(Comparator.comparing(City::getName))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static void SortNameAndDistrict() {
        List<City> cities = readCity();
        cities.stream().sorted(Comparator.comparing(City::getDistrict)
                .thenComparing(City::getName)).collect(Collectors.toList())
                .forEach(System.out::println);


    }

    public static void populationSize() {
        List<City> cities = readCity();
        City[] cityPop = cities.toArray(new City[0]);

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

    public static void sizeOfDistrict() {

        List<City> cities = readCity();


        Map<String, List<City>> district = cities.stream().collect(
                Collectors.groupingBy(City::getDistrict));

        for (Map.Entry<String, List<City>> item : district.entrySet()) {

            System.out.println(item.getKey() + " - " + item.getValue().size());


        }
    }

        public static void StartSystem () throws IOException {

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
                    List<City> cities = readCity();

                    cities.forEach(System.out::println);
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

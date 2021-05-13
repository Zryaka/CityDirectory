package ru.sber.directory.jdbc;

import ru.sber.directory.models.City;
import ru.sber.directory.service.CityService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UpdateBD {

    CityService service = new CityService();
    List<City> cities = service.readCity();

    private static final String INSERT_USERS_SQL = "INSERT INTO Cities" +
            "  (name , region, district, population, foundation) VALUES " +
            " (?, ?, ?, ?, ?);";

    public static void main(String[] argv) throws SQLException {
        UpdateBD createTableExample = new UpdateBD();
        createTableExample.insertRecord();
    }

    public void insertRecord() throws SQLException {
        System.out.println(INSERT_USERS_SQL);

        try (Connection connection = JDBCBase.getConnection();


             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){

              for(int i = 0; i < cities.size(); i++){
                preparedStatement.setString(1, cities.get(i).getName());
                preparedStatement.setString(2, cities.get(i).getRegion());
                preparedStatement.setString(3, cities.get(i).getDistrict());
                preparedStatement.setInt(4, cities.get(i).getPopulation());
                preparedStatement.setInt(5, cities.get(i).getFoundation());

                preparedStatement.executeUpdate();
             }
        } catch (SQLException e) {
            JDBCBase.printSQLException(e);
        }
    }

}

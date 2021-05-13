package ru.sber.directory.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadBD {
    private static final String QUERY = "select name,region,district,population,foundation from Cities where name =?";

    public static void main(String[] args) {

        try (Connection connection = JDBCBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            preparedStatement.setString(1, "Абаза");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String region = rs.getString("region");
                String district = rs.getString("district");
                int population = rs.getInt("population");
                int foundation = rs.getInt("foundation");
                System.out.println(name + "," + region + "," + district + "," + population + "," + foundation);
            }
        } catch (SQLException e) {
            JDBCBase.printSQLException(e);
        }
    }
}

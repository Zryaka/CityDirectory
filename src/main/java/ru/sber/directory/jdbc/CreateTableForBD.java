package ru.sber.directory.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTableForBD {
    private static final String createTableSQL = "create table Cities (\r\n" +
            "  name varchar (25) not null ,\r\n" +
            "  region varchar(20) not null,\r\n" +
            "  district varchar(20) not null,\r\n" +
            "  population integer (20) not null,\r\n" +
            "  foundation integer(4) not null\r\n" + "  );";

    public static void main(String[] argv) throws SQLException {
        CreateTableForBD createTableExample = new CreateTableForBD();
        createTableExample.createTable();
    }

    public void createTable() throws SQLException {

        System.out.println(createTableSQL);

        try (Connection connection = JDBCBase.getConnection();

             PreparedStatement statement = (PreparedStatement) connection.createStatement();) {

             statement.execute(createTableSQL);

        } catch (SQLException e) {

            JDBCBase.printSQLException(e);
        }
    }
}

package by.fitr.webproject.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DbConnection {
//    private static final String URL = "jdbc:mysql://localhost:3306/psp_project?useSSL=false&serverTimezone=UTC";
    private static final String URL = "url";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String SQL_QUERY = "SELECT password FROM user WHERE user.login = ?";
    private static InputStream inputStream = DbConnection.class.getClassLoader().getResourceAsStream("config/database.properties");

    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(properties.getProperty(URL), properties
                /*properties.getProperty(USER), properties.getProperty(PASSWORD)*/);
             PreparedStatement preparedStatement =  connection.prepareStatement(SQL_QUERY)) {
            preparedStatement.setString(1, "iamadmin");
            ResultSet resultSet = preparedStatement.executeQuery();
            String emailFromDb = "default";
            if (resultSet.next()) {
                emailFromDb = resultSet.getString(1);
            }
            System.out.println(emailFromDb);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}

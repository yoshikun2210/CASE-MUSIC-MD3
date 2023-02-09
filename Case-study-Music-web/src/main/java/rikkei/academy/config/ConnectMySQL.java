package rikkei.academy.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMySQL {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/web_music";
    private static final String USER = "root";
    private static final String PASS = "123456";
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL,USER,PASS);
            System.out.println("Ket noi thanh cong");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("Ket noi that bai");
            throw new RuntimeException(e);

        }
        return connection;
    }
}

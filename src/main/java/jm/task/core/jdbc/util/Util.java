package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/lesson_1.1.4";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Util instance;
    private static Connection connection;
    private Util(){}

    public static Connection getConnection() {
        Util localInstance = instance;
        if (localInstance == null) {
            synchronized (Util.class) {
                localInstance = instance;
                if (localInstance == null) {
                    try {
                        Class.forName(DB_DRIVER);
                        connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    } catch (ClassNotFoundException | SQLException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }
}

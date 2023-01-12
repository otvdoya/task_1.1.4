package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE IF NOT EXISTS users(" +
                "user_id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(30)," +
                "lastname VARCHAR(50)," +
                "age INT" +
                ");";
        sqlUpdateCommandRun(sqlCommand);
    }

    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE IF EXISTS users;";
        sqlUpdateCommandRun(sqlCommand);
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlCommand = "INSERT users(name, lastname, age) " +
                String.format("VALUES(\"%s\", \"%s\", \"%d\");", name, lastName, age);
        sqlUpdateCommandRun(sqlCommand);
        System.out.println(String.format("User с именем – %s добавлен в базу данных", name));
    }

    public void removeUserById(long id) {
        String sqlCommand = String.format("DELETE FROM users WHERE user_id=%s;", id);
        sqlUpdateCommandRun(sqlCommand);
    }

    public List<User> getAllUsers() {

        List<User> list_of_users = new ArrayList<>();

        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastname"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("user_id"));
                list_of_users.add(user);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return list_of_users;
    }

    public void cleanUsersTable() {
        String sqlCommand = "DELETE FROM users";
        sqlUpdateCommandRun(sqlCommand);
    }

    public void sqlUpdateCommandRun(String sqlCommand){
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

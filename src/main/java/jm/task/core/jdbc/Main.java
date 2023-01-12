package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService user = new UserServiceImpl();

        user.createUsersTable();
        user.saveUser("Flea", "Flea", (byte)29);
        user.saveUser("Anthony", "Kiedis", (byte)27);
        user.saveUser("John", "Frusciante", (byte)27);
        user.saveUser("Chad", "Smith", (byte)29);

        for (int i = 0; i < user.getAllUsers().size(); i++) {
            System.out.println(user.getAllUsers().get(i));
        }

        user.cleanUsersTable();
        user.dropUsersTable();


    }
}

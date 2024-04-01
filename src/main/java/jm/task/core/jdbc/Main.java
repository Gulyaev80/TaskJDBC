package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();



        userService.createUsersTable();

        userService.saveUser("Иван", "Иванов", (byte) 27);

        userService.saveUser("Андрей", "Андреев", (byte) 37);

        userService.saveUser("Петр", "Петров", (byte) 47);

        userService.saveUser("Сидр", "Сидоров", (byte) 57);

        userService.removeUserById(3);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();


    }
}

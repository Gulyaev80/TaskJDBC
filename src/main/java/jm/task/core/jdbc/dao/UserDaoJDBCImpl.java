package jm.task.core.jdbc.dao;

import java.sql.*;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl  implements UserDao {


        private static final Connection connection = Util.getConnection();

        public UserDaoJDBCImpl() {

        }


    public void createUsersTable()  {
        String creatSQL = "CREATE TABLE IF NOT EXISTS user (Id BIGINT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20), LastName VARCHAR(20), Age TINYINT)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(creatSQL)
        ) {
            preparedStatement.executeUpdate();

        } catch (SQLException  e) {
            e.printStackTrace();
        }
//
    }

    public void dropUsersTable() {
        String dropTable = "DROP TABLE IF EXISTS user";
        try (PreparedStatement preparedStatement = connection.prepareStatement(dropTable)
        ){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age)  {
        String saveSQL = "INSERT INTO user ( NAME, LASTNAME, AGE) VALUES ( ?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(saveSQL)
        ) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String removeUserId = "DELETE FROM user WHERE Id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(removeUserId)
        ) {

            preparedStatement.setInt(1, (int) id);
            preparedStatement.executeUpdate();
        }   catch (SQLException  e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers()  {
        List<User> userList = new ArrayList<>();
        String sqlList = "SELECT * FROM user";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlList)){

            ResultSet resultSet = preparedStatement.executeQuery(sqlList);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));
                userList.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  userList;
    }

    public void cleanUsersTable() {
        String deleteTableData ="TRUNCATE TABLE user";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteTableData)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

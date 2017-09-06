package kz.javalab.va.dao;

import kz.javalab.va.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoFactory {


    ConnectionPool pool = ConnectionPool.getInstance();
    private Connection connection = pool.getConnection();

    String query = "SELECT * FROM  ACCESS";

    public DaoFactory() throws InterruptedException {
    }


    public void DaoFactory() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("access");
                System.out.println(name);
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

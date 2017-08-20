package kz.javalab.va;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class run {


    public static void main(String[] args) {
Connection connection=null;
        try {
            Class.forName("org.h2.Driver");
            connection=DriverManager.getConnection("jdbc:h2:./webapp/src/main/sql","test","test");
            Statement st =connection.createStatement();
            st.execute("create TABLE pawn(name VARCHAR(20))");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

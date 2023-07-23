package com.example.oopassignment4;

import java.sql.*;

public class DBConnector {

    /**
     * hostname=oopassignment4cbsm.mysql.database.azure.com
     * SQL Username: oopChristianShania
     * SQL password: j&HhN2BL70K19tB4
     * Port 3306
     */

    public static void DBTest() {
        Connection connection = null;

        try {
            //Below line is used for connectivity, calls static methods in the class
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://oopassignment4cbsm.mysql.database.azure.com:3306/testschema?useSSL=true", "oopChristianShania", "j&HhN2BL70K19tB4");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from testTable");

            int id;
            String data;

            while (resultSet.next()) {
                id = resultSet.getInt("ID");
                data = resultSet.getString("name").trim();
                System.out.println("ID: " + id + " Data: " + data);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
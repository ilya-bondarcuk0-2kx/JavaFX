package com.example.sql_javafx;

import java.sql.*;


public class DatabaseHandler extends Configs{

    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionsString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionsString, dbUser, dbPass);

        return dbConnection;
    }

    public void signUpUser(User user) {

        String insertQuery = "INSERT INTO "
                + Const.USER_TABLE
                +
                "("
                + Const.USER_FIRSTNAME + ","
                + Const.USER_LASTNAME + ","
                + Const.USER_USERNAME + ","
                + Const.USER_PASSWORD + ","
                + Const.USER_LOCATION + ","
                + Const.USER_GENDER + ")"
                + "VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insertQuery);
            prSt.setString(1, user.getFirstname());
            prSt.setString(2, user.getLastname());
            prSt.setString(3, user.getUsername());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, user.getLocation());
            prSt.setString(6, user.getGender());
            prSt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    public ResultSet getUser(User user) {

        ResultSet rSet = null;

        String selectQuery = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_USERNAME + "= ? AND " + Const.USER_PASSWORD + "= ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(selectQuery);
            prSt.setString(1, user.getUsername());
            prSt.setString(2, user.getPassword());

            rSet = prSt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return rSet;
    }

}

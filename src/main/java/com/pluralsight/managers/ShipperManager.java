package com.pluralsight.managers;

import com.pluralsight.models.Shipper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShipperManager {

    private final DataSource dataSource;
    private List<Shipper> shippers = new ArrayList<>();

    public ShipperManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //methods
    public List<Shipper> getAllShippers() {
        List<Shipper> shippers = new ArrayList<>();

        try (
                Connection connection = dataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        SELECT shipperID,
                        CompanyName,
                        Phone
                        FROM Shippers
                        """)
        ) {
            try (
                    ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                addShipperToList(resultSet, shippers);
            }

        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
        return shippers;
    }

    public Integer insertNewShipper(String name, String phoneNum) {
        try (
                Connection connection = dataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        INSERT INTO Shippers
                        VALUES (NULL, ?, ?)
                        """, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phoneNum);

            //checks how many rows were affected
            int rows = preparedStatement.executeUpdate();

            if (rows == 0) {
                System.out.println("No rows inserted, shipper not added.");
            }

            //checks if the key was added
            //this will be used to create the new shipper object
            try (
                    ResultSet keys = preparedStatement.getGeneratedKeys()
            ) {
                if (keys.next()) {
                    //the column that contains the key
                    int newID = keys.getInt(1);

                    Shipper shipper = new Shipper(newID, name, phoneNum);
                    shippers.add(shipper);
                    return newID;
                } else {
                    System.out.println("Insert succeeded but no ID was returned.");
                   return null;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
            return null;
        }
    }

    public void updateField(int shipperID, String fieldName, String newVal) {

    }

    public void removeShipper(int shipperID) {

    }


    public void addShipperToList(ResultSet resultSet, List<Shipper> list) throws SQLException {

        while (resultSet.next()) {
            int shipperID = resultSet.getInt("ShipperID");
            String name = resultSet.getString("CompanyName");
            String phone = resultSet.getString("Phone");
            Shipper shipper = new Shipper(shipperID, name, phone);
            list.add(shipper);
        }
    }
}

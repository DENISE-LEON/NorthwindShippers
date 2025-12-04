package com.pluralsight.managers;

import com.pluralsight.models.Shipper;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipperManager {

    private final DataSource dataSource;

    public ShipperManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //methods
    public List<Shipper> getAllShippers() {
        List<Shipper> shippers = new ArrayList<>();

        try(
                Connection connection = dataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        SELECT shipperID,
                        CompanyName,
                        Phone
                        FROM Shippers
                        """)
                ) {
            try(
                    ResultSet resultSet = preparedStatement.executeQuery()
                    ) {
                addShipperToList(resultSet, shippers);
            }

        }catch (SQLException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
        return shippers;
    }

    public void insertNewShipper(String name, String phoneNum) {

    }

    public void updateField(int shipperID ,String fieldName, String newVal) {

    }

    public void removeShipper(int shipperID) {

    }


    public void addNewShipperToList(ResultSet resultSet, List<Shipper> list) {

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

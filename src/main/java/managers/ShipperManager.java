package managers;

import models.Shipper;

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
                        SELECT 
                        """)
                ) {

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
}

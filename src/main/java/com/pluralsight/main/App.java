package com.pluralsight.main;

import com.pluralsight.UserInterface;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) {

        if(args.length != 2) {
            System.out.println("Application needs two args to run: A username and a password");
            //exit the app due to failure because we dont have a username and password from the command line
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];
        String url = "jdbc:mysql://localhost:3306/northwind";


        try(
                BasicDataSource dataSource = new BasicDataSource();
                ) {
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);

            UserInterface ui = new UserInterface(dataSource);
            ui.menuDisplay();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
    }
}

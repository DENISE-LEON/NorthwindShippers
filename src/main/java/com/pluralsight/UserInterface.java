package com.pluralsight;

import com.pluralsight.managers.ShipperManager;
import com.pluralsight.models.Shipper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    public static Scanner scanner = new Scanner(System.in);
    DataSource dataSource;
    ShipperManager shipperManager;

    public UserInterface(DataSource dataSource) {
        this.dataSource = dataSource;
        this.shipperManager = new ShipperManager(dataSource);
    }

    public void menuDisplay() {
        boolean run = true;
        try {
            while (run) {
                System.out.println("What would you like to do?");
                System.out.println("""
                        1) View all shippers
                        2) Add a new shipper
                        3) Update a shippers information
                        4) Delete a shipper
                        0) Eit
                        """);
                int menuChoice = scanner.nextInt();
                scanner.nextLine();
                switch (menuChoice) {
                    case 1:
                        viewALlShippersProcess();
                        break;
                    case 2:
                        newShipperProcess();
                        break;
                    case 3:
                        updateShipperProcess();
                        break;
                    case 4:
                        deleteShipperProcess();
                        break;
                    case 0:
                        System.out.print("Exiting");
                        loadingDots();
                        System.out.println("\nGoodbye!");
                        run = false;
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Please input the number that matches what you would like to do");
            System.out.println("Example:");
            System.out.println("I want to: 1) View all shippers");
            System.out.println("My input should be: 1");
            //need to clear buffer bc bad input is still in the buffer
            scanner.nextLine();
        }
    }

    public void viewALlShippersProcess() {
    printShippers(shipperManager.getAllShippers());
    }

    public void newShipperProcess() {
        System.out.println("Enter the name of the shipper");
        String shipperName = scanner.nextLine();

        System.out.println("Enter the shippers phone number");
        System.out.println("Please use the following format:");
        System.out.println("Example: (555) 555-5555");

        String phoneNum = scanner.nextLine();

        shipperManager.insertNewShipper(shipperName, phoneNum);
    }

    public void updateShipperProcess() {

    }

    public void deleteShipperProcess() {

    }


        public static void printShippers(List<Shipper> shippers) {
            if (shippers.isEmpty()) {
                System.out.println("No shippers found.");
                System.out.println();
                return;
            }

            //insert pretty formatting in here
            System.out.printf("%-10s %-30s %-20s%n", "ID", "Company Name", "Phone Number");
            System.out.println("---------------------------------------------------------------------");

            for (Shipper shipper : shippers) {
                System.out.printf("%-10d %-30s %-20s%n",
                        shipper.getShipperID(),
                        shipper.getCompanyName(),
                        shipper.getPhoneNumber());
            }
            System.out.println();
        }




    public void nowDoingMgs(String what) {
        System.out.print("Loading");
        loadingDots();
        System.out.println();
        System.out.print("Completing task " + what);
        loadingDots();
        System.out.println();

    }

    public void nowDisplayingMgs(String what) {
        System.out.print("Searching");
        loadingDots();
        System.out.println();
        System.out.print("Now displaying " + what);
        loadingDots();
        System.out.println();

    }


    public void loadingDots() {
        try {
            for (int i = 0; i < 4; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
        } catch (InterruptedException e) {
            // ignore
        }
    }

}

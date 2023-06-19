package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class DealerShipFileManager {
            private static final String DB_URL = "jdbc:mysql://localhost:3306/dealership";
            private static final String DB_USER = "Karma";
            private static final String DB_PASSWORD = "Gurung";

            public DealerShip getDealership() {
                DealerShip dealerShip = new DealerShip();

                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM inventory");

                    while (resultSet.next()) {
                        String vin = resultSet.getString("VIN");
                        int year = resultSet.getInt("YEAR");
                        String make = resultSet.getString("MAKE");
                        String model = resultSet.getString("MODEL");
                        String vehicleType = resultSet.getString("Type");
                        String color = resultSet.getString("COLOR");
                        int odometer = resultSet.getInt("ODOMETER");
                        double price = resultSet.getDouble("PRICE");

                        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                        dealerShip.addVehicle(vehicle);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException("Error retrieving dealership inventory from the database: " + e.getMessage(), e);
                }

                return dealerShip;
            }

            public static void saveDealership(DealerShip dealerShip) {
                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("TRUNCATE TABLE inventory");

                    for (Vehicle v : dealerShip.getAllVehicles()) {
                        String query = String.format("INSERT INTO inventory (VIN, YEAR, MAKE, MODEL, Type, COLOR, ODOMETER, PRICE) " +
                                        "VALUES ('%s', %d, '%s', '%s', '%s', '%s', %d, %.2f)",
                                v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(),
                                v.getColor(), v.getOdometer(), v.getPrice());
                        statement.executeUpdate(query);
                    }

                    System.out.println("Changes have been saved to the database.");
                } catch (SQLException e) {
                    throw new RuntimeException("Error saving dealership inventory to the database: " + e.getMessage(), e);
                }
            }
        }

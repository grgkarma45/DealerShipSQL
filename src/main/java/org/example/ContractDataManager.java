package org.example;

import java.io.FileWriter;
import java.io.IOException;


import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.PreparedStatement;
        import java.sql.SQLException;

public class ContractDataManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    public void saveContract(Contract contract) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO contracts (contract_id, customer_name, amount) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set the parameter values
            preparedStatement.setString(1, contract.getDate());
            preparedStatement.setString(2, contract.getCustomerName());
            preparedStatement.setString(3, contract.getCustomerEmail());

            // Execute the query
            preparedStatement.executeUpdate();

            System.out.println("Contract saved to the database.");
        } catch (SQLException e) {
            throw new RuntimeException("Error saving contract to the database: " + e.getMessage(), e);
        }
    }

}

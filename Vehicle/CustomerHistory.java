package Vehicle;

import java.sql.*;
import java.util.Scanner;

public class CustomerHistory {

    public static void viewHistory(Scanner sc)
            throws SQLException {

        sc.nextLine();

        System.out.println("Enter customer name:");
        String name = sc.nextLine();

        System.out.println("Enter customer phone:");
        String phone = sc.nextLine();

        String sql =
                "SELECT c.customer_id, c.name, c.phone, " +
                "v.vehicle_number, v.vehicle_model, " +
                "s.service_type, s.service_date " +
                "FROM customers c " +
                "JOIN vehicles v " +
                "ON c.customer_id = v.customer_id " +
                "JOIN service_records s " +
                "ON v.vehicle_id = s.vehicle_id " +
                "WHERE c.name=? AND c.phone=? " +
                "ORDER BY s.service_date";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, phone);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            System.out.println(
                    "\n===== CUSTOMER BOOKING HISTORY =====");

            while (rs.next()) {

                found = true;

                System.out.println(
                        "Customer ID: " +
                        rs.getInt("customer_id"));

                System.out.println(
                        "Customer: " +
                        rs.getString("name"));

                System.out.println(
                        "Phone: " +
                        rs.getString("phone"));

                System.out.println(
                        "Vehicle: " +
                        rs.getString("vehicle_number"));

                System.out.println(
                        "Model: " +
                        rs.getString("vehicle_model"));

                System.out.println(
                        "Service: " +
                        rs.getString("service_type"));

                System.out.println(
                        "Date: " +
                        rs.getString("service_date"));

                System.out.println(
                        "-----------------------------");
            }

            if (!found) {

                System.out.println(
                        "No booking history found.");
            }
        }
    }
}
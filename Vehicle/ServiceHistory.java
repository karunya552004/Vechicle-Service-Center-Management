package Vehicle;

import java.sql.*;
import java.util.Scanner;

public class ServiceHistory {

    public static void viewHistory(Scanner sc)
            throws SQLException {

        sc.nextLine();

        System.out.println("Enter vehicle number:");
        String vehicleNumber = sc.nextLine();

        String sql =
                "SELECT c.name, v.vehicle_number, " +
                "v.vehicle_model, s.service_type, " +
                "s.service_date " +
                "FROM customers c " +
                "JOIN vehicles v " +
                "ON c.customer_id = v.customer_id " +
                "JOIN service_records s " +
                "ON v.vehicle_id = s.vehicle_id " +
                "WHERE v.vehicle_number=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(1, vehicleNumber);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- SERVICE HISTORY ---");

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println(
                        "Customer: " +
                        rs.getString("name"));

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

                System.out.println("----------------------");
            }

            if (!found) {
                System.out.println(
                        "No service history found.");
            }
        }
    }
}
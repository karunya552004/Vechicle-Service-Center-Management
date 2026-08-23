package Vehicle;

import java.sql.*;
import java.util.Scanner;

public class ServiceBooking {

    public static void bookService(Scanner sc)
            throws SQLException {

        sc.nextLine();

        System.out.println("Enter vehicle number:");
        String vehicleNumber = sc.nextLine();

        String check =
                "SELECT vehicle_id FROM vehicles " +
                "WHERE vehicle_number=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(check)) {

            ps.setString(1, vehicleNumber);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {

                System.out.println(
                        "Vehicle not found. Please register vehicle first.");

                return;
            }

            int vehicleId =
                    rs.getInt("vehicle_id");

            System.out.println("Enter service type:");
            String serviceType = sc.nextLine();

            System.out.println(
                    "Enter service date (YYYY-MM-DD):");

            String date = sc.nextLine();

            String insert =
                    "INSERT INTO service_records" +
                    "(vehicle_id,service_type,service_date)" +
                    " VALUES (?,?,?)";

            try (PreparedStatement ps2 =
                         con.prepareStatement(insert)) {

                ps2.setInt(1, vehicleId);
                ps2.setString(2, serviceType);
                ps2.setString(3, date);

                ps2.executeUpdate();

                System.out.println(
                        "Service booked successfully");
            }
        }
    }
}
package Vehicle;

import java.sql.*;
import java.util.Scanner;

public class VehicleRegistration {

    public static void registerVehicle(Scanner sc)
            throws SQLException {

        sc.nextLine();

        System.out.println("Enter customer name:");
        String customerName = sc.nextLine();

        System.out.println("Enter customer phone:");
        String phone = sc.nextLine();

        String check =
                "SELECT customer_id FROM customers " +
                "WHERE name=? AND phone=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(check)) {

            ps.setString(1, customerName);
            ps.setString(2, phone);

            ResultSet rs = ps.executeQuery();

            int customerId;

            if (rs.next()) {

                customerId =
                        rs.getInt("customer_id");

                System.out.println(
                        "Existing customer found.");

                System.out.println(
                        "Customer ID: " + customerId);

            } else {

                String insertCustomer =
                        "INSERT INTO customers(name,phone) " +
                        "VALUES (?,?)";

                try (PreparedStatement ps2 =
                             con.prepareStatement(
                                     insertCustomer,
                                     Statement.RETURN_GENERATED_KEYS)) {

                    ps2.setString(1, customerName);
                    ps2.setString(2, phone);

                    ps2.executeUpdate();

                    ResultSet keys =
                            ps2.getGeneratedKeys();

                    keys.next();

                    customerId =
                            keys.getInt(1);

                    System.out.println(
                            "New customer registered.");

                    System.out.println(
                            "Customer ID: " + customerId);
                }
            }

            // Vehicle details

            System.out.println("Enter vehicle number:");
            String vehicleNumber = sc.nextLine();

            System.out.println("Enter vehicle model:");
            String model = sc.nextLine();

            String checkVehicle =
                    "SELECT vehicle_id FROM vehicles " +
                    "WHERE vehicle_number=?";

            try (PreparedStatement ps3 =
                         con.prepareStatement(checkVehicle)) {

                ps3.setString(1, vehicleNumber);

                ResultSet vehicleRs =
                        ps3.executeQuery();

                if (vehicleRs.next()) {

                    System.out.println(
                            "Vehicle already registered.");

                    return;
                }
            }

            String insertVehicle =
                    "INSERT INTO vehicles" +
                    "(customer_id,vehicle_number,vehicle_model)" +
                    " VALUES (?,?,?)";

            try (PreparedStatement ps4 =
                         con.prepareStatement(insertVehicle)) {

                ps4.setInt(1, customerId);
                ps4.setString(2, vehicleNumber);
                ps4.setString(3, model);

                ps4.executeUpdate();

                System.out.println(
                        "Vehicle registered successfully.");
            }
        }
    }
}
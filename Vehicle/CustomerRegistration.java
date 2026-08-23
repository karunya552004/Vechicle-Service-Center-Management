package Vehicle;

import java.sql.*;
import java.util.Scanner;

public class CustomerRegistration {

    public static void registerCustomer(Scanner sc)
            throws SQLException {

        sc.nextLine();

        System.out.println("Enter customer name:");
        String name = sc.nextLine();

        System.out.println("Enter customer phone:");
        String phone = sc.nextLine();

        String check =
                "SELECT customer_id FROM customers " +
                "WHERE name=? AND phone=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(check)) {

            ps.setString(1, name);
            ps.setString(2, phone);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int customerId =
                        rs.getInt("customer_id");

                System.out.println(
                        "Customer already exists.");

                System.out.println(
                        "Customer ID: " + customerId);

                return;
            }

            String insert =
                    "INSERT INTO customers(name,phone) " +
                    "VALUES (?,?)";

            try (PreparedStatement ps2 =
                         con.prepareStatement(insert)) {

                ps2.setString(1, name);
                ps2.setString(2, phone);

                ps2.executeUpdate();

                System.out.println(
                        "Customer registered successfully");
            }
        }
    }
}
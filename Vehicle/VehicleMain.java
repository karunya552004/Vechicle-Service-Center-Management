package Vehicle;

import java.util.Scanner;

public class VehicleMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice;

        try {

            do {

                System.out.println(
                        "\n===== VEHICLE SERVICE CENTER =====");

                System.out.println(
                        "1. Customer Registration");

                System.out.println(
                        "2. Vehicle Registration");

                System.out.println(
                        "3. Service Booking");

                System.out.println(
                        "4. Service History");

                System.out.println(
                        "5. Customer Booking History");

                System.out.println(
                        "6. Exit");

                System.out.println(
                        "Enter your choice:");

                choice = sc.nextInt();

                switch (choice) {

                case 1:

                    CustomerRegistration.registerCustomer(sc);
                    break;

                case 2:

                    VehicleRegistration.registerVehicle(sc);
                    break;

                case 3:

                    ServiceBooking.bookService(sc);
                    break;

                case 4:

                    ServiceHistory.viewHistory(sc);
                    break;

                case 5:

                    CustomerHistory.viewHistory(sc);
                    break;

                case 6:

                    System.out.println("Exit");
                    break;

                default:

                    System.out.println(
                            "Invalid choice");
                }

            } while (choice != 6);

        } catch (Exception e) {

            e.printStackTrace();
        }

        sc.close();
    }
}
import java.sql.*;
import java.util.Scanner;

public class HotelReservationSystem {

    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String username = "root";
    private static final String password = "archit";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully");

            Connection connection =
                    DriverManager.getConnection(url, username, password);

            System.out.println("Connection Established Successfully");

            Scanner sc = new Scanner(System.in);

            while (true) {

                System.out.println();
                System.out.println("=================================");
                System.out.println("      HOTEL MANAGEMENT SYSTEM");
                System.out.println("=================================");
                System.out.println("1. Reserve a Room");
                System.out.println("2. View Reservations");
                System.out.println("3. Get Room Number");
                System.out.println("4. Update Reservation");
                System.out.println("5. Delete Reservation");
                System.out.println("0. Exit");
                System.out.print("Choose An Option: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        reserveRoom(connection, sc);
                        break;

                    case 2:
                        viewReservations(connection);
                        break;

                    case 3:
                        getRoomNumber(connection, sc);
                        break;

                    case 4:
                        updateReservation(connection, sc);
                        break;

                    case 5:
                        deleteReservation(connection, sc);
                        break;

                    case 0:
                        System.out.println("Thank You!");
                        connection.close();
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid Choice!");
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver Not Found: " + e.getMessage());

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Please Enter Numbers Only!");
        }
    }


    // =====================================================
    // 1. RESERVE ROOM
    // =====================================================

    private static void reserveRoom(Connection connection, Scanner sc) {

        try {

            System.out.print("Enter Guest Name: ");
            String guestName = sc.nextLine();

            System.out.print("Enter Room Number: ");
            int roomNumber = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Contact Number: ");
            String contactNumber = sc.nextLine();

            String query =
                    "INSERT INTO reservations " +
                            "(guest_name, room_number, contact_number) " +
                            "VALUES (?, ?, ?)";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setString(1, guestName);
            preparedStatement.setInt(2, roomNumber);
            preparedStatement.setString(3, contactNumber);

            int rowsAffected =
                    preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Room Reserved Successfully!");
            } else {
                System.out.println("Reservation Failed!");
            }

            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Room Number Must Be A Number!");
        }
    }


    // =====================================================
    // 2. VIEW RESERVATIONS
    // =====================================================

    private static void viewReservations(Connection connection) {

        try {

            String query = "SELECT * FROM reservations";

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            System.out.println();
            System.out.println("========== RESERVATIONS ==========");

            boolean found = false;

            while (resultSet.next()) {

                found = true;

                int reservationId =
                        resultSet.getInt("reservation_id");

                String guestName =
                        resultSet.getString("guest_name");

                int roomNumber =
                        resultSet.getInt("room_number");

                String contactNumber =
                        resultSet.getString("contact_number");

                System.out.println("----------------------------------");
                System.out.println("Reservation ID : " + reservationId);
                System.out.println("Guest Name     : " + guestName);
                System.out.println("Room Number    : " + roomNumber);
                System.out.println("Contact Number : " + contactNumber);
            }

            if (!found) {
                System.out.println("No Reservations Found!");
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // =====================================================
    // 3. GET ROOM NUMBER
    // =====================================================

    private static void getRoomNumber(
            Connection connection, Scanner sc) {

        try {

            System.out.print("Enter Reservation ID: ");
            int reservationId = Integer.parseInt(sc.nextLine());

            String query =
                    "SELECT room_number FROM reservations " +
                            "WHERE reservation_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, reservationId);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if (resultSet.next()) {

                int roomNumber =
                        resultSet.getInt("room_number");

                System.out.println(
                        "Room Number: " + roomNumber
                );

            } else {
                System.out.println("Reservation Not Found!");
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Please Enter A Valid Number!");
        }
    }


    // =====================================================
    // 4. UPDATE RESERVATION
    // =====================================================

    private static void updateReservation(
            Connection connection, Scanner sc) {

        try {

            System.out.print("Enter Reservation ID: ");
            int reservationId = Integer.parseInt(sc.nextLine());

            System.out.print("Enter New Guest Name: ");
            String guestName = sc.nextLine();

            System.out.print("Enter New Room Number: ");
            int roomNumber = Integer.parseInt(sc.nextLine());

            System.out.print("Enter New Contact Number: ");
            String contactNumber = sc.nextLine();

            String query =
                    "UPDATE reservations SET " +
                            "guest_name = ?, " +
                            "room_number = ?, " +
                            "contact_number = ? " +
                            "WHERE reservation_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setString(1, guestName);
            preparedStatement.setInt(2, roomNumber);
            preparedStatement.setString(3, contactNumber);
            preparedStatement.setInt(4, reservationId);

            int rowsAffected =
                    preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(
                        "Reservation Updated Successfully!"
                );
            } else {
                System.out.println("Reservation Not Found!");
            }

            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Please Enter Valid Numbers!");
        }
    }


    // =====================================================
    // 5. DELETE RESERVATION
    // =====================================================

    private static void deleteReservation(
            Connection connection, Scanner sc) {

        try {

            System.out.print("Enter Reservation ID: ");
            int reservationId = Integer.parseInt(sc.nextLine());

            String query =
                    "DELETE FROM reservations " +
                            "WHERE reservation_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, reservationId);

            int rowsAffected =
                    preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(
                        "Reservation Deleted Successfully!"
                );
            } else {
                System.out.println("Reservation Not Found!");
            }

            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Please Enter A Valid Number!");
        }
    }
}



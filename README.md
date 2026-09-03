# 🏨 Hotel Reservation System

A console-based **Hotel Reservation System** developed using **Java, JDBC, and MySQL**.
This project allows users to manage hotel room reservations through a simple menu-driven console application.

## 🚀 Features

* 🏨 Reserve a room
* 📋 View all reservations
* 🔍 Get room number using Reservation ID
* ✏️ Update reservation details
* 🗑️ Delete reservation
* 🚪 Exit the application
* 🗄️ MySQL database integration using JDBC
* 🔐 PreparedStatement for database operations

## 🛠️ Tech Stack

* **Language:** Java
* **Database:** MySQL
* **Connectivity:** JDBC (Java Database Connectivity)
* **IDE:** IntelliJ IDEA
* **Driver:** MySQL Connector/J

## 📂 Project Structure

```text
Hotel-Reservation-System/
│
├── src/
│   └── HotelReservationSystem.java
│
├── README.md
└── mysql-connector-j.jar
```

## 🗄️ Database Setup

Create the database and reservations table in MySQL:

```sql
CREATE DATABASE hotel_db;

USE hotel_db;

CREATE TABLE reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    guest_name VARCHAR(100) NOT NULL,
    room_number INT NOT NULL,
    contact_number VARCHAR(15) NOT NULL
);
```

## ⚙️ JDBC Configuration

Update the database credentials in the Java file if required:

```java
private static final String url =
        "jdbc:mysql://localhost:3306/hotel_db";

private static final String username = "root";
private static final String password = "Your_Password";
```

> **Note:** For security, avoid uploading real database passwords to a public repository. Use environment variables or configuration files for production projects.

## ▶️ How to Run

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Add the **MySQL Connector/J** `.jar` file to the project dependencies.
4. Create the `hotel_db` database and `reservations` table.
5. Update your MySQL username and password.
6. Run `HotelReservationSystem.java`.
7. Select an option from the menu.

## 💻 Application Menu

```text
=================================
      HOTEL MANAGEMENT SYSTEM
=================================
1. Reserve a Room
2. View Reservations
3. Get Room Number
4. Update Reservation
5. Delete Reservation
0. Exit
Choose An Option:
```

## 📌 CRUD Operations

| Operation        | JDBC Method       |
| ---------------- | ----------------- |
| Create / Reserve | `executeUpdate()` |
| Read / View      | `executeQuery()`  |
| Update           | `executeUpdate()` |
| Delete           | `executeUpdate()` |

## 🎯 Learning Outcomes

Through this project, I gained hands-on experience with:

* JDBC connectivity
* MySQL database integration
* `Connection`
* `Statement`
* `PreparedStatement`
* `ResultSet`
* SQL CRUD operations
* Exception handling
* Scanner-based user input
* Building a menu-driven Java application

## 🔮 Future Improvements

* Add room availability checking
* Add customer check-in/check-out
* Add payment management
* Add date and time for reservations
* Add a graphical user interface
* Convert the application into a Spring Boot REST API
* Add authentication and authorization

## 👨‍💻 Author

**Archit Nikam**

Computer Science & Engineering
Java | JDBC | MySQL | Backend Development

---

⭐ If you found this project useful, feel free to star the repository!


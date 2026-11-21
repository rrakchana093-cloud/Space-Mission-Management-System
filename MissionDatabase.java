
import java.sql.*;

// JDBC: Manages database connectivity and mission records.
public class MissionDatabase {

    // Database connection details.
    private static final String URL =
            "jdbc:mysql://localhost:3306/space_mission_db";

    private static final String USER = "root";

    // Change this if your MySQL account has a password.
    private static final String PASSWORD = "";

    // JDBC: Establishes connection with MySQL.
    public static Connection getConnection()
            throws SQLException, ClassNotFoundException {

        // Loads the MySQL JDBC driver.
        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(
                URL, USER, PASSWORD);
    }

    // JDBC: Inserts mission details into the database.
    public static void insertMission(
            SpaceMission mission, String status)
            throws SQLException, ClassNotFoundException {

        String sql =
            "INSERT INTO missions " +
            "(mission_id, destination, fuel, required_fuel, status) " +
            "VALUES (?, ?, ?, ?, ?)";

        // PreparedStatement: Executes a parameterized SQL query.
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, mission.getMissionId());
            ps.setString(2, mission.getDestination());
            ps.setDouble(3, mission.getFuel());

            ps.setDouble(4,
                    mission.calculateFuelRequired());

            ps.setString(5, status);

            ps.executeUpdate();

            System.out.println(
                    "Mission inserted into database successfully!");
        }
    }

    // JDBC: Retrieves and displays all stored mission records.
    public static void displayMissions()
            throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM missions";

        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n=== DATABASE MISSION RECORDS ===");

            while (rs.next()) {

                System.out.println(
                        "Mission ID: " +
                        rs.getString("mission_id"));

                System.out.println(
                        "Destination: " +
                        rs.getString("destination"));

                System.out.println(
                        "Fuel: " +
                        rs.getDouble("fuel"));

                System.out.println(
                        "Required Fuel: " +
                        rs.getDouble("required_fuel"));

                System.out.println(
                        "Status: " +
                        rs.getString("status"));

                System.out.println("----------------------");
            }
        }
    }
}

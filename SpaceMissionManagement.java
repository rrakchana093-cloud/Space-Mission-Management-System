import java.util.Scanner;
// Main class: Accepts user input and manages space mission execution.
public class SpaceMissionManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("=== SPACE MISSION MANAGEMENT ===");
            System.out.println("1. Mars Mission");
            System.out.println("2. Satellite Mission");
            System.out.print("Choose mission type: ");
            int choice = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter mission ID: ");
            String id = sc.nextLine();
            System.out.print("Enter available fuel: ");
            double fuel = sc.nextDouble();
            // Polymorphism: Parent reference holds different subclass objects.
            SpaceMission mission;
            if (choice == 1) {
                mission = new MarsMission(id, fuel);
            } else if (choice == 2) {
                mission = new SatelliteMission(id, fuel);
            } else {
                System.out.println("Invalid mission type!");
                return;
            }
            // Method call: Displays mission details using the parent reference.
            mission.display();
            System.out.println("Required fuel: "
                    + mission.calculateFuelRequired());
            // Runtime polymorphism: Calls the launch method of the actual object.
            mission.launch();
        // Exception handling: Catches insufficient fuel errors.
        } catch (InsufficientFuelException e) {
            System.out.println("Launch Error: " + e.getMessage());
        // Exception handling: Catches invalid fuel values.
        } catch (IllegalArgumentException e) {
            System.out.println("Input Error: " + e.getMessage());
        // Exception handling: Catches invalid numeric input.
        } catch (java.util.InputMismatchException e) {
            System.out.println("Please enter valid numeric input.");
        // Finally block: Executes cleanup and displays the mission count.
        } finally {
            System.out.println("Total missions created: "
                    + SpaceMission.missionCount);
            System.out.println("Mission system closed.");
            System.out.println();
            sc.close();
        }
    }
}

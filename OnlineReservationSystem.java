import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OnlineReservationSystem {

    private static List<User> users = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Enter email: ");
                String email = scanner.nextLine();

                User user = new User(username, password, email);
                users.add(user);
                System.out.println("Registration successful!");
            } 
            else if (choice == 2) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                User user = authenticateUser(username, password);
                if (user != null) {
                    System.out.println("Login successful!");
                    userMenu(user);
                } else {
                    System.out.println("Invalid credentials, please try again.");
                }
            } 
            else if (choice == 3) {
                System.out.println("Exiting the system...");
                break;
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
    }
    private static User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private static void userMenu(User user) {
        while (true) {
            System.out.println("\nWelcome " + user.getUsername());
            System.out.println("1. Make a Reservation");
            System.out.println("2. View Reservations");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            if (choice == 1) {
                System.out.print("Enter service name (e.g., Hotel Room, Flight Ticket): ");
                String serviceName = scanner.nextLine();
                System.out.print("Enter reservation date (e.g., 2024-12-20): ");
                String date = scanner.nextLine();

                Reservation reservation = new Reservation(user, serviceName, date);
                reservations.add(reservation);
                System.out.println("Reservation successful!");
            } 
            else if (choice == 2) {
                System.out.println("\nYour Reservations:");
                boolean hasReservations = false;
                for (Reservation reservation : reservations) {
                    if (reservation.getUser().equals(user)) {
                        System.out.println("Service: " + reservation.getServiceName() + " | Date: " + reservation.getDate());
                        hasReservations = true;
                    }
                }
                if (!hasReservations) {
                    System.out.println("You have no reservations yet.");
                }
            } 
            else if (choice == 3) {
                System.out.println("Logging out...");
                break;
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
    }
}

class User {
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
}
class Reservation {
    private User user;
    private String serviceName;
    private String date;

    public Reservation(User user, String serviceName, String date) {
        this.user = user;
        this.serviceName = serviceName;
        this.date = date;
    }

    public User getUser() { return user; }
    public String getServiceName() { return serviceName; }
    public String getDate() { return date; }
}

package poe.part.pkg1;
import java.util.Scanner;
import java.util.regex.Matcher;// (Farrell,2016).
import java.util.regex.Pattern; // (Farrell,2016).
import java.util.HashMap; // (Farrell,2016).

public class POEPart1 {
    // HashMap to store registered users (username -> password)
    private static HashMap<String, String> registeredUsers = new HashMap<>();
    // HashMap to store user phone numbers (username -> phone)
    private static HashMap<String, String> userPhones = new HashMap<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n===== USER AUTHENTICATION SYSTEM =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    registerNewUser(scanner);
                    break;
                case 2:
                    loginUser(scanner);
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private static void registerNewUser(Scanner scanner) {
        String username, password, phoneNumber;
        
        // Get and validate username
        do {
            System.out.println("Enter username (must contain an underscore and be 5 characters or less):");
            username = scanner.nextLine();
            
            if (!checkUserName(username)) {
                System.out.println("Invalid username format. Please try again.");
            } else if (registeredUsers.containsKey(username)) {
                System.out.println("Username already exists. Please choose another.");
                username = ""; // Force re-entry
            }
        } while (!checkUserName(username) || registeredUsers.containsKey(username));
        
        // Get and validate password
        do {
            System.out.println("Enter password (must be at least 8 characters, include 1 capital letter, 1 special character, and 1 number):");
            password = scanner.nextLine();
            
            if (!checkPasswordComplexity(password)) {
                System.out.println("Password does not meet complexity requirements. Please try again.");
            }
        } while (!checkPasswordComplexity(password));
        
        // Get and check if phone number is entered as needed 
        //ClaudeAI
        do {
            System.out.println("Enter international phone number (country code followed by up to 10 digits):");
            System.out.println("Example: +27123456789");
            phoneNumber = scanner.nextLine();
            
            if (!checkCellPhoneNumber(phoneNumber)) {
                System.out.println("Invalid phone number format. Please try again.");
            }
        } while (!checkCellPhoneNumber(phoneNumber));
        
        // Register the user
        String result = registerUser(username, password, phoneNumber);
        System.out.println(result);
    }
    
    private static void loginUser(Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        
        String status = returnLoginStatus(username, password);
        System.out.println(status);
    }
    
    // Checking if the username is entered as required 
    public static boolean checkUserName(String username) {
        // Check if username contains an underscore and is 5 characters or less
        return username != null && username.length() <= 5 && username.contains("_");
    }
    
    // Checking the length of the password 
    public static boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasCapital = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        
        return hasCapital && hasDigit && hasSpecial;
    }
    
    // Checks if the phone number has a + sign
    public static boolean checkCellPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.startsWith("+")) {
            return false;
        }
        
        // Remove the '+' and check if the rest are digits
        String digitsOnly = phoneNumber.substring(1).replaceAll("\\D", "");
        
        // Check if there's at least one digit for country code and at most 10 digits after that
        if (digitsOnly.length() < 1) {
            return false;
        }
        
        // Ensure there are no more than 10 digits after the country code
        // (simplified implementation - would need refinement for real use)
        int countryCodeLength = Math.min(3, digitsOnly.length() - 1); // Assume 1-3 digit country code
        return (digitsOnly.length() - countryCodeLength) <= 10;
    }
    
    // Checking if the username has an underscore and is not more than 5 characters
    public static String registerUser(String username, String password, String phoneNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }
        
        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Phone number is not correctly formatted, please ensure it starts with a country code and has up to 10 digits.";
        }
        
        if (registeredUsers.containsKey(username)) {
            return "Username already exists, please enter a different username.";
        }
        
        registeredUsers.put(username, password);
        userPhones.put(username, phoneNumber);
        
        return "User registered successfully!\nUsername: " + username + "\nPhone Number: " + phoneNumber;
    }
    
  // This checks if the user name entered is equals to the user name stored 
    public static boolean loginUser(String username, String password) {
        if (!registeredUsers.containsKey(username)) {
            return false;
        }
        
        return registeredUsers.get(username).equals(password);
    }
    
    // Returens the log in message
    public static String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + username + "! You have successfully logged in.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
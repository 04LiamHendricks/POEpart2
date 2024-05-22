import java.util.Scanner;
class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static String username;
    private static String password;
    private static String firstName;
    private static String lastName;
    private static boolean loggedIn = false;
    private static int taskCount = 0;

    public static void main(String[] args) {
        register();
        login();
        displayWelcomeMessage();

        if (loggedIn) {
            showMenu();
        } else {
            System.out.println("Login required. Exiting the application.");
        }
    }

    public static void register() {
        System.out.println("Welcome to User Registration");
        System.out.println("Please enter your details to register:");

        System.out.print("Username: ");
        username = scanner.nextLine();
        if (isValidUsername(username)) {
            System.out.println("Username successfully captured");
        } else {
            System.out.println("Username is not correctly formatted. Please ensure that your username contains an underscore and is no more than 5 characters in length.");
            register();
        }

        System.out.print("Password: ");
        password = scanner.nextLine();

        System.out.print("First Name: ");
        firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        lastName = scanner.nextLine();

        System.out.println("Registration Successful");
    }

    public static boolean isValidUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public static void login() {
        System.out.println("\nWelcome to Login");
        System.out.println("Please enter your credentials to Login:");

        System.out.print("Username: ");
        String inputUsername = scanner.nextLine();

        System.out.print("Password: ");
        String inputPassword = scanner.nextLine();

        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            System.out.println("Login Successful! Welcome, " + firstName + " " + lastName);
            loggedIn = true;
        } else {
            System.out.println("Invalid username or password. Please try again.");
            login();
        }
    }

    public static void displayWelcomeMessage() {
        System.out.println("\nWelcome to EasyKanban");
    }

    public static void showMenu() {
        int choice;
        do {
            System.out.println("\nMain Menu");
            System.out.println("1. Add tasks");

            System.out.println("2. Show report");
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("\nEnter the number of tasks you want to enter: ");
                    int numTasks = scanner.nextInt();
                    scanner.nextLine(); // consume the newline character
                    enterTasks(numTasks);
                    break;
                case 2:
                    System.out.println("\nComing Soon");
                    break;
                case 3:
                    System.out.println("\nThank you for using EasyKanban. Goodbye!");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
                    break;
            }
        } while (choice != 3);
    }

    public static void enterTasks(int numTasks) {
        System.out.println("\nEntering tasks:");
        for (int i = 1; i <= numTasks; i++) {
            System.out.println("\nTask " + taskCount);
            System.out.print("Task Name: ");
            String taskName = scanner.nextLine();

            System.out.print("Task Description: ");
            String taskDescription = scanner.nextLine();
            if (taskDescription.length() > 50) {
                System.out.println("Please enter a task description of less than 50 characters");
                i--;
                continue;
            } else {
                System.out.println("Task successfully captured");
            }

            System.out.print("Developer First Name: ");
            String devFirstName = scanner.nextLine();

            System.out.print("Developer Last Name: ");
            String devLastName = scanner.nextLine();

            System.out.print("Task Duration (in hours): ");
            double taskDuration = scanner.nextDouble();
            scanner.nextLine(); // consume the newline character

            String taskID = generateTaskID(taskName, taskCount, devLastName);

            System.out.println("Task ID: " + taskID);

            showTaskStatusMenu();

            taskCount++;
        }
        System.out.println("All tasks entered.");
    }

    public static String generateTaskID(String taskName, int taskNumber, String devLastName) {
        String taskID = taskName.substring(0, 2).toUpperCase() + ":" + taskNumber + ":" + devLastName.substring(devLastName.length() - 3).toUpperCase();
        return taskID;
    }

    public static void showTaskStatusMenu() {
        System.out.println("Task Status Menu");
        System.out.println("1. To Do");
        System.out.println("2. Done");
        System.out.println("3. Doing");
        System.out.print("Select the task status: ");
        int statusChoice = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        switch (statusChoice) {
            case 1:
                System.out.println("Task status updated to: To Do");
                break;
            case 2:
                System.out.println("Task status updated to: Done");
                break;
            case 3:
                System.out.println("Task status updated to: Doing");
                break;
            default:
                System.out.println("Invalid status choice. Task status remains unchanged.");
                break;
        }
    }
}
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class DirectoryManager {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option;
        do {
            System.out.println("\n=== Directory Management System ===");
            System.out.println("[1] List Files");
            System.out.println("[2] Create Directory");
            System.out.println("[3] Change Directory");
            System.out.println("[4] Exit");
            System.out.print("Select an option (1 to 4): ");

            option = getNumericInput();

            switch (option) {
                case 1 -> listFilesMenu();
                case 2 -> createDirectory();
                case 3 -> changeDirectory();
                case 4 -> System.out.println("Exiting program...");
                default -> System.out.println("Invalid input. Please enter a number from 1 to 4.");
            }
        } while (option != 4);
    }

    static void listFilesMenu() {
        System.out.println("\n[1] List All Files");
        System.out.println("[2] List by Extension");
        System.out.println("[3] List by Pattern");
        System.out.print("Enter choice (1 to 3): ");

        int choice = getNumericInput();

        switch (choice) {
            case 1 -> listAllFiles();
            case 2 -> listByExtension();
            case 3 -> listByPattern();
            default -> System.out.println("Invalid choice. Please enter a number from 1 to 3.");
        }
    }

    static void listAllFiles() {
        File currentDir = new File(".");
        File[] files = currentDir.listFiles();

        boolean found = false;
        if (files != null) {
            for (File file : files) {
                if (!file.getName().equals(".") && !file.getName().equals("..")) {
                    System.out.println(file.getName());
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No files found in the current directory.");
        }
    }

    static void listByExtension() {
        System.out.print("Enter file extension (e.g., txt): ");
        String ext = scanner.nextLine().trim();

        File currentDir = new File(".");
        File[] files = currentDir.listFiles();

        boolean found = false;
        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith("." + ext)) {
                    System.out.println(file.getName());
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No matching files found.");
        }
    }

    static void listByPattern() {
        System.out.print("Enter pattern (e.g., log): ");
        String pattern = scanner.nextLine().trim();

        File currentDir = new File(".");
        File[] files = currentDir.listFiles();

        boolean found = false;
        if (files != null) {
            for (File file : files) {
                if (file.getName().contains(pattern)) {
                    System.out.println(file.getName());
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No files matched the pattern.");
        }
    }

    static void createDirectory() {
        System.out.print("Enter directory name: ");
        String dirName = scanner.nextLine().trim();

        File newDir = new File(dirName);
        if (newDir.mkdir()) {
            System.out.println("Directory created successfully.");
        } else {
            System.out.println("Error: Directory may already exist or cannot be created.");
        }
    }

    static void changeDirectory() {
        System.out.println("\n=== Change Directory ===");
        System.out.println("[1] Move to Parent Directory");
        System.out.println("[2] Move to Root Directory");
        System.out.println("[3] Enter Custom Path");
        System.out.print("Select an option: ");

        int choice = getNumericInput();

        try {
            switch (choice) {
                case 1 -> {
                    String parent = new File(".").getCanonicalFile().getParent();
                    System.setProperty("user.dir", parent);
                    System.out.println("Moved to parent directory: " + parent);
                }
                case 2 -> {
                    System.setProperty("user.dir", "C:\\");
                    System.out.println("Moved to root directory: C:\\");
                }
                case 3 -> {
                    System.out.print("Enter full path: ");
                    String path = scanner.nextLine().trim();
                    if (Files.exists(Paths.get(path))) {
                        System.setProperty("user.dir", path);
                        System.out.println("Changed to: " + path);
                    } else {
                        System.out.println("Error: Path not found.");
                    }
                }
                default -> System.out.println("Invalid option.");
            }
        } catch (IOException e) {
            System.out.println("Error while changing directory: " + e.getMessage());
        }
    }

    static int getNumericInput() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

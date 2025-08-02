package myPackage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DirectoryManagerSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static File currentDir = new File(System.getProperty("user.dir"));

    public static void main(String[] args) {
        mainMenu();
    }

    private static void mainMenu() {
        int option;
        do {
            System.out.println("\n=== Directory Management System ===");
            System.out.println("[1] List Files");
            System.out.println("[2] Create Directory");
            System.out.println("[3] Change Directory");
            System.out.println("[4] Exit");
            System.out.print("Select an option: ");
            option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1 -> listFilesMenu();
                case 2 -> createDirectory();
                case 3 -> changeDirectory();
                case 4 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid option. Try again.");
            }
        } while (option != 4);
    }

    private static void listFilesMenu() {
        System.out.println("\n[1] List All Files");
        System.out.println("[2] List by Extension");
        System.out.println("[3] List by Pattern");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1 -> listAllFiles();
            case 2 -> listByExtension();
            case 3 -> listByPattern();
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void listAllFiles() {
        File[] files = currentDir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("No files found.");
        }
    }

    private static void listByExtension() {
        System.out.print("Enter file extension (e.g., .txt): ");
        String ext = scanner.nextLine();
        File[] files = currentDir.listFiles((dir, name) -> name.endsWith(ext));
        if (files != null && files.length > 0) {
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("No matching files found.");
        }
    }

    private static void listByPattern() {
        System.out.print("Enter pattern (e.g., moha): ");
        String pattern = scanner.nextLine();
        File[] files = currentDir.listFiles((dir, name) -> name.contains(pattern));
        if (files != null && files.length > 0) {
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("No files matched the pattern.");
        }
    }

    private static void createDirectory() {
        System.out.print("Enter directory name: ");
        String dirName = scanner.nextLine();
        File newDir = new File(currentDir, dirName);
        if (newDir.exists()) {
            System.out.println("Directory already exists.");
        } else if (newDir.mkdirs()) {
            System.out.println("Directory created successfully.");
        } else {
            System.out.println("Failed to create directory.");
        }
    }

    private static void changeDirectory() {
        System.out.println("\n=== Change Directory ===");
        System.out.println("[1] Move to Parent Directory");
        System.out.println("[2] Move to Root Directory");
        System.out.println("[3] Enter Custom Path");
        System.out.print("Select an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1 -> {
                currentDir = currentDir.getParentFile();
                System.out.println("Moved to parent directory: " + currentDir.getAbsolutePath());
            }
            case 2 -> {
                currentDir = new File("C:\\");
                System.out.println("Moved to root directory: " + currentDir.getAbsolutePath());
            }
            case 3 -> {
                System.out.print("Enter full path to directory: ");
                String path = scanner.nextLine();
                File customDir = new File(path);
                if (customDir.exists() && customDir.isDirectory()) {
                    currentDir = customDir;
                    System.out.println("Changed to: " + currentDir.getAbsolutePath());
                } else {
                    System.out.println("Error: Invalid or inaccessible path.");
                }
            }
            default -> System.out.println("Invalid option.");
        }
    }
}


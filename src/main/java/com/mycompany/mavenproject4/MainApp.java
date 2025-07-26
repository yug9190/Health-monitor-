package com.mycompany.mavenproject4;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HealthMonitor monitor = new HealthMonitor();
        monitor.loadTestData(); // Load default test data

        int choice;
        do {
            System.out.println("\n========= Health Monitor Menu =========");
            System.out.println("1. Add new record");
            System.out.println("2. Search by Patient ID");
            System.out.println("3. Display statistics");
            System.out.println("4. Display all records");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    monitor.addNewRecord(sc);
                    break;
                case 2:
                    System.out.print("Enter Patient ID: ");
                    String id = sc.nextLine();
                    monitor.searchById(id);
                    break;
                case 3:
                    monitor.displayStatistics();
                    break;
                case 4:
                    monitor.displayAllRecords();
                    break;
                case 9:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 9);

        sc.close();
    }
}

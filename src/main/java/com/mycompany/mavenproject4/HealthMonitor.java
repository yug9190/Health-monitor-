package com.mycompany.mavenproject4;

import java.util.*;

public class HealthMonitor {
    private ArrayList<PatientRecord> records = new ArrayList<>();

    public void loadTestData() {
        String[] ids = { "S20", "S10", "S35", "S45", "S15", "S25", "S05" };
        int[] sbp = { 70, 100, 120, 140, 150, 100, 180 };
        int[] dbp = { 50, 70, 80, 90, 100, 22, 100 };

        for (int i = 0; i < ids.length; i++) {
            records.add(new PatientRecord(ids[i], sbp[i], dbp[i]));
        }
        sortRecords();
    }

    public void addNewRecord(Scanner sc) {
        System.out.print("Enter Patient ID: ");
        String id = sc.nextLine().trim();

        int sbp = getValidatedInput(sc, "Enter SBP (systolic): ");
        int dbp = getValidatedInput(sc, "Enter DBP (diastolic): ");

        records.add(new PatientRecord(id, sbp, dbp));
        sortRecords();
        System.out.println("Record added.");
    }

    private int getValidatedInput(Scanner sc, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value > 0)
                    break;
            }
            System.out.println("Invalid input. Please enter a positive integer.");
            sc.nextLine(); // clear invalid input
        }
        sc.nextLine(); // consume newline
        return value;
    }

    public void displayAllRecords() {
        if (records.isEmpty()) {
            System.out.println("No records to display.");
            return;
        }
        System.out.println("\nPatient Records:");
        for (PatientRecord pr : records) {
            System.out.println(pr);
        }
    }

    public void searchById(String id) {
        boolean found = false;
        for (PatientRecord pr : records) {
            if (pr.id.equalsIgnoreCase(id)) {
                System.out.println("Record found:\n" + pr);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Patient ID not found.");
        }
    }

    public void displayStatistics() {
        if (records.isEmpty()) {
            System.out.println("No records available for statistics.");
            return;
        }

        List<Double> maps = new ArrayList<>();
        for (PatientRecord pr : records)
            maps.add(pr.map);

        double min = Collections.min(maps);
        double max = Collections.max(maps);
        double avg = maps.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double median = calculateMedian(maps);

        System.out.printf("Min MAP: %.2f\n", min);
        System.out.printf("Max MAP: %.2f\n", max);
        System.out.printf("Average MAP: %.2f\n", avg);
        System.out.printf("Median MAP: %.2f\n", median);
    }

    private double calculateMedian(List<Double> values) {
        Collections.sort(values);
        int n = values.size();
        if (n % 2 == 1)
            return values.get(n / 2);
        else
            return (values.get(n / 2 - 1) + values.get(n / 2)) / 2.0;
    }

    private void sortRecords() {
        records.sort(Comparator.comparing(r -> r.id));
    }
}

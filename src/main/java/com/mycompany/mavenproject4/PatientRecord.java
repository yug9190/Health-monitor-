package com.mycompany.mavenproject4;

public class PatientRecord {
    String id;
    int sbp;
    int dbp;
    double map;
    String classification;

    public PatientRecord(String id, int sbp, int dbp) {
        this.id = id;
        this.sbp = sbp;
        this.dbp = dbp;
        this.map = calculateMAP();
        this.classification = classifyMAP();
    }

    private double calculateMAP() {
        return dbp + (sbp - dbp) / 3.0;
    }

    private String classifyMAP() {
        if (map < 70)
            return "Low";
        else if (map <= 110)
            return "Normal";
        else
            return "High";
    }

    @Override
    public String toString() {
        return String.format("%s\tSBP: %d\tDBP: %d\tMAP: %.2f\tClass: %s", id, sbp, dbp, map, classification);
    }
}

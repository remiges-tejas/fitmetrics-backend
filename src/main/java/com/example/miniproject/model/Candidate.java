package com.example.miniproject.model;

public class Candidate {
    private String phone;
    private String name;
    private int height; // in cm
    private double weight; // in kg

    public Candidate(String phone,  String name, int height, double weight) {
        this.phone = phone;
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double processBMI() {
        double heightInMeters = height / 100.0;
        double bmi = weight / (heightInMeters * heightInMeters);
        return Math.round(bmi * 100.0) / 100.0; // Round to 2 decimal places
    }

    @Override
    public String toString() {
        return phone + ", " + name + ", " + height + ", " + weight;
    }
}
package com.example.miniproject.security;

import java.util.Scanner;

public class ProductKeyValidator {
    private static final String VALID_PRODUCT_KEY = "Tejas123"; // Replace with your actual product key
    private static boolean isProductKeyValidated = false; // Track validation state

    public static boolean validateProductKey() {
        if (isProductKeyValidated) {
            return true; // Skip validation if already validated
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the product key to continue: ");
            String inputKey = scanner.nextLine();

            if (VALID_PRODUCT_KEY.equals(inputKey)) {
                isProductKeyValidated = true; // Mark as validated
                return true;
            } else {
                return false; // Invalid product key
            }
        }

    }
}
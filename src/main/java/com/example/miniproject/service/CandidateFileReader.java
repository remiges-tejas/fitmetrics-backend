package com.example.miniproject.service;

import com.example.miniproject.model.Candidate;
import com.example.miniproject.exception.FileValidationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@Service
public class CandidateFileReader {

    public List<Candidate> candidateReader(MultipartFile file) throws IOException, FileValidationException {
        List<Candidate> candidates = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));

        String line;
        // Skip the header line
        br.readLine(); // This line will skip the header

        int lineNumber = 2; // Start from 2 because we skipped the header

        List<String> errors = new ArrayList<>(); // Collect errors

        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            if (values.length == 4) { // Ensure correct number of columns
                String phone = values[0].trim();
                String name = values[1].trim();

                // Validate phone number
                if (!isValidPhoneNumber(phone)) {
                    errors.add("Invalid phone number at line " + lineNumber + ": " + phone);
                }

                // Validate name
                if (!isValidName(name)) {
                    errors.add("Invalid name at line " + lineNumber + ": " + name);
                }

                try {
                    int height = Integer.parseInt(values[2].trim());

                    // Validate height
                    if (height <= 0) {
                        errors.add("Invalid height at line " + lineNumber + ": " + height);
                    }

                    double weight = Double.parseDouble(values[3].trim());

                    // Validate weight
                    if (weight < 0) {
                        errors.add("Invalid weight at line " + lineNumber + ": " + weight);
                    }

                    // If there are no errors, add candidate
                    if (errors.isEmpty()) {
                        candidates.add(new Candidate(phone, name, height, weight));
                    }
                } catch (NumberFormatException e) {
                    errors.add("Invalid number format at line " + lineNumber + ": " + Arrays.toString(values));
                }
            } else {
                errors.add("Incorrect number of columns at line " + lineNumber + ": " + Arrays.toString(values));
            }
            lineNumber++;
        }

        br.close();

        if (!errors.isEmpty()) {
            throw new FileValidationException(String.join("; ", errors)); // Throw custom exception with all collected errors
        }

        return candidates;
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d{10}"); // Ensure it is exactly 10 digits
    }

    private boolean isValidName(String name) {
        return !name.isEmpty() && name.matches("[a-zA-Z ]+"); // Ensure it contains only letters and spaces
    }
}
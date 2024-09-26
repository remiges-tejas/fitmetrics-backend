package com.example.miniproject.controller;

import com.example.miniproject.model.BmiResult;
import com.example.miniproject.service.BmiService;
import com.example.miniproject.exception.FileValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/bmi")
public class BmiController {

    @Autowired
    private BmiService bmiService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        // Check if there are already existing results in the database
        List<BmiResult> existingResults = bmiService.getAllBmiResults();
        if (!existingResults.isEmpty()) {
            return ResponseEntity.badRequest().body("Data has already been uploaded. Please do not upload again."); // Prevent
                                                                                                                    // duplicate
                                                                                                                    // uploads
        }

        try {
            List<BmiResult> results = bmiService.calculateBmiFromFile(file);
            return ResponseEntity.ok(results); // Return the list of results if successful
        } catch (FileValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Return validation errors
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error processing file: " + e.getMessage());
        }
    }

    @GetMapping("/results")
    public ResponseEntity<?> getBmiResults() {
        List<BmiResult> results = bmiService.getAllBmiResults();

        if (results.isEmpty()) {
            return ResponseEntity.ok("No data found. Please upload a file to add data."); // No data found message
        }

        return ResponseEntity.ok(results); // Return existing results
    }

    // Route that delete the bmi data
    @DeleteMapping("/delete")
    public String deleteBmiResult() {
        List<BmiResult> isExistsBmiData = bmiService.getAllBmiResults();
        if (!isExistsBmiData.isEmpty()) {
            boolean isDelete = bmiService.deleteBmiResults();
            if (isDelete) {
                return "BMI Data Deleted Successfully!";
            }
        } else {

            return "Data Not Found";
        }
        return "BMI Data Deleted Successfully!";
    }
}
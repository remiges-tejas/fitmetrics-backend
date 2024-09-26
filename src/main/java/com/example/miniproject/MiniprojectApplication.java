package com.example.miniproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiniprojectApplication {

public static void main(String[] args) {
SpringApplication.run(MiniprojectApplication.class, args);
}

}

// package com.example.miniproject;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import com.example.miniproject.security.ProductKeyValidator;

// @SpringBootApplication
// public class MiniprojectApplication {

// public static void main(String[] args) {
// // Validate product key before running the application
// if (!ProductKeyValidator.validateProductKey()) {
// System.out.println("Invalid product key. The application will exit.");
// System.exit(1); // Exit if the product key is invalid
// }

// // Proceed with starting the Spring Boot application
// SpringApplication.run(MiniprojectApplication.class, args);
// }
// }
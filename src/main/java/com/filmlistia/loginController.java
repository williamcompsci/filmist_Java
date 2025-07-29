package com.filmlistia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;

public class loginController {

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_register;

    @FXML
    private PasswordField input_password;

    @FXML
    private TextField input_username;

    @FXML
    private Text txt_welcomeSubtitle;

    @FXML
    private Text txt_welcomeTitle;

    private ArrayList<User> users = new ArrayList<>();
    // Default admin account

    int numberOfUsers = 0;

    @FXML
    void initialize() {
        loadUsers();
        input_username.requestFocus();
        //Sets focus to prompt entering username
    }
    public static String loggedInUser; // Keep track of the logged-in user for later usage mostly in directory and file management ssytems
    @FXML
    void handleLogin(ActionEvent event) {
        String username = input_username.getText();
        String password = input_password.getText();
        //Temp variables to store entered username and password

        User user = usernameSearch(username);

        if (user != null && user.getPassword().equals(password)) {
            loginStatus("Login Successful", "Welcome, " + user.getUsername() + "!");
            System.out.println("Successful login with username: " + user.getUsername() + ". " +  "Sign in permitted. Logged in user set to: " + "\"" + user.getUsername() + "\"" + " with userID: " + user.getUserID());
            // Display a success message if the login is successful
            loggedInUser = user.getUsername();
            openMainMenu(); // Opens the main menu after successful login
            Stage loginStage = (Stage) btn_login.getScene().getWindow();

            loginStage.close();
        } else {
            loginStatus("Login Failed", "Invalid username or password! Please try again.");
            input_password.requestFocus(); // Set focus back to the password field for re-entry
            // Display an error message if the login fails
        }
    }

    @FXML
    void handleRegister(ActionEvent event) {
        String username = input_username.getText();
        String password = input_password.getText();

        if (username.isEmpty() || password.isEmpty()) {
            loginStatus("Registration Failed", "Username and password cannot be empty!");
            return; // Exit the method if fields are empty
        } else if (usernameSearch(username) != null) {
            loginStatus("Registration Failed", "Username already exists! Please choose a different username.");
            return; // Exit the method if username already exists
        } else {
            users.add(new User(username, password, numberOfUsers+1));
            loginStatus("Registration Successful", "User registered successfully! You can now log in.");
            input_username.clear();
            input_password.clear();
            input_username.requestFocus();// Reset focus to the username field
            saveUsers(); // Save the updated user list to the file


            // Create a directory for the user that is being registered.
            Path userDir = Paths.get(username);

            try {
                Files.createDirectories(userDir);
                System.out.println("New user registered. User directory created: " + userDir.toAbsolutePath());
            } catch (IOException e) {
                System.out.println("Unable to create user directory");
            }
            numberOfUsers++; // Add user count
            return; // Exit the method after successful registration
        }
    }

    @FXML
    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
            writer.write(String.valueOf(numberOfUsers));
            writer.newLine(); // Write the number of users as the first line for user identificaiton
            for (int i =0; i<users.size(); i++) {
                writer.write(users.get(i).getUsername() + "," + users.get(i).getPassword() + "," + (i+1)); // Write each user's details in the format: username,password,userID
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    @FXML
    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String header = reader.readLine();
            if (header == null) {
                System.out.println("Invalid header, defaulting to 0 users.");
                numberOfUsers = 0; // Default to 0 users if the file is empty or invalid
                return; // Exit the method if the file is empty
            } else {
                numberOfUsers = Integer.parseInt(header); // Insert first line as number of users
            }


            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    users.add(new User(parts[0], parts[1], Integer.parseInt(parts[2])));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
    }

    @FXML

    private void openMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/filmlistia/mainMenu.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Filmist");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error opening main menu: " + e.getMessage());
        }
    }

    @FXML
    private User usernameSearch(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return users.get(i); // Return the user if the username matches
            }
        }
        return null; // Return null if no user is found

    }

    @FXML
    void loginStatus (String message, String details) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(message);
        alert.setHeaderText(null);
        alert.setContentText(details);
        alert.showAndWait();
        // Displays an alert dialog with the given message and details
    }

    private void createUserFile() {
    }

}

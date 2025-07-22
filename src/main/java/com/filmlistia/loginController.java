package com.filmlistia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

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

    @FXML
    void initialize() {
        loadUsers();
        // Default admin account
        users.add(new User("admin", "admin123"));

        input_username.requestFocus();
        //Sets focus to prompt entering username
    }

    @FXML
    void handleLogin(ActionEvent event) {
        String username = input_username.getText();
        String password = input_password.getText();
        //Temp variables to store entered username and password

        User user = usernameSearch(username);

        if (user != null && user.getPassword().equals(password)) {
            loginStatus("Login Successful", "Welcome, " + user.getUsername() + "!");
            // Display a success message if the login is successful

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
            users.add(new User(username, password));
            loginStatus("Registration Successful", "User registered successfully! You can now log in.");
            input_username.clear();
            input_password.clear();
            input_username.requestFocus();// Reset focus to the username field
            saveUsers(); // Save the updated user list to the file
            return; // Exit the method after successful registration
        }
    }

    @FXML
    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
            for (int i =0; i<users.size(); i++) {
                writer.write(users.get(i).getUsername() + "," + users.get(i).getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    @FXML
    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.add(new User(parts[0], parts[1]));
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

}

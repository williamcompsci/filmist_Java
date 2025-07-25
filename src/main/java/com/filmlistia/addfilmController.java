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

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class addfilmController {

    @FXML
    private Button btn_cancelFilmNew;

    @FXML
    private Button btn_confirmFilmNew;

    @FXML
    private TextField input_directorFilmNew;

    @FXML
    private ComboBox<Integer> input_durationFilmNew;

    @FXML
    private TextField input_genreFilmNew;

    @FXML
    private TextField input_titleFilmNew;

    @FXML
    private ComboBox<Integer> input_yearFilmNew;

    @FXML
    private Text txt_Title;



   //Path filePath = Paths.get(username);

    @FXML
    void cancelNewFilm(ActionEvent event) {

    }

    @FXML
    void confirmNewFilm(ActionEvent event) {
        String title = input_titleFilmNew.getText();
        String director = input_directorFilmNew.getText();
        String genre = input_genreFilmNew.getText();
        Integer year = input_yearFilmNew.getValue();
        Integer duration = input_durationFilmNew.getValue();

        if (title.isEmpty() || director.isEmpty() || genre.isEmpty() || year == null || duration == null) {
            // Show an error message if any field is empty
            alert("Error", "Please fill in all fields.");
            return;
        } else {

        }

        // Here you would typically save the film details to a database or file
        //writeFilmToFile();

        // Close the window after saving
        btn_confirmFilmNew.getScene().getWindow().hide();
    }
/*
    private void writeFilmToFile() {
        // Method to write film details to a file
        // use the same as login reg
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("filmlist.txt", true))) { // Remember to add directory
            //writer.write(String.valueOf());
            writer.newLine();
            for int i=1; i < numberOfFilms; i++) {
                writer.write(input_titleFilmNew.getText() + "," + input_genreFilmNew.getText() + "," + input_directorFilmNew.getText() + "," + input_yearFilmNew.getValue() + "," + input_durationFilmNew.getValue() + "," + "false");
                writer.newLine();
            }

            // The last "false" indicates that the film has not been watched yet. This is by default.
            alert("Success", "Film added successfully!");
        } catch (IOException e) {
            alert("Error", "Failed to save film details: " + e.getMessage());
        }
    }
*/
    private void loadFilmList() {
        // Method to load the film list from a file
    }

    @FXML
    private void initialize() {
        int currentYear = java.time.Year.now().getValue();

        // Fill with years from current year down to 1888 since that's the year the first film was made
        for (int i = currentYear; i > 1888; i--) {
            input_yearFilmNew.getItems().add(i);
        }

        // Set default selection to current year
        input_yearFilmNew.setValue(currentYear);


        // Fill with durations from 300 down to 0
        for (int i = 300; i >= 0; i--) {
            input_durationFilmNew.getItems().add(i);
        }

        // Set default selection to 300 since that's a duration most films wouldn't exceed
        input_durationFilmNew.setValue(300);
    }

    private void alert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

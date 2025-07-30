package com.filmlistia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

import static com.filmlistia.filmistController.filmlist;

public class filmEditController {

    @FXML
    private Button btn_cancelFilm;

    @FXML
    private Button btn_confirmFilm;

    @FXML
    private TextField input_directorFilm;

    @FXML
    private ComboBox<Integer> input_durationFilm;

    @FXML
    private TextField input_genreFilm;

    @FXML
    private TextField input_titleFilm;

    @FXML
    private ComboBox<Integer> input_yearFilm;

    @FXML
    private Text txt_Title;


    @FXML
    void cancelNewFilm(ActionEvent event) {
        ((Stage) btn_cancelFilm.getScene().getWindow()).close();

    }

    @FXML
    void confirmNewFilm(ActionEvent event) {
        String title = input_titleFilm.getText();
        String director = input_directorFilm.getText();
        String genre = input_genreFilm.getText();
        Integer year = input_yearFilm.getValue();
        Integer duration = input_durationFilm.getValue();

        if (title.isEmpty() || director.isEmpty() || genre.isEmpty() || year == null || duration == null) {
            // Show an error message if any field is empty
            alert("Error", "Please fill in all fields.");
            return;
        } else {
            // Set the current selected film with the setter methods
            filmlist.get(filmistController.selectedFilmIndex).setFilmTitle(title);
            filmlist.get(filmistController.selectedFilmIndex).setFilmDirector(director);
            filmlist.get(filmistController.selectedFilmIndex).setFilmGenre(genre);
            filmlist.get(filmistController.selectedFilmIndex).setFilmYear(year);
            filmlist.get(filmistController.selectedFilmIndex).setFilmLength(duration);
            filmlist.get(filmistController.selectedFilmIndex).setWatched(false); // Set watched status to false by default
        }

         // Update the table in the filmistController to reflect the new film

        ((Stage) btn_confirmFilm.getScene().getWindow()).close();
    }


    @FXML
    private void initialize() {
        int currentYear = java.time.Year.now().getValue();

        // Fill the input fields with selected film values
        input_titleFilm.setText(filmlist.get(filmistController.selectedFilmIndex).getFilmTitle());
        input_directorFilm.setText(filmlist.get(filmistController.selectedFilmIndex).getFilmDirector());
        input_genreFilm.setText(filmlist.get(filmistController.selectedFilmIndex).getFilmGenre());
        input_yearFilm.setValue(filmlist.get(filmistController.selectedFilmIndex).getFilmYear());
        input_durationFilm.setValue(filmlist.get(filmistController.selectedFilmIndex).getFilmLength());

        // Fill with years from current year down to 1888 since that's the year the first film was made
        for (int i = currentYear; i > 1888; i--) {
            input_yearFilm.getItems().add(i);
        }

        // Fill with durations from 300 down to 0
        for (int i = 300; i >= 0; i--) {
            input_durationFilm.getItems().add(i);
        }
    }

    private void alert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

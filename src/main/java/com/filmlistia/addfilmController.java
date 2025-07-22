package com.filmlistia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class addfilmController {

    @FXML
    private Button btn_cancelFilmNew;

    @FXML
    private Button btn_confirmFilmNew;

    @FXML
    private TextField input_directorFilmNew;

    @FXML
    private Spinner<?> input_durationFilmNew;

    @FXML
    private TextField input_genreFilmNew;

    @FXML
    private TextField input_titleFilmNew;

    @FXML
    private Spinner<?> input_yearFilmNew;

    @FXML
    private Text txt_Title;

    @FXML
    void cancelNewFilm(ActionEvent event) {

    }

    @FXML
    void confirmNewFilm(ActionEvent event) {

    }

    private void writeFilmToFile() {
        // Method to write film details to a file
    }


}

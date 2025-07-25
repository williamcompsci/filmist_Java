package com.filmlistia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class editFilmController {

    @FXML
    private Button btn_cancelFilmNew;

    @FXML
    private Button btn_confirmFilmNew;

    @FXML
    private TextField input_directorFilmNew;

    @FXML
    private ComboBox<?> input_durationFilmNew;

    @FXML
    private TextField input_genreFilmNew;

    @FXML
    private TextField input_titleFilmNew;

    @FXML
    private ComboBox<?> input_yearFilmNew;

    @FXML
    private Text txt_Title;

    @FXML
    void cancelEditFilm(ActionEvent event) {

    }

    @FXML
    void confirmEditFilm(ActionEvent event) {

    }

    private void initialize() {
        // Initialize the controller with any necessary data or settings
        // For example, you might want to load film details into the input fields


    }

}

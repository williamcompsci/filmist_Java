package com.filmlistia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class filmistController {

    @FXML
    private Button btn_addFilm;

    @FXML
    private Button btn_editFilm;

    @FXML
    private Button btn_logout;

    @FXML
    private Button btn_markWatched;

    @FXML
    private MenuItem btn_menupreferences;

    @FXML
    private Button btn_search;

    @FXML
    private TableView<?> displayTable;

    @FXML
    private TextField input_searchBar;

    @FXML
    private MenuItem menu_addfilm;

    @FXML
    private MenuItem menu_createList;

    @FXML
    private MenuItem menu_delete;

    @FXML
    private MenuItem menu_editfilm;

    @FXML
    private MenuItem menu_loadList;

    @FXML
    private MenuItem menu_markwatched;

    @FXML
    private MenuItem menu_saveList;

    @FXML
    private MenuItem menu_saveListAs;

    @FXML
    private TableColumn<?, ?> table_filmDirector;

    @FXML
    private TableColumn<?, ?> table_filmDuration;

    @FXML
    private TableColumn<?, ?> table_filmGenre;

    @FXML
    private TableColumn<?, ?> table_filmStatus;

    @FXML
    private TableColumn<?, ?> table_filmTitle;

    @FXML
    private TableColumn<?, ?> table_filmYear;

    @FXML
    private Text txt_welcome;

    @FXML
    void addFilm(ActionEvent event) {
        openAddFilmDialog();
    }

    @FXML
    void editFilm(ActionEvent event) {

    }

    @FXML
    void markwatched(ActionEvent event) {

    }


    private void openAddFilmDialog() {
        // Logic to open the Add Film dialog
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/filmlistia/addfilmMenu.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Film");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error opening main menu: " + e.getMessage());
        }
    }




}


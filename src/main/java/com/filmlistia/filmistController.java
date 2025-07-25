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

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static com.filmlistia.loginController.loggedInUser;

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
    private MenuItem menu_logout;

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

    public static int filmCount = 0; // Keeping track of the number of films for the current user. Default 0 until file read.

    @FXML
    void addFilm(ActionEvent event) {
        openAddFilmDialog();
    }

    @FXML
    void btn_startsearch(ActionEvent event) {

    }

    @FXML
    void deleteFilm(ActionEvent event) {

    }

    @FXML
    void editFilm(ActionEvent event) {
// Use same UI for editing as for adding a film
        openEditFilmDialog();
    }

    @FXML
    void inspectPreferences(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void markwatched(ActionEvent event) {

    }

    @FXML
    void unkmarkwatched(ActionEvent event) {

    }

    private int numberOfFilms; // Keeping track of the number of films for the current user. Default 0 until file read.


    private ArrayList<Film> filmlist = new ArrayList<>();


    @FXML
    private void initialize() {
        // initialize by loading the information in the txt file and displaying it in the table
        // grab info from txt file from filmlist.txt in user directory
        Path userDirectory = Paths.get(loginController.loggedInUser);

        try (BufferedReader reader = new BufferedReader(new FileReader(userDirectory+ "/filmlist.txt"))) {
            String header = reader.readLine();
            if (header == null) {
                System.out.println("Invalid header, defaulting to 0 users.");
                numberOfFilms = 0; // Default to 0 films if the file is empty or invalid
                return; // Exit the method if the file is empty
            } else {
                numberOfFilms = Integer.parseInt(header); // Insert first line as number of films
            }

// Reading the film data from the file, just like for the users.
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    filmlist.add(new Film(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Boolean.parseBoolean(parts[5])));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading user films: " + e.getMessage());
        }
    }

    private void loadData() {

    }

    private void updateTable() {
        // Logic to update the table with the data

    }

    private void openAddFilmDialog() {
        // Logic to open the Add Film dialog
        // Add a new film with empty details. Then reads this empty film for the addFilmMenu.fxml. Uses setter methods to set the film details.
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

    private void openEditFilmDialog() {
        // Logic to open the edit film dialog
        // Grab currently selected film from the table
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/filmlistia/editFilmMenu.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Edit Film");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error opening main menu: " + e.getMessage());
        }
    }


    private void writeDataToFile() {
        // Logic to write the data to a file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
                writer.write(String.valueOf(numberOfFilms));
                writer.newLine(); // Write the number of users as the first line for user identificaiton
                for (int i =0; i<filmlist.size(); i++) {
                    writer.write(filmlist.get(i).getFilmTitle() + "," + filmlist.get(i).getFilmDirector() + filmlist.get(i).getFilmGenre() + "," +filmlist.get(i).getFilmYear() + "," + filmlist.get(i).getFilmLength() + "," + filmlist.get(i).getWatchStatus()); // Write each user's details in the format:
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("Error saving films: " + e.getMessage());
            }
        }


    }



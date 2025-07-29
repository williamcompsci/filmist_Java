package com.filmlistia;
import javafx.collections.FXCollections;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;



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
    private TableView<Film> displayTable;

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
    private Button btn_refreshtable;

    @FXML
    private Button btn_save;

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


    public static ArrayList<Film> filmlist = new ArrayList<>();
    private Path filmFile;

    @FXML
    void refreshMovieList(ActionEvent event) {
        updateTable();
    }

    @FXML
    void saveMovieList(ActionEvent event) {
        writeDataToFile();
    }

    @FXML
    private void initialize() {
        // initialize by loading the information in the txt file and displaying it in the table
        // grab info from txt file from filmlist.txt in user directory
        Path userDirectory = Paths.get(loginController.loggedInUser);
        filmFile = userDirectory.resolve("filmlist.txt"); // Setting path for filmlist file
        loadData();
        updateTable();


    }

    private void loadData() {

        Path userDirectory = Paths.get(loginController.loggedInUser);
        Path filmFile = userDirectory.resolve("filmlist.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(filmFile.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    filmlist.add(new Film(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Boolean.parseBoolean(parts[5])));
                }
            }
            numberOfFilms = filmlist.size();
        } catch (
                IOException e) { //If it can't read the file, it will create a new one with default values. Which is 0 films. Maybe implement a default film here later with copy function..
            System.err.println("Error loading user films: " + e.getMessage());
            System.out.println("Creating new film file for user: " + loginController.loggedInUser + " with default values.");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filmFile.toFile()))) {
                writer.write(String.valueOf(numberOfFilms));
                writer.newLine();
            } catch (IOException ex) {
                System.err.println("Error creating fimlist.txt: " + ex.getMessage());
            }
        }
        updateTable();

    }



    private void updateTable() {
        // Logic to update the table with the data
            //importing data from arraylist
            table_filmTitle.setCellValueFactory(new PropertyValueFactory<>("filmTitle"));
            table_filmGenre.setCellValueFactory(new PropertyValueFactory<>("filmGenre"));
            table_filmDirector.setCellValueFactory(new PropertyValueFactory<>("filmDirector"));
            table_filmYear.setCellValueFactory(new PropertyValueFactory<>("filmYear"));
            table_filmDuration.setCellValueFactory(new PropertyValueFactory<>("filmLength"));
            table_filmStatus.setCellValueFactory(new PropertyValueFactory<>("watchStatus"));
            // Set the table items to the observable list created from the film list
            ObservableList<Film> filmObservableList = FXCollections.observableArrayList(filmlist);
            // show on table
            displayTable.setItems(filmObservableList);
        }


    private void openAddFilmDialog() {
        // Logic to open the Add Film dialog
        // Add a new film with empty details. Then reads this empty film for the addFilmMenu.fxml. Uses setter methods to set the film details.
        Film newFilm = new Film("", "", "", 0, 0, false);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/filmlistia/filmMenu.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Film");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error opening main menu: " + e.getMessage());
        }
        updateTable();
        writeDataToFile();
    }

    private void openEditFilmDialog() {
        // Logic to open the edit film dialog\

        // Grab currently selected film from the table
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/filmlistia/filmMenu.fxml"));
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
        Path userDirectory = Paths.get(loginController.loggedInUser);
        Path filmFile = userDirectory.resolve("filmlist.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filmFile.toFile()))) {
                for (int i =0; i<filmlist.size(); i++) {
                    writer.write(filmlist.get(i).getFilmTitle() + "," + filmlist.get(i).getFilmDirector()+ "," + filmlist.get(i).getFilmGenre() + "," +filmlist.get(i).getFilmYear() + "," + filmlist.get(i).getFilmLength() + "," + filmlist.get(i).getWatchStatus()); // Write each user's details in the format:
                    writer.newLine();
                }

                System.out.println("Film list saved successfully.");
            } catch (IOException e) {
                System.err.println("Error saving films: " + e.getMessage());
            }
        }


    }



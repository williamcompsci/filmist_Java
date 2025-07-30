package com.filmlistia;

public class Film {
    private String filmTitle;
    private String filmGenre;
    private String filmDirector;
    private int filmYear;
    private int filmLength;
    private boolean watched;
    private int filmID;

    // Constructor method
    public Film(String filmTitle, String filmGenre, String filmDirector, int filmYear, int filmLength, boolean watched, int filmID) {
        this.filmTitle = filmTitle;
        this.filmGenre = filmGenre;
        this.filmDirector = filmDirector;
        this.filmYear = filmYear;
        this.filmLength = filmLength;
        this.watched = watched;
        this.filmID = filmID; // Default ID, can be set later
    }

    //Getter methods

    //title getter
    public String getFilmTitle() {
        return filmTitle;
    }

    //genre getter
    public String getFilmGenre() {
        return filmGenre;
    }

    //director, year, length and watched getters
    public String getFilmDirector() {
        return filmDirector;
    }
    public int getFilmYear() {
        return filmYear;
    }
    public int getFilmLength() {
        return filmLength;
    }
    public boolean getWatchStatus() {
        return watched;
    }
    public int getFilmID() { return filmID;}

    //Setter methods

    //title setter
    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    //genre setter
    public void setFilmGenre(String filmGenre) {
        this.filmGenre = filmGenre;
    }

    //director, year, length and watched setters
    public void setFilmDirector(String filmDirector) {
        this.filmDirector = filmDirector;
    }
    public void setFilmYear(int filmYear) {
        this.filmYear = filmYear;
    }
    public void setFilmLength(int filmLength) {
        this.filmLength = filmLength;
    }
    public void setWatched(boolean watched) {
        this.watched = watched;
    }
    // No set film ID since it is set during construction and should not change so that you can actually track the film

}

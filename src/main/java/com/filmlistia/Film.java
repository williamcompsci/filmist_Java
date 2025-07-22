package com.filmlistia;

public class Film {
    private String filmTitle;
    private String filmGenre;
    private String filmDirector;
    private int filmYear;
    private int filmLength;
    private boolean watched;

    // Constructor method
    public Film(String filmTitle, String filmGenre, String filmDirector, int filmYear, int filmLength, boolean watched) {
        this.filmTitle = filmTitle;
        this.filmGenre = filmGenre;
        this.filmDirector = filmDirector;
        this.filmYear = filmYear;
        this.filmLength = filmLength;
        this.watched = watched;
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
    public boolean isWatched() {
        return watched;
    }

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

}

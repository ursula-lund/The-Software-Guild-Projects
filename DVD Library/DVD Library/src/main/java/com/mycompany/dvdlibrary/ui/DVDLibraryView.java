/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.ui;

import com.mycompany.dvdlibrary.dao.DVDLibraryDaoException;
import com.mycompany.dvdlibrary.dto.Movie;
import java.util.List;

/**
 *
 * @author ursul
 */
public class DVDLibraryView {

    private UserIO io;

    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    public int getLibrarySelection() {

        io.print("1. Add new DVD to Library.\n ");
        io.print("2. Remove DVD from Library.\n");
        io.print("3. Show Title info.\n");
        io.print("4. Edit DVD info.\n");
        io.print("5. List all Titles.\n");
        io.print("6. Exit.\n");

        return io.readInt("Please select from above choices: ", 1, 6);

    }

    public Movie getNewTitleInfo() {

        String title = io.readString("Please enter DVD title\n");
        int releaseDate = io.readInt("Please enter Release Date\n");
        String mpaaRate = io.readString("Please enter MPAA Rating\n");
        String directorName = io.readString("Please enter the Director's Name\n");
        String studio = io.readString("Please enter Studio\n");
        String userNote = io.readString("Please enter DVD information\n");

        Movie currentMovie = new Movie(title);
        
        currentMovie.setTitle(title);
        currentMovie.setDirectorName(directorName);
        currentMovie.setStudio(studio);
        currentMovie.setReleaseDate(releaseDate);
        currentMovie.setMpaaRate(mpaaRate);
        currentMovie.setUserNote(userNote);
        return currentMovie;
    }

    public Movie editMovie(Movie m) {
        
       // String title = io.readString("Please edit title (or press enter to skip) \n");
        String directorName = io.editString("Please edit Director (or press enter to skip) \n", m.getDirectorName() );
        String studio = io.editString("Please edit studio (or press enter to skip) \n", m.getStudio());
        int releaseDate = io.editInt( "Please edit release date (or press enter to skip) \n", m.getReleaseDate());
        String mpaaRate = io.editString( "Please edit Mpaa Rating(or press enter to skip) \n", m.getMpaaRate());
        String userNote = io.editString( "Please enter new user note (or press enter to skip) \n", m.getUserNote());

       Movie editedMovie = new Movie(m.getTitle());
       
       //editedMovie.setTitle(title);
       
        editedMovie.setDirectorName(directorName);
        editedMovie.setStudio(studio);
        editedMovie.setReleaseDate(releaseDate);
       editedMovie.setMpaaRate(mpaaRate);
       editedMovie.setUserNote(userNote);

        return editedMovie;

    }

    public void displayTitleInfo(Movie title) {
        if (title != null) {
            io.print(title.getTitle());
            io.print(title.getDirectorName());
            io.print(title.getStudio());
            io.print(title.getReleaseDate() + ""); 
            io.print(title.getMpaaRate());
            io.print(title.getUserNote());

            io.print("");
        } else {
            io.print("No such Movie.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void getListofTitles(List<Movie> titleList) {
        for (Movie currentMovie : titleList) {

            String titleInfo = String.format("#%s : %s %s",
                    currentMovie.getTitle(),
                    currentMovie.getDirectorName(),
                    currentMovie.getStudio(),
                    currentMovie.getReleaseDate(),
                    currentMovie.getMpaaRate(),
                    currentMovie.getUserNote());

            io.print(titleInfo);
        }
        io.readString("Please hit enter to continue.\n");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===\n");
        io.print(errorMsg);
    }

    public void displayUpdateDVD() {
        io.print("=== Add New DVD ===\n");
    }

    public void displayUpdateSuccess() {
        io.readString("DVD successfully added. Please hit enter to continue\n");
    }

    public void displayAllTitles() {
        io.print("=== Display All Titles ===\n");
    }

    public void displayDVDTitle() {
        io.print("=== Display Title ==\n");
    }

    public void displayTitleEditBanner() {
        io.print("=== Edit Title ===\n");
    }

    public void displayEditSuccess() {
        io.readString("DVD successfully updated. Please hit enter to contine\n");

    }

    public String getUserTitleChoice() {
        return io.readString("Please enter the DVD title\n");
    }

    public void displayExitBanner() {
        io.print("Good bye!!!\n");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!\n");
    }

    public void displayDisplayUserBanner() {
        io.print("=== Display Movie Title ===\n");
    }

    public void displayRemoveBanner() {
        io.print("=== Remove Title ===");
    }

    public void displayRemoveResult(Movie titleInfo) {
        if (titleInfo != null) {
            io.print("Title sucessfullly removed.\n");
        } else {
            io.print("No such Title.\n");
        }
        io.readString("Please hit enter to continue.\n");
    }

    public void displayEditBanner() {
        io.print("=== Edit Title ===");
    }

}

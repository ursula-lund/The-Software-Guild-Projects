/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.dao;

import com.mycompany.dvdlibrary.dto.Movie;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author ursul
 */
public class DVDLibraryFileDao implements DVDLibraryDao {

    private final Map<String, Movie> titles = new HashMap<>();

    public static final String DVD_LIBRARYFILE = "dvdlibrary.txt";
    public static final String DELIMITER = "::";

    @Override
    public Movie addTitle(String title, Movie movie) throws DVDLibraryDaoException {
        loadTitle();
        Movie newTitle = titles.put(title, movie);
        writeLibrary();
        return newTitle;
    }

    @Override
    public Movie getTitle(String title) throws DVDLibraryDaoException {
        loadTitle();
        return titles.get(title);
    }

    @Override
    public Movie removeTitle(String title) throws DVDLibraryDaoException {
        loadTitle();
        Movie removedTitle = titles.remove(title);
        writeLibrary();
        return removedTitle;

    }

    @Override
    public List<Movie> getAllTitles() throws DVDLibraryDaoException {
        loadTitle();
        return new ArrayList<>(titles.values());
    }

    @Override
    public void editDvdInfo(String title, Movie library) throws DVDLibraryDaoException {
        loadTitle();
        titles.put(title, library);
        writeLibrary();
        

    }

    

    private Movie unmarshallMovie(String titleAsText) {

        String[] titleTokens = titleAsText.split(DELIMITER);

        String title = titleTokens[0];

        Movie movieFromFile = new Movie(title);

        movieFromFile.setDirectorName(titleTokens[1]);

        movieFromFile.setStudio(titleTokens[2]);

        movieFromFile.setMpaaRate(titleTokens[3]);

        movieFromFile.setReleaseDate(Integer.parseInt(titleTokens[4]));

        movieFromFile.setUserNote(titleTokens[5]);

        return movieFromFile;

    }

    private void loadTitle() throws DVDLibraryDaoException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader("dvdlibrary.txt")));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException("-_- Could not load library data into memtory.", e);
        }
        String currentLine;
        Movie currentTitle;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            if (!currentLine.isBlank()) {
                currentTitle = unmarshallMovie(currentLine);
                titles.put(currentTitle.getTitle(), currentTitle);
            }
        }
        scanner.close();

    }

    private String marshallTitle(Movie aTitle) {

        String titleAsText = aTitle.getTitle() + DELIMITER;

        titleAsText += aTitle.getDirectorName() + DELIMITER;
        titleAsText += aTitle.getStudio() + DELIMITER;
        titleAsText += aTitle.getMpaaRate() + DELIMITER;
        titleAsText += aTitle.getReleaseDate() + DELIMITER;
        titleAsText += aTitle.getUserNote();
        return titleAsText;

    }

    
        private void writeLibrary() throws DVDLibraryDaoException {
        PrintWriter out;

        try {

            out = new PrintWriter(new FileWriter("dvdlibrary.txt"));
        } catch (IOException e) {
            throw new DVDLibraryDaoException("Could not save title data.", e);
        }
        String titleAsText;

        List<Movie> titleList = new ArrayList(titles.values());
        for (Movie currentTitle : titleList) {
            titleAsText = marshallTitle(currentTitle);
            out.println(titleAsText);
            out.flush();
        }
        out.close();
    }
        
        
    @Override
    public Movie getMovieByTitle(String title) throws DVDLibraryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

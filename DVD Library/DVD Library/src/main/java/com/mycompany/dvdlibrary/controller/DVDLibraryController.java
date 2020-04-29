/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.controller;

import com.mycompany.dvdlibrary.ui.ConsoleIO;
import com.mycompany.dvdlibrary.dao.DVDLibraryFileDao;
import com.mycompany.dvdlibrary.dao.DVDLibraryDao;
import com.mycompany.dvdlibrary.dao.DVDLibraryDaoException;
import com.mycompany.dvdlibrary.dto.Movie;
import com.mycompany.dvdlibrary.ui.DVDLibraryView;
import com.mycompany.dvdlibrary.ui.UserIO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ursul
 */
public class DVDLibraryController {

    private DVDLibraryView view;
    private DVDLibraryDao dao;

    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepRunning = true;
        int librarySelection = 0;
        try {
            while (keepRunning) {

                librarySelection = getLibrarySelection();

                switch (librarySelection) {
                    case 1:
                        createDVD();
                        break;
                    case 2:
                        removeTitle();
                        break;
                    case 3:
                        viewTitleInfo();
                        break;
                    case 4:
                        editTitle();
                        break;
                    case 5:
                        listTitles();
                        break;
                    case 6:
                        keepRunning = false;
                        break;

                    default:
                        unknownCommand();
                }

            }
            exitMessage();

        } catch (DVDLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getLibrarySelection() {
        return view.getLibrarySelection();
    }

    private void createDVD() throws DVDLibraryDaoException {
        view.displayUpdateDVD();
        Movie newTitle = view.getNewTitleInfo();
        dao.addTitle(newTitle.getTitle(), newTitle);
        view.displayUpdateSuccess();
    }

    private void viewTitleInfo() throws DVDLibraryDaoException {
        view.displayDisplayUserBanner();
        String title = view.getUserTitleChoice();
        Movie movie = dao.getTitle(title);
        view.displayTitleInfo(movie);

    }

    private void listTitles() throws DVDLibraryDaoException {
        view.displayAllTitles();
        List<Movie> titleList = dao.getAllTitles();
        view.getListofTitles(titleList);

    }

    private void removeTitle() throws DVDLibraryDaoException {
        view.displayRemoveBanner();
        String title = view.getUserTitleChoice();
        Movie removedTitle = dao.removeTitle(title);
        view.displayRemoveResult(removedTitle);
    }

    private void editTitle() throws DVDLibraryDaoException {

        view.displayEditBanner();
        String title = view.getUserTitleChoice(); //asks user to input Movie they want to edit

        Movie m = dao.getTitle(title);

        view.displayTitleInfo(m);

        if (m != null) {

            Movie editTitle = view.editMovie(m); //editing part
            dao.editDvdInfo(editTitle.getTitle(), editTitle);
            view.displayEditSuccess();
        }
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}

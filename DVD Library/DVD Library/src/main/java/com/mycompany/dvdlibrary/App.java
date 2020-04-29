/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary;

import com.mycompany.dvdlibrary.controller.DVDLibraryController;
import com.mycompany.dvdlibrary.dao.DVDLibraryFileDao;
import com.mycompany.dvdlibrary.dao.DVDLibraryDao;
import com.mycompany.dvdlibrary.ui.ConsoleIO;
import com.mycompany.dvdlibrary.ui.DVDLibraryView;
import com.mycompany.dvdlibrary.ui.UserIO;

/**
 *
 * @author ursul
 */
public class App {
    public static void main(String[] args) {
        UserIO myIo = new ConsoleIO();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DVDLibraryDao myDao = new DVDLibraryFileDao();
        DVDLibraryController controller = new DVDLibraryController(myDao, myView);
            controller.run();
        }
    }


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.dto;

/**
 *
 * @author ursul
 */
public class Movie {
    
    private String title;
    private int releaseDate; 
    private String mpaaRate;
    private String directorName;
    private String studio;
    private String userNote; 

    public Movie(String title) {
        this.title = title;
    }
    
 /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the releaseDate
     */
    public int getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate the releaseDate to set
     */
    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return the mpaaRate
     */
    public String getMpaaRate() {
        return mpaaRate;
    }

    /**
     * @param mpaaRate the mpaaRate to set
     */
    public void setMpaaRate(String mpaaRate) {
        this.mpaaRate = mpaaRate;
    }

    /**
     * @return the directorName
     */
    public String getDirectorName() {
        return directorName;
    }

    /**
     * @param directorName the directorName to set
     */
    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    /**
     * @return the studio
     */
    public String getStudio() {
        return studio;
    }

    /**
     * @param studio the studio to set
     */
    public void setStudio(String studio) {
        this.studio = studio;
    }

    /**
     * @return the userNote
     */
    public String getUserNote() {
        return userNote;
    }

    /**
     * @param userNote the userNote to set
     */
    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }
    
    
}
    

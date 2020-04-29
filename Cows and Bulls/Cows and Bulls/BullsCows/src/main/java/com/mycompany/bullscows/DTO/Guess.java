/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullscows.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author ursul
 */
public class Guess {

    private int gameId;
    private int guessId;
    private int guessNum;
    private int match;
    private int partial;
    private LocalDateTime timeOf;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getGuessId() {
        return guessId;
    }

    public void setGuessId(int guessId) {
        this.guessId = guessId;
    }

    public int getGuessNum() {
        return guessNum;
    }

    public void setGuessNum(int guessNum) {
        this.guessNum = guessNum;
    }

    public int getMatch() {
        return match;
    }

    public void setMatch(int match) {
        this.match = match;
    }

    public int getPartial() {
        return partial;
    }

    public void setPartial(int partial) {
        this.partial = partial;
    }

    public LocalDateTime getTimeOf() {
        return timeOf;
    }

    public void setTimeOf(LocalDateTime timeOf) {
        this.timeOf = timeOf;
    }

}

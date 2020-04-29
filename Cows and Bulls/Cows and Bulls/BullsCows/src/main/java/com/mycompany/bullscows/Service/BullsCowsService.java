/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullscows.Service;

import com.mycompany.bullscows.DTO.Game;
import com.mycompany.bullscows.DTO.Guess;
import com.mycompany.bullscows.dao.GameDao;
import com.mycompany.bullscows.dao.GameDaoException;
import com.mycompany.bullscows.dao.GuessDao;
import com.mycompany.bullscows.dao.GuessDaoException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ursul
 */
@Component
public class BullsCowsService {

    GameDao gameDao;
    GuessDao guessDao;

    @Autowired
    public BullsCowsService(GameDao gameDao, GuessDao guessDao) {
        this.gameDao = gameDao;
        this.guessDao = guessDao;
    }

    private static Random rng = new Random();

    public List<Game> getAllGames() throws GameDaoException {
        List<Game> toCheck = gameDao.getAllGames();
        for (Game toHide : toCheck) {
            hideAnswer(toHide);
        }
        return toCheck;
    }

    public Game beginGame() throws GameDaoException {
        Game toAdd = new Game();
        int toGuess = getBullNumber();
        toAdd.setanswerNumber(toGuess);
        Game newGame = gameDao.newGame(toAdd);
        Game hiddenAnswer = hideAnswer(newGame);
        return hiddenAnswer;
    }

    public Game getGameById(int id) throws GameDaoException {
        if (id <= 0) {
            throw new GameDaoException("Please enter a valid GameId");
        }
        Game toReturn = gameDao.getGameById(id);
        Game hiddenAns = hideAnswer(toReturn);
        return hiddenAns;
    }

    private int getBullNumber() {

        List<Integer> numList = new ArrayList<Integer>();
        numList.add(0);
        numList.add(1);
        numList.add(2);
        numList.add(3);
        numList.add(4);
        numList.add(5);
        numList.add(6);
        numList.add(7);
        numList.add(8);
        numList.add(9);

        int toReturn = 0;

        for (int i = 0; i < 4; i++) {
            toReturn *= 10;
            int index = rng.nextInt(numList.size());
            toReturn += numList.get(index);
            numList.remove(index);

        }

        return toReturn;
    }

    public Guess calcGuess(Integer guess, Integer gameId) throws InvalidGuessException, GuessDaoException, GameDaoException {

        if (guess > 9999) {
            throw new InvalidGuessException("Please enter a number with 4 digits.");
        }
        if (guess < 123) {
            throw new InvalidGuessException("Please enter a number with 4 digits.");
        }
        if (guess == null) {
            throw new InvalidGuessException("Null guess entered.");
        }

        boolean unique = isUnique(guess);
        if (!unique) {
            throw new InvalidGuessException("Please enter 4-digit number with no repeating digits.");
        }

        if (gameId <= 0) {
            throw new InvalidGuessException("Please enter valid gameId");
        }

        Game toGet = gameDao.getGameById(gameId);
        LocalDateTime ofRound = LocalDateTime.now();
        Guess newGuess = new Guess();

        Integer answer = toGet.getanswerNumber();
        int guessCount = 0;
        int bulls = 0;
        int cows = 0;

        String toGuess = guess.toString();
        String trueAnswer = answer.toString();

        int i = 0;
        int j = 0;

        char[] trueArray = trueAnswer.toCharArray();
        char[] guessArray = toGuess.toCharArray();

        for (i = 0; i < guessArray.length; i++) {
            for (j = 0; j < trueArray.length; j++) {
                if (trueArray[j] == guessArray[i]) {
                    if (j == i) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }

            }
        }
        if (bulls == 4) {
            gameDao.setIsOver(gameId);

        }

        guessCount++;
        newGuess.setGuessNum(guess);
        newGuess.setMatch(bulls);
        newGuess.setPartial(cows);
        newGuess.setGameId(gameId);
        newGuess.setTimeOf(ofRound);
        guessDao.addGuess(newGuess);
        return newGuess;
    }

    private boolean isUnique(int x) throws InvalidGuessException {

        boolean unique = true;
        int onesPlace = x % 10;
        int tensPlace = (x / 10) % 10;
        int hundredsPlace = (x / 100) % 10;
        int thousandsPlace = (x / 1000) % 10;

        if (onesPlace == tensPlace || onesPlace == hundredsPlace || onesPlace == thousandsPlace) {
            unique = false;
        }

        if (tensPlace == hundredsPlace || tensPlace == thousandsPlace) {
            unique = false;
        }

        if (hundredsPlace == thousandsPlace) {
            unique = false;
        }

        return unique;
    }

    public List<Guess> getGuessesByGameId(int gameId) throws GuessDaoException {
       
        if (gameId <= 0) {
            throw new GuessDaoException("Please enter positive GameId");
        }

        return guessDao.getGuessesByid(gameId);
    }

    private Game hideAnswer(Game newGame) {
        boolean isOver = newGame.getIsOver();
        if (!isOver) {
            newGame.setanswerNumber(9999);
        }
        return newGame;
    }

}

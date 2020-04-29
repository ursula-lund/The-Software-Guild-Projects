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
import com.mycompany.bullscows.dao.GameTemplateInMem;
import com.mycompany.bullscows.dao.GuessDao;
import com.mycompany.bullscows.dao.GuessDaoException;
import com.mycompany.bullscows.dao.GuessTemplateInMem;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author ursul
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BullsCowsServiceTest {

    @Autowired
    BullsCowsService service;

    public BullsCowsServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {

        GameDao gameDao = new GameTemplateInMem();
        GuessDao guessDao = new GuessTemplateInMem();

        service = new BullsCowsService(gameDao, guessDao);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllGames method, of class BullsCowsService.
     */
    /**
     * Test of beginGame method, of class BullsCowsService.
     */
    @Test
    public void testBeginGameGoldenPath() {

        List<Game> allGames = new ArrayList<>();

        try {
            Game toBegin = service.beginGame();

            toBegin.setGameId(1);
            toBegin.setIsOver(false);
            toBegin.setanswerNumber(6758);

            allGames.add(toBegin);
            Game toCheck = allGames.get(0);

            assertEquals(1, toCheck.getGameId().intValue());
            assertEquals(false, toCheck.getIsOver());
            assertEquals(6758, toCheck.getanswerNumber().intValue());

        } catch (GameDaoException ex) {
            fail("Threw GameDaoException on GoldenPathTest");
        }
    }

    @Test
    public void testGetGameByIdNotOverHiddenAnswer() {
        try {

            Game toRetrieve = service.getGameById(3);

            assertEquals(3, toRetrieve.getGameId().intValue());
            assertEquals(9999, toRetrieve.getanswerNumber().intValue());
            assertEquals(false, toRetrieve.getIsOver());

        } catch (GameDaoException ex) {
            fail("Threw GameDaoException on GoldenPathTest");
        }
    }

    /**
     * Test of getGameById method, of class BullsCowsService.
     */
    @Test
    public void testGetGameByIdIsOver() {
        try {
            Game toRetrieve = service.getGameById(4);

            assertEquals(4, toRetrieve.getGameId().intValue());
            assertEquals(6789, toRetrieve.getanswerNumber().intValue());
            assertEquals(true, toRetrieve.getIsOver());

        } catch (GameDaoException ex) {
            fail("Threw GameDaoException on GoldenPath");
        }

    }

    /**
     * Test of calcGuess method, of class BullsCowsService.
     */
    @Test
    public void testCalcGuessFinishedGamed() {

        Integer guess = 5432;
        Integer gameId = 3;

        try {

            Guess toGet = service.calcGuess(guess, gameId);
            toGet.setGuessId(1);

            assertEquals(3, toGet.getGameId());
            assertEquals(1, toGet.getGuessId());
            assertEquals(5432, toGet.getGuessNum());
            assertEquals(4, toGet.getMatch());
            assertEquals(0, toGet.getPartial());
            assertTrue(LocalDateTime.now().minusSeconds(1).compareTo(toGet.getTimeOf()) < 0);
            assertTrue(LocalDateTime.now().compareTo(toGet.getTimeOf()) >= 0);

        } catch (GameDaoException ex) {
            fail("threw GameDaoException on calcGuess golden path");
        } catch (InvalidGuessException ex) {
            fail("threw InvalidGuessException on golden path test");
        } catch (GuessDaoException ex) {
            fail("threw GuessDaoException on golden path test");
        }

    }

    @Test
    public void testCalcGuessMixedPartialsBulls() {

        //answerNum = 5432;
        Integer guess = 5423;

        try {

            Guess toGet = service.calcGuess(guess, 3);

            assertEquals(3, toGet.getGameId());
            assertEquals(0, toGet.getGuessId());
            assertEquals(5423, toGet.getGuessNum());
            assertEquals(2, toGet.getMatch());
            assertEquals(2, toGet.getPartial());
            assertTrue(LocalDateTime.now().minusSeconds(1).compareTo(toGet.getTimeOf()) < 0);
            assertTrue(LocalDateTime.now().compareTo(toGet.getTimeOf()) >= 0);

        } catch (GameDaoException ex) {
            fail("threw GameDaoException on calcGuess golden path");
        } catch (InvalidGuessException ex) {
            fail("threw InvalidGuessException on golden path test");
        } catch (GuessDaoException ex) {
            fail("threw GuessDaoException on golden path test");
        }

    }

    @Test
    public void testCalcGuessNoMatches() {
        //answerNum = 5432;
        Integer guess = 6098;

        try {

            Guess toGet = service.calcGuess(guess, 3);

            assertEquals(3, toGet.getGameId());
            assertEquals(0, toGet.getGuessId());
            assertEquals(6098, toGet.getGuessNum());
            assertEquals(0, toGet.getMatch());
            assertEquals(0, toGet.getPartial());
            assertTrue(LocalDateTime.now().minusSeconds(1).compareTo(toGet.getTimeOf()) < 0);
            assertTrue(LocalDateTime.now().compareTo(toGet.getTimeOf()) >= 0);

        } catch (GameDaoException ex) {
            fail("threw GameDaoException on calcGuess golden path");
        } catch (InvalidGuessException ex) {
            fail("threw InvalidGuessException on golden path test");
        } catch (GuessDaoException ex) {
            fail("threw GuessDaoException on golden path test");
        }

    }

    public void testCalcGuessAnswerNumGreaterThanFourDigits() {

        try {
            Guess toGet = service.calcGuess(78901, 3);

            fail("Did not throw InvalidGuessException on InvalidGuess Test");
        } catch (InvalidGuessException ex) {

        } catch (GuessDaoException ex) {
            fail("threw GuessDaoException on InvalidGuess Test");
        } catch (GameDaoException ex) {
            fail("threw GuessDaoException on InvalidGuess Test");
        }

    }

    public void testCalcGuessAnswerNumLessThanFourDigits() {

        try {
            Guess toGet = service.calcGuess(103, 3);

            fail("Did not throw InvalidGuessException on InvalidGuess Test");
        } catch (InvalidGuessException ex) {

        } catch (GuessDaoException ex) {
            fail("threw GuessDaoException on InvalidGuess Test");
        } catch (GameDaoException ex) {
            fail("threw GuessDaoException on InvalidGuess Test");
        }
    }

    public void testCalcGuessNullGuess() {
        try {
            Guess toGet = service.calcGuess(null, 3);

            fail("Did not throw InvalidGuessException on InvalidGuess Test");
        } catch (InvalidGuessException ex) {

        } catch (GuessDaoException ex) {
            fail("threw GuessDaoException on InvalidGuess Test");
        } catch (GameDaoException ex) {
            fail("threw GuessDaoException on InvalidGuess Test");
        }

    }

    public void testCalcGuessNegativeGuessNum() {
        try {
            Guess toGet = service.calcGuess(-42, 3);

            fail("Did not throw InvalidGuessException on InvalidGuess Test");
        } catch (InvalidGuessException ex) {

        } catch (GuessDaoException ex) {
            fail("threw GuessDaoException on InvalidGuess Test");
        } catch (GameDaoException ex) {
            fail("threw GuessDaoException on InvalidGuess Test");
        }
    }

    public void testCalcGuessZeroGuessNum() {

        try {
            Guess toGet = service.calcGuess(0, 3);

            fail("Did not throw InvalidGuessException on InvalidGuess Test");
        } catch (InvalidGuessException ex) {

        } catch (GuessDaoException ex) {
            fail("threw GuessDaoException on InvalidGuess Test");
        } catch (GameDaoException ex) {
            fail("threw GuessDaoException on InvalidGuess Test");
        }

    }


    public void testCalcGuessNotUnique() {

        try {
            Guess toGet = service.calcGuess(3333, 3);

            fail("Did not throw InvalidGuessException on InvalidGuess Test");
        } catch (InvalidGuessException ex) {

        } catch (GuessDaoException ex) {
            fail("threw GuessDaoException on InvalidGuess Test");
        } catch (GameDaoException ex) {
            fail("threw GuessDaoException on InvalidGuess Test");
        }

    }

    /**
     * Test of getGuessesByGameId method, of class BullsCowsService.
     */
    @Test
    public void testGetGuessesByGameId() {

        try {
            List<Guess> allGuesses = service.getGuessesByGameId(3);
            Guess toCheck = allGuesses.get(0);

            assertEquals(3, toCheck.getGameId());
            assertEquals(3, toCheck.getMatch());
            assertEquals(8956, toCheck.getGuessNum());
            assertEquals(2, toCheck.getGuessId());
            assertEquals(1, toCheck.getPartial());
            assertEquals(LocalDateTime.of(2019, Month.MARCH, 19, 19, 19), toCheck.getTimeOf());

        } catch (GuessDaoException ex) {
            fail("Threw GuessDaoException on GoldenPathTest");
        }
    }

    @Test
    public void testGetGuessByGameIdZero() {

        try {
            List<Guess> allGuesses = service.getGuessesByGameId(0);
            fail("Did not catch GuessDaoException on zero GameId test");
        } catch (GuessDaoException ex) {

        }
    }

    @Test
    public void testGetGuessByGameIdNegativeId() {

        try {
            List<Guess> allGuesses = service.getGuessesByGameId(-3);
            fail("Did not catch GuessDaoException on negative gameId test");
        } catch (GuessDaoException ex) {

        }
    }

    @Test
    public void testGetAllGamesGoldenPath() {

        try {
            List<Game> allGames = service.getAllGames();
            Game toCheck = allGames.get(0);

            assertEquals(3, toCheck.getGameId().intValue());
            assertEquals(9999, toCheck.getanswerNumber().intValue());
            assertEquals(false, toCheck.getIsOver());

            Game next = allGames.get(1);

            assertEquals(4, next.getGameId().intValue());
            assertEquals(6789, next.getanswerNumber().intValue());
            assertEquals(true, next.getIsOver());

        } catch (GameDaoException ex) {
            fail("GameDaoException on getAllGamesGoldenpath");
        }

    }
    
}

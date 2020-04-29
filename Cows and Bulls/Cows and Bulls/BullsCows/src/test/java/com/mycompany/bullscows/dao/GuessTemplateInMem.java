/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullscows.dao;

import com.mycompany.bullscows.DTO.Guess;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author ursul
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GuessTemplateInMem implements GuessDao {

    List<Guess> allGuesses = new ArrayList<>();

    public GuessTemplateInMem() {

        Guess inMem = new Guess();
        inMem.setGameId(3);
        inMem.setMatch(2);
        inMem.setPartial(2);
        inMem.setGuessNum(3567);
        inMem.setGuessId(1);
        inMem.setTimeOf(LocalDateTime.of(2020, Month.MARCH, 10, 10, 10));

        allGuesses.add(inMem);

        Guess mem = new Guess();
        mem.setGameId(3);
        mem.setMatch(3);
        mem.setPartial(1);
        mem.setGuessNum(8956);
        mem.setGuessId(2);
        mem.setTimeOf(LocalDateTime.of(2019, Month.MARCH, 19, 19, 19));

        allGuesses.add(mem);

        Guess inMem2 = new Guess();
        inMem.setGameId(2);
        inMem.setMatch(0);
        inMem.setPartial(0);
        
        inMem.setGuessNum(9056);
        inMem.setGuessId(3);
        inMem.setTimeOf(LocalDateTime.of(2019, Month.MARCH, 20, 20, 20));

        allGuesses.add(inMem2);

    }

    @Override
    public Guess addGuess(Guess toAdd) throws GuessDaoException {
        allGuesses.add(toAdd);
        return toAdd;
    }

    @Override
    public List<Guess> getGuessesByid(Integer gameId) throws GuessDaoException {

        return allGuesses
                .stream()
                .filter(toCheck -> toCheck.getGameId() == gameId)
                .collect(Collectors.toList());
              
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullscows.Controller;

import com.mycompany.bullscows.DTO.Game;
import com.mycompany.bullscows.DTO.Guess;
import com.mycompany.bullscows.Service.BullsCowsService;
import com.mycompany.bullscows.Service.InvalidGuessException;
import com.mycompany.bullscows.dao.GameDaoException;
import com.mycompany.bullscows.dao.GuessDaoException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ursul
 */
@RestController
@RequestMapping("/api")
public class BullsCowsController {

    @Autowired
    BullsCowsService service;

    @PostMapping("/begin")
    public Game beginGame() throws GameDaoException {
        Game newGame = service.beginGame();
        return newGame;
    }
    
    @PostMapping("/guess")
    public Guess guess(@RequestBody Map<String, Integer> postValues) throws InvalidGuessException, GuessDaoException, GameDaoException {     
        int guess = postValues.get("guess");
        int gameId = postValues.get("gameId");
        Guess round = service.calcGuess(guess, gameId);
        return round;
    }    
        
    @GetMapping("/game")
    public List <Game> getAllGames() throws GameDaoException {
       return service.getAllGames();
    }
    
    
    @GetMapping("game/{gameId}")
    public Game getGameById(@PathVariable Integer gameId) throws GameDaoException {
       return service.getGameById(gameId);
        
    }
    
    @GetMapping("rounds/{gameId}")
    public List<Guess> getGuessesByGameId(@PathVariable Integer gameId) throws GuessDaoException {
        return service.getGuessesByGameId(gameId);
    }
  
}


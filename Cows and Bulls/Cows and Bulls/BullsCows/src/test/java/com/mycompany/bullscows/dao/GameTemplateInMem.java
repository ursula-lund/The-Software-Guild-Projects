/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullscows.dao;

import com.mycompany.bullscows.DTO.Game;
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
public class GameTemplateInMem implements GameDao {

    List<Game> allGames = new ArrayList<>();

    public GameTemplateInMem() {

        Game add = new Game();
        add.setGameId(3);
        add.setanswerNumber(5432);
        add.setIsOver(false);
        allGames.add(add);

        Game toAdd = new Game();
        toAdd.setGameId(4);
        toAdd.setIsOver(true);
        toAdd.setanswerNumber(6789);
        allGames.add(toAdd);

    }

    @Override
    public List<Game> getAllGames() {
        return allGames;
    }

    @Override
    public Game getGameById(Integer gameId) throws GameDaoException {
        return allGames
                .stream()
                .filter(toCheck -> toCheck.getGameId().equals(gameId))
                .findAny()
                .orElse(null);
    }

    @Override
    public void setIsOver(Integer gameId) throws GameDaoException {
        Game toSet = allGames.stream()
                .filter(toCheck -> toCheck.getGameId().equals(gameId))
                .findAny()
                .orElse(null);
        toSet.setIsOver(true);

    }

    @Override
    public Game newGame(Game newGame) throws GameDaoException {
        return newGame;
    }

}

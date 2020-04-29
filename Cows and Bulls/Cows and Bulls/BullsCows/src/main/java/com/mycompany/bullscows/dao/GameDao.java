/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullscows.dao;

import com.mycompany.bullscows.DTO.Game;
import java.util.List;

/**
 *
 * @author ursul
 */
public interface GameDao {

    public Game newGame(Game newGame) throws GameDaoException;

    public List<Game> getAllGames() throws GameDaoException;

    public Game getGameById(Integer gameId) throws GameDaoException;

    public void setIsOver(Integer gameId) throws GameDaoException;


}

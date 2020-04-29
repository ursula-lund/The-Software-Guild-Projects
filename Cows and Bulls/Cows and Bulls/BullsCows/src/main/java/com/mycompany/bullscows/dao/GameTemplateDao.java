/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullscows.dao;

import com.mycompany.bullscows.DTO.Game;
import static java.sql.Date.valueOf;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ursul
 */
@Repository
public class GameTemplateDao implements GameDao {

    @Autowired
    JdbcTemplate template;
    
  

    @Override
    public Game newGame(Game newGame) throws GameDaoException {
        
        if (newGame == null) {
                 throw new GameDaoException("Null Game entered");
            }
        
        KeyHolder kh = new GeneratedKeyHolder();

        int rowsAffected = template.update(
                connection -> {

                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO Games (answerNum)"
                            + "VALUE(?)",
                            Statement.RETURN_GENERATED_KEYS);

                   
                    ps.setInt(1, newGame.getanswerNumber());  

                    return ps;
                },
                kh);

        int generatedId = kh.getKey().intValue();
        newGame.setGameId(generatedId);
        return newGame;

    }

    @Override
    public List<Game> getAllGames() throws GameDaoException {

        List<Game> allGames = template.query("SELECT * FROM Games", new GameMapper());

        return allGames;
    }

    @Override
    public Game getGameById(Integer gameId) throws GameDaoException {
         if (gameId == null) {
                 throw new GameDaoException("Null GameId entered");
            }
        Game toReturn = template.queryForObject("SELECT * FROM Games"
                + " WHERE GameId = ?", new GameMapper(), gameId);
        return toReturn;
    }

    @Override
    public void setIsOver(Integer gameId) throws GameDaoException {
        if (gameId == null) {
                 throw new GameDaoException("Null GameId entered");
            }
        template.update("UPDATE Games SET isOver = 1 WHERE gameId = ?", gameId);
    }


    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet row, int rowNum) throws SQLException {

            Game converted = new Game();

            converted.setGameId(row.getInt("GameId"));
            converted.setanswerNumber(row.getInt("answerNum"));
            converted.setIsOver(row.getBoolean("isOver"));
            return converted;
        }

    }

}

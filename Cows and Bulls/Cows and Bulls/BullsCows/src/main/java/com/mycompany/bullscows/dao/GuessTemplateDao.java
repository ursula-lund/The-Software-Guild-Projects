/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullscows.dao;

import com.mycompany.bullscows.DTO.Game;
import com.mycompany.bullscows.DTO.Guess;
import static java.sql.Date.valueOf;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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
public class GuessTemplateDao implements GuessDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public Guess addGuess(Guess toAdd) throws GuessDaoException {
        
         if (toAdd == null) {
            throw new GuessDaoException("Null Guess entered");
        }
        
        KeyHolder kh = new GeneratedKeyHolder();

        int rowsAffected = template.update(
                connection -> {

                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO Guesses(GameId, `Match`, `Partial`, TimeOf, GuessNum) VALUES (?,?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);

                    ps.setInt(1, toAdd.getGameId());
                    ps.setInt(2, toAdd.getMatch());
                    ps.setInt(3, toAdd.getPartial());
                    ps.setString(4, toAdd.getTimeOf().toString());
                    ps.setInt(5, toAdd.getGuessNum());

                    return ps;
                },
                kh);
        toAdd.setGuessId(kh.getKey().intValue());

        return toAdd;
    }

    @Override
    public List<Guess> getGuessesByid(Integer gameId) throws GuessDaoException {
        if (gameId == null) {
            throw new GuessDaoException("Null GameId entered");
        }
        List<Guess> allGuesses = template.query("Select * FROM Guesses WHERE GameId = ?" +
        " ORDER BY TimeOf DESC", new GuessMapper(), gameId);
        return allGuesses;
    }

    private static final class GuessMapper implements RowMapper<Guess> {

        @Override
        public Guess mapRow(ResultSet row, int rowNum) throws SQLException {

            Guess converted = new Guess();

            converted.setGuessId(row.getInt("GameId"));
            converted.setMatch(row.getInt("Match"));
            converted.setPartial(row.getInt("Partial"));
            converted.setGuessId(row.getInt("GuessId"));
            converted.setGuessNum(row.getInt("GuessNum"));
            converted.setTimeOf(row.getDate("TimeOf").toLocalDate().atStartOfDay());
            return converted;
        }

    }

}

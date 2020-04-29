/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullscows.dao;
import com.mycompany.bullscows.DTO.Guess;
import java.util.List;

/**
 *
 * @author ursul
 */
public interface GuessDao {
    
    
    public Guess addGuess(Guess toAdd) throws GuessDaoException;

    public List<Guess> getGuessesByid(Integer gameId)throws GuessDaoException;
}

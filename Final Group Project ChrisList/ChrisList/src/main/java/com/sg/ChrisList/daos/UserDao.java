/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.ChrisList.daos;

import com.sg.ChrisList.models.User;
import java.util.List;

/**
 *
 * @author board
 */
public interface UserDao {
    
    //C
    User createUser(User toAdd) throws DaoException;
    //R
    List<User> getAllUsers();    
    User getUserById(int userId);
    User getUserByUsername(String username);    
    //U
    void updateUser(User user) throws DaoException;    
    //D
    void deleteUser(int userId);
    //CRUD ^_^
}

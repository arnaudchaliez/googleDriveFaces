/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isima.zzdrive.services;

import com.isima.zzdrive.dao.UserDAO;
import com.isima.zzdrive.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jeremy
 */
@Service("UserService")
@Transactional(readOnly = true)
public class UserService {
    
    @Autowired
    UserDAO userDAO;
    
    public UserService() {
    }
    
    @Transactional(readOnly = false)
    public void addUser(User user) {
        getUserDAO().addUser(user);
    }
    
    @Transactional(readOnly = false)
    public void deleteUser(User user) {
        getUserDAO().deleteUser(user);
    }

    @Transactional(readOnly = false)
    public void updateUser(User user) {
        getUserDAO().updateUser(user);
    }

    public User getUserById(int id) {
        return getUserDAO().getUserById(id);
    }

    public List<User> getUsers() {
        return getUserDAO().getUsers();
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    public User find(String username, String password) {
        User user = userDAO.getUserByCredentials(username, password);
        /*if(username != null && username.equals("admin") && password != null && password.equals("admin")) { 
            User user = new User();
            return user;
        }*/
        return user;
    }
    
}

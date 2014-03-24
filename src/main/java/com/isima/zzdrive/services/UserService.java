/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isima.zzdrive.services;

import com.isima.zzdrive.jpa.User;
import javax.ejb.Singleton;

/**
 *
 * @author Jeremy
 */
@Singleton
public class UserService {
    
    public UserService() {
        
    }
    
    public User find(String username, String password) {
        if(username != null && username.equals("admin") && password != null && password.equals("admin")) { 
            User user = new User();
            return user;
        }
        return null;
    }
    
}

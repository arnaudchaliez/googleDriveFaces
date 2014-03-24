/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isima.zzdrive.ejb;

import com.isima.zzdrive.jpa.User;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 *
 * @author Jeremy
 */
@Singleton
@LocalBean
public class SessionService {

    /**
     * Default constructor. 
     */
    public SessionService() {
    }
    
    public User signIn(String login, String password) {
    	return null;
    }
    
}

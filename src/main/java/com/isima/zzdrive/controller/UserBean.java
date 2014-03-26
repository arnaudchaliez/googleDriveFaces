/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isima.zzdrive.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jeremy
 */
@ManagedBean(name="userBean")
@SessionScoped
public class UserBean implements Serializable {

    private String username;    

    public String getUsername() {  
        return username;  
    }
  
    public void setUsername(String username) {  
        this.username = username;  
    }

    public boolean isLogged() {
        return username != null;
    }
}

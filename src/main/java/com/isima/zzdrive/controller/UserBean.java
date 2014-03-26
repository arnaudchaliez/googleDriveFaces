/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isima.zzdrive.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jeremy
 */
@ManagedBean(name="userBean")
@SessionScoped
public class UserBean implements Serializable {

    @Getter
    @Setter
    private String username;    
    
    @Getter
    @Setter
    private int idUser;

    public boolean isLogged() {
        return getUsername() != null;
    }
}

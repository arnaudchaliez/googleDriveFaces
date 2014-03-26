/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isima.zzdrive.controller;

import com.isima.zzdrive.services.UserService;
import com.isima.zzdrive.model.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jeremy
 */
@ManagedBean(name="user")
@RequestScoped
public class UserController implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @ManagedProperty(value="#{UserService}")
    UserService userService;

    List<User> userList;
    
    @Getter
    @Setter
    private int id;
    
    @Getter
    @Setter
    private String name;
    
    @Getter
    @Setter
    private String surname;
    
    public void reset() {
        this.setId(0);
        this.setName("");
        this.setSurname("");
    }

    public List<User> getUserList() {
        userList = new ArrayList<User>();
        userList.addAll(getUserService().getUsers());
        return userList;
    }
}

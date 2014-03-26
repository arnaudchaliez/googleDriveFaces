/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isima.zzdrive.controller;

import com.isima.zzdrive.model.User;
import com.isima.zzdrive.services.UserService;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Jeremy
 */
@RequestScoped
@ManagedBean(name="loginBean")
public class LoginBean {
    
    @ManagedProperty("#{UserService}")
    private UserService userService;
    
    @ManagedProperty("#{userBean}")
    private UserBean userBean;

    private String username;  
      
    private String password; 

    public String getUsername() {  
        return username;  
    }
  
    public void setUsername(String username) {  
        this.username = username;  
    }
  
    public String getPassword() {  
        return password;  
    }
  
    public void setPassword(String password) {  
        this.password = password;  
    }
    
    public UserService getUserService() {
        return userService;
    }
    
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    public UserBean getUserBean() {
        return userBean;
    }
    
    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
  
    public void login(ActionEvent actionEvent) {  
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;
        boolean loggedIn = false;
        
        User current = getUserService().find(username, password);
        
        if(null != current) {  
            loggedIn = true;
            userBean.setUsername(username);
            userBean.setIdUser(current.getIduser());
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);  
        } else {
            loggedIn = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials");  
        }
        
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("loggedIn", loggedIn);
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }
}

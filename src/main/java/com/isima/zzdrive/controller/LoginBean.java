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
import lombok.Getter;
import lombok.Setter;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Jeremy
 */
@RequestScoped
@ManagedBean(name="loginBean")
public class LoginBean {
    
    @Getter
    @Setter
    @ManagedProperty("#{UserService}")
    private UserService userService;
    
    @Getter
    @Setter
    @ManagedProperty("#{userBean}")
    private UserBean userBean;

    @Getter
    @Setter
    private String username;  
      
    @Getter
    @Setter
    private String password;
  
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

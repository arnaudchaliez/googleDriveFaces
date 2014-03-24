/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isima.zzdrive.ejb;

import com.isima.zzdrive.jpa.User;
import com.isima.zzdrive.services.UserService;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Jeremy
 */
@Singleton
@LocalBean
@ManagedBean(name="loginBean")
public class LoginBean {
    
    @EJB
    private UserService userService;

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
  
    public void login(ActionEvent actionEvent) {  
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;
        boolean loggedIn = false;
        
        User user = userService.find(username, password);
        
        if(null != user) {  
            loggedIn = true;
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

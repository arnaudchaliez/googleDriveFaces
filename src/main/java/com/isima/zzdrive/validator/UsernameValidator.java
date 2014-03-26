/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isima.zzdrive.validator;

import com.isima.zzdrive.services.UserService;
import java.util.ResourceBundle;
import javax.ejb.Singleton;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import lombok.Getter;
import lombok.Setter;
/*
@Singleton
@ManagedBean(name="usernameValidator")
public class UsernameValidator implements Validator {

    @Getter
    @Setter
    @ManagedProperty("#{msg}")
    private ResourceBundle msg;
    
    @Getter
    @Setter
    @ManagedProperty("#{UserService}")
    private UserService userService;
    
    private ValidatorException createException(String title, String message) {
        FacesMessage facesMessage = new FacesMessage(title, message);
        facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        return new ValidatorException(facesMessage);
    }
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String username = value.toString().trim();
        
        if (username.isEmpty() || username.length() < 3) {
            throw createException(
                msg.getString("error.validation.username"),
                msg.getString("error.validation.username.length")
            );
        }
        
        if (null != userService.getUserByUsername(username)) {
            throw createException(
                msg.getString("error.validation.username"),
                msg.getString("error.validation.username.exists")
            );
        }
    }
    
}
*/
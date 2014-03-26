/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.controller;

import com.isima.zzdrive.model.User;
import com.isima.zzdrive.service.UserService;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.context.RequestContext;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "userController")
public class UserController {

    @ManagedProperty("#{UserService}")
    @Getter
    @Setter
    private UserService userService;

    @Getter
    @Setter
    @ManagedProperty("#{msg}")
    private ResourceBundle msg;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    public void register(ActionEvent actionEvent) {
        String errorMessage = null;
        try {
            User user = new User(username, firstName, lastName, password);

            userService.addUser(user);

        } catch (NoSuchAlgorithmException ex) {
            errorMessage = msg.getString("error.user.register.password");
        } catch (InvalidKeySpecException ex) {
            errorMessage = msg.getString("error.user.register.password");
        }

        boolean success = (null == errorMessage);
        if (!success) {
            FacesMessage facesMessages = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    msg.getString("error.user.register"),
                    errorMessage
            );
            FacesContext.getCurrentInstance().addMessage(null, facesMessages);
        }

        RequestContext.getCurrentInstance().addCallbackParam("registered", success);
    }

    private ValidatorException createException(String title, String message) {
        FacesMessage facesMessage = new FacesMessage(title, message);
        facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        return new ValidatorException(facesMessage);
    }

    public void validateUsername(FacesContext context, UIComponent component, Object value) {
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

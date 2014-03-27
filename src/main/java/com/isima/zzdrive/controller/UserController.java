/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.controller;

import com.isima.zzdrive.bean.UserBean;
import com.isima.zzdrive.model.User;
import com.isima.zzdrive.service.UserService;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
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
public class UserController implements Serializable {

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
    @ManagedProperty("#{msg}")
    private ResourceBundle msg;

    @Getter
    @Setter
    @ManagedProperty("#{languageController}")
    private LanguageController languageController;

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
    
    @Getter
    @Setter
    private String localeCode;

    private User user;

    private void update(String errorMessage) {
        userService.updateUser(user);
        boolean success = (null == errorMessage);
        FacesMessage facesMessages;
        if (!success) {
            facesMessages = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    msg.getString("error.user.update"),
                    errorMessage
            );
        } else {
            facesMessages = new FacesMessage(
                    FacesMessage.SEVERITY_INFO,
                    msg.getString("user.account.manage"),
                    msg.getString("success.user.update")
            );
        }
        FacesContext.getCurrentInstance().addMessage(null, facesMessages);

        RequestContext.getCurrentInstance().addCallbackParam("updated", success);
    }

    private ValidatorException createException(String title, String message) {
        FacesMessage facesMessage = new FacesMessage(title, message);
        facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        return new ValidatorException(facesMessage);
    }

    @PostConstruct
    public void init() {
        user = userService.getUserById(userBean.getIdUser());
        if (null != user) {
            username = user.getUsername();
            firstName = user.getFirstname();
            lastName = user.getLastname();
            localeCode = user.getLocaleCode();
        }
    }

    public void register(ActionEvent actionEvent) {
        String errorMessage = null;
        try {
            User user = new User(username, firstName, lastName, password);
            user.setLocaleCode("en");
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

    public void updateInfos() {
        user.setFirstname(firstName);
        user.setLastname(lastName);
        update(null);
    }

    public void updatePassword() {
        String errorMessage = null;
        try {
            user.setPasswordToHash(password);
        } catch (NoSuchAlgorithmException ex) {
            errorMessage = msg.getString("error.user.register.password");
        } catch (InvalidKeySpecException ex) {
            errorMessage = msg.getString("error.user.register.password");
        }
        update(errorMessage);
    }

    public void updateLocaleCode() {
        user.setLocaleCode(localeCode);
        languageController.setLocaleCode(localeCode);
        update(null);
    }

    /**
     * Validate a new username Check size and if it is not already used
     *
     * @param context
     * @param component
     * @param value
     */
    public void validateNewUsername(FacesContext context, UIComponent component, Object value) {
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

    public void validateFirstName(FacesContext context, UIComponent component, Object value) {
        String firstname = value.toString().trim();

        if (firstname.isEmpty() || firstname.length() < 3) {
            throw createException(
                    msg.getString("error.validation.firstname"),
                    msg.getString("error.validation.firstname.length")
            );
        }
    }

    public void validateLastName(FacesContext context, UIComponent component, Object value) {
        String lastname = value.toString().trim();

        if (lastname.isEmpty() || lastname.length() < 3) {
            throw createException(
                    msg.getString("error.validation.lastname"),
                    msg.getString("error.validation.lastname.length")
            );
        }
    }

    public void validatePassword(FacesContext context, UIComponent component, Object value) {
        final String pattern = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=[\\S]+$).{5,30})";
        String password = value.toString().trim();

        if (!password.matches(pattern)) {
            throw createException(
                    msg.getString("error.validation.password"),
                    msg.getString("error.validation.password.complexity")
            );
        }
    }
}

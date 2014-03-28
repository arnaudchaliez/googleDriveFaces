/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.controller;

import com.isima.zzdrive.bean.DirectoryBean;
import com.isima.zzdrive.bean.UserBean;
import com.isima.zzdrive.model.User;
import com.isima.zzdrive.service.UserService;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.context.RequestContext;

@RequestScoped
@ManagedBean(name = "loginController")
public class LoginController {

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
    @ManagedProperty("#{directoryBean}")
    private DirectoryBean directoryBean;

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

    public void login(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean loggedIn = false;

        User current = getUserService().find(username, password);

        if (null != current) {
            loggedIn = true;

            // Set user data
            userBean.setUsername(username);
            userBean.setIdUser(current.getIduser());
            userBean.setIdHome(current.getHomeid());

            // Set user preferences (languages)
            languageController.setLocaleCode(current.getLocaleCode());

            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
        } else {
            loggedIn = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("loggedIn", loggedIn);
    }

    public String logout() {
        // Invalidate the session to logout the user
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }
}

    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isima.zzdrive.controller;

import com.isima.zzdrive.bean.DirectoryBean;
import com.isima.zzdrive.bean.UserBean;
import com.isima.zzdrive.model.Directory;
import com.isima.zzdrive.service.DirectoryService;
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

/**
 *
 * @author Arnaud
 */
@ManagedBean(name = "directoryController")
//@SessionScoped
public class DirectoryController {

    @Getter
    @Setter
    @ManagedProperty("#{DirectoryService}")
    DirectoryService directoryService;

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
    @ManagedProperty("#{msg}")
    private ResourceBundle msg;

    @Getter
    @Setter
    private String name;

    public void create(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean created = false;

        int idOwner = userBean.getIdUser();
        int idParent = directoryBean.getCurrentIdDirectory();

        Directory directory = new Directory(name, idParent, idOwner);

        try {
            directoryService.createDirectory(directory);
            created = true;
            msg = new FacesMessage("Succesful ", directory.getName() + " is created.");
        } catch (Exception e) {
            msg = new FacesMessage("Error", name + " cannot be created.");
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        context.addCallbackParam("created", created);
    }

    private ValidatorException createException(String title, String message) {
        FacesMessage facesMessage = new FacesMessage(title, message);
        facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        return new ValidatorException(facesMessage);
    }

    public void validateName(FacesContext context, UIComponent component, Object value) {
        String name = value.toString().trim();

        if (name.isEmpty() || name.length() < 3) {
            throw createException(
                    msg.getString("error.validation.name"),
                    msg.getString("error.validation.name.length"));
        }
    }
}

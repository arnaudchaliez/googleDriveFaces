/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import lombok.Getter;
import lombok.Setter;

@ManagedBean(name = "userBean")
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

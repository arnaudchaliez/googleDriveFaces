/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    
package com.isima.zzdrive.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Arnaud
 */
@ManagedBean(name="directoryBean")
@SessionScoped
public class DirectoryBean implements Serializable {

    @Setter
    private String currentDirectory; 

    
    
    public int getCurrentIdDirectory() {
        return 1;
    }
    
    public void setCurrentIdDirectory() {
        
    }
}

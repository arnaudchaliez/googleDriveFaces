    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isima.zzdrive.controller;

import com.isima.zzdrive.helper.FileHelper;
import com.isima.zzdrive.jpa.File;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Arnaud
 */
@ManagedBean
@SessionScoped
public class FileController {

    DataModel fileModel;
    FileHelper helper;
    public static final String toto = "blabla";
    
    /**
     * Creates a new instance of FileController
     */
    public FileController() {
        //helper = new FileHelper();
    }
    
    public String returnHello() {
        System.out.println("called");
        return "Hello";
    }
     public void handleFileUpload(FileUploadEvent event) {  
        System.out.println("called2");
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        
        byte[] content = event.getFile().getContents();
        System.out.println(content);
        /* File file = new File();
        file.setContent(content);*/
        
       // helper.saveFile(file);
        
        //msg = new FacesMessage("Succesful", event.getFile().getContents().toString() + " is uploaded.");  
        
        //FacesContext.getCurrentInstance().addMessage(null, msg); 
    }  
    
}

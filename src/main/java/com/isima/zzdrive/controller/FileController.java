    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isima.zzdrive.controller;

import com.isima.zzdrive.services.FileService;
import com.isima.zzdrive.model.File;
import com.isima.zzdrive.services.FileService;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Arnaud
 */
@ManagedBean
//@SessionScoped
public class FileController {

    //DataModel fileModel;
    @ManagedProperty("#{FileService}")
    FileService fileService;
    
    public FileService getFileService() {
        return fileService;
    }
    
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
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
        String name = event.getFile().getFileName();
        
        System.out.println(name);
        System.out.println(content);
        
        File file = new File(1, name, 0, "file", content, 1);
       
        try {
            fileService.saveFile(file);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        
        msg = new FacesMessage("Succesful ", file.getName() + " is uploaded.");  
        
        FacesContext.getCurrentInstance().addMessage(null, msg); 
    }  
    
}

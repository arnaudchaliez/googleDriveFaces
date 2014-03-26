    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isima.zzdrive.controller;

import com.isima.zzdrive.services.FileService;
import com.isima.zzdrive.model.File;
import com.isima.zzdrive.services.FileService;
import java.util.ArrayList;
import java.util.List;
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

    public List<File> filesUser() {
        /*
         *  <h:dataTable value="#{fileController.filesUser()}" var="file"  border="1" >
         <h:column>
         <h:link value="#{file.name}" outcome="file">
         <f:param name="id" value="#{file.idfile}" />
         </h:link>
         </h:column>
         </h:dataTable>       
         */
        System.out.println("------------- Files -----------");
        
        List<File> files = getFileService().getFileUser(1);

        for (File file : files) {
            System.out.print(file.getName());
        }
        return files;
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = null;
      
        byte[] content = event.getFile().getContents();
        String name = event.getFile().getFileName();

        File file = new File(name, 0, "file", content, 1);

        try {
            fileService.saveFile(file);
            msg = new FacesMessage("Succesful ", file.getName() + " is uploaded.");
        } catch (Exception e) {
            msg = new FacesMessage("Error", name + " cannot be uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}

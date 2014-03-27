/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.controller;

import com.isima.zzdrive.model.File;
import com.isima.zzdrive.service.FileService;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Arnaud
 */
@ManagedBean
//@SessionScoped
public class FileController {

    @Getter
    @Setter
    @ManagedProperty("#{FileService}")
    FileService fileService;
    
    @Getter
    @Setter
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    
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

        File file = new File(name, 0, "file", content, userBean.getIdUser());

        try {
            fileService.saveFile(file);
            msg = new FacesMessage("Succesful ", file.getName() + " is uploaded.");
        } catch (Exception e) {
            msg = new FacesMessage("Error", name + " cannot be uploaded.");
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}

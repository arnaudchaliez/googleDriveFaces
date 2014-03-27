/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.controller;

import com.isima.zzdrive.bean.UserBean;
import com.isima.zzdrive.model.File;
import com.isima.zzdrive.model.FileRaw;
import com.isima.zzdrive.service.FileService;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


@ManagedBean(name = "fileController")
//@SessionScoped
public class FileController implements Serializable {

    @Getter
    @Setter
    @ManagedProperty("#{FileService}")
    FileService fileService;
    @Getter
    @Setter
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    @Getter
    @Setter
    private List<File> files;
    @Getter
    @Setter
    private File selectedFile;
    @Getter
    @Setter
    private StreamedContent streamedContent = null;
     
    @PostConstruct
    private void init() {
        this.files = filesCurrentUser();
    }

    protected List<File> filesUser(int idUser) {
        List<File> files = getFileService().getFileUser(idUser);
        return files;
    }

    public List<File> filesCurrentUser() {
        List<File> files = filesUser(userBean.getIdUser());
        return files;
    }

    public StreamedContent downloadFile() {
        InputStream stream;
        if (selectedFile != null && selectedFile.getType().equals(FileRaw.TYPE) ) {
            FileRaw file = (FileRaw)selectedFile;
            stream = new ByteArrayInputStream(file.getContent());
            streamedContent = new DefaultStreamedContent(stream, "text/plain", file.getName());
        } else {
            streamedContent = null;
        }
        return streamedContent;
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = null;

        byte[] content = event.getFile().getContents();
        String name = event.getFile().getFileName();

        FileRaw file = new FileRaw(name, 0, "file", content, userBean.getIdUser());

        try {
            fileService.saveFile(file);
            msg = new FacesMessage("Succesful ", file.getName() + " is uploaded.");
        } catch (Exception e) {
            msg = new FacesMessage("Error", name + " cannot be uploaded.");
            System.out.println(e);
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}

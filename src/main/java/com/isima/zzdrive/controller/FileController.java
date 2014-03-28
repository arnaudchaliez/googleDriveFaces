/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.controller;

import com.isima.zzdrive.bean.DirectoryBean;
import com.isima.zzdrive.bean.UserBean;
import com.isima.zzdrive.model.Directory;
import com.isima.zzdrive.model.File;
import com.isima.zzdrive.model.FileRaw;
import com.isima.zzdrive.model.User;
import com.isima.zzdrive.service.FileService;
import com.isima.zzdrive.service.UserService;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ViewScoped
@ManagedBean(name = "fileController")
public class FileController implements Serializable {

    @Getter
    @Setter
    @ManagedProperty("#{FileService}")
    transient FileService fileService;

    @Getter
    @Setter
    @ManagedProperty("#{directoryBean}")
    transient private DirectoryBean directoryBean;

    @Getter
    @Setter
    @ManagedProperty("#{UserService}")
    transient private UserService userService;

    @Getter
    @Setter
    @ManagedProperty("#{userBean}")
    transient private UserBean userBean;

    @Getter
    @Setter
    private List<File> files;

    @Getter
    @Setter
    private File selectedFile;

    @Getter
    @Setter
    private StreamedContent downloadFile;

    @Getter
    @Setter
    private String username;

    @PostConstruct
    private void init() {
        this.files = filesCurrentUser();
        /*InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/img/logo.png");
         downloadFile = new DefaultStreamedContent(stream, "image/png", "logo.png");*/
    }

    protected List<File> filesUser(int idUser) {
        List<File> files = getFileService().getFilesDirectoryUser(idUser, directoryBean.getCurrentIdDirectory());
        return files;
    }

    public List<File> filesCurrentUser() {
        List<File> files = filesUser(userBean.getIdUser());
        return files;
    }

    protected void updateDownloadFile() {
        InputStream stream;
        System.out.println("selectedFile" + selectedFile);
        if (selectedFile != null && selectedFile.getType().equals(FileRaw.TYPE)) {
            FileRaw file = (FileRaw) selectedFile;
            stream = new ByteArrayInputStream(file.getContent());
            downloadFile = new DefaultStreamedContent(stream, "text/plain", file.getName());
            System.out.println("selectedFile" + downloadFile);
        } else {
            downloadFile = null;
        }
        //return downloadFile;
    }

    public void onRowSelect(SelectEvent event) {
        //selectedFile = ((File) event.getObject());
        updateDownloadFile();
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = null;

        byte[] content = event.getFile().getContents();
        String name = event.getFile().getFileName();

        FileRaw file = new FileRaw(name, directoryBean.getCurrentIdDirectory(), "file", content, userBean.getIdUser());

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

    public void shareFile(ActionEvent actionEvent) {

        RequestContext context = RequestContext.getCurrentInstance();

        User user = userService.getUserByUsername(username);

        if (null != user) {
            fileService.shareFile(selectedFile, user);
            context.addCallbackParam("shared", true);
        }
    }
}

/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.bean;

import com.isima.zzdrive.service.DirectoryService;
import java.io.Serializable;
import java.util.AbstractMap;
import com.isima.zzdrive.model.Directory;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import lombok.Getter;

@ManagedBean(name = "directoryBean")
@SessionScoped
public class DirectoryBean implements Serializable {

    private int currentDirectory;

    private transient DirectoryService directoryService;

    public DirectoryService getDirectoryService() {
        if (directoryService == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            directoryService = context.getApplication().evaluateExpressionGet(context, "#{DirectoryService}", DirectoryService.class);
        }
        return directoryService;
    }

    @Getter
    private ArrayList<AbstractMap.SimpleEntry<Integer, String>> parents = null;

    public int getCurrentIdDirectory() {
        return this.currentDirectory;
    }

    public void setCurrentIdDirectory(Integer currentDirectory) {
        if (null == currentDirectory) {
            return;
        }
        this.currentDirectory = currentDirectory;

        Directory directory = getDirectoryService().getDirectoryById(currentDirectory);
        Directory parent = directory;

        if (null != directory) {
            if (null == parents) {
                parents = new ArrayList<>();
            } else {
                parents.clear();
            }

            do {
                parents.add(0, new AbstractMap.SimpleEntry(parent.getIddirectory(), parent.getName()));
            } while (parent.getIddirectory() != parent.getIdparent()
                    && (parent = getDirectoryService().getDirectoryById(parent.getIdparent())) != null);
        }
    }
}

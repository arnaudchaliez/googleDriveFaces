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
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;

@ManagedBean(name = "directoryBean")
@ViewScoped
public class DirectoryBean implements Serializable {
    

    private int currentDirectory;

    @Getter
    @Setter
    @ManagedProperty("#{DirectoryService}")
    transient DirectoryService directoryService;

    @Getter
    @Setter
    @ManagedProperty("#{userBean}")
    transient private UserBean userBean;
    
    @PostConstruct
    private void init() {
        setCurrentIdDirectory(userBean.getIdHome());
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
                parents.add(0, new AbstractMap.SimpleEntry(parent.getIdfile(), parent.getName()));
            } while (parent.getIdfile() != parent.getIddirectory()
                    && (parent = getDirectoryService().getDirectoryById(parent.getIddirectory())) != null);
        }
    }

    public int getDirectory() {
        return currentDirectory;
    }

    public void setDirectory(int directory) {
        setCurrentIdDirectory(directory);
    }
}

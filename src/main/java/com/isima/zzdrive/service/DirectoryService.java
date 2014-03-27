/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isima.zzdrive.service;

import com.isima.zzdrive.dao.DirectoryDAO;
import com.isima.zzdrive.model.Directory;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Arnaud CHALIEZ and Jérémy BOUNY
 */
@Service("DirectoryService")
@Transactional(readOnly = true)
public class DirectoryService {
    
    @Getter
    @Setter
    @Autowired
    DirectoryDAO directoryDAO;
    
    public DirectoryService() {
    }
    
    public List getDirectoryUser(int idUser) {
        return directoryDAO.getDirectoriesByUserId(idUser);
    }
    
    @Transactional(readOnly = false)
    public void createDirectory(Directory directory) {
        directoryDAO.addDirectory(directory);
    }
            
}

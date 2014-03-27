/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
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
    
    public Directory getDirectoryById(int idDirectory) {
        return directoryDAO.getDirectoryById(idDirectory);
    }
            
}

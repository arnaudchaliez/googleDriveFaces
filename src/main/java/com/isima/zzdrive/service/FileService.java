/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.service;

import com.isima.zzdrive.dao.AccessDAO;
import com.isima.zzdrive.dao.FileDAO;
import com.isima.zzdrive.model.File;
import com.isima.zzdrive.model.User;
import com.isima.zzdrive.model.Access;
import com.isima.zzdrive.model.AccessId;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("FileService")
@Transactional(readOnly = true)
public class FileService {

    @Getter
    @Setter
    @Autowired
    FileDAO fileDAO;

    @Getter
    @Setter
    @Autowired
    AccessDAO accessDAO;

    public FileService() {
    }

    public List getFileUser(int idUser) {
        return fileDAO.getFilesByUserId(idUser);
    }

    public List getSharedFileUser(int idUser) {
        return fileDAO.getSharedFilesByUserId(idUser);
    }

    public List getSharedFilesDirectoryUser(int idUser, int idDirectory) {
        if (-1 == idDirectory) {
            return fileDAO.getSharedFilesByUserId(idUser);
        }
        return fileDAO.getFilesDirectory(idDirectory);
    }

    public List getFilesDirectoryUser(int idUser, int idDirectory) {
        return fileDAO.getFilesDirectoryByUserId(idUser, idDirectory);
    }

    @Transactional(readOnly = false)
    public void saveFile(File file) {
        fileDAO.addFile(file);
    }

    @Transactional(readOnly = false)
    public void shareFile(File file, User user) {
        Access access = new Access(new AccessId(file.getIdfile(), user.getIduser()));
        accessDAO.addAccess(access);
    }
    
    @Transactional(readOnly = false)
    public void deleteFile(File file) {
        fileDAO.deleteFile(file);
    }
}

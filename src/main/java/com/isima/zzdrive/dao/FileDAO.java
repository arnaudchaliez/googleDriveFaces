/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.dao;

import com.isima.zzdrive.model.File;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileDAO {

    @Getter
    @Setter
    @Autowired
    SessionFactory sessionFactory;

    public List getFilesByUserId(int idOwner) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from File as file where file.idowner = :idowner")
                .setParameter("idowner", idOwner).list();
        return list;
    }

    /* public List getSharedFilesByUserId(int idUser) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("select user from User user, AccessId access where access.idfileaccess = file.idfile AND access.iduseraccess = :iduser")
                .setParameter("iduser", idUser).list();
        return list;
    } */
    
     public List getSharedFilesByUserId(int idUser) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("select file from File file, Access access where access.id.idfileaccess = file.idfile AND access.id.iduseraccess = :iduser")
                .setParameter("iduser", idUser).list();
        return list;
    }

    public List getFilesDirectoryByUserId(int idOwner, int idDirectory) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from File as file where file.idowner = :idowner and file.iddirectory = :directory")
                .setParameter("idowner", idOwner)
                .setParameter("directory", idDirectory).list();
        return list;
    }

    public List getFilesByDirectory(int idDirectory) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from File as file where file.iddirectory = :iddirectory")
                .setParameter("iddirectory", idDirectory).list();
        return list;
    }

    public void addFile(File file) {
        getSessionFactory().getCurrentSession().save(file);
    }

    public void deleteFile(File file) {
        getSessionFactory().getCurrentSession().delete(file);
    }

    public void updateFile(File file) {
        getSessionFactory().getCurrentSession().update(file);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isima.zzdrive.helper;

import com.isima.zzdrive.jpa.File;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import com.isima.zzdrive.services.HibernateUtil;

/**
 *
 * @author Arnaud
 */
public class FileHelper {
    Session session = null;

    public FileHelper() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    public List getFileUser(int inUserId) {
        List<File> fileList = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query query = (Query) session.createQuery ("from File as file where file.userid = :userid");
            query.setParameter("userid", inUserId);

            fileList = (List<File>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileList;
    }
    
    public void saveFile(File inFile) {
        this.session.save(inFile);
    }
            
}

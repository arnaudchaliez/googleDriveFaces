/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isima.zzdrive.dao;

import com.isima.zzdrive.model.Directory;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Arnaud
 */
@Repository
public class DirectoryDAO {
    @Getter
    @Setter
    @Autowired
    SessionFactory sessionFactory;

    public List getDirectoriesByUserId(int idOwner) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from directory as directory where directory.idowner = :idowner")
                                            .setParameter("idowner", idOwner).list();
        return list;
    }
    
    public void addDirectory(Directory directory) {
        getSessionFactory().getCurrentSession().save(directory);
    }

    public void deleteDirectory(Directory directory) {
        getSessionFactory().getCurrentSession().delete(directory);
    }

    public void updateFDirectory(Directory directory) {
        getSessionFactory().getCurrentSession().update(directory);
    }
    
}

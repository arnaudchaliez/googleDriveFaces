/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.dao;

import com.isima.zzdrive.model.Access;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccessDAO {

    @Getter
    @Setter
    @Autowired
    SessionFactory sessionFactory;

    public void addAccess(Access access) {
        getSessionFactory().getCurrentSession().save(access);
    }

    public void deleteAccess(Access access) {
        getSessionFactory().getCurrentSession().delete(access);
    }

    public void updateAccess(Access access) {
        getSessionFactory().getCurrentSession().update(access);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isima.zzdrive.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.isima.zzdrive.model.User;
import java.util.List;


/**
 *
 * @author Jeremy
 */
@Repository
public class UserDAO  {
    @Autowired
    SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addUser(User user) {
        getSessionFactory().getCurrentSession().save(user);
    }

    public void deleteUser(User user) {
        getSessionFactory().getCurrentSession().delete(user);
    }

    public void updateUser(User user) {
        getSessionFactory().getCurrentSession().update(user);
    }

    public User getUserById(int id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from User where id=?")
                                            .setParameter(0, id).list();
        return (User)list.get(0);
    }
    
    public User getUserByCredentials(String username, String password) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from User where username=:username and password=:password")
                                            .setParameter("username", username)
                                            .setParameter("password", password)
                                            .list();
        if(list.size() > 0) {
            return (User)list.get(0);
        }
        return null;
    }

    public List<User> getUsers() {
        List list = getSessionFactory().getCurrentSession().createQuery("from User").list();
        return list;
    }
}

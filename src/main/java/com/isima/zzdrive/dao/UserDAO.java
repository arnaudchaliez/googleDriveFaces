/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.isima.zzdrive.model.User;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Repository
public class UserDAO {

    @Getter
    @Setter
    @Autowired
    SessionFactory sessionFactory;

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
        if (list.size() > 0) {
            return (User) list.get(0);
        }
        return null;
    }

    public User getUserByUsername(String username) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from User where username=:username")
                .setParameter("username", username)
                .list();
        if (list.size() > 0) {
            return (User) list.get(0);
        }
        return null;
    }

    public User getUserByCredentials(String username, String password) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from User where username=:username and password=:password")
                .setParameter("username", username)
                .setParameter("password", password)
                .list();
        if (list.size() > 0) {
            return (User) list.get(0);
        }
        return null;
    }

    public List<User> getUsers() {
        List list = getSessionFactory().getCurrentSession().createQuery("from User").list();
        return list;
    }
    
    public List getUsernamesByBeginning(String username) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("select u.username from User u WHERE u.username LIKE :username")
                .setParameter("username", username + '%')
                .list();
        return list;
    }
}

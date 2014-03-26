/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.service;

import com.isima.zzdrive.dao.UserDAO;
import com.isima.zzdrive.model.User;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserService")
@Transactional(readOnly = true)
public class UserService {

    @Getter
    @Setter
    @Autowired
    UserDAO userDAO;

    public UserService() {
    }

    @Transactional(readOnly = false)
    public void addUser(User user) {
        getUserDAO().addUser(user);
    }

    @Transactional(readOnly = false)
    public void deleteUser(User user) {
        getUserDAO().deleteUser(user);
    }

    @Transactional(readOnly = false)
    public void updateUser(User user) {
        getUserDAO().updateUser(user);
    }

    public User getUserById(int id) {
        return getUserDAO().getUserById(id);
    }

    public User getUserByUsername(String username) {
        return getUserDAO().getUserByUsername(username);
    }

    public List<User> getUsers() {
        return getUserDAO().getUsers();
    }

    public User find(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        if (null != username) {
            try {
                return user.checkPasswordForLogin(password) ? user : null;
            } catch (InvalidKeySpecException e) {
                return null;
            } catch (NoSuchAlgorithmException e) {
                return null;
            }
        }
        return user;
    }

}

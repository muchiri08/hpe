package org.acme.service;

import java.util.List;

import org.acme.dao.UserDao;
import org.acme.domain.User;
import org.acme.exceptions.UserNotExistException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

    @Inject
    private UserDao userDao;

    public List<User> users() {
        List<User> users = userDao.listAll();

        return users;
    }

    @Transactional
    public void createUser(User user) {
        User u = new User();
        u.username = user.username;
        u.password = user.password;
        u.email = user.email;

        userDao.persist(u);
    }

    public User get(Long id) {
        User user = userDao.findByIdOptional(id).orElseThrow(
            () -> new UserNotExistException("null")
        );
        
        return user;
    }

}

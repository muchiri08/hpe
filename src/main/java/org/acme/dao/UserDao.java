package org.acme.dao;

import org.acme.domain.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserDao implements PanacheRepository<User> {

}

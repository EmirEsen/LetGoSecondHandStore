package org.example.repository;

import org.example.entity.User;

public class UserRepository extends RepositoryManager<User, Long> {
    public UserRepository() {
        super(User.class);
    }
}

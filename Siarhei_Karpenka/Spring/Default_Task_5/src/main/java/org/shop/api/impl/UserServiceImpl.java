package org.shop.api.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.shop.api.UserService;
import org.shop.data.User;
import org.shop.repository.UserRepository;

@Named
@Singleton
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository repository;

    /* (non-Javadoc)
     * @see org.shop.api.UserService#registerUser(org.shop.data.User)
     */
    @Override
    public Long registerUser(User user) {
        return repository.createUser(user);
    }

    /* (non-Javadoc)
     * @see org.shop.api.UserService#getUserById(java.lang.Long)
     */
    @Override
    public User getUserById(Long userId) {
        return repository.getUserById(userId);
    }

    /* (non-Javadoc)
     * @see org.shop.api.UserService#updateUserProfile(org.shop.data.User)
     */
    @Override
    public void updateUserProfile(User user) {
        repository.updateUser(user);
    }

    /* (non-Javadoc)
     * @see org.shop.api.UserService#getUsers()
     */
    @Override
    public List<User> getUsers() {
        return repository.getUsers();
    }

    public void populate(UserRepository repository) {
        this.repository = repository;
    }
}
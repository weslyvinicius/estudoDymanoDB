package br.academy.aws.dymanodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.academy.aws.dymanodb.dao.UserCrudDao;
import br.academy.aws.dymanodb.entity.User;

@Service
public class UserCrudService {

    private UserCrudDao userCrudDao;

    @Autowired
    public UserCrudService(UserCrudDao userCrudDao) {
        this.userCrudDao = userCrudDao;
    }

    public User createUser(User user) {
        return userCrudDao.createUser(user);
    }

    public User readUser(String userId) {
        return userCrudDao.readUser(userId);
    }

    public User updateUser(User user) {
        return userCrudDao.updateUser(user);
    }

    public void deleteUser(String userId) {
        userCrudDao.deleteUser(userId);
    }
}

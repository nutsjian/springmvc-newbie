package me.nutsjian.springmvc.newbie.service.impl;

import me.nutsjian.springmvc.newbie.dao.UserDao;
import me.nutsjian.springmvc.newbie.dto.UserDTO;
import me.nutsjian.springmvc.newbie.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserDTO> listAll() {
        return this.userDao.findAll();
    }
}

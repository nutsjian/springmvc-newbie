package me.nutsjian.springmvc.newbie.dao;

import me.nutsjian.springmvc.newbie.dto.UserDTO;

import java.util.List;

public interface UserDao {
    List<UserDTO> findAll();
}

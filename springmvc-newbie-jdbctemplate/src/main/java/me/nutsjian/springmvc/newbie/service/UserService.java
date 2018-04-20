package me.nutsjian.springmvc.newbie.service;

import me.nutsjian.springmvc.newbie.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> listAll();
}

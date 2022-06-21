package com.example.Final.Project.service;

import com.example.Final.Project.model.UserDto;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface UserService {
    UserDto getUser(Integer id);

    void createUser(UserDto userDto, Integer id);

    void updateAsAdmin(Integer id, Integer reqId);

    void updateUser(Integer id, Integer reqId, UserDto userDto);

    void deleteUser(Integer id, Integer reqId);

    List<UserDto> getUsers(Integer id);
}

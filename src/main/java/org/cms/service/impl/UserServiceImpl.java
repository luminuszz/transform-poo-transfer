package org.cms.service.impl;

import org.cms.entity.User;
import org.cms.enums.Role;
import org.cms.service.contract.UserService;
import org.cms.service.dto.UpdateUserDto;

import java.util.ArrayList;
import java.util.HashMap;

public class UserServiceImpl implements UserService {

    private final HashMap<String, User> users =  new HashMap<>();

    public User login(String email, String password) {
        return users.values().stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public void createUser(String email, String password, Role role) {
        var user = new User(email, password, role);
        users.put(user.getId(), user);
    }

    public void updateUser(String userId, UpdateUserDto updateUserDto) {
        User user = users.get(userId);

       if(user == null) {
           throw new IllegalArgumentException("User not found");
       }

         updateUserDto.email().ifPresent(user::setEmail);
         updateUserDto.password().ifPresent(user::setPassword);
         updateUserDto.role().ifPresent(user::setRole);

         users.put(user.getId(), user);
    }

    public void deleteUser(String userId) {
        users.remove(userId);
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    private ArrayList<User> getUserList() {
        return new ArrayList<>(users.values());
    }
}

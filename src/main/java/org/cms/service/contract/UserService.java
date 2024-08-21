package org.cms.service.contract;

import org.cms.entity.User;
import org.cms.enums.Role;



public interface UserService {
    void createUser(String email, String password, Role role);
    User getUser(String userId);
    User login(String email, String password);
}

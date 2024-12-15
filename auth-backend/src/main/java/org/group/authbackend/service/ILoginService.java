package org.group.authbackend.service;

import org.group.authbackend.model.dto.UserDto;
import org.group.authbackend.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ILoginService extends UserDetailsService {
    User authentication(UserDto userDto);
    User getUserDetailsByUsername(String username);
}

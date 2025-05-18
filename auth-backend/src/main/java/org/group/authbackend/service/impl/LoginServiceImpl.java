package org.group.authbackend.service.impl;

import org.group.authbackend.model.dto.UserDto;
import org.group.authbackend.model.entity.User;
import org.group.authbackend.repository.UserRepository;
import org.group.authbackend.service.ILoginService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoginServiceImpl implements ILoginService {

    private final UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User authentication(UserDto userDto) {
        return userRepository.findByUsername(userDto.getUsername());
    }

    @Override
    public User getUserDetailsByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user==null) throw new UsernameNotFoundException(username);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user==null) {
            throw new UsernameNotFoundException(username);
        }
        else {
            //since your ILoginService is extended with UserDetails class it will same as long the attribute name is same with your custom entity
            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),true,true,true,true,new ArrayList<>());
        }
    }
}

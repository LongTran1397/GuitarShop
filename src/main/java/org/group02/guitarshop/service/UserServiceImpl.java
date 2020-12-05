package org.group02.guitarshop.service;

import org.group02.guitarshop.entity.Role;
import org.group02.guitarshop.entity.User;
import org.group02.guitarshop.repository.RoleRepository;
import org.group02.guitarshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                             RoleRepository roleRepository,
                             BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRoleName("user");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

}
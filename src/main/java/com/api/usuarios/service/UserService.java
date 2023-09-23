package com.api.usuarios.service;

import com.api.usuarios.model.User;
import com.api.usuarios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveUser(User user){
        userRepository.save(user);
    }

}

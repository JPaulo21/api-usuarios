package com.api.usuarios.service;

import com.api.usuarios.exception.UsernameUniqueViolationException;
import com.api.usuarios.web.dto.UserDTO;
import com.api.usuarios.entity.User;
import com.api.usuarios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User saveUser(UserDTO userDTO) {
        User user = new User(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try{
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e){
            throw new UsernameUniqueViolationException(String.format("Username %s já cadastrado!", user.getUsername()));
        }
    }

    @Override //Implementação da UserDetailsService, sempre que for feito autenticação, será chamado esse método
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Usuário com '%s' não encontrado.", username))
        );
    }
}

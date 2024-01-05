package com.api.usuarios.service;

import com.api.usuarios.web.dto.UserDTO;
import com.api.usuarios.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import com.api.usuarios.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private User user;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserDTO userDTO;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    void deve_Salvar_usuario(){

        //ARRANGE
        userDTO = new UserDTO("Mariana","123456789");
        String senha = new BCryptPasswordEncoder().encode(userDTO.password());

        BDDMockito.when(user.getPassword()).thenReturn(userDTO.password());
        BDDMockito.when(passwordEncoder.encode(user.getPassword())).thenReturn(senha);

        //ACT]
        userService.saveUser(userDTO);

        //ASSERTIONS
        BDDMockito.verify(userRepository).save(userCaptor.capture());
        User user = userCaptor.getValue();

        assertEquals(userDTO.username(), user.getUsername());
        assertNotNull(user.getPassword());
        assertNotEquals(userDTO.password(), user.getPassword());

    }

}
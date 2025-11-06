package com.mateus.trip_planner_api.services;

import com.mateus.trip_planner_api.dto.UserDTO;
import com.mateus.trip_planner_api.models.UserModel;
import com.mateus.trip_planner_api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    void shouldCreateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("mateus@gmail.com");
        userDTO.setPassword("passwordtest");
        userDTO.setName("Mateus");

        Mockito.when(passwordEncoder.encode(Mockito.anyString()))
                .thenReturn("$2a$mockedhash");

        Mockito.when(userRepository.save(Mockito.any(UserModel.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        UserModel savedUser = userService.save(userDTO);

        assertNotNull(savedUser);
        assertEquals("mateus@gmail.com", savedUser.getEmail());
        assertEquals("$2a$mockedhash", savedUser.getPassword());

        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(UserModel.class));
    }



    @Test
    void shouldDeleteUserById() {
        UUID userId = UUID.randomUUID();

        userService.deleteById(userId);

        Mockito.verify(userRepository, Mockito.times(1)).deleteById(userId);
    }

    @Test
    void shouldUpdateUserSuccessfully() {
        UUID userId = UUID.randomUUID();
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Novo Nome");
        userDTO.setEmail("novoemail@gmail.com");
        userDTO.setPassword("novasenha");

        UserModel existingUser = new UserModel();
        existingUser.setId(userId);
        existingUser.setName("Antigo Nome");
        existingUser.setEmail("antigo@gmail.com");
        existingUser.setPassword("antigaSenhaCriptografada");

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        Mockito.when(passwordEncoder.encode("novasenha")).thenReturn("$2a$encodedpassword");
        Mockito.when(userRepository.save(Mockito.any(UserModel.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        UserModel updatedUser = userService.update(userId, userDTO);
        assertNotNull(updatedUser);
        assertEquals("Novo Nome", updatedUser.getName());
        assertEquals("novoemail@gmail.com", updatedUser.getEmail());
        assertEquals("$2a$encodedpassword", updatedUser.getPassword());

        Mockito.verify(userRepository, Mockito.times(1)).findById(userId);
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(UserModel.class));
        Mockito.verify(passwordEncoder, Mockito.times(1)).encode("novasenha");
    }


}

package com.ninos.service.auth;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ninos.model.dto.SignupRequest;
import com.ninos.model.dto.UserDTO;
import com.ninos.model.entity.User;
import com.ninos.model.enums.UserRole;
import com.ninos.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;




    @Override
    public UserDTO createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        User createdUser = userRepository.save(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(createdUser.getId());
        return userDTO;
    }

    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }


    @PostConstruct
    public void createAdminAccount(){
        User adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if(null == adminAccount){
            User user = new User();
            user.setEmail("admin@gmail.com");
            user.setName("admin");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
        }
    }



}

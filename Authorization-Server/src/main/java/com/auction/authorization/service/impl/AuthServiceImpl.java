package com.auction.authorization.service.impl;

import com.auction.authorization.dto.UserDetailResponse;
import com.auction.authorization.entity.UserCredentialDetails;
import com.auction.authorization.exception.CustomException;
import com.auction.authorization.repository.UserDetailsRepository;
import com.auction.authorization.service.AuthService;
import com.auction.authorization.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    UserDetailsRepository userDetailsRepository;

    PasswordEncoder passwordEncoder;

    JwtUtil jwtUtil;


    @Override
    public UserDetailResponse registerUser(UserCredentialDetails userCredentialDetails) {
        if     (userCredentialDetails == null ||
                userCredentialDetails.getUserName() == null ||
                userCredentialDetails.getPassword() == null) {
            throw new IllegalArgumentException("Invalid user details provided");
        }
        boolean userExists = userDetailsRepository.findByUserName(userCredentialDetails.getUserName()).isPresent();
        if (userExists) {
            throw new CustomException("UserName already exists");
        }
        userCredentialDetails.setPassword(passwordEncoder.encode(userCredentialDetails.getPassword()));
        UserCredentialDetails response = userDetailsRepository.save(userCredentialDetails);
        return new UserDetailResponse(response.getUserName());
    }

    @Override
    public void validateToken(String token) {
        jwtUtil.validateToken(token);
    }

    @Override
    public String generateToken(String username) {
        return jwtUtil.generateToken(username);
    }
}

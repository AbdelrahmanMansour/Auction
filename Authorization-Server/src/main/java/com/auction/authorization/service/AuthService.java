package com.auction.authorization.service;

import com.auction.authorization.dto.UserDetailResponse;
import com.auction.authorization.entity.UserCredentialDetails;

public interface AuthService {

    UserDetailResponse registerUser(UserCredentialDetails userCredentialDetails);
    void validateToken(String username);
    String generateToken(String username);
}

package com.auction.authorization.controller;

import com.auction.authorization.config.ModelMapperConfig;
import com.auction.authorization.dto.AuthRequest;
import com.auction.authorization.dto.RegisterationRequest;
import com.auction.authorization.dto.UserDetailResponse;
import com.auction.authorization.entity.UserCredentialDetails;
import com.auction.authorization.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    AuthService authService;

    ModelMapperConfig modelMapper;

    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserDetailResponse> register(@RequestBody RegisterationRequest request){
        UserDetailResponse response = authService.registerUser(modelMapper.modelMapper().map(request, UserCredentialDetails.class));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token){
        authService.validateToken(token);
        return ResponseEntity.ok("Valid Token");
    }

    @PostMapping("/token")
    public ResponseEntity<String> generateToken(@RequestBody AuthRequest auth){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUserName(), auth.getPassword()));
        if(authenticate.isAuthenticated()){
            return ResponseEntity.ok(authService.generateToken(auth.getUserName()));
        }
        throw new RuntimeException("Not valid");
    }

}

package com.example.jwtdemo.controller;

import com.example.jwtdemo.entity.AuthRequest;
import com.example.jwtdemo.entity.UserInfo;
import com.example.jwtdemo.service.JwtService;
import com.example.jwtdemo.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome. This endpoint is not secure";
    }

    @PostMapping("/addnewuser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return userInfoService.addUser(userInfo);
    }

    @PostMapping("/generatetoken")
    public String authenticateAndGenerateToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        }
        throw new UsernameNotFoundException("User does not exists with email: "+ authRequest.getUsername());
    }

    @GetMapping("/user/userProfile")
    public String getUserProfile(Authentication authentication) {
        logger.info("### Current user: "+ authentication.getName());
        logger.info("### Authorities: "+ authentication.getAuthorities());
        return "User Profile accessed successfully";
    }
}

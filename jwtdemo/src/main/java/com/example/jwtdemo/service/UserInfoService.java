package com.example.jwtdemo.service;

import com.example.jwtdemo.entity.UserInfo;
import com.example.jwtdemo.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

  @Autowired
  private UserInfoRepository userInfoRepository;

  //Method to load user details by username(email)
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserInfo> userInfo = userInfoRepository.findByEmail(username);

    if(userInfo.isEmpty()) {
      throw new UsernameNotFoundException("User does not exists. Email: "+ username);
    }

    return new UserInfoDetails(userInfo.get());
  }

  //additional method for registering and managing users
  public String addUser(UserInfo userInfo) {
    userInfoRepository.save(userInfo);
    return "User added successfully";
  }
}

package com.example.restapidemo.security;

import com.example.restapidemo.model.User;
import com.example.restapidemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImp")
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserRepo userRepo;

    @Autowired
    public UserDetailsServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
        return SecurityUser.fromUser(user);
    }
}
package com.code.demo.service;

import com.code.demo.domain.Token;
import com.code.demo.domain.User;
import com.code.demo.domain.enums.UserStatus;
import com.code.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private static final String USER_NOT_FOUND = "User %s not found";

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public User findByUsername(String username) {
        return repository.findById(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format(USER_NOT_FOUND, username)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username);
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.PENDING);
        repository.save(user);
        String token = tokenService.createActivationToken(user.getId());
        emailService.sendActivationEmail(user.getEmail(), token);
        return user;
    }

    public void activate(String token) {
        Token activationToken = tokenService.findActivationToken(token);
        User user = findByUsername(activationToken.getUserId());
        user.setStatus(UserStatus.ACTIVE);
        repository.save(user);
        tokenService.remove(activationToken);
    }

    public void lock(String username) {
        User user = findByUsername(username);
        user.setStatus(UserStatus.LOCKED);
        repository.save(user);
    }

}
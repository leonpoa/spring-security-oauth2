package com.code.demo.controller;

import com.code.demo.domain.Token;
import com.code.demo.domain.User;
import com.code.demo.service.TokenService;
import com.code.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public Page<User> findAllPaged(Pageable pageable) {
        log.debug("Finding all users paged.");
        return service.findAll(pageable);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable("username") String username) {
        log.debug("Finding user '{}'.", username);
        User user = service.findByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/principal")
    public ResponseEntity<Principal> getPrincipal(Principal principal) {
        log.debug("Getting principal.");
        return ResponseEntity.ok(principal);
    }

    @PostMapping
    public ResponseEntity register(@RequestBody User user) {
        log.debug("Registering user {}.", user);
        service.register(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{username}").buildAndExpand(user.getUsername()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/confirm/{token}")
    public ResponseEntity confirmRegistration(@PathVariable("token") String token) {
        log.debug("Confirming token '{}'.", token);
        service.activate(token);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{username}")
    public ResponseEntity lock(@PathVariable("username") String username) {
        log.debug("Locking user '{}'.", username);
        service.lock(username);
        return ResponseEntity.noContent().build();
    }

}

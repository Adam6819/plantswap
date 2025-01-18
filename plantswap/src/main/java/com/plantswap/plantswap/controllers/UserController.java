package com.plantswap.plantswap.controllers;

import com.plantswap.plantswap.models.Plant;
import com.plantswap.plantswap.models.User;
import com.plantswap.plantswap.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {
@Autowired
private UserRepository userRepository;

@PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

}
@GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);

    }

@GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){
    User user = userRepository.findById(id)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"user not found"));

    return ResponseEntity.ok(user);

}

@PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User userDetails){
    User existingUser = userRepository.findById(id)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));

        existingUser.setId(userDetails.getId());
        existingUser.setAge(userDetails.getAge());
        existingUser.setFullName(userDetails.getFullName());

        return ResponseEntity.ok(userRepository.save(userDetails));

}

@DeleteMapping
    public ResponseEntity<User> deleteUser(@PathVariable String id){
        if (!userRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"user not found");

        }

        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();

}



}

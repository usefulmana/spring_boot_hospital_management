package rmit.spring.hospital.controllers;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rmit.spring.hospital.exceptions.ResourceNotFoundException;
import rmit.spring.hospital.models.User;
import rmit.spring.hospital.repositories.UserRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/user")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public User addUser(@RequestBody User user) {
        System.out.println(user.toString());
        String pw = user.getPassword();
        String encryptedPassword = bCryptPasswordEncoder.encode(pw);
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }

    ;

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUserById(@Valid @PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Unable to find a user with id = ", id)
        );
    }

    ;

    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public @ResponseBody ResponseEntity<?> deleteAUser(@Valid @PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Unable to find a user with id = ", id)
        );
        userRepository.delete(user);
        HashMap<String, String> res = new HashMap<>();
        res.put("message", String.format("Successfully deleted a user with id = %s", id));
        return ResponseEntity.ok(res);
    }

    @PutMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public User updateAUser(@Valid @PathVariable(name = "id") Long id, @RequestBody User updatedUser) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Unable to find a user with id = ", id)
        );
        BeanUtils.copyProperties(user, updatedUser);
        return userRepository.save(user);
    }
}

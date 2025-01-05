package com.hammed.UserManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> listAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("user/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) { return userService.getUserById(id);};

    @GetMapping("/manage")
    public String manageUsers() {
        return "Admin can manage users";
    }
    @PutMapping("user/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Integer id,
            @RequestBody User updatedUser
    ) {
        try {
            User user = userService.updateUser(id, updatedUser);
            System.out.println(user +"ytyytty");
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null);
        }
    }


    @DeleteMapping("user/{id}")
    public String  deleteUser(@PathVariable Integer id) { userService.deleteUser(id);
    return "deleted";
    }

    @GetMapping("/settings")
    public String accessSettings() {
        return "Admin can access settings";
    }

}

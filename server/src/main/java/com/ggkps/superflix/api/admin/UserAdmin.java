package com.ggkps.superflix.api.admin;

import com.ggkps.superflix.entities.Movie;
import com.ggkps.superflix.entities.Serie;
import com.ggkps.superflix.entities.User;
import com.ggkps.superflix.models.SerieContent;
import com.ggkps.superflix.repositories.SerieRepository;
import com.ggkps.superflix.repositories.UserRepository;
import com.ggkps.superflix.services.SerieService;
import com.ggkps.superflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
public class UserAdmin {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SerieService serieService;

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private UserService userService;

    public UserAdmin() {
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> readUsers(@RequestHeader(value="Authorization") String authorization) {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<User> readUser(@PathVariable("user_id") Long user_id) {
        Optional<User> user = userRepository.findById(user_id);
        return user.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/user/{user_id}")
    public String deleteSerie(@PathVariable("user_id") Long user_id) {
        boolean exists = userService.deleteUser(user_id);

        return exists ? "Serie deleted" : "Serie not found";
    }
}

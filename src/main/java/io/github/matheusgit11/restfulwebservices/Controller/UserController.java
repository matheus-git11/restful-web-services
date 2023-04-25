package io.github.matheusgit11.restfulwebservices.Controller;

import io.github.matheusgit11.restfulwebservices.Entity.User;
import io.github.matheusgit11.restfulwebservices.Exceptions.UserNotFoundException;
import io.github.matheusgit11.restfulwebservices.Service.UserDaoService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

//server exception = 500
//validiation error = 400
//unautherized = 401
// bad request = 400
// sucess = 200
// created = 201
// no content = 204


@RestController
public class UserController {

    private final UserDaoService service;

    public UserController(UserDaoService service){
        this.service=service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        User user = service.findOne(id);
        if(user == null){
            throw new UserNotFoundException("id:"+id);
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser= service.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest() //get the current url
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        service.deleteById(id);
    }
}

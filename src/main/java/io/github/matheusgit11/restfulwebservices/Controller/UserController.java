package io.github.matheusgit11.restfulwebservices.Controller;

import io.github.matheusgit11.restfulwebservices.Entity.User;
import io.github.matheusgit11.restfulwebservices.Service.UserDaoService;
import org.springframework.web.bind.annotation.*;

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
        return service.findOne(id);
    }

    @PostMapping("/users")
    public void CreateUser(@RequestBody User user){
        service.save(user);
    }
}

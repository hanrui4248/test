package ca.uqtr.dmi.demo.controllers;

import ca.uqtr.dmi.demo.model.User;
import ca.uqtr.dmi.demo.services.StudentService;
import ca.uqtr.dmi.demo.services.impl.UserDetailsServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/users")
@Api(value = "ActControllerAPI",
     produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserDetailsServiceImpl userDetailsService;
    public UserController( UserDetailsServiceImpl userDetailsService) {

        this.userDetailsService = userDetailsService;
    }

    @PostMapping(path = "/signup")
    public void  creat(@RequestBody User user){
        userDetailsService.save(user);
    }


}

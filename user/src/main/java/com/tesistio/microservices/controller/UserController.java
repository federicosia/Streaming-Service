package com.tesistio.microservices.controller;

import com.tesistio.microservices.model.User;
import com.tesistio.microservices.exceptions.UserAlreadyExistsException;
import com.tesistio.microservices.exceptions.UserNotFoundException;
import com.tesistio.microservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    //get /users
    @GetMapping("/users/{email}")
    public ResponseEntity<User> retrieveUser(@PathVariable String email){
        Optional<User> user = Optional.ofNullable(service.findByEmail(email));
        if(user.isEmpty())
            throw new UserNotFoundException("email: " + email);

        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @DeleteMapping("/users/{email}")
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable String email){
        service.deleteByEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //post /user
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        //check email
        if(service.existsUserByEmail(user.getEmail()))
            throw new UserAlreadyExistsException("A user with: " + user.getEmail() + " already exists...");

        service.storeUser(user);
        //costruiamo URI per indirizzare la pagina alla get dell'utente tramite la sua singola id
        //nella head della request avremo una entry chiamata location dove avremo URL che ci serve
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{email}")
                        .buildAndExpand(user.getEmail())
                        .toUri();
        return ResponseEntity.created(location).build();
    }
}

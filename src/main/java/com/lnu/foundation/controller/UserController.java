package com.lnu.foundation.controller;

import com.lnu.foundation.model.*;
import com.lnu.foundation.service.NoteService;
import com.lnu.foundation.service.SecurityContextService;
import com.lnu.foundation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by rucsac on 10/10/2018.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService service;
    @Autowired
    private SecurityContextService securityContextService;

    @Autowired
    private NoteService noteService;


    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @GetMapping("/me/tests")
    public Collection<Request> getRequests() {
        Collection<Request> requests;
        User user = securityContextService.currentUser().orElseThrow(RuntimeException::new);
        if (user.getRole() != null && "researcher".equals(user.getRole().getName())) {
            requests = service.getRequests(user.getUsername());
        } else {
            requests = service.getRequests();
        }
        return requests;
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @GetMapping("user/{username}/tests")
    public Collection<Request> getClientRequests(@PathVariable String username) {
        return service.getClientRequests(username);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @GetMapping("user/{username}")
    public User getUser(@PathVariable String username) {
        return service.findUserByUsername(username);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @GetMapping("/me")
    public User getMe() {
        return securityContextService.currentUser().orElseThrow(RuntimeException::new);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @PostMapping("user/me/tests/{requestId}/note")
    public Collection<Note> addNote(@PathVariable Long requestId, @RequestBody Note note) {
        User user = securityContextService.currentUser().orElseThrow(RuntimeException::new);
        return noteService.addNote(requestId, note, user);
    }


    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @GetMapping("/contracts")
    public Collection<Contract> getContracts() {
        Collection<Contract> contracts = null;
        User user = securityContextService.currentUser().orElseThrow(RuntimeException::new);
        if ("physician".equals(user.getRole().getName())) {
            contracts = service.getContractsByProvider(user.getUsername());
        } else if ("researcher".equals(user.getRole().getName())
                || "junior researcher".equals(user.getRole().getName())) {
            contracts = service.getContracts();
        } else {
            contracts = service.getContractsByClient(user.getUsername());
        }

        return contracts;
    }

}

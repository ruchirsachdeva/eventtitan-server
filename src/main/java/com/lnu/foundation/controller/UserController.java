package com.lnu.foundation.controller;

import com.lnu.foundation.model.*;
import com.lnu.foundation.service.ListingService;
import com.lnu.foundation.service.NoteService;
import com.lnu.foundation.service.SecurityContextService;
import com.lnu.foundation.service.UserService;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;

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
    @GetMapping("/me")
    public User getMe() {
        return securityContextService.currentUser().orElseThrow(RuntimeException::new);
    }


    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @PostMapping("/{orgId}")
    public void addListings(@RequestBody Organization org) {
        User user = securityContextService.currentUser().orElseThrow(RuntimeException::new);
        service.addListing(user, org);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @PostMapping("user/me/requests/{requestId}/note")
    public Collection<Note> addNote(@PathVariable Long requestId, @RequestBody Note note) {
        User user = securityContextService.currentUser().orElseThrow(RuntimeException::new);
        return noteService.addNote(requestId, note, user);
    }


}

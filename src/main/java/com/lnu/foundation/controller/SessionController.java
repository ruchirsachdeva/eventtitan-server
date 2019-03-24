package com.lnu.foundation.controller;

import com.lnu.foundation.model.*;
import com.lnu.foundation.service.SecurityContextService;
import com.lnu.foundation.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by rucsac on 10/10/2018.
 */
@RestController
@RequestMapping("/api/sessions")
public class SessionController {


    @Autowired
    private RequestService service;
    @Autowired
    private SecurityContextService securityContextService;


    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @PostMapping("request/{contractId}/{requestedHours}")
    public void request(@PathVariable Long contractId, @PathVariable int requestedHours) {
        service.requestSession(contractId, requestedHours);
    }


    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @PostMapping("book/{sessionId}")
    public void book(@PathVariable Long sessionId, @RequestBody Duration duration) {
        service.bookSession(sessionId, duration);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @PostMapping("end/{sessionId}")
    public void end(@PathVariable Long sessionId) {
        service.endSession(sessionId);
    }
}

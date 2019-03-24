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
@RequestMapping("/api/requests")
public class RequestController {


    @Autowired
    private RequestService service;
    @Autowired
    private SecurityContextService securityContextService;


    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @PostMapping("contract/{contractId}/{type}")
    public void request(@PathVariable Long contractId, @PathVariable String type) {
        service.createRequest(contractId, type);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @PostMapping("end/{requestId}")
    public void end(@PathVariable Long requestId) {
        service.endRequest(requestId);
    }


}

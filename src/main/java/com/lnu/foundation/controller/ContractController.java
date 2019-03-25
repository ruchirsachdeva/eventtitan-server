package com.lnu.foundation.controller;

import com.lnu.foundation.service.ContractService;
import com.lnu.foundation.service.RequestService;
import com.lnu.foundation.service.SecurityContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by rucsac on 10/10/2018.
 */
@RestController
@RequestMapping("/api/contracts")
public class ContractController {


    @Autowired
    private ContractService service;
    @Autowired
    private SecurityContextService securityContextService;


    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @PostMapping("contract/{guests}/{orgId}")
    public void post(@PathVariable Integer guests, @PathVariable Long orgId) {
        service.startContract(guests, orgId);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @PostMapping("contract/end/{contractId}")
    public void request(@PathVariable Long contractId) {
        service.endContract(contractId);
    }
}

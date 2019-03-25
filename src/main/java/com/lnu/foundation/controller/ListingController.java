package com.lnu.foundation.controller;

import com.lnu.foundation.model.Organization;
import com.lnu.foundation.service.ListingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by rucsac on 10/10/2018.
 */
@RestController
@RequestMapping("/api/listings")
public class ListingController {
    private static final Logger logger = LoggerFactory.getLogger(ListingController.class);


    @Autowired
    private ListingService listingService;


    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @PostMapping("/filtered")
    public Collection<Organization> getListings(@RequestBody ListingService.UserData filter) {
        logger.info("getListings#UserData: " + filter);

        return listingService.getListings(filter);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @PostMapping("/")
    public ResponseEntity post(@RequestBody Organization listing) {
        listingService.save(listing);
        return ResponseEntity.ok().build();
    }


}

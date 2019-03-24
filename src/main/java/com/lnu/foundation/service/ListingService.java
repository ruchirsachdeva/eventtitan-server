package com.lnu.foundation.service;

import com.lnu.foundation.model.*;
import com.lnu.foundation.repository.NoteRepository;
import com.lnu.foundation.repository.OrganizationRepository;
import com.lnu.foundation.repository.RequestRepository;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rucsac on 04/11/2018.
 */
@Service
@Transactional
public class ListingService {


    @Autowired
    private OrganizationRepository organizationRepo;


    public Collection<Listing> getListings(UserData filter) {
        List<Organization> listings = organizationRepo.findAll();

        return filterListings(listings, filter);
    }

    private Collection<Listing> filterListings(Collection<Organization> listings, UserData filter) {
        return listings.stream().parallel()
                .filter(org ->

                        (org.getMinDailyCapacity() == null || org.getMinDailyCapacity() >= filter.getGuests())
                                && (filter.getGuests() <= org.getMaxDailyCapacity())
                                && (filter.getMaxBudget() <= org.getTotalPrice(filter.getGuests()).doubleValue())
                                && (filter.getMinBudget() > org.getTotalPrice(filter.getGuests()).doubleValue())

                )
                .map(org -> Listing.of(org, filter.getLatitude(), filter.getLongitude()))
                .collect(Collectors.toList());
    }


    @Value
    public static final class UserData {
        private final Double latitude;
        private final Double longitude;
        private final Integer guests;
        private final Double minBudget;
        private final Double maxBudget;
        private final Date eventDate;
    }


}

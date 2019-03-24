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


    public Collection<Organization> getListings(UserData filter) {
        List<Organization> listings = organizationRepo.findAll();

        return filterListings(listings, filter);
    }

    private Collection<Organization> filterListings(Collection<Organization> listings, UserData filter) {
        return listings.stream().parallel()
                .filter(org ->
                        (org.getMinDailyCapacity() == null || org.getMinDailyCapacity() >= filter.getGuests())
                                && (filter.getGuests() <= org.getMaxDailyCapacity())
                                && (filter.getMaxBudget() >= org.getTotalPrice(filter.getGuests()).doubleValue())
                                && (filter.getType() == null || filter.getType() == org.getOrganizationType())
                )
                .map(org -> {
                    org.setDistance(filter.getLatitude(), filter.getLongitude());
                    return org;
                })
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
        private final OrganizationType type;
    }


}

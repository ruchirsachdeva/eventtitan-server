package com.lnu.foundation.service;

import com.lnu.foundation.model.*;
import com.lnu.foundation.repository.OrganizationRepository;
import lombok.Value;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by rucsac on 04/11/2018.
 */
@Service
@Transactional
public class ListingService {


    @Autowired
    private OrganizationRepository organizationRepo;

    @Autowired
    private SecurityContextService securityContextService;

    @Autowired
    private ContractService contractService;


    public Collection<Organization> getListings(UserData filter) {
        User user = securityContextService.currentUser().orElse(null);
        if (user != null) {
            Collection<Contract> clientContracts = contractService.getClientContracts(user);
            if (!clientContracts.isEmpty()) {
                List<Long> filterListingIds = clientContracts.stream()
                        .parallel()
                        .map(c -> c.getOrganization().getOrganizationId())
                        .collect(Collectors.toList());
                return organizationRepo
                        .byListingsFilteredForClient(filterListingIds, filter.getGuests(), filter.getType(), BigDecimal.valueOf(filter.getMaxBudget()))
                        .parallelStream()
                        .map(org -> {
                            org.setDistance(filter.getLatitude(), filter.getLongitude());
                            return org;
                        })
                        .collect(Collectors.toSet());
            }
        }
        return organizationRepo
                .byListingsFiltered(filter.getGuests(), filter.getType(), BigDecimal.valueOf(filter.getMaxBudget()))
                .parallelStream()
                .map(org -> {
                    org.setDistance(filter.getLatitude(), filter.getLongitude());
                    return org;
                })
                .collect(Collectors.toSet());


        //List<Organization> listings = organizationRepo.findAll();

        //return filterListingIds(listings, filter);
    }

    public Collection<Organization> getMyListings(String type) {
        User user = securityContextService.currentUser().orElseThrow(RuntimeException::new);
        return user.getOrganizations().stream()
                .parallel()
                .filter(org -> (type == null || type.equalsIgnoreCase(org.getOrganizationType().name())))
                .collect(Collectors.toList());
    }

    private Collection<Organization> filterListings(Collection<Organization> listings, UserData filter) {
        User user = securityContextService.currentUser().orElse(null);
        ArrayList<Contract> clientContracts = new ArrayList<>();
        if (user != null) {
            clientContracts.addAll(contractService.getClientContracts(user));
        }
        return listings.stream().parallel()
                .filter(org ->
                        (clientContracts.stream()
                                .parallel()
                                .allMatch(contract -> !contract.getOrganization().getOrganizationId().equals(org.getOrganizationId())))
                                && (org.getMinDailyCapacity() == null || org.getMinDailyCapacity() >= filter.getGuests())
                                && (filter.getGuests() <= org.getMaxDailyCapacity())
                                && (filter.getMaxBudget() >= org.getTotalPrice(filter.getGuests()).doubleValue())
                                && (filter.getType() == null || filter.getType() == org.getOrganizationType())
                )
                .map(org -> {
                    org.setDistance(filter.getLatitude(), filter.getLongitude());
                    return org;
                })
                .collect(Collectors.toSet());
    }

    public void save(Organization listing) {
        String base64 = listing.getBase64();
        //TODO remove below if setBase64 in Organization works
        byte[] imageByte = Base64.decodeBase64(base64);
        listing.setImage(imageByte);

        //TODO remove below and get org from server before updating and send contracts along
        if (listing.getOrganizationId() != null) {
            Organization existing = organizationRepo.getOne(listing.getOrganizationId());
            if (existing != null) {
                listing.setContracts(existing.getContracts());
            }
        }

        organizationRepo.save(listing);
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

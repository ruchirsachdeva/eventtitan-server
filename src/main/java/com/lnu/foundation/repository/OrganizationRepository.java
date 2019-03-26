package com.lnu.foundation.repository;

import com.lnu.foundation.model.Organization;
import com.lnu.foundation.model.OrganizationType;
import com.lnu.foundation.model.Request;
import com.lnu.foundation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by rucsac on 10/10/2018.
 */
@RepositoryRestResource
//@CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    @RestResource
    List<Organization> findDistinctBy();


    @RestResource(path = "byListingsFilteredForClient", rel = "byListingsFilteredForClient")
    @Query("select distinct(o) from Organization o where o.organizationId not in ?1" +
            " and o.maxDailyCapacity >= ?2" +
            " and o.organizationType = ?3" +
            " and (o.totalPrice is not null and o.totalPrice <= ?4 " +
            "       or  o.totalPrice is null and (o.pricePerUnit * ?2) <= ?4)")
    Set<Organization> byListingsFilteredForClient(@Param("filterListingIds") List<Long> filterListingIds, @Param("guests") Integer guests,
                                                  @Param("organizationType") OrganizationType organizationType,
                                                  @Param("maxBudget") BigDecimal maxBudget);


    @RestResource(path = "byListingsFiltered", rel = "byListingsFilteredForClient")
    @Query("select distinct(o) from Organization o where o.maxDailyCapacity >= ?1" +
            " and o.organizationType = ?2" +
            " and (o.totalPrice is not null and o.totalPrice <= ?3 " +
            "       or  o.totalPrice is null and (o.pricePerUnit * ?1) <= ?3)")
    Set<Organization> byListingsFiltered(@Param("guests") Integer guests,
                                                  @Param("organizationType") OrganizationType organizationType,
                                                  @Param("maxBudget") BigDecimal maxBudget);

/**

 contractRepository.findByClient_UsernameAndDuration_EndTimeIsNull(user.getUsername());
 listings.stream().parallel()
 .filter(org ->
 (clientContracts.stream()
 .parallel()
 .allMatch(contract -> !contract.getOrganization().getOrganizationId().equals(org.getOrganizationId())))
 && (org.getMinDailyCapacity() == null || org.getMinDailyCapacity() >= filter.getGuests())
 && (filter.getGuests() <= org.getMaxDailyCapacity())
 && (filter.getMaxBudget() >= org.getTotalPrice(filter.getGuests()).doubleValue())
 && (filter.getType() == null || filter.getType() == org.getOrganizationType())
 )
 */

}

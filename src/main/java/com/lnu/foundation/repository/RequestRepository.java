package com.lnu.foundation.repository;

import com.lnu.foundation.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by rucsac on 15/10/2018.
 */
@RepositoryRestResource(excerptProjection = RequestProjection.class)
public interface RequestRepository extends JpaRepository<Request, Long> {
    @RestResource(path = "byProvider", rel = "byProvider")
    Collection<Request> findByContract_Organization_Provider_Username(@Param("provider") String username);

    @RestResource(path = "byClient", rel = "byClient")
    Collection<Request> findByContract_Client_Username(@Param("client") String username);

    @RestResource(path = "byProviderId", rel = "byProviderId")
    Collection<Request> findByContract_Organization_Provider_UserId(@Param("id") Long id);

    @RestResource(path = "byProviderOrgId", rel = "byProviderOrgId")
    Collection<Request> findByContract_Organization_OrganizationId(@Param("id") Long id);

    @RestResource(path = "byClientId", rel = "byClientId")
    Collection<Request> findByContract_Client_UserId(@Param("id") Long id);

    @RestResource(path = "byContractId", rel = "byContractId")
    Collection<Request> findByContract_ContractId(@Param("id") Long id);

    @RestResource
    @Query("select t from Request t left join t.contract th where th.contractId = ?1 and t.duration.startTime is null")
    Collection<Request> byContractIdRequested(@Param("id") Long id);

    @RestResource
    @Query("select t from Request t left join t.contract th where th.contractId = ?1 and t.duration.startTime is not null and t.duration.endTime >= CURRENT_TIME ")
    Collection<Request> byContractIdUpcoming(@Param("id") Long id);

    @RestResource
    @Query("select t from Request t left join t.contract th where th.contractId = ?1 and t.duration.startTime is not null and t.duration.endTime < CURRENT_TIME ")
    Collection<Request> byContractIdHistory(@Param("id") Long id);

    @RestResource(path = "byProviderUpcoming", rel = "byProviderUpcoming")
    Collection<Request> findByRequestIdAndDuration_StartTimeNotNullAndDuration_StartTimeGreaterThan(@Param("requestId") long contractId, @Param("currentDate") LocalDateTime currentDate);

}

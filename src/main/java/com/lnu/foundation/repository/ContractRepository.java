package com.lnu.foundation.repository;

import com.lnu.foundation.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Collection;
import java.util.List;

/**
 * Created by rucsac on 15/10/2018.
 */
@RepositoryRestResource(excerptProjection = ContractProjection.class)
public interface ContractRepository extends JpaRepository<Contract, Long> {

    @RestResource(path = "byProvider", rel = "byProvider")
    List<Contract> findByOrganization_Provider_Username(@Param("provider") String provider);

    @RestResource(path = "byClient", rel = "byClient")
    List<Contract> findByClient_Username(@Param("client") String client);

    @RestResource(path = "byOrganization", rel = "byOrganization")
    List<Contract> findByOrganization_OrganizationId(@Param("organizationId") Long organizationId);

    @RestResource(path = "byProviderOngoing", rel = "byProviderOngoing")
    Collection<Contract> findByOrganization_Provider_UsernameAndDuration_EndTimeIsNull(@Param("provider") String provider);

    @RestResource(path = "byClientOngoing", rel = "byClientOngoing")
    Collection<Contract> findByClient_UsernameAndDuration_EndTimeIsNull(@Param("client") String client);

    @RestResource(path = "byProviderHistory", rel = "byProviderHistory")
    Collection<Contract> findByOrganization_Provider_UsernameAndDuration_EndTimeNotNull(@Param("provider") String provider);

    @RestResource(path = "byClientHistory", rel = "byClientHistory")
    Collection<Contract> findByClient_UsernameAndDuration_EndTimeNotNull(@Param("client") String client);
}

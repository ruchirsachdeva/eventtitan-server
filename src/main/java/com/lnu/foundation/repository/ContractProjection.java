package com.lnu.foundation.repository;

import com.lnu.foundation.model.Contract;
import com.lnu.foundation.model.Duration;
import com.lnu.foundation.model.Organization;
import com.lnu.foundation.model.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "contractProjection", types = {Contract.class})
public interface ContractProjection {


    Long getContractId();

    User getClient();

    Duration getDuration();

    Organization getOrganization();

}

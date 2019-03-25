package com.lnu.foundation.service;

import com.lnu.foundation.model.Duration;
import com.lnu.foundation.model.Organization;
import com.lnu.foundation.model.Contract;
import com.lnu.foundation.model.User;
import com.lnu.foundation.repository.OrganizationRepository;
import com.lnu.foundation.repository.ContractRepository;
import com.lnu.foundation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContractService {


    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private OrganizationRepository orgRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityContextService context;


    public void startContract(Integer guests, Long organizationId) {
        Contract contract = new Contract();
        User me = context.getMe();
        contract.setClient(me);
        contract.setGuests(guests);
        Organization org = this.orgRepository.getOne(organizationId);
        contract.setOrganization(org);
        contract.setDuration(new Duration(LocalDateTime.now(), null));
        this.contractRepository.save(contract);
    }

    public void endContract(Long contractId) {
        Contract contract = contractRepository.getOne(contractId);
       contract.endContract();
        this.contractRepository.save(contract);
    }


    private User findNearestProviderByOrganization(User client, Long organizationId) {


        List<User> possibleProviders = this.userRepository.findByRole_NameAndOrganizations_organizationId("physician", organizationId);
        double distance = 0d;
        double minDistance = 1000000d;
        User nearestProvider = null;
        for (User possibleProvider : possibleProviders) {
            distance = DistanceHelper.distance(possibleProvider.getLat(), possibleProvider.getLongitude(), client.getLat(), client.getLongitude(), null);
            if (distance < minDistance) {
                minDistance = distance;
                nearestProvider = possibleProvider;
            }
        }
        return nearestProvider;
    }

}

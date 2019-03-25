package com.lnu.foundation.service;

import com.lnu.foundation.model.*;
import com.lnu.foundation.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by rucsac on 15/10/2018.
 */
@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private RequestRepository requestRepo;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractService contractService;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private OrganizationRepository orgRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SecurityContextService context;


    public Collection<Request> getRequests(String username) {
        return requestRepo.findByContract_Organization_Provider_Username(username);
    }


    public Collection<Request> getRequests() {
        return requestRepo.findAll();
    }

    public Collection<Request> getClientRequests(String username) {
        return requestRepo.findByContract_Client_Username(username);
    }

    public List<Contract> getContractsByClient(String username) {
        return contractRepository.findByClient_Username(username);
    }

    public List<Contract> getContractsByProvider(String username) {
        return contractRepository.findByOrganization_Provider_Username(username);
    }


    public List<Contract> getContracts() {
        return contractRepository.findAll();
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public User signup(SignupForm signupForm) {
        Role role = this.roleRepo.findByName(signupForm.getRoleName());

        final User user = new User();
        user.setEmail(signupForm.getEmail());
        user.setUsername(signupForm.getUsername());
        user.setFirstName(signupForm.getName());
        if (signupForm.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
        }
        user.setLat(signupForm.getLatitude());
        user.setLongitude(signupForm.getLongitude());
        user.setRole(role);
        if ("provider".equalsIgnoreCase(signupForm.getRoleName())) {
            Organization org = this.orgRepo.getOne(signupForm.getOrganizationId());
            user.addOrganization(org);
        }
        repository.save(user);


        return user;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public User save(User user) {
        repository.save(user);
        return user;
    }


    // Will be called after form based authentication to fetch user details
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        com.lnu.foundation.model.User user = repository.findByUsername(email).orElseThrow(() -> new UsernameNotFoundException("No user found with email: " + email));
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority("LOGGED_USER"));
        //new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities)
        return user;


    }


    public User findUserByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }

    public User findByRole_Name(String role) {
        return repository.findByRole_Name(role).get(0);
    }

    public void addListing(User user, Organization org) {
        if (user.isProvider()) {
            org.setProvider(user);
            user.addOrganization(org);
        }
        repository.save(user);
    }
    /**
     public List<Duration> getAvailableWorkingDurations(long requestId) {
     Request request = requestRepo.getOne(requestId);
     Organization providerOrganization = request.getContract().getOrganization();
     List<Duration> available = providerOrganization.getWorkingDates(LocalDate.now().plusMonths(12));
     List<Duration> upcoming = new ArrayList<>();
     Collection<Request> upcomingSessions = requestRepo.findByRequestIdAndDuration_StartTimeNotNullAndDuration_StartTimeGreaterThan(requestId, LocalDateTime.now());
     for (Request upcomingSession : upcomingSessions) {
     upcoming.add(upcomingSession.getDuration());
     }
     return new DurationsHelper().calculateAvailableDurations(available, upcoming);
     }
     */
}

package com.lnu.foundation.service;

import com.lnu.foundation.model.Contract;
import com.lnu.foundation.model.Duration;
import com.lnu.foundation.model.Request;
import com.lnu.foundation.repository.RequestRepository;
import com.lnu.foundation.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by rucsac on 04/11/2018.
 */
@Service
@Transactional
public class RequestService {


    @Autowired
    private RequestRepository sessionRepo;


    @Autowired
    private ContractRepository contractRepository;


    public void requestSession(long contractId, int requestedHours) {
        Contract contract = contractRepository.getOne(contractId);
        Request session = new Request();
        session.setContract(contract);
        sessionRepo.save(session);
    }

    public void bookSession(long sessionId, Duration duration) {
        Request session = sessionRepo.getOne(sessionId);
        session.setDuration(duration);
        sessionRepo.save(session);
    }


    public void endSession(Long sessionId) {
        Request session = sessionRepo.getOne(sessionId);
        Duration duration = session.getDuration();
        LocalDateTime now = LocalDateTime.now();
        if(duration.getStartTime()!=null && duration.getStartTime().isAfter(now)){
        duration.setStartTime(now);
        }
        duration.setEndTime(now);
        session.setDuration(duration);
        sessionRepo.save(session);

    }
}

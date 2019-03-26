package com.lnu.foundation.service;

import com.lnu.foundation.model.*;
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
    private RequestRepository requestRepo;


    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private SecurityContextService context;


    public void createRequest(long contractId, String type) {
        Contract contract = contractRepository.getOne(contractId);
        Request request = new Request();
        Note note = new Note();
        note.setNote("Request type is : + " + type);
        note.setRequest(request);
        note.setUser(context.getMe());
        request.addNote(note);
        request.setContract(contract);
        Duration duration = new Duration();
        duration.setStartTime(LocalDateTime.now());
        request.setDuration(duration);
        requestRepo.save(request);
    }

    public void endRequest(Long requestId) {
        Request request = requestRepo.getOne(requestId);
        Duration duration = request.getDuration();
        LocalDateTime now = LocalDateTime.now();
        if (duration.getStartTime() != null && duration.getStartTime().isAfter(now)) {
            duration.setStartTime(now);
        }
        duration.setEndTime(now);
        request.setDuration(duration);
        requestRepo.save(request);

    }
}

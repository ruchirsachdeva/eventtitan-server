package com.lnu.foundation.service;

import com.lnu.foundation.model.Note;
import com.lnu.foundation.model.Request;
import com.lnu.foundation.model.User;
import com.lnu.foundation.repository.NoteRepository;
import com.lnu.foundation.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by rucsac on 04/11/2018.
 */
@Service
@Transactional
public class NoteService {


    @Autowired
    private RequestRepository requestRepo;

    @Autowired
    private NoteRepository noterepo;

    public Collection<Note> addNote(Long requestId, Note note, User user) {
        Request request = requestRepo.getOne(requestId);
        note.setProviderUser(user);
        note.setRequest(request);
        noterepo.save(note);
        return noterepo.findByRequest(request);
    }
}

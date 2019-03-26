package com.lnu.foundation.repository;

import com.lnu.foundation.model.Duration;
import com.lnu.foundation.model.Note;
import com.lnu.foundation.model.Request;
import com.lnu.foundation.model.User;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "noteProjection", types = {Note.class})
public interface NoteProjection {

    Long getNoteId();

    Long getRequestId();

    String getNote();

    UserProjection getUser();


    @Projection(name = "userProjection", types = {User.class})
    interface UserProjection {
        Long getUserId();

        String getUsername();
    }
}

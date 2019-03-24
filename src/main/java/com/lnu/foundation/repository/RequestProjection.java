package com.lnu.foundation.repository;

import com.lnu.foundation.model.*;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "requestProjection", types = {Request.class})
public interface RequestProjection {

    Long getRequestId();

    ContractProjection getContract();

    List<Note> getNotes();

    Duration getDuration();

    int getRequestedHours();

}

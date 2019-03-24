package com.lnu.foundation.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private List<Note> notes = new ArrayList<>();


    @Embedded
    private Duration duration;


    public void addNote(Note note) {
        this.notes.add(note);
    }
}

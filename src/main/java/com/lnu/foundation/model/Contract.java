package com.lnu.foundation.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @Embedded
    private Duration duration;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    // Remove if want to restrict delition of listing which have contracts associated
    @JsonIgnore
    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Request> requests = new ArrayList<>();

    private Integer guests;

    public void addRequest(Request request) {
        requests.add(request);
        request.setContract(this);
    }

    public void removeRequest(Request request) {
        requests.remove(request);
        request.setContract(null);
    }

    public void endContract() {
        if (this.duration == null) {
            this.duration = new Duration(LocalDateTime.now(), LocalDateTime.now());
        } else {
            this.duration.setEndTime(LocalDateTime.now());
        }
    }
}

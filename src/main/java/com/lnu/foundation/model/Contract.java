package com.lnu.foundation.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

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

    private Integer guests;


    public void endContract() {
        Duration duration = getDuration();
        duration.setEndTime(LocalDateTime.now());
        setDuration(duration);
    }
}

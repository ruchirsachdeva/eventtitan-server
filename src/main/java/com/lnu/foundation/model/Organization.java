package com.lnu.foundation.model;


import com.fasterxml.jackson.annotation.*;
import com.lnu.foundation.service.DistanceHelper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true, value = {"users", "organizations"})
public class Organization implements Comparable<Organization> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organizationId;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    @JsonIgnore
    private User provider;

    @Enumerated(EnumType.STRING)
    private OrganizationType organizationType;

    private Double lat;
    @Column(name = "`long`")
    private Double longitude;
    private Integer minDailyCapacity;
    private Integer maxDailyCapacity;
    private Double atHomeServiceRadius;
    private BigDecimal pricePerUnit;
    private BigDecimal totalPrice;

    @OneToOne
    @JoinColumn(name = "working_hours_id")
    private WorkingHours workingHours;

    @Lob
    @Basic
    private byte[] image;

    @Transient
    private Integer distance;

    public String toString() {
        return "Organization(organizationId=" + this.getOrganizationId() + ", name=" + this.getName() + ")";
    }

    public BigDecimal getTotalPrice(int units) {
        return this.totalPrice != null ? this.totalPrice : pricePerUnit.multiply(BigDecimal.valueOf(units));
    }


    private WorkingHours getNonNullWorkingHours() {
        return this.workingHours == null ? new WorkingHours() :
                this.workingHours;
    }


    @JsonProperty
    public List<Duration> getWorkingDates(LocalDate toDate) {
        List<Duration> workingDates = getNonNullWorkingHours().getWorkingDates(toDate);
        return workingDates;
    }


    @JsonProperty
    public String getBase64() {
        return Base64.encodeBase64String(this.image);
    }

    public void setBase64(String base64) {
        byte[] imageByte = Base64.decodeBase64(base64);
        this.setImage(imageByte);
    }

    public void setImageBase64(String base64) {
        byte[] imageByte = Base64.decodeBase64(base64);
        this.setImage(imageByte);
    }

    @JsonProperty
    public Integer getDistance() {
        return this.distance;
    }

    public void setDistance(double clientLatitude, double clientLongitude) {
        this.distance = getDistance(clientLatitude, clientLongitude);
    }

    public Integer getDistance(double clientLatitude, double clientLongitude) {
        return DistanceHelper.distance(getLat(), getLongitude(), clientLatitude, clientLongitude, "K").intValue();
    }

    @Override
    public int compareTo(Organization to) {
        return this.getDistance().compareTo(to.getDistance());
    }
}

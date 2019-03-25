package com.lnu.foundation.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lnu.foundation.service.DistanceHelper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    public Organization(OrganizationType organizationType, String organizationName, Double latitude,
                        Double longitude, Integer dailyCapacity, Double pricePerUnit, Double totalPrice, String base64) {
        this.organizationType = organizationType;
        this.name = organizationName;
        this.lat = latitude;
        this.longitude = longitude;
        this.maxDailyCapacity = dailyCapacity;
        if (pricePerUnit != null) {
            this.pricePerUnit = BigDecimal.valueOf(pricePerUnit);
        }
        if (totalPrice != null) {
            this.totalPrice = BigDecimal.valueOf(totalPrice);
        }
        this.setImageBase64(base64);
    }

    public static Organization from(SignupForm signupForm) {
        String organizationType = signupForm.getOrganizationType();
        OrganizationType type = OrganizationType.valueOf(organizationType);
        return new Organization(type, signupForm.getName()
                , signupForm.getLatitude(), signupForm.getLongitude(), signupForm.getMaxDailyCapacity(), signupForm.getPricePerUnit(), signupForm.getTotalPrice()
                , signupForm.getBase64());
    }

    public String toString() {
        return "Organization(organizationId=" + this.getOrganizationId() + ", firstName=" + this.getName() + ")";
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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Organization)) return false;
        final Organization other = (Organization) o;
        if (!other.canEqual(this)) return false;
        final Object this$organizationId = this.getOrganizationId();
        final Object other$organizationId = other.getOrganizationId();
        if (!Objects.equals(this$organizationId, other$organizationId))
            return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (!Objects.equals(this$name, other$name)) return false;
        final Object this$distance = this.getDistance();
        final Object other$distance = other.getDistance();
        if (!Objects.equals(this$distance, other$distance)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Organization;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $organizationId = this.getOrganizationId();
        result = result * PRIME + ($organizationId == null ? 43 : $organizationId.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        result = result * PRIME + java.util.Arrays.hashCode(this.getImage());
        final Object $distance = this.getDistance();
        result = result * PRIME + ($distance == null ? 43 : $distance.hashCode());
        return result;
    }
}

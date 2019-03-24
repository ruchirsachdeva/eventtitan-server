package com.lnu.foundation.model;

import com.lnu.foundation.service.DistanceHelper;
import lombok.Data;

@Data
public class Listing extends Organization {
    private double distance;

    public static Listing of(Organization org, double clientLatitude, double clientLongitude) {
        Listing listing = (Listing) org;
        double distance = DistanceHelper.distance(org.getLat(), org.getLongitude(), clientLatitude, clientLongitude, null);
        listing.setDistance(distance);
        return listing;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}

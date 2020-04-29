/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supers.DTOs;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ursul
 */
public class Location {

    private int locationId;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Boolean isDeleted;
    private List<Super> supersList;
    private List<Sighting> sightingsList;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.locationId;
        hash = 67 * hash + Objects.hashCode(this.address);
        hash = 67 * hash + Objects.hashCode(this.latitude);
        hash = 67 * hash + Objects.hashCode(this.longitude);
        hash = 67 * hash + Objects.hashCode(this.isDeleted);
        hash = 67 * hash + Objects.hashCode(this.supersList);
        hash = 67 * hash + Objects.hashCode(this.sightingsList);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (this.locationId != other.locationId) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        if (!Objects.equals(this.isDeleted, other.isDeleted)) {
            return false;
        }
        if (!Objects.equals(this.supersList, other.supersList)) {
            return false;
        }
        if (!Objects.equals(this.sightingsList, other.sightingsList)) {
            return false;
        }
        return true;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<Super> getSupersList() {
        return supersList;
    }

    public void setSupersList(List<Super> supersList) {
        this.supersList = supersList;
    }

    public List<Sighting> getSightingsList() {
        return sightingsList;
    }

    public void setSightingsList(List<Sighting> sightingsList) {
        this.sightingsList = sightingsList;
    }

}

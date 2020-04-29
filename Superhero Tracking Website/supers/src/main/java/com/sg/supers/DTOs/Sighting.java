/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supers.DTOs;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ursul
 */
public class Sighting {

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.sightingId;
        hash = 41 * hash + Objects.hashCode(this.loc);
        hash = 41 * hash + Objects.hashCode(this.sup);
        hash = 41 * hash + Objects.hashCode(this.sightDate);
        hash = 41 * hash + Objects.hashCode(this.isDeleted);
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
        final Sighting other = (Sighting) obj;
        if (this.sightingId != other.sightingId) {
            return false;
        }
        if (!Objects.equals(this.loc, other.loc)) {
            return false;
        }
        if (!Objects.equals(this.sup, other.sup)) {
            return false;
        }
        if (!Objects.equals(this.sightDate, other.sightDate)) {
            return false;
        }
        if (!Objects.equals(this.isDeleted, other.isDeleted)) {
            return false;
        }
        return true;
    }

    private int sightingId;
    private Location loc;
    private Super sup;
    private LocalDate sightDate;
    private Boolean isDeleted;

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public Super getSup() {
        return sup;
    }

    public void setSup(Super sup) {
        this.sup = sup;
    }

    public LocalDate getSightDate() {
        return sightDate;
    }

    public void setSightDate(LocalDate sightDate) {
        this.sightDate = sightDate;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    
   



}

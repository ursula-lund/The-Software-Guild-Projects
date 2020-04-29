/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supers.DTOs;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author ursul
 */
public class Super {

    private int superId;
    private String superName;
    private String superDesc;
    private Power superPower;
    private int powerId;
    private Boolean isDeleted;
    private List<Organization> organizationsList;
    private List<Sighting> sightingsList;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.superId;
        hash = 79 * hash + Objects.hashCode(this.superName);
        hash = 79 * hash + Objects.hashCode(this.superDesc);
        hash = 79 * hash + Objects.hashCode(this.superPower);
        hash = 79 * hash + this.powerId;
        hash = 79 * hash + Objects.hashCode(this.isDeleted);
        hash = 79 * hash + Objects.hashCode(this.organizationsList);
        hash = 79 * hash + Objects.hashCode(this.sightingsList);
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
        final Super other = (Super) obj;
        if (this.superId != other.superId) {
            return false;
        }
        if (this.powerId != other.powerId) {
            return false;
        }
        if (!Objects.equals(this.superName, other.superName)) {
            return false;
        }
        if (!Objects.equals(this.superDesc, other.superDesc)) {
            return false;
        }
        if (!Objects.equals(this.superPower, other.superPower)) {
            return false;
        }
        if (!Objects.equals(this.isDeleted, other.isDeleted)) {
            return false;
        }
        if (!Objects.equals(this.organizationsList, other.organizationsList)) {
            return false;
        }
        if (!Objects.equals(this.sightingsList, other.sightingsList)) {
            return false;
        }
        return true;
    }

    public int getSuperId() {
        return superId;
    }

    public void setSuperId(int superId) {
        this.superId = superId;
    }

    public String getSuperName() {
        return superName;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

    public String getSuperDesc() {
        return superDesc;
    }

    public void setSuperDesc(String superDesc) {
        this.superDesc = superDesc;
    }

    public Power getSuperPower() {
        return superPower;
    }

    public void setSuperPower(Power superPower) {
        this.superPower = superPower;
    }

    public int getPowerId() {
        return powerId;
    }

    public void setPowerId(int powerId) {
        this.powerId = powerId;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<Organization> getOrganizationsList() {
        return organizationsList;
    }

    public void setOrganizationsList(List<Organization> organizationsList) {
        this.organizationsList = organizationsList;
    }

    public List<Sighting> getSightingsList() {
        return sightingsList;
    }

    public void setSightingsList(List<Sighting> sightingsList) {
        this.sightingsList = sightingsList;
    }
    

    

  

   
   
}

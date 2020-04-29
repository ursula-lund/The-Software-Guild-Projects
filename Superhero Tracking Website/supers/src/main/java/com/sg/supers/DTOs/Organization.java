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
public class Organization {

    private int organizationId;
    private String description;
    private String address;
    private String phone;
    private Boolean isDeleted;
    private String orgName;
    private List<Super> supersList;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + this.organizationId;
        hash = 19 * hash + Objects.hashCode(this.description);
        hash = 19 * hash + Objects.hashCode(this.address);
        hash = 19 * hash + Objects.hashCode(this.phone);
        hash = 19 * hash + Objects.hashCode(this.isDeleted);
        hash = 19 * hash + Objects.hashCode(this.orgName);
        hash = 19 * hash + Objects.hashCode(this.supersList);
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
        final Organization other = (Organization) obj;
        if (this.organizationId != other.organizationId) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.orgName, other.orgName)) {
            return false;
        }
        if (!Objects.equals(this.isDeleted, other.isDeleted)) {
            return false;
        }
        if (!Objects.equals(this.supersList, other.supersList)) {
            return false;
        }
        return true;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public List<Super> getSupersList() {
        return supersList;
    }

    public void setSupersList(List<Super> supersList) {
        this.supersList = supersList;
    }

}

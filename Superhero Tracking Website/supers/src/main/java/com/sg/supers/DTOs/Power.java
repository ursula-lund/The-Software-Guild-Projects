/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supers.DTOs;

import java.util.Objects;

/**
 *
 * @author ursul
 */
public class Power {

    private int powerId;
    private String powerDesc;
    private String powerName;
    private Boolean isDeleted;
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.powerId;
         hash = 59 * hash + Objects.hashCode(this.powerDesc);
        hash = 59 * hash + Objects.hashCode(this.powerName);
        hash = 59 * hash + Objects.hashCode(this.isDeleted);
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
        final Power other = (Power) obj;
        if (this.powerId != other.powerId) {
            return false;
        }
         if (!Objects.equals(this.powerDesc, other.powerDesc)) {
            return false;
        }
        if (!Objects.equals(this.powerName, other.powerName)) {
            return false;
        }
        if (!Objects.equals(this.isDeleted, other.isDeleted)) {
            return false;
        }
        return true;
    }

    

  
    public int getPowerId() {
        return powerId;
    }

    public void setPowerId(int superPowerId) {
        this.powerId = superPowerId;
    }

    public String getPowerDesc() {
        return powerDesc;
    }

    public void setPowerDesc(String powerDesc) {
        this.powerDesc = powerDesc;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}

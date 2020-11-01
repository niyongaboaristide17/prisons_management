/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author aristide
 */
@Embeddable
public class Crime implements Serializable {
    
    private String crimeName;
    private String crimeDetails;

    public Crime() {
    }

    public String getCrimeName() {
        return crimeName;
    }

    public void setCrimeName(String crimeName) {
        this.crimeName = crimeName;
    }

    public String getCrimeDetails() {
        return crimeDetails;
    }

    public void setCrimeDetails(String crimeDetails) {
        this.crimeDetails = crimeDetails;
    }
     
}

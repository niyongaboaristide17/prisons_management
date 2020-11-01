/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.domain;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author aristide
 */
@Entity
@DiscriminatorValue("SUPERVISOR")
public class Supervisor extends Guard{
    
    @OneToMany(mappedBy = "supervisor")
    private List<Guard> guards;

    public Supervisor() {
    }

    public Supervisor(String guardId) {
        super(guardId);
    }
    
    public List<Guard> getGuards() {
        return guards;
    }

    public void setGuards(List<Guard> guards) {
        this.guards = guards;
    }
      
}

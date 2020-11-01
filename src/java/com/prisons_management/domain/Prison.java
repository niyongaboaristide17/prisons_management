/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.domain;

import com.prisons_management.utilities.PrisonIDGenerator;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author aristide
 */
@Entity
public class Prison implements Serializable {
    @Id
    @GeneratedValue(generator = PrisonIDGenerator.generatorName)
    @GenericGenerator(name = PrisonIDGenerator.generatorName, strategy = "com.prisons_management.utilities.PrisonIDGenerator")
    private String prisonCode;
    private String prisonName;
    private int prisonCapacity;
    
    @OneToMany(mappedBy = "prison", fetch = FetchType.EAGER)
    private List<Prisoner> prisoners;
    @OneToMany(mappedBy = "prison", fetch = FetchType.EAGER)
    private List<Guard> guards;

    public Prison() {
    }

    public Prison(String prisonCode) {
        this.prisonCode = prisonCode;
    }
       
    public Prison(String prisonName, int prisonCapacity) {
        this.prisonName = prisonName;
        this.prisonCapacity = prisonCapacity;
    }

    public String getPrisonCode() {
        return prisonCode;
    }

    public void setPrisonCode(String prisonCode) {
        this.prisonCode = prisonCode;
    }

    public String getPrisonName() {
        return prisonName;
    }

    public void setPrisonName(String prisonName) {
        this.prisonName = prisonName;
    }

    public int getPrisonCapacity() {
        return prisonCapacity;
    }

    public void setPrisonCapacity(int prisonCapacity) {
        this.prisonCapacity = prisonCapacity;
    }

    public List<Prisoner> getPrisoners() {
        return prisoners;
    }

    public void setPrisoners(List<Prisoner> prisoners) {
        this.prisoners = prisoners;
    }

    public List<Guard> getGuards() {
        return guards;
    }

    public void setGuards(List<Guard> guards) {
        this.guards = guards;
    }
    
    
    
}

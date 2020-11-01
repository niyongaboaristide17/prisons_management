/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.domain;

import com.prisons_management.utilities.PrisonerIDGenerator;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author aristide
 */
@Entity
public class Prisoner implements Serializable {
    @Id
    @GeneratedValue(generator = PrisonerIDGenerator.generatorName)
    @GenericGenerator(name = PrisonerIDGenerator.generatorName, strategy = "com.prisons_management.utilities.PrisonerIDGenerator")
    private String prisonerId;
    private String prisonerName;
    private int cellNumber;
    private String profilePicture;
    @ManyToOne
    private Prison prison = new Prison();
    private Crime crime = new Crime();
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date arrivalDate = new Date();
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date releaseDate;
    @OneToMany(mappedBy = "prisoner")
    private List<PrisonerVisitor> visits;
    @Enumerated(EnumType.STRING)
    private PrisonerStatus prisonerStatus = PrisonerStatus.JAILED;
    
    public Prisoner() {
    }

    public Prisoner(String prisonerName, int cellNumber, Prison prison) {
        this.prisonerName = prisonerName;
        this.cellNumber = cellNumber;
        this.prison = prison;
    }

    public Prisoner(String prisonerId) {
        this.prisonerId = prisonerId;
    }
    
    
    
    public String getPrisonerId() {
        return prisonerId;
    }

    public void setPrisonerId(String prisonerId) {
        this.prisonerId = prisonerId;
    }

    public String getPrisonerName() {
        return prisonerName;
    }

    public void setPrisonerName(String prisonerName) {
        this.prisonerName = prisonerName;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Prison getPrison() {
        return prison;
    }

    public void setPrison(Prison prison) {
        this.prison = prison;
    }

    public Crime getCrime() {
        return crime;
    }

    public void setCrime(Crime crime) {
        this.crime = crime;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<PrisonerVisitor> getVisits() {
        return visits;
    }

    public void setVisits(List<PrisonerVisitor> visits) {
        this.visits = visits;
    }

    public PrisonerStatus getPrisonerStatus() {
        return prisonerStatus;
    }

    public void setPrisonerStatus(PrisonerStatus prisonerStatus) {
        this.prisonerStatus = prisonerStatus;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author aristide
 */
@Entity
public class PrisonerVisitor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int visitId;
    @ManyToOne
    private Visitor visitor;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date requestedOn = new Date();
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date visitOn;
    private VisitStatus visitStatus = VisitStatus.PENDING;
    @ManyToOne
    private Prisoner prisoner;

    public PrisonerVisitor() {
        this.visitStatus = VisitStatus.PENDING;
        this.requestedOn = new Date();
    }

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public Date getRequestedOn() {
        return requestedOn;
    }

    public void setRequestedOn(Date requestedOn) {
        this.requestedOn = requestedOn;
    }

    public Date getVisitOn() {
        return visitOn;
    }

    public void setVisitOn(Date visitOn) {
        this.visitOn = visitOn;
    }

    public VisitStatus getVisitStatus() {
        return visitStatus;
    }

    public void setVisitStatus(VisitStatus visitStatus) {
        this.visitStatus = visitStatus;
    }

    public Prisoner getPrisoner() {
        return prisoner;
    }

    public void setPrisoner(Prisoner prisoner) {
        this.prisoner = prisoner;
    }

    
}

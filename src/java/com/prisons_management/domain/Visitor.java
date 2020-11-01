/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.domain;

import com.prisons_management.utilities.VisitorIDGenerator;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author aristide
 */
@Entity
public class Visitor implements Serializable {

    @OneToMany(mappedBy = "visitor")
    private List<PrisonerVisitor> visits;
    
    @Id
    @GeneratedValue(generator = VisitorIDGenerator.generatorName)
    @GenericGenerator(name = VisitorIDGenerator.generatorName, strategy = "com.prisons_management.utilities.VisitorIDGenerator")
    private String vistorId;
    private String visitorName;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String nationalIdOrPassport;

    public Visitor() {
    }

    public Visitor(String vistorId) {
        this.vistorId = vistorId;
    }
    

    
    
    public String getVistorId() {
        return vistorId;
    }

    public void setVistorId(String vistorId) {
        this.vistorId = vistorId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNationalIdOrPassport() {
        return nationalIdOrPassport;
    }

    public void setNationalIdOrPassport(String nationalIdOrPassport) {
        this.nationalIdOrPassport = nationalIdOrPassport;
    }

    public List<PrisonerVisitor> getVisits() {
        return visits;
    }

    public void setVisits(List<PrisonerVisitor> visits) {
        this.visits = visits;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
   
}

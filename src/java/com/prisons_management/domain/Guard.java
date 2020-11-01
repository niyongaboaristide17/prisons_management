/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.domain;

import com.prisons_management.utilities.GuardIDGenerator;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author aristide
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "GUARD", discriminatorType = DiscriminatorType.STRING)
public class Guard implements Serializable{
    @Id
    @GeneratedValue(generator = GuardIDGenerator.generatorName)
    @GenericGenerator(name = GuardIDGenerator.generatorName, strategy = "com.prisons_management.utilities.GuardIDGenerator")
    private String guardId;
    private String name;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    @ManyToOne
    private Supervisor supervisor;
    @Enumerated(EnumType.STRING)
    private GuardStatus status = GuardStatus.ACTIVE;
    @ManyToOne
    private Prison prison;

    public Guard() {
    }

    public Guard(String guardId) {
        this.guardId = guardId;
    }
    
    public String getGuardId() {
        return guardId;
    }

    public void setGuardId(String guardId) {
        this.guardId = guardId;
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


    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GuardStatus getStatus() {
        return status;
    }

    public void setStatus(GuardStatus status) {
        this.status = status;
    }

    public Prison getPrison() {
        return prison;
    }

    public void setPrison(Prison prison) {
        this.prison = prison;
    }

    @Override
    public String toString() {
        return guardId + "--------------------" + status; //To change body of generated methods, choose Tools | Templates.
    }
    
}

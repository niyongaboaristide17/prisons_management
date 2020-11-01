/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.model;

import com.prisons_management.dao.GuardDao;
import com.prisons_management.domain.Guard;
import com.prisons_management.domain.GuardStatus;
import com.prisons_management.domain.Prison;
import com.prisons_management.domain.Supervisor;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author aristide
 */
@ManagedBean
@RequestScoped
public class GuardModel {

    /**
     * Creates a new instance of GuardModel
     */
    private Guard guard = new Guard();
    private Map<String, String> map;
    private List<Guard> guards;
    private String myGuardStatus;
    
    public GuardModel() {
        map = new LinkedHashMap<>();
        for (Supervisor g : GuardDao.supervisorList()) {
            if (g.getStatus() != GuardStatus.ACTIVE) {
                continue;
            }
            map.put(g.getGuardId(), g.getName());
            System.out.println(g.getName());
        }
        guards = GuardDao.guardsList();
    }

    public Guard getGuard() {
        return guard;
    }

    public void setGuard(Guard guard) {
        this.guard = guard;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
    
    public List<Guard> getGuards() {
        return guards;
    }

    public void setGuards(List<Guard> guards) {
        this.guards = guards;
    }

    public String getMyGuardStatus() {
        return myGuardStatus;
    }

    public void setMyGuardStatus(String myGuardStatus) {
        this.myGuardStatus = myGuardStatus;
    }

    
    
    
    public String saveNewGuard(String guardId, String prisonCode){
        String msg;
        try {
            Supervisor supervisor = new Supervisor();
            supervisor.setGuardId(guardId);
            guard.setPrison(new Prison(prisonCode));
            guard.setSupervisor(supervisor);
            msg = GuardDao.createGuard(guard);
            guard = new Guard();
        } catch (Exception e) {
            msg = "Something went wrong";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
        return "/supervisor/addGuard.xhtml?faces-redirect=true";
    }
    
    public GuardStatus[] guardStatuses(){
        return GuardStatus.values();
    }
    
    public String updateGuardStatusActive(String guardId){
        System.out.println(guardId);
        System.out.println(myGuardStatus);
        String msg = GuardDao.updateStatus("ACTIVE", guardId);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
        return "/admin/viewSupervisor?faces-redirect=true";
    }
    public String updateGuardStatusFired(String guardId){
        System.out.println(guardId);
        System.out.println(myGuardStatus);
        String msg = GuardDao.updateStatus("FIRED", guardId);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
        return "/admin/viewSupervisor?faces-redirect=true";
    }
    public String updateGuardStatusBlocked(String guardId){
        System.out.println(guardId);
        System.out.println(myGuardStatus);
        String msg = GuardDao.updateStatus("BLOCKED", guardId);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
        return "/admin/viewSupervisor?faces-redirect=true";
    }

}

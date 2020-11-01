/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.model;

import com.prisons_management.dao.GuardDao;
import com.prisons_management.domain.GuardStatus;
import com.prisons_management.domain.Prison;
import com.prisons_management.domain.Supervisor;
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
public class SupervisorModel {

    /**
     * Creates a new instance of SupervisorModel
     */
    private Supervisor supervisor = new Supervisor();
    
    private String username;
    private String password;
    
    public SupervisorModel() {
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
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
    
    
    
    public String addNewSupervisor(String prisonCode){
        Prison p = new Prison(prisonCode);
        String msg;
        try {
            supervisor.setSupervisor(supervisor);
            supervisor.setPrison(p);
            msg = GuardDao.createGuard(supervisor);
            FacesContext.getCurrentInstance().addMessage(msg, new FacesMessage(msg));
            supervisor = new Supervisor();
            return "/admin/addSupervisor.xhtml";
        } catch (Exception e) {
            msg = "Something went wrong!!!";
            FacesContext.getCurrentInstance().addMessage(msg, new FacesMessage(msg));
            return "/admin/addSupervisor.xhtml";
        }
    }
    
    public String login(){
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            Supervisor superv = GuardDao.singleSupervisor(username, password);
            if (superv.getStatus() == GuardStatus.BLOCKED) {
                System.out.println(superv.getName()+" BLOCKED");
                fc.addMessage("BLOCKED", new FacesMessage("Account Has Been BLOCKED"));
                return "/supervisorLogin.xhtml?faces-redirect=true";
            }else if(superv.getStatus() == GuardStatus.FIRED){
                System.out.println(superv.getName()+" FIRED");
                fc.addMessage("FIRED", new FacesMessage("You have been Fired Access Denied"));
                return "/supervisorLogin.xhtml?faces-redirect=true";
            }
            fc.getExternalContext().getSessionMap().put("superv", superv);
            return "supervisor/supervisorLayout.xhtml?faces-redirect=true";
            
        } catch (Exception e) {
            return "supervisorLogin.xhtml?faces-redirect=true";
        }
        
    }
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/supervisorLogin.xhtml?faces-redirect=true";
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.model;

import com.prisons_management.dao.AdminstratorDao;
import com.prisons_management.dao.PrisonerDao;
import com.prisons_management.domain.Adminstrator;
import com.prisons_management.domain.Prisoner;
import com.prisons_management.domain.PrisonerStatus;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author aristide
 */
@ManagedBean
@ViewScoped
public class AdminModel {

    /**
     * Creates a new instance of AdminModel
     */
    private String username;
    private String password;
    
    public AdminModel() {
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
    
    public String login(){
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            Adminstrator adminstrator = AdminstratorDao.singleAdmin(username, password);
            fc.getExternalContext().getSessionMap().put("admin", adminstrator);
            return "admin/adminLayout.xhtml?faces-redirect=true";
            
        } catch (Exception e) {
            return "adminLogin.xhtml?faces-redirect=true";
        }
        
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }
    
    public List<Prisoner> prisonersAll(int id){
        List<Prisoner> l = new ArrayList<>();
        for (Prisoner prisoner : AdminstratorDao.singleAdminById(id).getPrison().getPrisoners()) {
            if (prisoner.getPrisonerStatus() == PrisonerStatus.JAILED) {
                l.add(prisoner);
            }
        }
        return l;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.model;

import com.prisons_management.dao.AdminstratorDao;
import com.prisons_management.domain.Adminstrator;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author aristide
 */
@ManagedBean
@RequestScoped
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
        return "/adminLogin.xhtml?faces-redirect=true";
    }
    
}

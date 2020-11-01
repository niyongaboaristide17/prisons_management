/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.model;

import com.prisons_management.dao.AdminstratorDao;
import com.prisons_management.dao.VisitorDao;
import com.prisons_management.domain.Adminstrator;
import com.prisons_management.domain.Visitor;
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
public class VisitorModel {

    /**
     * Creates a new instance of VisitorModel
     */
    private String username;
    private String password;
    
    private Visitor visitor = new Visitor();
    
    public VisitorModel() {
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
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
    
    
    public String createVisitor(){
        String msg;
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            msg = VisitorDao.createVisitor(visitor);
            fc.addMessage(msg, new FacesMessage(msg));
            return "/visitorLogin.xhtml?faces-redirect=true";
        } catch (Exception e) {
            msg = "something went wrong";
            fc.addMessage(msg, new FacesMessage(msg));
            return "/visitorCreateAccount.xhtml?faces-redirect=true";
        }
    }
    
    public String login(){
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            Visitor visito = VisitorDao.singleVisitor(username, password);
            fc.getExternalContext().getSessionMap().put("visito", visito);
            return "visitor/visitorLayout.xhtml?faces-redirect=true";
            
        } catch (Exception e) {
            return "adminLogin.xhtml?faces-redirect=true";
        }
        
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/visitorLogin.xhtml?faces-redirect=true";
    }
    
}

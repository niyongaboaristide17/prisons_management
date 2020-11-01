/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.model;

import com.prisons_management.dao.PrisonerDao;
import com.prisons_management.dao.PrisonerVisitorDao;
import com.prisons_management.domain.Prisoner;
import com.prisons_management.domain.PrisonerVisitor;
import com.prisons_management.domain.Visitor;
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
public class PrisonerVisitorModel {

    /**
     * Creates a new instance of PrisonerVisitorModel
     */
    private PrisonerVisitor prisonerVisitor = new PrisonerVisitor();
    
    private String prisonerName;
    private List<Prisoner> prisoners = new ArrayList<>();
    
    public PrisonerVisitorModel() {
        
    }

    public String getPrisonerName() {
        return prisonerName;
    }

    public void setPrisonerName(String prisonerName) {
        this.prisonerName = prisonerName;
    }

    public List<Prisoner> getPrisoners() {
        return prisoners;
    }

    public void setPrisoners(List<Prisoner> prisoners) {
        this.prisoners = prisoners;
    }
    
    public void searchListener(){
        prisoners = new ArrayList<>();
        for (Prisoner prisoner : PrisonerDao.allPrisoner()) {
            if ((prisoner.getPrisonerName().toUpperCase().startsWith(prisonerName.toUpperCase()) || 
                    prisoner.getPrisonerName().toUpperCase().endsWith(" "+prisonerName.toUpperCase()))) {
                prisoners.add(prisoner);
            }
        }
    }
    
    public String scheduleVisit(String prisonId, String visitorId){
        String msg;
        try {
            System.out.println("hey");
            prisonerVisitor.setPrisoner(new Prisoner(prisonId));
            prisonerVisitor.setVisitor(new Visitor(visitorId));
            msg = PrisonerVisitorDao.createVisit(prisonerVisitor);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
            return "/visitor/newVisit.xhtml?faces-redirect=true";
            
        } catch (Exception e) {
            msg = "something went wrong";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
            return "/visitor/newVisit.xhtml?faces-redirect=true";
        }
    }

    public PrisonerVisitor getPrisonerVisitor() {
        return prisonerVisitor;
    }

    public void setPrisonerVisitor(PrisonerVisitor prisonerVisitor) {
        this.prisonerVisitor = prisonerVisitor;
    }
    
}

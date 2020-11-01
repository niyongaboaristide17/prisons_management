/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.model;

import com.prisons_management.dao.PrisonDao;
import com.prisons_management.domain.Guard;
import com.prisons_management.domain.Prison;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author aristide
 */
@ManagedBean
@RequestScoped
public class PrisonModel {

    /**
     * Creates a new instance of PrisonModel
     */
//    List<Prison> prisons = new ArrayList<>();
    
    public PrisonModel() {
    }

//    public List<Prison> getPrisons() {
//        return prisons;
//    }
//
//    public void setPrisons(List<Prison> prisons) {
//        this.prisons = prisons;
//    }
    
    public List<Guard> someGuards(String prisonCode){
        Prison prison = PrisonDao.somePrison(prisonCode);
        return prison.getGuards();
    }
    
}

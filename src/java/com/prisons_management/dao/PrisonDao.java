/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.dao;

import com.prisons_management.domain.Prison;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author aristide
 */
public class PrisonDao {
    public static String createPrison(Prison prison){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(prison);
        s.getTransaction().commit();
        s.close();
        return prison.getPrisonName()+" Successfully created";
    }
    public static Prison somePrison(String prisonCode){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query query = s.createQuery("FROM Prison P WHERE P.prisonCode = :c");
        query.setParameter("c",prisonCode);
        Prison prison = (Prison) query.uniqueResult();
        s.close();
        return prison;
    }
    
    
}

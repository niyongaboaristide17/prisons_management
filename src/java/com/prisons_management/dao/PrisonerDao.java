/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.dao;

import com.prisons_management.domain.Prison;
import com.prisons_management.domain.Prisoner;
import com.prisons_management.domain.Supervisor;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author aristide
 */
public class PrisonerDao {
    public static String createPrisoner(Prisoner prisoner){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(prisoner);
        s.getTransaction().commit();
        s.close();
        return prisoner.getPrisonerName()+" Successfully registered";
    }
    
    public static List<Prisoner> allPrisonerInAprison(String prisonCode){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("FROM Prisoner A WHERE A.prison = :pid");
        q.setParameter("pid", new Prison(prisonCode));
        List<Prisoner> a =  q.list();
        session.close();
        return a;
    }
    
    public static List<Prisoner> allPrisoner(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("FROM Prisoner");
        List<Prisoner> a =  q.list();
        session.close();
        return a;
    }
}

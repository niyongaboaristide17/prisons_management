/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.dao;

import com.prisons_management.domain.PrisonerVisitor;
import com.prisons_management.domain.VisitStatus;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author aristide
 */
public class PrisonerVisitorDao {
    public static String createVisit(PrisonerVisitor pv){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(pv);
        session.getTransaction().commit();
        session.close();
        return "visit request Added";
    }
    public static List<PrisonerVisitor> readAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<PrisonerVisitor> prisonerVisitors = session.createQuery("From PrisonerVisitor").list();
        session.close();
        return prisonerVisitors;
    }
    public static String updateStatus(VisitStatus status, int id){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("UPDATE PrisonerVisitor P SET P.visitStatus = :vs WHERE P.visitId = :vsid");
        q.setParameter("vs", status);
        q.setParameter("vsid", id);
        q.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return "UPDATE SUCCESSFULLY";
        
    }
    
}

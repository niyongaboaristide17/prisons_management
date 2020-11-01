/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.dao;

import com.prisons_management.domain.PrisonerVisitor;
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
}

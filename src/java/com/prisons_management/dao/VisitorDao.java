/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.dao;

import com.prisons_management.domain.Adminstrator;
import com.prisons_management.domain.Guard;
import com.prisons_management.domain.Visitor;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author aristide
 */
public class VisitorDao {
    public static String createVisitor(Visitor visitor){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(visitor);
        s.getTransaction().commit();
        s.close();
        return visitor.getUsername()+" Registered";
    }
    
    public static Visitor singleVisitor(String username, String password){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("FROM Visitor A WHERE username = :u and password = :p");
        q.setParameter("u", username);
        q.setParameter("p", password);
        Visitor a = (Visitor) q.uniqueResult();
        session.close();
        return a;
    }
}

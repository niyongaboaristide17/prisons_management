/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.dao;

import com.prisons_management.domain.Adminstrator;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author aristide
 */
public class AdminstratorDao {
    public static String createAdmin(Adminstrator adminstrator){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(adminstrator);
        session.getTransaction().commit();
        session.close();
        return "Admin Added";
    }
    
    public static Adminstrator singleAdmin(String username, String password){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("FROM Adminstrator A WHERE username = :u and password = :p");
        q.setParameter("u", username);
        q.setParameter("p", password);
        Adminstrator a = (Adminstrator) q.uniqueResult();
        session.close();
        return a;
    }
    
    public static Adminstrator singleAdminById(int id){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("FROM Adminstrator A WHERE adminId = :aid");
        q.setParameter("aid", id);
        Adminstrator a = (Adminstrator) q.uniqueResult();
        session.close();
        return a;
    }
    
}

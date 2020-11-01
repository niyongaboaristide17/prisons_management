/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.dao;

import com.prisons_management.domain.Adminstrator;
import com.prisons_management.domain.Guard;
import com.prisons_management.domain.GuardStatus;
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
public class GuardDao {
    public static String createGuard(Guard guard){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(guard);
        s.getTransaction().commit();
        s.close();
        return guard.getUsername()+" Registered";
    }
    
    public static List<Guard> guardsList(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Guard> l = s.createQuery("From Guard").list();
        s.close();
        return l;
    }
    public static List<Supervisor> supervisorList(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Supervisor> l = s.createQuery("From Supervisor").list();
        s.close();
        return l;
    }
    public static String updateStatus(String status, String guardId){
        System.out.println(status);
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        String qryString = "update Guard g set g.status= :st where g.guardId= :gid";
        Query query = s.createQuery(qryString);
        query.setParameter("st", GuardStatus.valueOf(status));
        query.setParameter("gid", guardId);
        int msg = query.executeUpdate();
        s.getTransaction().commit();
        s.close();
        return msg+" affected "+(status);
    }
    public void updateGuard(Guard g){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.update(g);
        s.getTransaction().commit();
        s.close();
    }
    
    public static Supervisor singleSupervisor(String username, String password){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("FROM Supervisor A WHERE username = :u and password = :p");
        q.setParameter("u", username);
        q.setParameter("p", password);
        Supervisor a = (Supervisor) q.uniqueResult();
        session.close();
        return a;
    }
    
    public static List<Guard> allGuardInAprison(String prisonCode){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("FROM Guard A WHERE A.prison = :pid");
        q.setParameter("pid", new Prison(prisonCode));
        List<Guard> a =  q.list();
        session.close();
        return a;
    }
    
}

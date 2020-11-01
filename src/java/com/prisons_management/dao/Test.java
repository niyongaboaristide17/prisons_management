/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.dao;

import com.prisons_management.domain.Prisoner;
import com.prisons_management.domain.PrisonerVisitor;
import com.prisons_management.domain.Visitor;
import java.util.Date;

/**
 *
 * @author aristide
 */
public class Test {
    public static void main(String[] args) {
//        Prisoner p = new Prisoner("Aristide NIYONGABO", 1, new Prison("PR38434594e015411097725234cc8ba2d6"));
//        System.out.println(PrisonDao.createPrison(new Prison("MAGERAGERE", 500)));
//        System.out.println(PrisonerDao.createPrisoner(p));
//        System.out.println(p.getPrisonerId());

//        Guard supervisor = new Guard();
//        supervisor.setName("MUNYANEZA Derrick");
//        supervisor.setPassword("super");
//        supervisor.setSupervisor(new Supervisor("GDfcf1c50d05e64c939207d3d9b8dde976"));
//        supervisor.setUsername("derro");
//        System.err.println(GuardDao.createGuard(supervisor));

//        for (Supervisor supervisor : GuardDao.supervisorList()) {
//            System.out.println(supervisor.getName());
//        }
//
//        Prison p = new Prison("MAGERAGERE", 1000);
//        System.out.println(PrisonDao.createPrison(p));

//        Adminstrator a = new Adminstrator();
//        a.setUsername("aristide");
//        a.setPassword("pass");
//        Prison p = new Prison();
//        p.setPrisonCode("PR1d9c91a4ac764d1ba69e3c51b1ef2614");
//        a.setPrison(p);
//        System.out.println(AdminstratorDao.createAdmin(a));
        
//        Adminstrator a = AdminstratorDao.singleAdmin("niyongabo", "pass");
//        
//        System.out.println(a.getUsername()+" "+a.getPassword());


        PrisonerVisitor p = new PrisonerVisitor();
        p.setPrisoner(new Prisoner("INMATE620559e773c64120860747f6acffa190"));
        p.setVisitor(new Visitor("VZTe5dd220f3299440b8e4d2b596257bd14"));
        p.setVisitOn(new Date());
        
        

        String msg = PrisonerVisitorDao.createVisit(p);

    }
}

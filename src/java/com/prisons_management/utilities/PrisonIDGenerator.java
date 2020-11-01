/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prisons_management.utilities;

import java.io.Serializable;
import java.util.UUID;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 *
 * @author aristide
 */
public class PrisonIDGenerator implements IdentifierGenerator{
    
    public static final String generatorName = "myGenerator";
    @Override
    public Serializable generate(SessionImplementor si, Object o) throws HibernateException {
        return "PR"+UUID.randomUUID().toString().replace("-", "");
    }
    
}

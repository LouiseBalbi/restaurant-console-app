package dev.service;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.dao.IPlatDao;

import dev.exception.PlatException;



public class PlatServiceVersion1Test {

    private PlatServiceVersion1 platServiceVersion1;
    private IPlatDao dao;

    @BeforeEach
    public void setUp() {
        
        dao = mock(IPlatDao.class);
        platServiceVersion1 = new PlatServiceVersion1(dao); 
    }
    
    
    @Test
    public void ajouterPlatNomInvalide() throws PlatException {

    	assertThrows(PlatException.class,()-> platServiceVersion1.ajouterPlat("la", 1234));
    }
    
    
    @Test
    public void ajouterPlatPrixInvalide() {
    	
    	assertThrows(PlatException.class,()-> platServiceVersion1.ajouterPlat("PlatTest", 2));

    }

    
    @Test
    public void ajouterPlat() {
    	platServiceVersion1.ajouterPlat("PlatTest", 1234);
    	verify(dao, atLeastOnce()).ajouterPlat(anyString(), anyInt());
    }
}

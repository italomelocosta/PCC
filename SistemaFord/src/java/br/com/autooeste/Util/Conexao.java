/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Italo
 */
public class Conexao {
    private static EntityManagerFactory emFactory;
    
    private Conexao(){}
    
    public static EntityManager getEntityManager(){
        if(emFactory == null || !emFactory.isOpen()){
            emFactory = javax.persistence.Persistence
                .createEntityManagerFactory("SistemaFordPU");
        }
        return emFactory.createEntityManager();
    }
    
    public static void desconecta(){
        emFactory.close();
    }
}

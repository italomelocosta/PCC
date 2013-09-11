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
    private static EntityManagerFactory emFactory = javax.persistence.Persistence
            .createEntityManagerFactory("SistemaFordPU");
    
    public static EntityManager getEntityManager(){
        return emFactory.createEntityManager();
    }
    
    public static void desconecta(){
        emFactory.close();
    }
}


package com.efficace.componente.dao;

import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Fabio Sicupira Cavalcante
 */
public class HibernateUtil implements Serializable{
    
    private HibernateUtil(){}
    
    /**
     *
     * @return
     */
    public static Session getSession(){
        SessionFactory fabrica = new AnnotationConfiguration()
                .configure().buildSessionFactory();
        return fabrica.openSession();
    }
    
    public static Session getSession(String nomeArquivoConfiguracao){
        SessionFactory fabrica = new AnnotationConfiguration()
                .configure(nomeArquivoConfiguracao).buildSessionFactory();
        return fabrica.openSession();
    }
        
}

/*
 * Desenvolvido por Efficace Tecnologia
 * Brasil, 21-04-2011
 */
package com.efficace.componente.dao;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 * DaoGenerico.java
 * @author Fabio Sicupira Cavalcante
 * @version 0.1-SNAPTSHOT 21/04/2011
 * @param <T>
 */
@SuppressWarnings({"serial","rawtypes", "unchecked", "deprecation"})
public class DaoGenerico<T> implements Serializable {

    private final Session sessao;
    private final Class classe;
    private Transaction transacao;

    /**
     * Construtor da classe
     * @param sessao
     * @param classe
     */
    public DaoGenerico(Session sessao, Class classe) {
        this.sessao = sessao;
        this.classe = classe;
    }

    /**
     * Persiste dados da entidade na base de dados e retorna
     * os dados persistidos
     * @param entidade
     */
    public T salvar(T entidade) {
        this.transacao = this.sessao.beginTransaction();
        this.sessao.save(entidade);
        this.transacao.commit();
        return entidade;
    }

    /**
     * Atualiza os dados da entidade na base de dados e retorna
     * os dados atualizados
     * @param entidade
     */
    public void atualizar(T entidade) {
        this.transacao = this.sessao.beginTransaction();
        this.sessao.clear();
        this.sessao.update(entidade);
        this.transacao.commit();
    }

    /**
     * Remove os dados da entidade da base de dados
     * @param entidade
     */
    public void excluir(T entidade) {
        this.transacao = this.sessao.beginTransaction();
        this.sessao.delete(entidade);
        this.transacao.commit();
    }

    /**
     * Retorna uma lista de objetos dos dados persistidos na base de dados
     * @return
     */
    public List<T> listarTodos() {
        Criteria criterio = this.sessao.createCriteria(classe);
        return criterio.list();
    }

    /**
     * Retorna os dados de uma entidade a partir de seu código de identificação
     * @param id
     * @return
     */
    public T buscaPorId(Long id) {
        Object obj = null;
        try {
            this.transacao = this.sessao.beginTransaction();
            //obj = sessao.load(classe, id);
            obj = sessao.get(classe, id);
            this.transacao.commit();
        } catch (HibernateException he) {
            Logger.getLogger(DaoGenerico.class.getName()).log(Level.SEVERE, null, he);
        }
        return (T) obj;
    }

    /**
     * Retorna uma lista de entidades a partir de um exemplo de uma entidade
     * (uma entidade semipreenchida)
     * @param o
     * @return
     */
    public List<T> buscaPorExemplo(Object o) {
        List<T> lista = null;
        try {
            Criteria criterio = this.sessao.createCriteria(classe);
            criterio.add(Example.create(o));
            lista = criterio.list();
        } catch (HibernateException he) {
            Logger.getLogger(DaoGenerico.class.getName()).log(Level.SEVERE, null, he);
        }
        return lista;
    }

    /**
     * Retorna uma lista de entidades a partir de uma Criteria
     * @param criterio
     * @return 
     */
    public List<T> buscaPorCriterio(Criteria criterio){
        List<T> lista = null;
        try{
            lista = criterio.list();
        } catch (HibernateException he){
            Logger.getLogger(DaoGenerico.class.getName()).log(Level.SEVERE, null, he);
        }
        return lista;
    }
    
    /**
     * Retorna uma lista de entidades a partir de uma Criteria
     * @param criterio
     * @return 
     */
    public List<T> buscaPorQuery(Query query){
        List<T> lista = null;
        try{
            lista = (List<T>)query.list();
        } catch (HibernateException he){
            Logger.getLogger(DaoGenerico.class.getName()).log(Level.SEVERE, null, he);
        }
        return lista;
    }
    

    /**
     * Limpa os dados da sessão
     */
    public void limparSessao() {
        this.sessao.clear();
    }
    
    /**
     * Retornar a sessao atual
     * @return Session
     */
    public Session getSessao(){
        return this.sessao;
    }
    
    /**
     * Verifica se existe uma transação aberta
     * @return
     */
    public boolean existeTransacao() {
        return this.transacao != null;
    }
}

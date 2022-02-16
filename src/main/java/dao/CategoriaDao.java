package dao;

import modelo.Categoria;

import javax.persistence.EntityManager;

public class CategoriaDao {

    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }


    public void cadastrar(Categoria computadores) {
        em.persist(computadores);
    }
}

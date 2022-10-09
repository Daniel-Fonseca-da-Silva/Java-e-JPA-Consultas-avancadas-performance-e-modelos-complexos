package dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import modelo.Pedido;

public class PedidoDao {

    private EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido) {
        this.em.persist(pedido);
    }
    
    public BigDecimal valorTotalVendido() {
    	String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
    	return em.createQuery(jpql, BigDecimal.class)
    			.getSingleResult();
    	
    }

}

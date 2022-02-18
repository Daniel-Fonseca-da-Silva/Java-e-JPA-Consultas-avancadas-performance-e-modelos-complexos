package testes;


import dao.CategoriaDao;
import dao.ProdutoDao;
import modelo.Categoria;
import modelo.Pagamento;
import modelo.Produto;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProdutos {

    public static void main(String[] args) {

        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto p = produtoDao.buscarPorId(1L);
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDao.buscarNomeCategoria("COMPUTADORES");
        todos.forEach(p2 -> System.out.println(p.getNome()));

        BigDecimal precoDoProduto = produtoDao.buscarPrecoProdutoComNome("Computador com Fedora");
        System.out.println("Preço do produto: "  + precoDoProduto);
    }

    private static void cadastrarProduto() {
        Categoria computadores = new Categoria("COMPUTADORES");
        Produto celular = new Produto("Computador com Fedora", "Novo computador legal", new BigDecimal("1500"), computadores, Pagamento.PIX );

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(computadores);
        produtoDao.cadastrar(celular);

        em.getTransaction().commit();
        em.close();
    }

}

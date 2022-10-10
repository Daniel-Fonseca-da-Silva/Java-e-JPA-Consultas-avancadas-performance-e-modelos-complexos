package testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import dao.CategoriaDao;
import dao.ClienteDao;
import dao.PedidoDao;
import dao.ProdutoDao;
import modelo.Categoria;
import modelo.Cliente;
import modelo.ItemPedido;
import modelo.Pagamento;
import modelo.Pedido;
import modelo.Produto;
import util.JPAUtil;
import vo.RelatorioDeVendasVO;

public class CadastroDePedido {

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		Produto produto = produtoDao.buscarPorId(1L);
		Cliente cliente = clienteDao.buscarPorId(1L);
		
		em.getTransaction().begin();
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(5, pedido, produto));
		
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		
		em.getTransaction().commit();
		
		List<RelatorioDeVendasVO> relatorio = pedidoDao.relatorioDeVendas();
		relatorio.forEach(System.out::println);
	}
	
	private static void popularBancoDeDados() {
        Categoria computadores = new Categoria("COMPUTADORES");
        
        Produto computador = new Produto("Computador com Fedora", "Novo computador legal", new BigDecimal("1500"), computadores, Pagamento.PIX );
        Cliente cliente = new Cliente("Iamazaki Moto", "123456");
        
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(computadores);
        produtoDao.cadastrar(computador);
        clienteDao.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
    }

}

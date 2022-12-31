package br.com.springjpa.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.springjpa.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("SELECT p FROM Produto p WHERE p.preco > :P_PRECO")
	public List<Produto> findProdutosPrecoMaior(@Param("P_PRECO") BigDecimal preco);

	@Query(value = "SELECT * FROM PRODUTOS WHERE DESCRICAO LIKE :P_DESCRICAO%", nativeQuery = true)
	public List<Produto> findByDescricao(@Param("P_DESCRICAO") String descricao);

	public List<Produto> findByNomeStartsWith(String nome);

	public List<Produto> findByNomeEndsWith(String nome);

	public List<Produto> findByNomeLike(String nome);

}

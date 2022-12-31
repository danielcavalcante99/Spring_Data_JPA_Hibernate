# Spring Data JPA Hibernate

<b>Introdução:</b>
- Qual o propósito do Spring Data JPA;
- Dependência pom.xml;
- Entendendo na prática como o Spring Data JPA facilita a implementação de repositórios.
##

### 1 - Qual o propósito do Spring Data JPA:

O propósito do <b>Spring Data JPA</b> é facilitar a implementação de repositórios baseados em JPA, isso como consequência irá aumentar a produtivadade do desenvolvedor.
##

### 2 - Dependência pom.xml:

~~~
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
~~~
##

### 3 - Entendendo na prática como o Spring Data JPA facilita a implementação de repositórios:

Entidade: Produto.class
package br.com.springjpa.model;
~~~
@Data
@Entity
@Table(name = "produtos")
public class Produto implements Serializable {

	private static final long serialVersionUID = -2059818942390560655L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String descricao;

	private BigDecimal preco;
	
	@Enumerated(EnumType.STRING)
	private Categorias categorias;
}
~~~


Entidade: ProdutoRepository.class
package br.com.springjpa.repositories;
~~~
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	//consulta orientada a objeto (JPQL)
	@Query("SELECT p FROM Produto p WHERE p.preco > :P_PRECO")
	public List<Produto> findProdutosPrecoMaior(@Param("P_PRECO") BigDecimal preco);
	
	//consulta query SQL nativa
	@Query(value = "SELECT * FROM PRODUTOS WHERE DESCRICAO LIKE :P_DESCRICAO%", nativeQuery = true)
	public List<Produto> findByDescricao(@Param("P_DESCRICAO") String descricao);
	
   // Consulta descrição IS NULL.
   List<Produto> findByDescricaoIsNull(); 
    
   // Consulta ordenando pela descrição.
   List<Produto> findByNomeStartingWithOrderByDescricao(String nome);
	
    //consulta Like nome%
    public List<Produto> findByNomeStartsWith(String nome);

    //consulta Like %nome 
    public List<Produto> findByNomeEndsWith(String nome);

    //consulta Like nome% (Ignorando se as letras é minúsculas ou maiúsculas)
      List<Produto> findByNomeStartingWithIgnoreCase(String nome);
    
    // consulta Like %nome%
    List<Produto> findByNomeContaining(String nome);
	
	//consulta Like - nesse caso além do nome também deverá ser passado junto o caracter "%". 
	//Ex: findByNomeLike("Samsung%")
	public List<Produto> findByNomeLike(String nome);
	
    // Consulta passando duas propriedades como parâmetro: nome e ativo.
    List<Produto> findByNomeStartingWithIgnoreCaseAndAtivoEquals(String nome, boolean ativo);
	
    // consulta produtos ativos
	// Pode ser usado False também.
    List<Produto> findByAtivoTrue();
    
    // consulta produtos com a data de cadastro posterior a data passada no parâmetro. 
    // Pode ser usado Before também.
    List<Produto> findByDataCadastroAfter(Instant dataCadastro);
    
    // consulta produtos com a data de cadastro de um determinado período
    List<Produto> findByDataCadastroBetween(Instant inicio, Instant fim);
    
    // consulta o preço dos produtos "menor que".
    // Poderia ser usado também LessThanEqual, GreaterThan, GreaterThanEqual.
    List<Produto> findByPrecoLessThan(BigDecimal preco);

}
~~~

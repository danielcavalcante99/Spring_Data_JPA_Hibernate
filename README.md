#  🧑🏽‍💻 👨🏽‍💻 Spring Data JPA Hibernate

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

<b>Entidade:</b> Produto.class

<b>package:</b> br.com.springjpa.model;
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
	
	private boolean ativo;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "GMT")
	private Instant dataCadastro = Instant.now();
	
	@Enumerated(EnumType.STRING)
	private Categorias categorias;
}
~~~

### Ao estender o JpaRepository obtemos os métodos CRUD já prontos

Obtemos os métodos CRUD mais relevantes para acesso a dados apenas se a interface <b>extender</b> o <b>JpaRepository</b>. Então aqui um ponto bastante positivo do <b>Spring Data JPA</b>, pois realmente não precisa do desenvolvedor criar na mão os métodos do CRUD.

<b>repositorio:</b> ProdutoRepository.class

<b>package:</b> br.com.springjpa.repositories;
~~~
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
~~~

### Consultas com a annotation @Query e com assinatura do método

Se quisermos construir consultas bem específicas podemos utilizar a annotation @Query que permite realizar consulta em <b>JPQL</b> ou com próprio <b>SQL nativo</b>, muito legal todos esses recursos, mas ainda não acabou! O <b>Spring Data JPA</b> surpreende ainda mais com seu super recurso de compreender e criar consultas a partir apenas das assinaturas dos métodos.

~~~
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	//consulta orientada a objeto (JPQL)
	@Query("SELECT p FROM Produto p WHERE p.preco > :P_PRECO")
	public List<Produto> findProdutosPrecoMaior(@Param("P_PRECO") BigDecimal preco);
	
	//consulta query SQL nativa
	@Query(value = "SELECT * FROM PRODUTOS WHERE DESCRICAO LIKE :P_DESCRICAO%", nativeQuery = true)
	public List<Produto> findByDescricao(@Param("P_DESCRICAO") String descricao);
	
    // Consulta descrição IS NULL.
	public List<Produto> findByDescricaoIsNull(); 
    
    // Consulta ordenando pela descrição.
	public List<Produto> findByNomeStartingWithOrderByDescricao(String nome);
	
	//consulta Like nome%
	public List<Produto> findByNomeStartsWith(String nome);
	
	//consulta Like %nome 
	public List<Produto> findByNomeEndsWith(String nome);
	
	//consulta Like nome% (Ignorando se as letras é minúsculas ou maiúsculas)
	public List<Produto> findByNomeStartingWithIgnoreCase(String nome);
    
    // consulta Like %nome%
	public List<Produto> findByNomeContaining(String nome);
	
	//consulta Like - nesse caso além do nome também deverá ser passado junto o caracter "%". 
	//Ex: findByNomeLike("Samsung%")
	public List<Produto> findByNomeLike(String nome);
	
    // Consulta passando duas propriedades como parâmetro: nome e ativo.
	public List<Produto> findByNomeStartingWithIgnoreCaseAndAtivoEquals(String nome, boolean ativo);
	
    // consulta produtos ativos
	// Pode ser usado False também.
	public List<Produto> findByAtivoTrue();
    
    // consulta produtos com a data de cadastro posterior a data passada no parâmetro. 
    // Pode ser usado Before também.
	public List<Produto> findByDataCadastroAfter(Instant dataCadastro);
    
    // consulta produtos com a data de cadastro de um determinado período
	public List<Produto> findByDataCadastroBetween(Instant inicio, Instant fim);
    
    // consulta o preço dos produtos "menor que".
    // Poderia ser usado também LessThanEqual, GreaterThan, GreaterThanEqual.
	public List<Produto> findByPrecoLessThan(BigDecimal preco);

}
~~~

package br.com.springjpa;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import br.com.springjpa.model.Produto;
import br.com.springjpa.model.enums.Categorias;
import br.com.springjpa.services.ProdutoService;

@SpringBootApplication
public class SpringJpaApplication implements CommandLineRunner {

	@Autowired
	private ProdutoService service;

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Produto celular = new Produto();
		celular.setNome("Samsung S10");
		celular.setPreco(new BigDecimal(2500.89));
		celular.setCategorias(Categorias.CELULARES);
		celular.setDescricao("Samsung Galaxy S10 128 GB preto-prisma 8 GB RAM");

		Produto celular2 = new Produto();
		celular2.setNome("Samsung S20");
		celular2.setPreco(new BigDecimal(4500.89));
		celular2.setCategorias(Categorias.CELULARES);
		celular2.setDescricao("Samsung Galaxy S22 128 GB preto-prisma 16 GB RAM");

		Produto livro = new Produto();
		livro.setNome("O poder do hábito");
		livro.setPreco(new BigDecimal(36.99));
		livro.setCategorias(Categorias.LIVROS);
		livro.setDescricao("O poder do hábito Capa comum ");

		List<Produto> listProduto = List.of(celular, celular2, livro);

		// insert
		System.out.println("INSERT: ");
		listProduto.forEach(p -> this.service.insert(p));
		listProduto.forEach(System.out::println);
		System.out.println("...");

		// update
		System.out.println("UPDATE: Altereando preço do Celular");
		celular.setPreco(new BigDecimal(2800.99));
		System.out.println(celular);
		this.service.update(celular);
		System.out.println("...");

		// select buscar todos
		System.out.println("SELECT: Buscar Todos OrderBy descricao ASC");
		listProduto = this.service.findAll(Sort.by(Sort.Direction.ASC, "descricao"));
		listProduto.forEach(System.out::println);
		System.out.println("...");

		// delete
		System.out.println("DELETE:");
		this.service.delete(celular2);
		System.out.println(celular2);
		System.out.println("...");

		// select buscar todos
		System.out.println("SELECT: Buscar Todos OrderBy descricao ASC");
		listProduto = this.service.findAll(Sort.by(Sort.Direction.ASC, "descricao"));
		listProduto.forEach(System.out::println);
		System.out.println("...");

		// select por ID
		System.out.println("SELECT: Buscar por ID");
		Optional<Produto> optProduto = this.service.findById(1L);
		System.out.println(optProduto.get());
		System.out.println("...");

		// select por descrição%
		System.out.println("SELECT LIKE: Buscar por descrição%");
		listProduto = this.service.findByDescricao("O poder");
		listProduto.forEach(System.out::println);
		System.out.println("...");

		// select por nome%
		System.out.println("SELECT LIKE: Buscar por nome%");
		listProduto = this.service.findByNomeStartsWith("Samsung");
		listProduto.forEach(System.out::println);
		System.out.println("...");

		// select por %nome
		System.out.println("SELECT LIKE: Buscar por %nome");
		listProduto = this.service.findByNomeEndsWith("S10");
		listProduto.forEach(System.out::println);
		System.out.println("...");

		// select por %nome%
		System.out.println("SELECT LIKE: Buscar por %nome%");
		listProduto = this.service.findByNomeLike("%hábito%");
		listProduto.forEach(System.out::println);
		System.out.println("...");

	}

}

package br.com.springjpa.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.springjpa.model.Produto;
import br.com.springjpa.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public Optional<Produto> findById(Long id) {
		return this.repository.findById(id);
	}

	public List<Produto> findAll() {
		return this.repository.findAll();
	}

	public List<Produto> findAll(Sort sort) {
		return this.repository.findAll(sort);
	}

	public List<Produto> findProdutosPrecoMaior(BigDecimal preco) {
		return this.repository.findProdutosPrecoMaior(preco);
	}

	public List<Produto> findByDescricao(String descricao) {
		return this.repository.findByDescricao(descricao);
	}

	public List<Produto> findByNomeStartsWith(String nome) {
		return this.repository.findByNomeStartsWith(nome);
	}

	public List<Produto> findByNomeEndsWith(String nome) {
		return this.repository.findByNomeEndsWith(nome);
	}

	public List<Produto> findByNomeLike(String nome) {
		return this.repository.findByNomeLike(nome);
	}

	public Produto insert(Produto produto) {
		try {
			this.repository.save(produto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return produto;
	}

	public Produto delete(Produto produto) {
		try {
			Optional<Produto> optProduto = this.findById(produto.getId());

			if (optProduto.isPresent()) {
				Produto entity = optProduto.get();
				this.repository.delete(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return produto;
	}

	public Produto update(Produto produto) {
		try {
			Optional<Produto> optProduto = this.findById(produto.getId());

			if (optProduto.isPresent()) {
				this.repository.save(produto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return produto;
	}

}

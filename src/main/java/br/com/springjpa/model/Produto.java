package br.com.springjpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.springjpa.model.enums.Categorias;
import lombok.Data;

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

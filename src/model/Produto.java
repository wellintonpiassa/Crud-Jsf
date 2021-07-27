package model;

import java.io.Serializable;

/*
 * Classe Java Bean do objeto produto
 */

public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String nome;
	private int quantidade;
	private double preco;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
	public boolean equals(Object o) {
		
		Produto that = (Produto) o;
		
		int resultado = this.id.compareTo(that.id);
		
		if(resultado == 0)
			return true;			
		else
			return false;			
		
		//return this.id == that.id;
	}
}

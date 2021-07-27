package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import model.Produto;

@Named
@SessionScoped
public class CarrinhoController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	List<Produto> listaProdutosCarrinho;
	
	
	@PostConstruct
	public void init() {
		listaProdutosCarrinho = new ArrayList<Produto>();
	}
	
	
	//Get and Set
	public List<Produto> getListaProdutosCarrinho() {
		return listaProdutosCarrinho;
	}
	public void setListaProdutosCarrinho(List<Produto> lista) {
		this.listaProdutosCarrinho = lista;
	}
	
	
	public void adicionarAoCarrinho(Produto produto) {
		
		if (!listaProdutosCarrinho.contains(produto)) {
			
			listaProdutosCarrinho.add(produto);
			
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Produto adicionado com sucesso!"));
			
		} else {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("O produto já está no carrinho"));
		}
	}
	
	
	public void removerDoCarrinho(Produto produto) {
		
		listaProdutosCarrinho.remove(produto);	
	}
}

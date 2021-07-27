package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import model.Produto;
import model.ProdutoDAO;

@Named
@ViewScoped
public class ProdutoController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Produto produto;
	private List<Produto> listaProdutos;
	
	
	@PostConstruct
	public void init() {
		produto = new Produto();
	}
	
	
	// Getters and Setters
	public List<Produto> getListaProdutos() {
		return this.listaProdutos;
	}
	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	public void adicionarProduto() {
		
		ProdutoDAO dao = new ProdutoDAO();
		
		dao.inserir(this.produto);
		
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Produto adicionado com sucesso!"));	
	}
	
	public void removerProduto(Produto produto) {
		
		long idProduto = produto.getId();
		
		ProdutoDAO dao = new ProdutoDAO();
		
		dao.excluir(idProduto);
		
		listaProdutos.remove(produto);
		
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Produto removido com sucesso!"));
		
	}
	
	public void alterarProduto(Produto produto) {
		
		ProdutoDAO dao = new ProdutoDAO();
		
		dao.alterar(produto);
		
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Produto alterado com sucesso!"));
	}
	
	public void buscarPorNome() {
		
		ProdutoDAO dao = new ProdutoDAO();
		
		this.listaProdutos = dao.buscarPorNome(this.produto.getNome());
	}
}


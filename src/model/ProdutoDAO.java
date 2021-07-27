package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Classe DAO para conexão e execuções sobre o banco de dados

public class ProdutoDAO {
	
	private Connection connection;
	
	public ProdutoDAO() {
		this.connection = new ConnectionFactory().getConnection();	
	}
	
	
	public void inserir(Produto produto) {
		
		String sql = 
				"INSERT INTO produto (NOME, PRECO, QUANTIDADE) VALUES (?,?,?)";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setString(1, produto.getNome());
			prstate.setDouble(2, produto.getPreco());
			prstate.setInt(3, produto.getQuantidade());
			
			prstate.execute();
			prstate.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		
		} finally {
			try {
				connection.close();
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}	
	}
	
	
	public void excluir(long id) {
		
		String sql = 
				"DELETE FROM produto WHERE ID=?";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setLong(1,id);
			
			prstate.execute();
			prstate.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		
		} finally {
			try {
				connection.close();
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	
	public void alterar(Produto produto) {
		
		String sql = 
				"UPDATE produto SET NOME=?, PRECO=?, QUANTIDADE=? WHERE ID=?";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setString(1, produto.getNome());
			prstate.setDouble(2, produto.getPreco());
			prstate.setInt(3, produto.getQuantidade());
			prstate.setLong(4, produto.getId());
			
			prstate.execute();
			prstate.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		
		} finally {
			try {
				connection.close();
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	
	public List<Produto> buscarPorNome(String nome){
		
		String sql = 
				"SELECT * FROM produto WHERE NOME LIKE UPPER(?)";
		
		List<Produto> listaProdutos = new ArrayList<Produto>();
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setString(1, new String("%"+nome+"%").toUpperCase());
			
			ResultSet result = prstate.executeQuery();
			
			while(result.next()) {
				Produto produto = new Produto();
				produto.setId(result.getLong("ID"));
				produto.setNome(result.getString("NOME"));
				produto.setPreco(result.getDouble("PRECO"));
				produto.setQuantidade(result.getInt("QUANTIDADE"));
				
				listaProdutos.add(produto);
			}
			
			result.close();
			prstate.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		
		} finally {
			try {
				connection.close();
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		return listaProdutos;
	}
	

	public Produto buscarPorId(Long id){
		
		String sql = 
				"SELECT * FROM produto WHERE ID = ?";
		
		Produto produto = new Produto();
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setLong(1, id);
			
			ResultSet result = prstate.executeQuery();
			
			result.next();
			
			produto.setId(result.getLong("ID"));
			produto.setNome(result.getString("NOME"));
			produto.setPreco(result.getDouble("PRECO"));
			produto.setQuantidade(result.getInt("QUANTIDADE"));
			
			result.close();
			prstate.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		
		} finally {
			try {
				connection.close();
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		return produto;
	}
}


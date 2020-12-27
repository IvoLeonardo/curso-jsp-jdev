package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Produto;
import connection.SingleConnection;

public class ProdutoDao {

	private Connection connection;

	public ProdutoDao() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Produto produto) {

		try {
			String sql = "insert into produto (nome,quantidade,valor) values (?, ?, ?)";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, produto.getNome());
			pst.setDouble(2, produto.getQuantidade());
			pst.setDouble(3, produto.getValor());
			pst.execute();

			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public List<Produto> listar() {

		List<Produto> lista = new ArrayList<>();

		try {
			String sql = "select * from produto";

			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				Produto produto = new Produto();
				produto.setId(rs.getLong("id"));
				produto.setNome(rs.getString("nome"));
				produto.setQuantidade(rs.getDouble("quantidade"));
				produto.setValor(rs.getDouble("valor"));

				lista.add(produto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	public void delete(String id) {

		try {
			String sql = "delete from produto where id = '" + id + "'";

			PreparedStatement pst = connection.prepareStatement(sql);
			pst.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void atualizar(Produto produto) {

		try {
			String sql = "update produto set nome=?, quantidade=?, valor=? where id= " + produto.getId();

			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, produto.getNome());
			pst.setDouble(2, produto.getQuantidade());
			pst.setDouble(3, produto.getValor());
			pst.executeUpdate();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public Produto consultar(String id) {

		try {
			String sql = "select * from produto where id='" + id + "'";
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getLong("id"));
				produto.setNome(rs.getString("nome"));
				produto.setQuantidade(rs.getDouble("quantidade"));
				produto.setValor(rs.getDouble("valor"));

				return produto;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
}

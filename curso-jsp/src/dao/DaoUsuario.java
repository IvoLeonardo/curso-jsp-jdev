package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanCursoJsp usuario) {

		try {
			String sql = "insert into usuario (login, senha, nome, telefone) values (?, ?, ?, ?)";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, usuario.getLogin());
			pst.setString(2, usuario.getSenha());
			pst.setString(3, usuario.getNome());
			pst.setString(4, usuario.getTelefone());
			pst.execute();

			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public List<BeanCursoJsp> listar() throws Exception {

		List<BeanCursoJsp> listar = new ArrayList<>();

		String sql = "select * from usuario";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setTelefone(resultSet.getString("telefone"));

			listar.add(beanCursoJsp);
		}
		return listar;
	}

	public void delete(String id) {

		try {
			String sql = "delete from usuario where id='" + id + "'";

			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.execute();

			connection.commit();

		} catch (Exception e) {

			e.printStackTrace();

			try {
				connection.rollback();

			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}
	}

	public BeanCursoJsp consultar(String id) throws Exception {

		String sql = "select * from usuario where id='" + id + "'";

		PreparedStatement pstm = connection.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(rs.getLong("id"));
			beanCursoJsp.setLogin(rs.getString("login"));
			beanCursoJsp.setSenha(rs.getString("senha"));
			beanCursoJsp.setNome(rs.getString("nome"));
			beanCursoJsp.setTelefone(rs.getString("telefone"));

			return beanCursoJsp;
		}
		return null;
	}

	// verifica se o login informado já está registrado
	public boolean validarLogin(String login) throws Exception {

		String sql = "select count(1) as qtd from usuario where login='" + login + "'";

		PreparedStatement pstm = connection.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {

			return rs.getInt("qtd") <= 0; // Return true
		}
		return false;
	}

	// verifica no momento de salvar um novo login se o login informado já está
	// registrado
	public boolean validarLoginUpdate(String login, String id) throws Exception {

		String sql = "select count(1) as qtd from usuario where login='" + login + "' and id <> '" + id;

		PreparedStatement pstm = connection.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {

			return rs.getInt("qtd") <= 0; // Return true
		}
		return false;
	}

	public boolean validarSenha(String senha) throws Exception {
		String sql = "select count(1) as qtd from usuario where senha='" + senha + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;/* Return true */
		}
		return false;
	}

	public void atualizar(BeanCursoJsp usuario) {

		try {

			String sql = "update usuario set login = ?, senha = ?, nome=?, telefone=? where id=" + usuario.getId();

			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, usuario.getLogin());
			pstm.setString(2, usuario.getSenha());
			pstm.setString(3, usuario.getNome());
			pstm.setString(4, usuario.getTelefone());
			pstm.executeUpdate();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();

			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}
	}
}

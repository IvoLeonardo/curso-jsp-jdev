package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import dao.DaoUsuario;

@WebServlet("/salvarUsuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.delete(user);

				RequestDispatcher rd = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				rd.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {

				BeanCursoJsp beanCursoJsp = daoUsuario.consultar(user);

				RequestDispatcher rd = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", beanCursoJsp);
				rd.forward(request, response);

			} else if (acao.equalsIgnoreCase("listarUsuarios")) {
				RequestDispatcher rd = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {

			try {
				RequestDispatcher rd = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());

				rd.forward(request, response);

			} catch (Exception e) {

				e.printStackTrace();
			}

		} else {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");

			BeanCursoJsp usuario = new BeanCursoJsp();
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setTelefone(telefone);

			try {

				String msg = null;
				boolean podeInserir = true;

				if (login == null || login.isEmpty()) {
					msg = "Login deve ser informado";
					podeInserir = false;

				} else if (senha == null || senha.isEmpty()) {
					msg = "Senha deve ser informado";
					podeInserir = false;

				} else if (nome == null || nome.isEmpty()) {
					msg = "Nome deve ser informado";
					podeInserir = false;

				} else if (telefone == null || telefone.isEmpty()) {
					msg = "Telefone deve ser informado";
					podeInserir = false;

				} else if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {

					msg = "Usuário ja existe com o mesmo login!";
					podeInserir = false;

				} else if (id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {
					msg = "\n A senha já existe para outro usuário!";
					podeInserir = false;

				}

				if (msg != null) {
					request.setAttribute("msg", msg);

				}

				if (id == null || id.isEmpty() && daoUsuario.validarLogin(login) && podeInserir) {
					daoUsuario.salvar(usuario);

				} else if (id != null && !id.isEmpty() && podeInserir) {
					daoUsuario.atualizar(usuario);

				} else if (!podeInserir) {
					request.setAttribute("user", usuario);
				}

				RequestDispatcher rd = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				rd.forward(request, response);

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

}

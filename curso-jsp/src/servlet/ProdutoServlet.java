package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Produto;
import dao.ProdutoDao;

@WebServlet("/produto")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ProdutoDao dao = new ProdutoDao();

	public ProdutoServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String prod = request.getParameter("prod");

		if (acao.equalsIgnoreCase("listarProdutos")) {
			RequestDispatcher rd = request.getRequestDispatcher("/cadastroProduto.jsp");
			request.setAttribute("produtos", dao.listar());
			rd.forward(request, response);

		} else if (acao.equalsIgnoreCase("delete")) {
			dao.delete(prod);

			RequestDispatcher rd = request.getRequestDispatcher("/cadastroProduto.jsp");
			request.setAttribute("produtos", dao.listar());
			rd.forward(request, response);

		} else if (acao.equalsIgnoreCase("editar")) {

			Produto produto = dao.consultar(prod);

			RequestDispatcher rd = request.getRequestDispatcher("/cadastroProduto.jsp");
			request.setAttribute("prod", produto);
			rd.forward(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("codigo");
		String nomeProduto = request.getParameter("nome");
		String qtd = request.getParameter("quantidade");
		String valor = request.getParameter("valor");

		Produto produto = new Produto();
		produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
		produto.setNome(nomeProduto);
		produto.setQuantidade(Double.parseDouble(qtd));
		produto.setValor(Double.parseDouble(valor));

		if (id == null || id.isEmpty()) {

			dao.salvar(produto);

		} else {
			dao.atualizar(produto);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/cadastroProduto.jsp");
		request.setAttribute("produtos", dao.listar());
		rd.forward(request, response);
	}

}

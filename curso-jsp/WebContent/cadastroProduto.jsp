<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Cadastro de usuário</title>
	
	<link rel="stylesheet" href="./resources/css/normalize.css"/>
    <link rel="stylesheet" href="./resources/css/reset.css"/>
	<link rel="stylesheet" href="./resources/css/cabecalho.css">
	<link rel="stylesheet" href="./resources/css/logo.css">
	<link rel="stylesheet" href="./resources/css/menu/menu-lista.css">
	<link rel="stylesheet" href="./resources/css/menu/menu-item.css">
	<link rel="stylesheet" href="./resources/css/menu/menu-link.css">
	<link rel="stylesheet" href="./resources/css/container.css">
	<link rel="stylesheet" href="./resources/css/formulario/formulario.css">
	<link rel="stylesheet" href="./resources/css/formulario/formulario-titulo.css">
	<link rel="stylesheet" href="./resources/css/formulario/formulario-input.css">
	<link rel="stylesheet" href="./resources/css/formulario/formulario-label.css">
	<link rel="stylesheet" href="./resources/css/formulario/formulario-nome.css">
	<link rel="stylesheet" href="./resources/css/formulario/formulario-cpf.css">
	<link rel="stylesheet" href="./resources/css/formulario/formulario-botao.css">
	<link rel="stylesheet" href="./resources/css/tabela/tabela.css">
	<link rel="stylesheet" href="./resources/css/tabela/tabela-th.css">
	<link rel="stylesheet" href="./resources/css/tabela/tabela-celula.css">
	<link rel="stylesheet" href="./resources/css/tabela/tabela-linha.css">
	
	</head>
	
	<body>
		<header class="cabecalho">
			<img class="logo" alt="cadastro" src="./resources/img/usuarios.png">
			<nav class="menu">
				<ul class="menu__lista">
					<li class="menu__item"><a class="menu__link" href="#">Início</a></li>
					<li class="menu__item"><a class="menu__link" href="#">Administrativo</a></li>
					<li class="menu__item"><a class="menu__link" href="#">Quem somos</a></li>
				</ul>
			</nav>
		</header>
		
		<div class="container">
			<h2>Cadastro de produtos</h2>
			
			<section>
			
				<form action="produto" method="post">
					<section>
						<label for="id" class="formulario__label">Código</label>
						<input type="text" id="id" name="codigo" readonly="readonly" value="${prod.id }" class="formulario__input">
					</section>
					
					<section>
						<label for="nome" class="formulario__label">Nome do produto</label>
						<input type="text" id="nome" name="nome" value="${prod.nome }" class="formulario__input">
					</section>
					
					<section>
						<label for="qtd" class="formulario__label">Quantidade</label>
						<input type="text" id="qtd" name="quantidade" value="${prod.quantidade }" class="formulario__input">
					</section>
					
					<section>
						<label for="valor">Valor R$</label>
						<input type="text" id="valor" name="valor" value="${prod.valor }" class="formulario__input">
					</section>
					
					<section>
						<input type="submit" value="salvar" class="formulario__botao">
						<input type="submit" value="cancelar" class="formulario__botao">
					</section>
				</form>
			</section>
			
			<div class="tabela">
				
				<table>
					<tr>
						<th class="tabela__celula" id="tabela__id">Código</th>
						<th class="tabela__celula" id="tabela__nome">Nome</th>
						<th class="tabela__celula" id="tabela__quantidade">Quantidade</th>
						<th class="tabela__celula" id="tabela__valor">Valor</th>
						<th class="tabela__celula" id="tabela__excluir">Excluir</th>
						<th class="tabela__celula" id="tabela__editar">Editar</th>
					</tr>
					
					<c:forEach items="${produtos }" var="produto">
					
						<tr>
							<td><c:out value="${produto.id }"></c:out></td>
							<td><c:out value="${produto.nome }"></c:out></td>
							<td><c:out value="${produto.quantidade }"></c:out></td>
							<td><c:out value="${produto.valor }"></c:out></td>
							<td><a href="produto?acao=delete&prod=${produto.id }"><img src="resources/img/excluir-botao.jpg" title="Excluir" width="20px" height="20px"></a></td>
							<td><a href="produto?acao=editar&prod=${produto.id }"><img src="resources/img/editar-botao.jpg" title="Editar" width="20px" height="20px"></a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</body>
</html>
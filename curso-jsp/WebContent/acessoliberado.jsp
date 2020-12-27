<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		
		<link rel="stylesheet" href="./resources/css/normalize.css"/>
	    <link rel="stylesheet" href="./resources/css/reset.css"/>
		<link rel="stylesheet" href="./resources/css/cad-usuarios/cad-usuarios.css">
		<link rel="stylesheet" href="./resources/css/cad-usuarios/cad-usuarios-container.css">
		<link rel="stylesheet" href="./resources/css/cad-usuarios/cad-usuarios-icon.css">
		<link rel="stylesheet" href="./resources/css/cad-usuarios/cad-usuarios-titulo.css">
		
	</head>
	<body>
		<div class="cad-usuarios-container">
			<section class="cad-usuarios">
				<h2 class="cad-usuarios__titulo">Seja bem vindo ao sistema em JSP</h2>
				
				<a href="salvarUsuario?acao=listarUsuarios"><img src="resources/img/usuarios-add.png" alt="Cadastrar usuários" title="Cadastrar usuários" class="cad-usuarios__icon"></a>
				<a href="produto?acao=listarProdutos"><img src="resources/img/produto-add.png" alt="Cadastrar produtos" title="Cadastrar produtos" class="cad-produtos__icon"></a>
			</section>
		</div>
	</body>
</html>
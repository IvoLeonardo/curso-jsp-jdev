<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
					
			<section class="formulario">
			
			<h2 class="formulario__titulo">Cadastro de usuário</h2>
						
				<h3 style="color: red;">${msg }</h3>
			
				<form action="salvarUsuario" method="post" id="formUser" onsubmit="return validarCampos() ? true : false;">
			
					<section>
						<label for="id" class="forumlario__label">Código</label>
						<input type="text" readonly="readonly" id="id" name="id" value="${user.id }" class="formulario__input" autofocus required> 
					</section>
					
					<section>
						<label for="login" class="formulario__label">Login</label>
						<input type="text" id="login" name="login" value="${user.login }" class="formulario__input" placeholder="Cadastre o seu login"> 
					</section>
					
					<section>
						<label for="senha" class="formulario__label">Senha</label>
						<input type="password" id="senha" name="senha" value="${user.senha }" class="formulario__input" placeholder="Cadastre a sua senha">
					</section>
					
					<section>
						<label for="nome" class="formulario__label">Nome</label>
						<input type="text" id="nome" name="nome" value="${user.nome }" class="formulario__input" placeholder="Cadastre a seu nome">
					</section>
					
					<section>
						<label for="fone" class="formulario__label">Telefone</label>
						<input type="text" id="fone" name="telefone" value="${user.telefone }" class="formulario__input" placeholder="Cadastre o seu telefone">
					</section>
					
					<section>
						<input type="submit" value="salvar" class="formulario__botao">
						<input type="submit" value="cancelar" onclick="document.getElementById('formUser').action='salvarUsuario?acao=reset'" class="formulario__botao">
					</section>
				</form>
			</section>
		
			<div class="tabela">
				<table>
		
					<tr class="tabela__th">
						<th class="tabela__celula" id="tabela__id">Código</th>
						<th class="tabela__celula" id="tabela__login">Login</th>
						<th class="tabela__celula" id="tabela__nome">Nome</th>
						<th class="tabela__celula" id="tabela__nome">Telefone</th>
						<th class="tabela__celula" id="tabela__excluir">Excluir</th>
						<th class="tabela__celula" id="tabela__editar">Editar</th>
							
					</tr>
					<c:forEach items="${usuarios }" var="user">
					
						
			
						<tr class="linha-tabela">
		
							<td class="tabela__celula" ><c:out value="${user.id }"></c:out></td>
							<td class="tabela__celula"><c:out value="${user.login }"></c:out></td>
							<td class="tabela__celula"><c:out value="${user.nome }"></c:out></td>
							<td class="tabela__celula"><c:out value="${user.telefone }"></c:out></td>
		
							<td class="tabela__celula">
								<a href="salvarUsuario?acao=delete&user=${user.id }">
									<img src="resources/img/excluir-botao.jpg" alt="excluir" title="Excluir" width="20px" height="20px">
								</a>
							</td>
							
							<td class="tabela__celula">
								<a href="salvarUsuario?acao=editar&user=${user.id }">
									<img src="resources/img/editar-botao.jpg" alt="editar" title="Editar" width="20px" height="20px">
								</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		
		<script type="text/javascript">
			
			function validarCampos(){
				
				if(document.getElementById("login").value == ''){
					alert('Informe o login!');
					return false;
					
				} else	if(document.getElementById("senha").value == ''){
					alert('Informe a senha!');
					return false;
					
				} else	if(document.getElementById("nome").value == ''){
					alert('Informe o nome!');
					return false;
					
				} else	if(document.getElementById("telefone").value == ''){
					alert('Informe o telefone!');
					return false;
				}
				
				return true;
			}
		</script>
	</body>
</html>
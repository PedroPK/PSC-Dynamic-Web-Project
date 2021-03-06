<%@page import="br.edu.unibratec.psc.web.CadastroServlet"%>
<%@page		language="java"	contentType="text/html; charset=UTF-8"		pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Tela de Cadastro</title>
	</head>
	<body>
		<form class="form-inline" style="margin: 20px 0" action="/MWA/Cadastro" method="post">
			<div class="form-group">
				<table>
					<tbody>
						<tr>
							<td>
								Nome:
							</td>
							<td>
								<input type="text" id="<%=CadastroServlet.NM_PARAMETRO_NOME%>" 
										name="<%=CadastroServlet.NM_PARAMETRO_NOME%>" 
										class="form-control" placeholder="Nome"/>
							</td>
						</tr>
						<tr>
							<td>
								CPF:
							</td>
							<td>
								<input type="text" id="<%=CadastroServlet.NM_PARAMETRO_CPF%>" 
										name="<%=CadastroServlet.NM_PARAMETRO_CPF%>" 
										class="form-control" placeholder="CPF"/>
							</td>
						</tr>
						<tr>
							<td>
								Data de Nascimento:
							</td>
							<td>
								<input type="date" id="<%=CadastroServlet.NM_PARAMETRO_DATA_NASCIMENTO%>" 
										name="<%=CadastroServlet.NM_PARAMETRO_DATA_NASCIMENTO%>" 
										class="form-control" placeholder="Data de Nascimento"/>
							</td>
						</tr>
						<tr>
							<td>
								Gênero:
							</td>
							<td>
								Masculino <input type="radio" id="masculino" 
										name="genero" 
								/>
								Feminino <input type="radio" id="feminino" 
										name="genero" 
								/>
							</td>
						</tr>
						<tr>
							<td>
								Necessidasdes especiais?
							</td>
							<td>
								<input type="checkbox" id="necessidadesEspeciais" name="genero" />
							</td>
						</tr>
						<tr>
							<td>
								País
							</td>
							<td>
								<select	name="genero"	id="genero">
									<option> --Selecione uma Opção --</option>
									<option>Brasil</option>
									<option>Venezuela</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								Senha:
							</td>
							<td>
								<input type="password" id="senha" 
										name="senha" 
										class="form-control" placeholder="Senha"/>
							</td>
						</tr>
						<tr>
							<td>
								Descrição:
							</td>
							<td>
								<textarea id="texto" name="texto" class="form-control" placeholder="Descreva aqui..."></textarea>
							</td>
						</tr>
						<tr>
							<td>
								
							</td>
							<td>
								<button type="submit" class="btn btn-primary">Adicionar</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
	</body>
</html>
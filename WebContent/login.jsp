<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String errorCode = request.getParameter("error");
	String errorMessage = "";
	String messageStyle = "color: red; font-weight: bold;";
	if (errorCode == null) {
		errorMessage = "Entre com login e senha.";
		messageStyle = "color: green; font-weight: bold;";
	} else if ("1".equals(errorCode)) {
		errorMessage = "Login ou senha incorretos!";
	} else if ("2".equals(errorCode)) {
		errorMessage = "Efetue login para continuar.";
	} else {
		errorMessage = "Unknown error occured - "
				+ "try again later or contact webmaster";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ODE - Ontology Development Environment - Versão 1.0</title>
<style type="text/css">
* {
	font-family: Verdana;
	font-size: 11px;
}
</style>
<script type="text/javascript">
	window.onload = function() {
		document.getElementById("ui1TxtbUsername").focus();
	};
</script>
</head>
<body>

	<div style="text-align: center">
		<br /> <br /> <br /> <br /> <img src="imagens/ode.jpg" /> <br />
		<br /> <br />
		<form action="j_security_check" method="post">

			<fieldset
				style="text-align: left; margin: 0 auto; width: 200px; border: solid 1px black; padding: 5px 15px">
				<legend>Login</legend>
				<%
					if (!errorMessage.isEmpty())
						out.println("<p style=\"" + messageStyle + "\">" + errorMessage
								+ "</p>");
				%>
				<p>
					<label>Login:</label> <br /> <input type="text" id="ui1TxtbUsername"
						name="j_username" style="width: 194px" />
				</p>
				<p>
					Senha: <br /> <input type="password" id="ui1TxtbPassword"
						name="j_password" style="width: 194px" />
				</p>
				<p style="text-align: right">
					<input type="submit" style="width: 100px" value="login"
						class="botaoPrincipal" />
				</p>

			</fieldset>
		</form>
<br />
		<p style="color: #363636; font-size: 10px; font-weight: bold;">
			ODE - Ontology Development Environment - Versão 1.0</p>
		<p style="color: #363636; font-size: 10px; font-weight: bold;">
			Desenvolvido por: NEMO / DI / UFES</p>
	</div>
</body>
</html>
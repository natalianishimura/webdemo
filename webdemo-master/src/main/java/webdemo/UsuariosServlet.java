package webdemo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webdemo.entidades.Usuario;

@WebServlet("/usuarios")
public class UsuariosServlet extends HttpServlet {
	
	@Override	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		
		List<Usuario> usuarios = Usuario.ObterTodos();
		
		request.setAttribute("Usuario", usuarios);
		request.setAttribute("Batata", "frita");
		
		request.getRequestDispatcher("usuarios.jsp").forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws{
		String nome = request.getParameter("nome");
		String sobrenome = request.getParameter("sobrenome");

		boolean isValid = true;

		if(nome == null || nome.isEmpty()){
			request.setAttribute("invalid-nome", "O nome deve ser preenchido");
			isValid = false;
		}
		if(sobrenome == null || sobrenome.isEmpty()){
			request.setAttribute("invalid-sobrenome", "O sobrenome deve ser preenchido");
			isValid = false;
		}

		Usuario usuario = new Usuario(nome, sobrenome);
		usuario.salvar();

		response.sendRedrect("/usuarios");
	}
}

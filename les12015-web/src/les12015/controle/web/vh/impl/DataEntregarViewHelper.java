package les12015.controle.web.vh.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import les12015.controle.web.vh.IViewHelper;
import les12015.core.aplicacao.Resultado;
import les12015.dominio.DataEntregar;
import les12015.dominio.EntidadeDominio;

public class DataEntregarViewHelper  implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		DataEntregar dataEntregar = new DataEntregar();
		String reservaId = request.getParameter("reservaId");
		String tipoUsuario= request.getParameter("tipoUsuario");
		String usuarioId = request.getParameter("idUsuario");
		String livroId = request.getParameter("idLivro");
		String idEstoque = request.getParameter("idEstoque");


		if (request.getParameter("operacao").equals("SALVAR")) {
			if(tipoUsuario != null) {
				
					dataEntregar.setId_reserva(Integer.valueOf(reservaId));
					dataEntregar.setTipoUsuario(tipoUsuario);
					dataEntregar.setId_usuario(Integer.valueOf(usuarioId));
					dataEntregar.setId_livro(Integer.valueOf(livroId));
					dataEntregar.setId_estoque(Integer.valueOf(idEstoque));


				}
				else {
					dataEntregar.setId_reserva(Integer.valueOf(reservaId));

				}
			}
		
		
		return dataEntregar;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String operacao = request.getParameter("operacao");

		RequestDispatcher d = null;

		if (resultado.getMsg() == null) {
			if (operacao.equals("SALVAR")) {
				d = request.getRequestDispatcher("FormLivro.jsp");
			} 
		} else {
			request.setAttribute("resposta", resultado.getMsg());
			d = request.getRequestDispatcher("Resposta.jsp");
		}

		try {
			d.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}
	

}

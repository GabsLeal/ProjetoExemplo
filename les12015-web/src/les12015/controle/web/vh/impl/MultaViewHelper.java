package les12015.controle.web.vh.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import les12015.controle.web.vh.IViewHelper;
import les12015.core.aplicacao.Resultado;
import les12015.dominio.Admin;
import les12015.dominio.Analise;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Multa;

public class MultaViewHelper  implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Multa multa = new Multa();
		String data = request.getParameter("idData");
		String idCat = request.getParameter("idCat");
		String idUsuario = request.getParameter("idUsuario");

		

		
		if (request.getParameter("operacao").equals("CONSULTAR")) {
			multa.setId(Integer.valueOf(idUsuario));
		}
		if (request.getParameter("operacao").equals("ALTERAR")) {
			multa.setId(Integer.valueOf(idUsuario));
			multa.setMotivo("pago");
		}
		return multa;
		}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub	
		String operacao = request.getParameter("operacao");

		RequestDispatcher d = null;

		String tipo = request.getParameter("tipo");


		if (resultado.getMsg() == null) {
			if (operacao.equals("CONSULTAR")) {
				if(tipo!= null) {
					request.setAttribute("listaMulta", resultado.getEntidades());
					d = request.getRequestDispatcher("MultasBiblioteca.jsp");
				}
				else {
					request.setAttribute("listaMulta", resultado.getEntidades());
					d = request.getRequestDispatcher("Multas.jsp");
				}

			} 
			if (operacao.equals("ALTERAR")) {
				//request.setAttribute("listaMulta", resultado.getEntidades());
				d = request.getRequestDispatcher("Home.jsp");
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

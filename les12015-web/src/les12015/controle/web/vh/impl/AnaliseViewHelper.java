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

public class AnaliseViewHelper  implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Analise analise = new Analise();
		String idCat = request.getParameter("idCat");
		String idAno = request.getParameter("idAno");

		

		
		if (request.getParameter("operacao").equals("CONSULTAR")) {
			analise.setCategoria(idCat);
			analise.setAno(idAno);
		}
		return analise;
		}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub	
		String operacao = request.getParameter("operacao");

		RequestDispatcher d = null;



		if (resultado.getMsg() == null) {
			if (operacao.equals("CONSULTAR")) {
				d = request.getRequestDispatcher("FormLivro.jsp");
				request.setAttribute("listaGrafico", resultado.getEntidades());
				d = request.getRequestDispatcher("Grafico.jsp");
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

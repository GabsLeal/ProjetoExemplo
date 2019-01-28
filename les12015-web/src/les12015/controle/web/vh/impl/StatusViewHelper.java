package les12015.controle.web.vh.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import les12015.controle.web.vh.IViewHelper;
import les12015.core.aplicacao.Resultado;
import les12015.dominio.Aluno;
import les12015.dominio.EntidadeDominio;


public class StatusViewHelper  implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Aluno aluno = new Aluno();
		String id = request.getParameter("txtId");
		String status = request.getParameter("idstatus");
		String motivo = request.getParameter("txtMotivo");
		

		if (request.getParameter("operacao").equals("SALVAR")  || request.getParameter("operacao").equals("ALTERAR")) {
				aluno.setMotivo(motivo);
				aluno.setId(Integer.valueOf(id));
				aluno.setStatus(Boolean.valueOf(status));		
		}

		

		return aluno;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

}

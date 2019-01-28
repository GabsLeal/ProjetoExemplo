package les12015.controle.web.vh.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import les12015.controle.web.vh.IViewHelper;
import les12015.core.aplicacao.Resultado;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Reserva;

public class ReservaViewHelper  implements IViewHelper  {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Reserva reserva = new Reserva();
		String id_usuario = request.getParameter("idUsuario");
		String condicao = request.getParameter("condicao");
		String id_reserva = request.getParameter("reservaId");
		String qtEstoque = request.getParameter("qtEstoque");
		String idEstoque = request.getParameter("idEstoque");
		String tipoUsuario= request.getParameter("tipoUsuario");

		if(request.getParameter("operacao").equals("CONSULTAR")){
		
			if(condicao != null) {
				if(condicao.equals("RETIRADOS")) {
					reserva.setCondicao(condicao);
					reserva.setId_usuario(Integer.valueOf(id_usuario));
					reserva.setTipoUsuario(tipoUsuario);
				}
				else if (condicao.equals("ESPERA")) {
					if(tipoUsuario != null) {
						if(tipoUsuario.equals("aluno")) {
							reserva.setCondicao(condicao);
							reserva.setTipoUsuario("aluno");	
						}
						else if(tipoUsuario.equals("biblioteca")) {
							reserva.setCondicao(condicao);
							reserva.setTipoUsuario("biblioteca");	
						}
					}
				}
				else if (condicao.equals("DEVOLVER")) {
					if(tipoUsuario != null) {
						if(tipoUsuario.equals("aluno")) {
							reserva.setCondicao(condicao);
							reserva.setTipoUsuario("aluno");	
						}
						else if(tipoUsuario.equals("biblioteca")) {
							reserva.setCondicao(condicao);
							reserva.setTipoUsuario("biblioteca");	
						}
						else if(tipoUsuario.equals("professor")) {
							reserva.setCondicao(condicao);
							reserva.setTipoUsuario("professor");	
						}
					}
				
			}
				if(condicao.equals("LIVROENTREGAR")) {
					reserva.setCondicao(condicao);
				}
			

			
			
		}
			else{
				reserva.setId_usuario(Integer.valueOf(id_usuario));
			}
		}
		if(request.getParameter("operacao").equals("ALTERAR")){
			if(condicao != null) {
				if(condicao.equals("DEVOLVER")) {
					if(tipoUsuario != null) {
						if(tipoUsuario.equals("biblioteca")) {
							reserva.setCondicao(condicao);
							reserva.setId_usuario(Integer.valueOf(id_usuario));
							reserva.setId(Integer.valueOf(id_reserva));
							reserva.setTipoUsuario(tipoUsuario);
						}
					}
					else {
						reserva.setCondicao(condicao);
						reserva.setId_usuario(Integer.valueOf(id_usuario));
						reserva.setId(Integer.valueOf(id_reserva));
						}
				}
				else if(condicao.equals("DEVOLVIDO")) {
					if(tipoUsuario != null) {
						if(tipoUsuario.equals("biblioteca")) {
							reserva.setTipoUsuario(tipoUsuario);
						}
					}
					else {
						reserva.setCondicao(condicao);
						reserva.setQtEstoque(qtEstoque);
						reserva.setId(Integer.valueOf(id_reserva));
						reserva.setId_estoque(Integer.valueOf(idEstoque));
					}
				
				}
				
			}
			else {
				if(id_reserva != null) {
					reserva.setId(Integer.valueOf(id_reserva));
				}
			}

			}
		return reserva;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String operacao = request.getParameter("operacao");

		RequestDispatcher d = null;
		String def = request.getParameter("historicoId");
		String situacao = request.getParameter("situacao");
		String login = request.getParameter("login");
		String condicao = request.getParameter("condicao");
		String tipoUsuario= request.getParameter("tipoUsuario");


		if (resultado.getMsg() == null) {
			if (operacao.equals("SALVAR")) {
				d = request.getRequestDispatcher("Home.jsp");
			} else if (operacao.equals("ALTERAR")) {
				if(condicao != null) {
					 if(condicao.equals("DEVOLVIDO")) {
							d = request.getRequestDispatcher("Home.jsp");
				}
					 if(condicao.equals("DEVOLVER")) {
						 if(tipoUsuario != null) {
							 if(tipoUsuario.equals("biblioteca")) {
									d = request.getRequestDispatcher("PerfilBiblioteca.jsp");

								}
						 }
						 
						 else {
								d = request.getRequestDispatcher("PerfilAluno.jsp");

							}
					 }
					 
				}
				 else {
					 if(tipoUsuario != null) {
							if(tipoUsuario.equals("biblioteca")) {
								d = request.getRequestDispatcher("PerfilBiblioteca.jsp");

							}
							else {
								d = request.getRequestDispatcher("PerfilAluno.jsp");

							}
					 }
						else {
							d = request.getRequestDispatcher("PerfilAluno.jsp");

						}

				 }
			} else if (operacao.equals("CONSULTAR")) {
				if(condicao != null) {
					if(condicao.equals("ESPERA")) {
						request.setAttribute("listaRetirados", resultado.getEntidades());
						d = request.getRequestDispatcher("LivroRetirar.jsp");
					}
					else if(condicao.equals("RETIRADOS")) {
						if(tipoUsuario != null) {
							if(tipoUsuario.equals("biblioteca")) {
								request.setAttribute("listaRetirados", resultado.getEntidades());
								d = request.getRequestDispatcher("RetiradosBiblioteca.jsp");
							}
							else {
								request.setAttribute("listaRetirados", resultado.getEntidades());
								d = request.getRequestDispatcher("Retirados.jsp");
							}
						}
		
						
					}
					else if(condicao.equals("DEVOLVER")) {
						request.setAttribute("listaDevolver", resultado.getEntidades());
						d = request.getRequestDispatcher("LivroDevolver.jsp");
					}
					else if(condicao.equals("LIVROENTREGAR")) {
						request.setAttribute("listaEntregar", resultado.getEntidades());
						d = request.getRequestDispatcher("LivroEntregar.jsp");
					}
				}
				else {
					request.setAttribute("listaReservas", resultado.getEntidades());
					d = request.getRequestDispatcher("Reservados.jsp");
				}
				
				
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

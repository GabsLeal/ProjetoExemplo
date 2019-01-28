package les12015.core.impl.negocio;

import java.util.List;

import les12015.core.IStrategy;
import les12015.dominio.Aluno;
import les12015.dominio.Categoria;
import les12015.dominio.Endereco;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Livro;

public class ValidarDadosObrigatoriosAluno implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {

		if (entidade instanceof Aluno) {
			Aluno aluno = (Aluno) entidade;
			String genero = aluno.getGenero();
			String nome = aluno.getNome();
			String data_nascimento = aluno.getData_nascimento();
			String cpf = aluno.getRa();
			String email = aluno.getEmail();
			String senha = aluno.getSenha();
			String matricula = aluno.getMatricula();
			String confir_senha = aluno.getSenhaconfirma();
			StringBuilder resposta = new StringBuilder();
			String situ = aluno.getSituacao();

			if (situ == null) {
				String tipo_telefone = aluno.getTelefone().getTipo();
				String ddd = aluno.getTelefone().getDdd();
				String telefone = aluno.getTelefone().getNumero();
				if (genero.trim().equals("") || genero == null) {
					return "Genero n�o pode ser vazio. ";
				}
				if (nome.trim().equals("") || nome == null) {
					return "Nome n�o pode ser vazio. ";
				}
				if (data_nascimento.trim().equals("") || data_nascimento == null) {
					return "Data de nascimento n�o pode ser vazio. ";
				}
				if (cpf.trim().equals("") || cpf == null) {
					return "Cpf n�o pode ser vazio. ";
				}
				if (tipo_telefone.trim().equals("") || tipo_telefone == null) {
					return "Tipo de telefone n�o pode ser vazio. ";
				}
				if (ddd.trim().equals("") || ddd == null) {
					return "DDD de telefone n�o pode ser vazio. ";
				}
				if (matricula.trim().equals("") || matricula == null) {
					return "matricula n�o pode ser vazio. ";
				}
				if (telefone.trim().equals("") || telefone == null) {
					return "Telefone n�o pode ser vazio. ";
				}
				if (email.trim().equals("") || email == null) {
					return "Email n�o pode ser vazio. ";
				}
				if (senha.trim().equals("") || senha == null) {
					return "senha n�o pode ser vazio. ";
				}
				if (confir_senha.trim().equals("") || confir_senha == null) {
					return "Confirmar senha n�o pode ser vazio. ";
				}
				if (!confir_senha.equals(senha)) {
					return "Os campos de senhas tem quer ser iguais";
				}
				if (aluno.getEndereco() == null) {
					return "Endere�o n�o pode ser vazio. ";
				}

				
				if (aluno.getEndereco().getTipo().trim().equals("") || aluno.getEndereco().getTipo() == null) {
					return "Tipo endere�o n�o pode ser vazio. ";
				}
				if (aluno.getEndereco().getLogradouro().getTipo().trim().equals("") || aluno.getEndereco().getLogradouro().getTipo() == null) {
					return "Tipo logradouro n�o pode ser vazio. ";
				}
				if (aluno.getEndereco().getLogradouro().getNome().trim().equals("") || aluno.getEndereco().getLogradouro().getNome() == null) {
					return "Nome logradouro n�o pode ser vazio. ";
				}
				if (aluno.getEndereco().getNumero().trim().equals("") || aluno.getEndereco().getNumero() == null) {
					return "Numero de endere�o n�o pode ser vazio. ";
				}
				if (aluno.getEndereco().getBairro().trim().equals("") || aluno.getEndereco().getBairro() == null) {
					return "Bairro n�o pode ser vazio. ";
				}
				if (aluno.getEndereco().getCep().trim().equals("") || aluno.getEndereco().getCep() == null) {
					return "Cep n�o pode ser vazio. ";
				}
				if (aluno.getEndereco().getCidade().getNome().trim().equals("") || aluno.getEndereco().getCidade().getNome() == null) {
					return "Cidade n�o pode ser vazio. ";
				}
				if (aluno.getEndereco().getCidade().getEstado() == null) {
					return "Estado n�o pode ser vazio. ";
				}
				if (aluno.getEndereco().getCidade().getPais() == null) {
					return "Pais n�o pode ser vazio. ";
				}

//				if (!senha.trim().equals("") || senha != null &&  !confir_senha.trim().equals("") || confir_senha != null 
	//					&& !confir_senha.equals(senha)) {
	//				String code = new StringBuilder(senha).reverse().toString();
	//				aluno.setSenha(code);
	//			}

				

			
			}
			else if(situ.equals("alterarstatus")) {

				String motivo = aluno.getMotivo();	
		
				if(motivo.trim().equals("") || motivo == null  ){
					return "N�o pode ser nulo";
				}
			}

		} else {
			return "Deve ser registrado um livro!";
		}

		return null;
	}

}

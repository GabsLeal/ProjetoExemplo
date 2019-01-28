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
					return "Genero não pode ser vazio. ";
				}
				if (nome.trim().equals("") || nome == null) {
					return "Nome não pode ser vazio. ";
				}
				if (data_nascimento.trim().equals("") || data_nascimento == null) {
					return "Data de nascimento não pode ser vazio. ";
				}
				if (cpf.trim().equals("") || cpf == null) {
					return "Cpf não pode ser vazio. ";
				}
				if (tipo_telefone.trim().equals("") || tipo_telefone == null) {
					return "Tipo de telefone não pode ser vazio. ";
				}
				if (ddd.trim().equals("") || ddd == null) {
					return "DDD de telefone não pode ser vazio. ";
				}
				if (matricula.trim().equals("") || matricula == null) {
					return "matricula não pode ser vazio. ";
				}
				if (telefone.trim().equals("") || telefone == null) {
					return "Telefone não pode ser vazio. ";
				}
				if (email.trim().equals("") || email == null) {
					return "Email não pode ser vazio. ";
				}
				if (senha.trim().equals("") || senha == null) {
					return "senha não pode ser vazio. ";
				}
				if (confir_senha.trim().equals("") || confir_senha == null) {
					return "Confirmar senha não pode ser vazio. ";
				}
				if (!confir_senha.equals(senha)) {
					return "Os campos de senhas tem quer ser iguais";
				}
				if (aluno.getEndereco() == null) {
					return "Endereço não pode ser vazio. ";
				}

				
				if (aluno.getEndereco().getTipo().trim().equals("") || aluno.getEndereco().getTipo() == null) {
					return "Tipo endereço não pode ser vazio. ";
				}
				if (aluno.getEndereco().getLogradouro().getTipo().trim().equals("") || aluno.getEndereco().getLogradouro().getTipo() == null) {
					return "Tipo logradouro não pode ser vazio. ";
				}
				if (aluno.getEndereco().getLogradouro().getNome().trim().equals("") || aluno.getEndereco().getLogradouro().getNome() == null) {
					return "Nome logradouro não pode ser vazio. ";
				}
				if (aluno.getEndereco().getNumero().trim().equals("") || aluno.getEndereco().getNumero() == null) {
					return "Numero de endereço não pode ser vazio. ";
				}
				if (aluno.getEndereco().getBairro().trim().equals("") || aluno.getEndereco().getBairro() == null) {
					return "Bairro não pode ser vazio. ";
				}
				if (aluno.getEndereco().getCep().trim().equals("") || aluno.getEndereco().getCep() == null) {
					return "Cep não pode ser vazio. ";
				}
				if (aluno.getEndereco().getCidade().getNome().trim().equals("") || aluno.getEndereco().getCidade().getNome() == null) {
					return "Cidade não pode ser vazio. ";
				}
				if (aluno.getEndereco().getCidade().getEstado() == null) {
					return "Estado não pode ser vazio. ";
				}
				if (aluno.getEndereco().getCidade().getPais() == null) {
					return "Pais não pode ser vazio. ";
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
					return "Não pode ser nulo";
				}
			}

		} else {
			return "Deve ser registrado um livro!";
		}

		return null;
	}

}

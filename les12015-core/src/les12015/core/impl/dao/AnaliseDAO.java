package les12015.core.impl.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import les12015.dominio.Analise;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Quantidade;
 
public class AnaliseDAO extends AbstractJdbcDAO {
 
    public AnaliseDAO() {
        super("tb_analise", "id_analise");
    }
 
    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
        // TODO Auto-generated method stub
         
    }
 
    @Override
    public void alterar(EntidadeDominio entidade) throws SQLException {
        // TODO Auto-generated method stub
         
    }
 
    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
        // TODO Auto-generated method stub
         
        List<Analise> datas = new ArrayList<Analise>();
        List<Analise> mes = new ArrayList<Analise>();
 
        //sqlDatas.append("SELECT distinct COUNT(*), EXTRACT('month'from data) as mes from tb_reserva where  extract(year from data) = "+analise.getAno()+" and tipo_usuario = 'biblioteca' " + 
    //          "group by tb_reserva.data");
    //  try {
        //          openConnection();
        //          pst = connection.prepareStatement(sqlBiblioteca.toString());
             
        //          Analise anos = new Analise();
        //          ResultSet rs = pst.executeQuery();
        //          List<EntidadeDominio> analises = new ArrayList<EntidadeDominio>();
        //          while (rs.next()) {
                 
                     
        //                  anos.setQtBiblioteca(rs.getString("count"));    
        //                      anos.setMotivo(rs.getString("mes"));
            //          analises.add(an);
 
                                                 
        //          }
             
        //                          datas.add(anos);
        //      } catch (SQLException e) {
        //          e.printStackTrace();
        //      }
        //      for(int i = 0; i< datas.size(); i++) {
        //      if(i>0) {
        //          if(datas.get(i).getMotivo().equals(datas.get(i+1).getMotivo())) {
                    //          datas.remove(i);
        //      datas.remove(i-1);
                    //              }
        //          }
             
        //      }
        String qtBiblioteca;
        List<EntidadeDominio> analises = new ArrayList<EntidadeDominio>();
        Analise analise = (Analise) entidade;
 
        for(int i= 1; i <=12; i++) {
            PreparedStatement pst = null;
            PreparedStatement pstAluno = null;
            PreparedStatement pstProfessor = null;
            Analise an = new Analise();
 
            StringBuilder sqlBiblioteca = new StringBuilder();
            StringBuilder sqlAluno = new StringBuilder();
            StringBuilder sqlProfessor = new StringBuilder();
            StringBuilder sqlDatas = new StringBuilder();
 
            sqlBiblioteca.append("select distinct EXTRACT('month'from data) as mes, (SELECT COUNT(*) from tb_reserva"
                    + " JOIN tb_estoque_livro on tb_reserva.id_estoque = tb_estoque_livro.id_estoque" + 
            " JOIN tb_re_livro on tb_estoque_livro.id_livro = tb_re_livro.id_livro "+
            " JOIN tb_categoria_livro_dados on tb_re_livro.id_livro= tb_categoria_livro_dados.id_livro "
                    + " where extract(month from data) = "
                    +i +" and extract(year from data) = "+ analise.getAno() +" and tipo_usuario = 'biblioteca' and  categoria = '" + analise.getCategoria()
                            + "' ) as contagem"
                    +" from tb_reserva"
                    + " JOIN tb_estoque_livro on tb_reserva.id_estoque = tb_estoque_livro.id_estoque" + 
            " JOIN tb_re_livro on tb_estoque_livro.id_livro = tb_re_livro.id_livro "+
            " JOIN tb_categoria_livro_dados on tb_re_livro.id_livro= tb_categoria_livro_dados.id_livro "
            + " where categoria = '"+ analise.getCategoria() + "' and EXTRACT('month'from data) = " + i
            + " group by titulo, tb_reserva.data  ");
 
             
            sqlAluno.append("select distinct EXTRACT('month'from data) as mes, (SELECT COUNT(*) from tb_reserva"
                    + " JOIN tb_estoque_livro on tb_reserva.id_estoque = tb_estoque_livro.id_estoque" + 
            " JOIN tb_re_livro on tb_estoque_livro.id_livro = tb_re_livro.id_livro "+
            " JOIN tb_categoria_livro_dados on tb_re_livro.id_livro= tb_categoria_livro_dados.id_livro "
                    + " where extract(month from data) = "
                    +i +" and extract(year from data) = "+ analise.getAno() +" and tipo_usuario = 'aluno' and  categoria = '" + analise.getCategoria()
                            + "' ) as contagem"
                    +" from tb_reserva"
                    + " JOIN tb_estoque_livro on tb_reserva.id_estoque = tb_estoque_livro.id_estoque" + 
            " JOIN tb_re_livro on tb_estoque_livro.id_livro = tb_re_livro.id_livro "+
            " JOIN tb_categoria_livro_dados on tb_re_livro.id_livro= tb_categoria_livro_dados.id_livro "
            + " where categoria = '"+ analise.getCategoria() + "' and EXTRACT('month'from data) = " + i
            + " group by titulo, tb_reserva.data  ");
             
            sqlProfessor.append("select distinct EXTRACT('month'from data) as mes, (SELECT COUNT(*) from tb_reserva"
                    + " JOIN tb_estoque_livro on tb_reserva.id_estoque = tb_estoque_livro.id_estoque" + 
            " JOIN tb_re_livro on tb_estoque_livro.id_livro = tb_re_livro.id_livro "+
            " JOIN tb_categoria_livro_dados on tb_re_livro.id_livro= tb_categoria_livro_dados.id_livro "
                    + " where extract(month from data) = "
                    +i +" and extract(year from data) = "+ analise.getAno() +" and tipo_usuario = 'professor' and  categoria = '" + analise.getCategoria()
                            + "' ) as contagem"
                    +" from tb_reserva"
                    + " JOIN tb_estoque_livro on tb_reserva.id_estoque = tb_estoque_livro.id_estoque" + 
            " JOIN tb_re_livro on tb_estoque_livro.id_livro = tb_re_livro.id_livro "+
            " JOIN tb_categoria_livro_dados on tb_re_livro.id_livro= tb_categoria_livro_dados.id_livro "
            + " where categoria = '"+ analise.getCategoria() + "' and EXTRACT('month'from data) = " + i
            + " group by titulo, tb_reserva.data  ");
         
        try {
            openConnection();
            pst = connection.prepareStatement(sqlBiblioteca.toString());
             
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                 
                     
                        //an.setCategoria(rs.getString("categoria"));
                        an.setQtBiblioteca(rs.getString("contagem"));   
 
            //          analises.add(an);
 
                                                 
            }
             
            openConnection();
            pstAluno = connection.prepareStatement(sqlAluno.toString());
             
             
            ResultSet rsAluno = pstAluno.executeQuery();
            while (rsAluno.next()) {
                 
            //          an.setCategoria(rs.getString("categoria"));
 
                        an.setQtAluno(rsAluno.getString("contagem"));   
 
            //          analises.add(an);
 
                                                 
            }
            openConnection();
            pstProfessor = connection.prepareStatement(sqlAluno.toString());
             
             
            ResultSet rsProfessor = pstProfessor.executeQuery();
            while (rsProfessor.next()) {
                 
            //          an.setCategoria(rs.getString("categoria"));
 
                        an.setQtProfessor(rsProfessor.getString("contagem"));   
 
            //          analises.add(an);
 
                                                 
            }
            if(an.getQtProfessor() == null) {
                an.setQtProfessor("0"); 
 
            }
            if(an.getQtAluno() == null) {
                an.setQtAluno("0"); 
 
            }
            if(an.getQtBiblioteca() == null) {
                an.setQtBiblioteca("0");    
 
            }
            analises.add(an);
             
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
        if(analises != null) {
            return analises;
        }
        return null;
    }
 
 
}
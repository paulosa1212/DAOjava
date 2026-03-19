import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CadastroDAOImpl implements CadastroDAO {
    @Override
    public void cadastrar(String nome, LocalDate datanascimento, String cpf, String telefone) {
        String sql = "INSERT INTO cadastros (nome,data_nascimento,cpf,telefone)VALUES(?,?,?,?)";

        try (Connection con = ConexaoSql.getconnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, nome);
            st.setDate(2, java.sql.Date.valueOf(datanascimento));
            st.setString(3, cpf);
            st.setString(4, telefone);
            st.executeUpdate();
            System.out.println("CADASTRO REALIZADO!");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void deletarCadastro(String cpf) {
        String sql = "DELETE FROM cadastros where cpf=?";

        try (Connection con = ConexaoSql.getconnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, cpf);
            st.executeUpdate();
            System.out.println("CADASTRO EXCLUIDO!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cadastro> listar() {
        String sql = "SELECT * FROM cadastros";
        List<Cadastro> lista = new ArrayList<>();

        try (Connection con = ConexaoSql.getconnection();
             PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Cadastro cadastro = new Cadastro();
                cadastro.setNome(rs.getString("nome"));
                cadastro.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                cadastro.setCpf(rs.getString("cpf"));
                cadastro.setTelefone(rs.getString("telefone"));
                lista.add(cadastro);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;

    }

    @Override
    public void atualizarCadastro(String nome, LocalDate datanascimento, String cpf, String telefone) {
        String sql = "UPDATE cadastros SET nome=?,data_nascimento=?,telefone=? where cpf=?";

        try (Connection con = ConexaoSql.getconnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, nome);
            st.setDate(2, java.sql.Date.valueOf(datanascimento));
            st.setString(3, telefone);
            st.setString(4, cpf);

            st.executeUpdate();
            System.out.println("CADASTRO ATUALIZADO!");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cadastro buscarCadastro(String cpf) {
        String sql = "SELECT * FROM cadastros where cpf=?";
        try (Connection con = ConexaoSql.getconnection();
             PreparedStatement st = con.prepareStatement(sql)
        ) {
            st.setString(1, cpf);
            try (ResultSet rs = st.executeQuery();) {


                if (rs.next()) {
                    return new Cadastro(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDate("data_nascimento").toLocalDate(),
                            rs.getString("cpf"),
                            rs.getString("telefone")
                    );
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}


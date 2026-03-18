    import java.time.LocalDate;
    import java.util.List;

    public interface CadastroDAO {
        void cadastrar(String nome, LocalDate datanascimento, String cpf, String telefone);
        void deletarCadastro(String cpf);
        List<Cadastro> listar();
        void atualizarCadastro(String nome, LocalDate datanascimento, String cpf, String telefone);
    }

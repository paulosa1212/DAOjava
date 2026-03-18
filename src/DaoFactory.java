public class DaoFactory {
    public static CadastroDAO creatCadastroDao(){
        return new CadastroDAOImpl();
    }
}

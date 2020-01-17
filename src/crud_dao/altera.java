package crud_dao;

import Modelo.Cliente;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Cleber Nascimento
 */
public class altera {
    
    private Connection connection;
    int id; 
    String nome;
    String idade;
    String telefone;
    
    public altera(){ // canexao com banco de dados
        this.connection = new ConnectionFactory().getConnection();
        Cliente cliente = new Cliente();
    } 
    
    public void alterarUsuario(Cliente usuario){
        
        try{
        PreparedStatement stmt = connection.prepareStatement("UPDATE Cliente SET nome=?,idade=?,telefone=? WHERE id=?");
        stmt.setString(1,usuario.getNome());
        stmt.setString(2,usuario.getIdade());
        stmt.setString(3,usuario.getTelefone());
        stmt.executeQuery();
        stmt.close();        
        }
        catch (SQLException u) { 
            //throw new RuntimeException(u);
        }   
    }
}

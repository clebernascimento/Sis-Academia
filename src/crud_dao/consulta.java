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
public class consulta {
    private Connection connection;
    int id; 
    String nome;
    String idade;
    String telefone;
    
    public consulta(){ // canexao com banco de dados
        this.connection = new ConnectionFactory().getConnection();
         Cliente cliente = new Cliente();
    } 
    
     public void ConsultarUsuario(String Nome){
        //String sql = "SELECT * FROM usuario WHERE nome like %?%"; 
        try{
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM cliente WHERE nome like %?%");  
        stmt.setString(1,Nome);
        stmt.executeQuery();
        stmt.close(); 
        }
        catch (SQLException u) { 
           // throw new RuntimeException(u);
        }   
    }  
}
// CADASTRO DO CLIENTE 

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

//Classe para cadastro 
public class cadastro {
    
    private Connection connection;
    int id; 
    String nome;
    String idade;
    String telefone;
    
    public cadastro(){ // canexao com banco de dados
        this.connection = new ConnectionFactory().getConnection();
        
        Cliente cliente = new Cliente();
    } 
    public void adiciona(Cliente cliente){ // adiciona usuario ao banco de dados
        try { 
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO cliente(nome,idade,telefone)values(?,?,?)");
            stmt.setString(1,cliente.getNome());
            stmt.setString(2,cliente.getIdade());
            stmt.setString(3,cliente.getTelefone());
            stmt.execute();
            stmt.close();
        } 
        catch (SQLException u) { 
           // throw new RuntimeException(u);
        }   
    } 
}
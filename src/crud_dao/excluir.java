
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
public class excluir {
    
     private Connection connection;
     int id; 
     String nome;
     String idade;
     String telefone;
     
     public excluir(){ // canexao com banco de dados
        this.connection = new ConnectionFactory().getConnection();
        Cliente cliente = new Cliente();
    } 
     
    public void excluirUsuario(int Id){
        
        try{
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM cliente WHERE id=?");
        stmt.setInt(1,Id);
        stmt.executeQuery();
        stmt.close(); 
        }
        catch (SQLException u) { 
            //throw new RuntimeException(u);
        }   
    }
}
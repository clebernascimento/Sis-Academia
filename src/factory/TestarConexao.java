// TESTANDO A CONEXAO 

package factory;

import java.sql.Connection; 
import java.sql.SQLException;

/**
 *
 * @author Cleber Nascimento
 */
public class TestarConexao {     
    public static void main(String[] args) throws SQLException {
         Connection connection = new ConnectionFactory().getConnection();
         System.out.println("Conexão aberta!");
         connection.close();
     }
}

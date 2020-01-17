//CONEXAO COM O BANCO DE DADOS MYSQL

package factory;

import java.security.MessageDigest;
import java.sql.Connection; // conexão SQL para Java  
import java.sql.DriverManager; // driver de conexão SQL para Java 
import java.sql.SQLException; // classe para tratamento de exceções 


/**
 *
 * @author Cleber Nascimento
 */
public class ConnectionFactory {
   static String user,password;
    
    //metodo rsponsavel por estabelecer a conexao com o banco de dados
    public void setUserSenha(String usuario,String senha){
        this.user = usuario;
        this.password = senha;
    } 
    public static String getPassword(){
        return password;
    }
    public static Connection getConnection() {
         java.sql.Connection conexao = null;
	
         //chama a driver
         String driver = "com.mysql.jdbc.Driver";
         
         //armazenando informaçoes referentes aobanco 
         String url = "jdbc:mysql://localhost:3306/projeto_academia";
         String user = "root";
         String password = "";
         ///estabelecendo a conxao com o banco de dados
         try {
             Class.forName(driver);
             conexao = DriverManager.getConnection(url, user, password);
             return conexao;  
         }
         catch (Exception e) {
             return null;
            }   
         }
     }
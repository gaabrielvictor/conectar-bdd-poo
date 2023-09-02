package aula.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String user = "root";
        String senha = "root";
        String url = "jdbc:mysql://localhost:3306/?user=root";

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, senha);
            System.out.println("Conexão realizada com sucesso.");
            con.close();
        } catch (ClassNotFoundException ex) {
            System.err.print(ex.getMessage());
        } catch (SQLException e) {
            System.err.print(e.getMessage());
        }
    }
}
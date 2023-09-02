package aula.jdbc;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CriarTabela extends JFrame {
    private Connection con;
    private Statement st;

    public CriarTabela() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String sUsuario = "root";
        String sSenha = "root";
        String nomeBanco = "loja"; 
        String sFonte = "jdbc:mysql://127.0.0.1:3306/" + nomeBanco + "?user=root";

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(sFonte, sUsuario, sSenha);
            JOptionPane.showMessageDialog(this, "Banco conectado com sucesso!", "Mensagem", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException eSQL) {
            eSQL.printStackTrace();
            JOptionPane.showMessageDialog(this, "Falha na conexão com o banco!\n" + "Mensagem: " + eSQL.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Falha na conexão com o banco!\n" + "Mensagem: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        String sentencaSQL = "CREATE TABLE Produto (codProduto INT PRIMARY KEY, produto VARCHAR(50));";

        try {
            st = con.createStatement();
            st.executeUpdate(sentencaSQL);
            JOptionPane.showMessageDialog(this, "Tabela criada com sucesso!", "Mensagem", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException eSQL) {
            eSQL.printStackTrace();
            JOptionPane.showMessageDialog(this, "Não foi possível criar a tabela!\n" + "Mensagem: " + eSQL.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(2);
        }

        try {
            String insertArroz = "INSERT INTO Produto (codProduto, produto) VALUES (1, 'Arroz');";
            String insertFeijao = "INSERT INTO Produto (codProduto, produto) VALUES (2, 'Feijão');";
            String insertMacarrao = "INSERT INTO Produto (codProduto, produto) VALUES (3, 'Macarrão');";
            
            st.executeUpdate(insertArroz);
            st.executeUpdate(insertFeijao);
            st.executeUpdate(insertMacarrao);

            JOptionPane.showMessageDialog(this, "Dados inseridos com sucesso!", "Mensagem", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException eSQL) {
            eSQL.printStackTrace();
            JOptionPane.showMessageDialog(this, "Não foi possível inserir os dados!\n" + "Mensagem: " + eSQL.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(3);
        }

        try {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(4);
        }

        Container P = getContentPane();
        P.setLayout(new FlowLayout());
        JLabel mensagem = new JLabel("Você acabou de testar um exemplo usando CREATE TABLE e INSERT!");
        P.add(mensagem);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            CriarTabela ex = new CriarTabela();
            ex.setDefaultCloseOperation(EXIT_ON_CLOSE);
            ex.setTitle("USANDO CREATE TABLE e INSERT");
            ex.setVisible(true);
            ex.setSize(400, 300);
        });
    }
}

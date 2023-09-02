package aula.jdbc;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver carregado com sucesso!");
        } catch (Exception ex) {
            System.out.println("Driver nao pode ser carregado!");
        }
    }
}

package exemplos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PersistenciaCSV {

    public static void main(String[] args) {
        String arquivoCSV = "dados.csv";

        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoCSV));
            escritor.write("Nome;Idade");
            escritor.newLine();
            escritor.write("Temistocles;26");
            escritor.newLine();
            escritor.write("Maria;22");
            escritor.newLine();
            escritor.close();
            System.out.println("Dados gravados com sucesso no arquivo CSV");
        } catch (IOException e) {
            System.err.println("Erro ao escrever arquivo CSV: " + e.getMessage());
        }
    }
}

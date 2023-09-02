package exemplos;

import java.io.FileWriter;
import java.io.IOException;

public class PersistenciaTexto {

	public static void main(String[] args) {
		String conteudo = "Este é um arquivo em formato txt";
		
		try {
			FileWriter escritor = new FileWriter ("arquivo.txt");
			escritor.write(conteudo);
			escritor.close();
			System.out.println("Dados gravados com sucesso");
		} catch (IOException e) {
			System.err.println("Erro ao escrever arquivo: " + e.getMessage());
		}
			
	}

}

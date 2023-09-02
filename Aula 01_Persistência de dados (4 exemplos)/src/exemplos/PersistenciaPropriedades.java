package exemplos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class PersistenciaPropriedades {

	public static void main(String[] args) {
		Properties propriedades = new Properties();
		propriedades.setProperty("usuario", "João");
		propriedades.setProperty("Senha" , "secreta");
		
		try {
			FileOutputStream arquivo = new FileOutputStream ("config.properties");
			propriedades.store(arquivo, "Configurações do usuário");
			
			arquivo.close();
			System.out.println("Propriedades gravadas com sucesso");
		} catch (IOException e) {
			System.err.println("Erro ao gravar propriedades:" + e.getMessage());
		}
		
	}

}

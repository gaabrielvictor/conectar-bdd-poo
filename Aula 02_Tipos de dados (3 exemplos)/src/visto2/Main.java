package visto2;

import java.io.IOException;
import java.io.FileWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://infoms.saude.gov.br/extensions/SEIDIGI_DEMAS_Vacina_C19/SEIDIGI_DEMAS_Vacina_C19.html").get();
            Elements titleElements = doc.getElementsByTag("span");

            if (!titleElements.isEmpty()) {
                Element titleElement = titleElements.first();
                String titleText = titleElement.text();

                String txtFilePath = "titulo2.txt";
                try (FileWriter writer = new FileWriter(txtFilePath)) {
                    writer.write(titleText);
                }

                System.out.println("Título salvo no arquivo " + txtFilePath);
            } else {
                System.out.println("Nenhuma tag <> encontrada na página.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}








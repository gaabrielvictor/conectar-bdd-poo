package visto2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String url = "https://sagres.tce.pb.gov.br/pessoal04.php?ugestora=201050&dt_mes=01&de_mes=Janeiro&dt_ano=2023&tipo_cargo=5&cargo=90000215";

        try {
            Document document = Jsoup.connect(url).get();
            String htmlContent = document.html();

            // Use regular expressions to extract numbers from the HTML content
            Pattern pattern = Pattern.compile("conteudo");
            Matcher matcher = pattern.matcher(htmlContent);

            String csvFileName = "numbers.csv";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileName))) {
                while (matcher.find()) {
                    String number = matcher.group();
                    writer.write(number);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Numbers extracted and saved to " + csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
import com.BrunoLins.WebScraping.service.WebScrapingService;

import java.io.IOException;
import java.util.List;

public class WebScrapingApplication {
    public static void main(String[] args) {
        WebScrapingService scraper = new WebScrapingService();

        try {
            List<String> pdfLinks = scraper.getPdfLinks();
            System.out.println("Links encontrados:");
            pdfLinks.forEach(System.out::println);

            String downloadDir = "downloads"; // Pasta onde os arquivos serão salvos
            scraper.downloadPdfs(pdfLinks, downloadDir);

            String zipFileName = "anexos.zip";
            scraper.zipPdfs(downloadDir, zipFileName);

            System.out.println("Processo concluído!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package com.BrunoLins.WebScraping;

import com.BrunoLins.WebScraping.service.WebScrapingService;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class WebScrapingServiceTest {

    private final WebScrapingService scraper = new WebScrapingService();

    @Test
    void testGetPdfLinks() throws IOException {
        List<String> pdfLinks = scraper.getPdfLinks();
        assertFalse(pdfLinks.isEmpty(), "A lista de PDFs não deve estar vazia!");
        assertTrue(pdfLinks.stream().allMatch(link -> link.endsWith(".pdf")), "Todos os links devem ser PDFs!");
    }

    @Test
    void testDownloadPdfs() throws IOException {
        List<String> pdfLinks = List.of(
                "https://www.exemplo.com/arquivo1.pdf",
                "https://www.exemplo.com/arquivo2.pdf"
        );
        String downloadDir = "test_downloads";
        scraper.downloadPdfs(pdfLinks, downloadDir);

        long count = Files.list(Path.of(downloadDir)).count();
        assertEquals(pdfLinks.size(), count, "A quantidade de arquivos baixados deve ser igual à quantidade de links!");
    }

    @Test
    void testZipPdfs() throws IOException {
        String downloadDir = "test_downloads";
        String zipFileName = "test_anexos.zip";
        scraper.zipPdfs(downloadDir, zipFileName);

        assertTrue(Files.exists(Path.of(zipFileName)), "O arquivo ZIP deve ser criado!");
    }
}

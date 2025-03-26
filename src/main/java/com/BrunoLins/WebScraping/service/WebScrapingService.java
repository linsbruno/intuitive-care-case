package com.BrunoLins.WebScraping.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class WebScrapingService {

    public void downloadPdfs(List<String> pdfLinks, String downloadDir) throws IOException {
        Files.createDirectories(Path.of(downloadDir)); // Cria a pasta de destino, se não existir

        for (String pdfUrl : pdfLinks) {
            String fileName = pdfUrl.substring(pdfUrl.lastIndexOf("/") + 1); // Extrai o nome do arquivo
            Path filePath = Path.of(downloadDir, fileName);

            try (InputStream in = new URL(pdfUrl).openStream()) {
                Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Baixado: " + filePath);
            } catch (IOException e) {
                System.err.println("Erro ao baixar: " + pdfUrl);
            }
        }
    }

    public void zipPdfs(String sourceDir, String zipFileName) throws IOException {
        Path zipPath = Path.of(zipFileName);

        try (ZipOutputStream zipOut = new ZipOutputStream(Files.newOutputStream(zipPath))) {
            Files.list(Path.of(sourceDir)).forEach(file -> {
                try {
                    ZipEntry zipEntry = new ZipEntry(file.getFileName().toString());
                    zipOut.putNextEntry(zipEntry);
                    Files.copy(file, zipOut);
                    zipOut.closeEntry();
                } catch (IOException e) {
                    System.err.println("Erro ao adicionar ao ZIP: " + file);
                }
            });
        }
        System.out.println("Arquivos compactados em: " + zipFileName);
    }


    private static final String URL = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";

    public List<String> getPdfLinks() throws IOException {
        List<String> pdfLinks = new ArrayList<>();

        // Conecta ao site e pega o HTML
        Document doc = Jsoup.connect(URL).get();

        // Seleciona todos os links da página
        Elements links = doc.select("a[href]");

        // Filtra apenas os links que terminam com .pdf e contém "Anexo I" ou "Anexo II" no texto do link
        for (Element link : links) {
            String href = link.attr("abs:href"); // Pega o link absoluto
            String text = link.text(); // Texto do link
            if (href.endsWith(".pdf") && (text.contains("Anexo I") || text.contains("Anexo II"))) {
                pdfLinks.add(href);
            }
        }

        return pdfLinks;
    }
}

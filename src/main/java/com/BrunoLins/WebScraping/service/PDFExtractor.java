package com.BrunoLins.WebScraping.service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PDFExtractor {

    public static void main(String[] args) {
        try {
            // Caminho do arquivo PDF
            File file = new File("C:\\Users\\Pichau\\Desktop\\WebScrapig\\downloads\\Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf");

            // Carregar o documento PDF
            PDDocument document = PDDocument.load(file);

            // Instanciar o PDFTextStripper para extrair o texto
            PDFTextStripper pdfStripper = new PDFTextStripper();

            // Extrair texto de todas as páginas
            String text = pdfStripper.getText(document);

            // Imprimir o conteúdo extraído
            System.out.println(text);

            // Fechar o documento PDF
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
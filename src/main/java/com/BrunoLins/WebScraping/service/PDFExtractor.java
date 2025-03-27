package com.BrunoLins.WebScraping.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;

public class PDFExtractor extends PDFTextStripper {
    private List<List<String>> table = new ArrayList<>();
    private List<String> currentRow = new ArrayList<>();
    private float previousY = -1;

    public PDFExtractor() throws IOException {
        super();
        setSortByPosition(true);
    }

    public static void main(String[] args) {
        try {
            PDDocument document = PDDocument.load(new File("C:\\Users\\Pichau\\Desktop\\WebScrapig\\downloads\\Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf"));
            PDFExtractor extractor = new PDFExtractor();
            extractor.getText(document);
            document.close();

            // Processar e substituir as abreviações
            List<List<String>> processedData = processExtractedData(extractor.table);

            saveToCSV(processedData, "C:\\Users\\Pichau\\Desktop\\WebScrapig\\downloads\\Teste_Bruno_Lins.csv");
            System.out.println("Dados extraídos e salvos com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void writeString(String text, List<TextPosition> textPositions) {
        if (textPositions.isEmpty()) return;

        float currentY = textPositions.get(0).getY();

        // Se mudou de linha (Y diferente), começa nova linha na tabela
        if (Math.abs(currentY - previousY) > 2) {
            if (!currentRow.isEmpty()) {
                table.add(new ArrayList<>(currentRow));
                currentRow.clear();
            }
            previousY = currentY;
        }

        currentRow.add(text.trim());
    }

    private static List<List<String>> processExtractedData(List<List<String>> rawData) {
        List<List<String>> processedData = new ArrayList<>();

        // Adiciona cabeçalho com nomes completos
        List<String> header = Arrays.asList("PROCEDIMENTO", "RN (alteração)", "VIGÊNCIA",
                "Seg. Odontológica", "Seg. Ambulatorial", "HCO", "HSO", "REF", "PAC", "DUT",
                "SUBGRUPO", "GRUPO", "CAPÍTULO");
        processedData.add(header);

        for (List<String> row : rawData) {
            if (row.size() < 2) continue; // Ignora linhas muito curtas

            List<String> processedRow = new ArrayList<>();
            for (String cell : row) {
                // Substitui as abreviações
                String processedCell = cell.replace(" OD ", " Seg. Odontológica ")
                        .replace(" AMB ", " Seg. Ambulatorial ");
                processedRow.add(processedCell);
            }
            processedData.add(processedRow);
        }

        return processedData;
    }

    private static void saveToCSV(List<List<String>> data, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (List<String> row : data) {
                writer.write(String.join(";", row) + "\n"); // Usando ; como separador para evitar conflitos com vírgulas no conteúdo
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
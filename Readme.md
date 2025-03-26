# Intuitive Care - Case Técnico - Parte 1

#### Este repositório contém a implementação do case técnico para o processo seletivo de estágio na Intuitive Care. O projeto foi desenvolvido em Java utilizando o Spring Framework e tem como objetivo realizar Web Scraping para baixar e compactar arquivos PDF.

## 🛠 Tecnologias Utilizadas

* Java 17

* Spring Boot

* Spring Boot Starter Web

* Spring Boot Starter Jersey

* Spring Boot DevTools

* Spring Boot Starter Test

* JUnit Jupiter Engine (para testes)

* Lombok (para reduzir boilerplate code)

* Jsoup (para scraping)

* Apache Commons Compress (para compactação)

* Maven

## 📌 Requisitos do Projeto

* O projeto implementa os seguintes requisitos fornecidos no teste técnico:

* Teste de Web Scraping

* Desenvolvido em Java utilizando o Spring Framework.

* Realiza o acesso ao site: ANS - Agência Nacional de Saúde Suplementar.

* Faz o download dos anexos I e II em formato PDF.

* Compacta os arquivos baixados em um único arquivo no formato ZIP.

## 🚀 Como Executar o Projeto

1️⃣ Clonar o repositório

git clone https://github.com/linsbruno/intuitive-care-case


2️⃣ Configurar e executar a aplicação

* Certifique-se de ter o Java 17+ instalado e rode o seguinte comando:

* mvn spring-boot:run

3️⃣ Como os arquivos são baixados e compactados

O projeto faz o scraping da página da ANS e identifica os links dos PDFs.

Faz o download dos arquivos.

Compacta os PDFs em um único arquivo ZIP dentro do diretório downloads/.

📂 Estrutura do Projeto

* 📦 WebScraping
* ┣ 📂 src/main/java/com/BrunpLins/WebScraping
* ┃ ┣ 📜 WebScrappingApplication.java  # Classe Principal
* ┃ ┣ 📜 WebScrappingService.java  # Serviço para baixar os PDFs
* ┣ ┣  📂 src/main/test  # Pasta onde sao realizados os testes
* ┣ 📜 pom.xml  # Dependências do projeto
* ┗ 📜 README.md  # Documentação do projeto

✅ Considerações Finais

O projeto foi desenvolvido com boas práticas de código, utilizando princípios de organização, separação de responsabilidades e modularização. Caso tenha dúvidas ou sugestões, fique à vontade para abrir uma Issue ou Pull Request. 🚀
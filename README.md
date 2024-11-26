# Projeto de Testes Automatizados API - desafio para nova oportunidade

Este projeto contém testes automatizados de API utilizando **JUnit 5** e **Rest Assured**. O Allure é utilizado para gerar relatórios detalhados sobre os testes executados.

## Requisitos

- **Java 17** ou superior
- **Maven 3.9.9** ou superior
- **Allure** instalado localmente para gerar o relatório visual

## Executando os Testes

Para executar os testes automatizados, siga os passos abaixo:

### 1. Clonar o repositório

Primeiro, clone o repositório para sua máquina local:

```
git clone <URL_do_repositorio>
cd <diretório_do_repositorio>
```
2. Executar os testes com Maven
Execute o comando abaixo para rodar todos os testes:

```
mvn clean test
```
Este comando vai compilar o projeto, executar os testes automatizados e gerar os relatórios do Maven Surefire.

3. Gerar o Relatório do Allure
Após a execução dos testes, você pode visualizar os resultados de uma forma mais interativa com o Allure. Para isso, execute o comando abaixo para gerar o relatório:
```
allure serve target/allure-results
```
Este comando irá iniciar um servidor local e abrir o relatório no seu navegador. O Allure fornecerá um painel interativo com detalhes sobre os testes, incluindo os testes passados, falhados e o tempo de execução.

4. Relatórios do Maven Surefire
Os resultados dos testes também podem ser visualizados nos relatórios gerados pelo Maven Surefire. Após rodar os testes, os relatórios de execução ficam localizados em:
```
target/surefire-reports/
```

Você pode abrir os arquivos *.txt ou *.xml para mais detalhes sobre as falhas e o comportamento de cada teste.

Estrutura do Projeto
src/test/java/: Contém os testes automatizados.
src/test/resources/: Contém arquivos de configuração e dados necessários para os testes.
target/: Diretório gerado após a execução dos testes, contendo os relatórios de execução, artefatos compilados e resultados do Allure.
Dependências
Este projeto utiliza as seguintes dependências:

JUnit 5: Para a execução dos testes.
Rest Assured: Para enviar requisições HTTP e validar as respostas.
Allure: Para gerar relatórios interativos dos testes.

### Explicação das Seções:

- **Requisitos**: Detalha as ferramentas e versões necessárias para rodar os testes e gerar o relatório.
- **Executando os Testes**: Passo a passo para rodar os testes com Maven e gerar o relatório Allure.
- **Relatórios do Maven Surefire**: Instruções sobre onde encontrar os relatórios de execução de testes gerados pelo Maven.
- **Estrutura do Projeto**: Descrição das pastas e arquivos importantes do projeto.
- **Dependências**: Lista de bibliotecas que o projeto utiliza.
- **Contribuição**: Orientações sobre como contribuir para o projeto.
- **Licença**: Informações sobre a licença do projeto.


Autor
Carlos Bonfatti
LinkedIn










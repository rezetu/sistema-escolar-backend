# Sistema Escolar - Backend

Este é o módulo de backend do Sistema Escolar, desenvolvido em Java com Spring Boot e git PostgreSQL.

## Funcionalidades Principais
- Cadastro e gestão de Pessoas (alunos, professores, etc.)
- Cadastro e gestão de Cursos
- Gestão de Matrículas (realização, busca, atualização de status de pagamento, cancelamento)

## Tecnologias Utilizadas
- Java 17+
- Spring Boot 3+
- Spring Data JPA
- PostgreSQL
- Maven

## Como Rodar o Projeto
1. Certifique-se de ter o Java 17+ e o Maven instalados.
2. Configure o PostgreSQL e crie um banco de dados (ex: `sistema_escolar_db`).
3. Atualize o arquivo `src/main/resources/application.properties` com as suas credenciais do banco de dados.
4. No terminal, navegue até a pasta `backend` e execute:
   ```bash
   mvn spring-boot:run

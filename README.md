# Hackaton FIAP - SUS Helper

## Sobre o Projeto
SUS Helper é uma API desenvolvida para o Hackaton da FIAP, focada em auxiliar na gestão e localização de unidades de saúde (como UBS, Hospitais, UPAs, etc). O projeto fornece um sistema de cadastro e consulta dessas unidades, incluindo informações como endereço, localização geográfica (latitude/longitude), horário de funcionamento e capacidade máxima de atendimento.

## Tecnologologias Utilizadas
* **Linguagem:** Java 26
* **Framework:** Spring Boot 4.0.4
* **Banco de Dados:** MySQL 8.0
* **ORM:** Spring Data JPA / Hibernate
* **Mapeamento de Objetos:** ModelMapper
* **Redução de Boilerplate:** Lombok
* **Containerização:** Docker & Docker Compose
* **Testes e Documentação:** Collection do Postman disponibilizada na pasta `/postman`

## Estrutura do Projeto
O projeto segue uma arquitetura baseada em camadas:
* **Controllers:** Exposição dos endpoints da API REST.
* **Services:** Regras de negócio e intermediação entre controllers e repositórios.
* **Repositories:** Comunicação com o banco de dados.
* **Entities:** Mapeamento objeto-relacional (JPA) das tabelas do banco.
* **DTOs:** Objetos de transferência de dados para requisições e respostas.
* **Mappers:** Conversão entre Entities e DTOs (utilizando ModelMapper).
* **Exceptions e Handlers:** Tratamento padronizado de erros da API.

## Funcionalidades Principais
* **Cadastro de Unidade de Saúde:** Permite registrar uma nova unidade com informações completas de endereço e horários através de rotas POST.
* **Listagem:** Busca todas as unidades de saúde cadastradas.
* **Busca por ID:** Recupera os detalhes de uma unidade de saúde específica.
* **Atualização:** Permite modificar os dados de uma unidade de saúde existente através de rotas PUT.
* **Exclusão:** Remove uma unidade de saúde do sistema através de rotas DELETE.

## Executando o Projeto com Docker

O projeto já está configurado para ser executado facilmente via Docker Compose, que subirá tanto o banco de dados MySQL quanto a aplicação Spring Boot.

### Pré-requisitos
* Docker
* Docker Compose

### Passos para execução
1. Clone o repositório.
2. Na raiz do projeto, execute o comando:
   ```bash
   docker-compose up -d --build
   ```
3. A aplicação estará disponível na porta `8080`.
4. O MySQL estará rodando na porta `3307` na sua máquina hospedeira (e `3306` internamente na rede do Docker).

### Variáveis de Ambiente e Configuração
O `docker-compose.yml` e o `application.properties` já configuram as seguintes credenciais e banco de dados automaticamente:
* **Database:** `hackaton`
* **Usuário:** `root`
* **Senha:** `root`

O Spring Boot (via Hibernate) criará/atualizará as tabelas automaticamente graças à configuração `SPRING_JPA_HIBERNATE_DDL_AUTO: update`.

## Testando a API
Na pasta `postman` do projeto existe um arquivo `sus-helper.postman_collection.json`. Você pode importá-lo no seu Postman para ter acesso rápido aos endpoints já configurados (`POST`, `GET`, `PUT`, `DELETE`).

# Projeto Serasa-API

Este projeto é uma API desenvolvida com Spring Boot que gerencia informações de pessoas, incluindo CRUD (Create, Read, Update, Delete) e consulta por filtros. Além disso, ele integra com o serviço ViaCep para buscar dados de endereço com base no CEP fornecido.

## Funcionalidades

- **Gerenciamento de Pessoas**: Cadastro, atualização, consulta e exclusão de registros de pessoas.
- **Consulta Paginada**: Listagem de pessoas com suporte a filtros (nome, idade, cep) e paginação.
- **Integração com ViaCep**: Busca de informações de endereço com base no CEP fornecido.
- **Swagger Documentation**: A API está documentada com o Swagger, tornando o uso fácil e intuitivo.

## Endpoints

A API expõe os seguintes endpoints:

### `/pessoa`

- `GET`: Retorna uma lista de todas as pessoas.
- `POST`: Cria uma nova pessoa.

### `/pessoa/paginado`

- `GET`: Retorna uma lista paginada de pessoas, com suporte a filtros opcionais (nome, idade, cep).

### `/pessoa/byNome`

- `GET`: Retorna uma pessoa com base no nome.

### `/pessoa/{id}`

- `PUT`: Atualiza uma pessoa pelo ID.
- `DELETE`: Deleta uma pessoa pelo ID.

## Tecnologias Utilizadas

- **Spring Boot**: Framework Java para desenvolvimento de aplicações web e APIs RESTful.
- **Spring Data JPA**: Para acesso a dados e persistência no banco de dados.
- **Swagger**: Para documentação e testes da API.
- **RestTemplate**: Para integração com o serviço ViaCep.

## Como Rodar o Projeto

### Pré-requisitos

- Java 17 ou superior
- Maven ou Gradle para gerenciamento de dependências

### Passos para execução

1. Clone este repositório:
   ```bash
   git clone https://github.com/luizhenrique3651/serasa-api.git
   ```
2. Navegue até a pasta do projeto:
   ```bash
   cd serasa-api
   ```
3. Execute o projeto utilizando o Maven:
   ```bash
   mvn spring-boot:run
   ```
4. A API estará disponível em `http://localhost:8080`.

## Testes

Você pode testar a API diretamente utilizando o Swagger, acessível através de:

```
http://localhost:8080/swagger-ui/index.html#/
```

## TO-DO

há uma branch 'security-impl' onde há a implementação do login e controle de acesso, porém devido a diferenças de versões
em dependências não resolvidas à tempo, não foi possível concluir a implementação, porém a mesma está disponível na branch
para avaliações sem interferir nos testes das demais funcionalidades, na branch 'main'.

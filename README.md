
# CRUD de Usuários com Spring Boot

Este é um projeto de API REST para gerenciamento de usuários, desenvolvido com Spring Boot, MySQL e JPA. Ele inclui operações CRUD para usuários e upload de imagem de perfil.

## Funcionalidades

-   Criação, leitura, atualização e remoção de usuários.
    
-   Associação de permissões aos usuários.
    
-   Upload de imagem de perfil para usuários.
    
    

## Tecnologias Utilizadas

-   **Java 17**
    
-   **Spring Boot 3.x**
    
-   **Spring Data JPA**
    
-   **MySQL**
    
-   **Maven**
    
-   **Lombok**
    
-   **ModelMapper**
    
-   **Postman** (para testes)
    

## Configuração do Ambiente

1.  Clone este repositório:
    
    ```
    git clone https://github.com/bernardotonin/crud-usuarios-backend.git
    cd crud-usuarios-backend
    ```
    
2.  Configure o arquivo `.env` na raiz do projeto:
    
    ```
    DB_URL="jdbc:mysql://seu-url:suaporta/seu-banco"
    DB_USERNAME="seu-user"
    DB_PASSWORD="sua-senha"
    ```
    
3.  Certifique-se de que o banco de dados MySQL está rodando.
    
4.  Configure as dependências:
    
    ```
    mvn clean install
    ```
    
5.  Execute o projeto:
    
    ```
    mvn spring-boot:run
    ```
    
6.  Configurar o front-end do projeto.
    

## Estrutura do Projeto

-   `core`: Contém as classes principais como models, DTOs e configurações.
    
-   `repository`: Interfaces de acesso ao banco de dados.
    
-   `service`: Regras de negócio.
    
-   `controller`: Definição dos endpoints.
    

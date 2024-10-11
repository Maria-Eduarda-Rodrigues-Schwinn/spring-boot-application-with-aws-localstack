# Spring Boot Application with AWS LocalStack

![hackrocks](https://media4.giphy.com/media/v1.Y2lkPTc5MGI3NjExMDUzNnlydTR5dzVqMHB3OXNkNHVhczl1MXR0cHl5cmo0cWh0cWg0aiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/fc421YtZqhJMcKxJ6s/giphy.webp)

Esta é uma aplicação Spring Boot configurada para usar LocalStack para emular serviços AWS localmente.

## Requisitos

- Java 17
- Docker
- Docker Compose
- MySQL
- Postman

## Configuração

1. Clone o repositório:
   ```bash
   git clone https://github.com/Maria-Eduarda-Rodrigues-Schwinn/spring-boot-application-with-aws-localstack.git

2. Entre na pasta do LocalStack:
   ```bash
   cd spring-boot-application-with-aws-localstack/localstack

3. Inicie os containers do LocalStack:
   ```bash
   docker-compose up

4. Execute o script `localstack.sh` para configurar o LocalStack:
   ```bash
   ./localstack.sh

### Banco de Dados

Configure o banco de dados MySQL. Você pode precisar ajustar o usuário e a senha no arquivo de configuração
`/src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/rdsdatabase?useSSL=false&serverTimezone=UTC
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=password
```

- Para criar o banco de dados e a tabela **student**, execute o seguinte _SQL_:

````
CREATE DATABASE rdsdatabase;

USE rdsdatabase;

CREATE TABLE student (
    id_student INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    rg VARCHAR(9) NOT NULL,
    birth_date DATE,
    address VARCHAR(255) NOT NULL,
    cep VARCHAR(8) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(2) NOT NULL
);
````

## Executando a aplicação

1. Inicie a aplicação Spring Boot com o perfil LocalStack:

   ``mvn spring-boot:run -Dspring-boot.run.profiles=localstack``


2. Importe a coleção Postman dos endpoints:

   Navegue até `localstack/collection/` e importe o arquivo `Spring Boot + LocalStack.postman_collection.json` no
   Postman ou Insomnia.

**Testando Endpoints**

- Use o Postman ou Insomnia para testar os endpoints definidos na coleção importada.

### Bora! ☕🚀
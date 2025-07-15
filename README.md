# 📦 Async Order Processing Service

Este é um serviço Spring Boot para **gestão e processamento assíncrono de pedidos**,
com suporte a produtos, validação de entrada via DTOs e persistência em banco de 
dados H2 na memória. Os pedidos são processados em background usando uma pool de 
threads configurada via `ThreadPoolTaskExecutor`.

---

## 🚀 Funcionalidades

- ✅ Cadastro e listagem de produtos
- ✅ Criação de pedidos com múltiplos produtos e quantidades
- ✅ Processamento assíncrono de pedidos via `@Async`
- ✅ Banco H2 em memória com console acessível
- ✅ Logging estruturado com Logback (console + arquivos)
- ✅ Validação com Jakarta Bean Validation (JSR-380)

---

## 🧰 Tecnologias

- **Java 21**
- **Spring Boot 3.5.3**
- **Spring Web**
- **Spring Data JPA**
- **Spring Validation**
- **Hibernate 6**
- **H2 Database**
- **Lombok**
- **Logback**
- **Maven**

---

## 📁 Estrutura do Projeto
```
src/
├── main/
│ ├── java/
│ │ └── sd.softdeving.orders/
│ │ ├── config/ # Configuração de pool assíncrona
│ │ ├── controller/ # REST Controllers
│ │ ├── dto/ # DTOs de entrada
│ │ ├── entity/ # Entidades JPA
│ │ ├── repository/ # Interfaces JPA
│ │ ├── service/ # Serviços (negócio + assíncrono)
│ │ └── OrdersApplication.java
│ └── resources/
│ ├── application.properties
├── logback.xml
├── pom.xml
└── README.md
```

---

## 🔗 Endpoints da API

### 🧾 Pedidos (`/order`)

| Método | Endpoint                 | Descrição                            |
|--------|--------------------------|--------------------------------------|
| POST   | `/order`                 | Cria um novo pedido                  |
| GET    | `/order/{id}`            | Busca um pedido pelo ID              |
| POST   | `/order/process/{id}`    | Processa um pedido de forma assíncrona |

### 📦 Produtos (`/product`)

| Método | Endpoint         | Descrição                |
|--------|------------------|--------------------------|
| POST   | `/product`       | Cria um novo produto     |
| GET    | `/product`       | Lista todos os produtos  |
| GET    | `/product/{id}`  | Busca produto pelo ID    |
| DELETE | `/product/{id}`  | Remove produto pelo ID   |

---

## 📌 Exemplo de JSON para Pedido

```json
{
  "customer": "Gregório Nhantumbo",
  "productIds": [1, 2],
  "quantity": {
    "1": 3,
    "2": 1
  }
}
```
## Como Rodar
- **Pré-requisitos**
- **Java 21 instalado**
- **Maven 3.8+**

## Executar
```bash
mvn spring-boot:run
```
## Acesso
``` database
Aplicação: http://localhost:8080

Console H2: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

User: sa

Password: (em branco)
```

## ⚙️ Configuração do Executor Assíncrono

A pool de threads é configurada em AsyncConfig.java:

```java
executor.setCorePoolSize(5);
executor.setMaxPoolSize(10);
executor.setQueueCapacity(25);
executor.setThreadNamePrefix("OrderProcessing");
```
**Os pedidos são processados com:**
```java
@Async("threadPoolTaskExecutor")
public void processOrder(String orderId) {
    // lógica de processamento
}
```
## 🪵 Logging
Console e arquivos em /logs/

Configurado com logback.xml

Usa AsyncAppender para desempenho

**Exemplo de log:**
```pgsql
2025-07-13 12:07:13.386 [OrderProcessing1] INFO  OrderService - Processing order 1
2025-07-13 12:07:16.386 [OrderProcessing1] INFO  OrderService - Order 1 processed.
```
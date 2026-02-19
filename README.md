# Stock Service

Microserviço responsável pelo controle e reserva de estoque em uma arquitetura orientada a eventos (Event Driven Architecture).

O serviço consome o evento `order.created`, valida a disponibilidade do estoque, realiza a reserva e registra uma movimentação de estoque.

---

## Tecnologias Utilizadas

- Java 17
- Maven
- Spring Boot
  - Spring Data JPA
  - Spring AMQP
- PostgreSQL
- RabbitMQ
- CloudAMQP

---

## Arquitetura

Este serviço segue o padrão **Event Driven Architecture (EDA)**.

### Fluxo

Order Service → RabbitMQ (order.created) → Stock Service → Atualiza Estoque → Registra Movimentação

### Responsabilidades do Stock Service

- Consumir o evento `order.created`
- Validar disponibilidade de estoque
- Realizar reserva
- Atualizar quantidade disponível
- Registrar movimentação de estoque
- Garantir consistência do domínio de estoque

---

## Entidades

### Stock

Representa o estoque disponível de um produto.

- `id`
- `productId` (único, não pode ser nulo)
- `availableQuantity`

### Regras de Negócio

#### Validação de Reserva

- A quantidade deve ser maior que zero
- A quantidade solicitada não pode ser maior que a disponível

#### Atualização do Estoque

Após validação:

quantidadeDisponivel = quantidadeDisponivel - quantidade

---

### StockMovement

Registra a movimentação do estoque.

Campos:

- `productId`
- `orderId`
- `quantity`
- `status` (Enum)

Responsável por manter o histórico das operações realizadas no estoque.

---

## Consumo de Evento

O serviço consome mensagens da fila configurada:

Ao receber o evento:
- Valida o estoque
- Atualiza a quantidade disponível
- Cria uma movimentação de estoque

## Eventos Publicados

Estoque Reservado
---
Exchange:
- stock.exchange

Routing Key:
- stock.reserved

Payload:
```
{
  "orderId": 123
}
```

Falha na Reserva
---
Exchange:
- stock.exchange

Routing Key:
- stock.failed

Payload:
```
{
  "orderId": 123
}
```

Estratégia de Retry e Resiliência
---
O serviço implementa retry exponencial para falhas técnicas:
1s, 2s, 4s, 8s, 10s

- Após 5 tentativas, a mensagem é encaminhada para Dead Letter Queue (DLQ)
- Erros de regra de negócio não geram retry. Nestes casos, o serviço publica diretamente o evento stock.failed

### Configuração RabbitMQ
#### Propriedades:
- broker.exchange.order=order.exchange
- broker.queue.stock=stock.order.created.queue
- broker.routing.order-created=order.created

Configuração
- Criação da queue do Stock
- Binding da queue ao exchange order.exchange
- Routing key: order.created
- Conversão automática de JSON

O Stock Service atua como consumer, sendo responsável por:
- Criar sua própria queue
- Realizar o binding no exchange
- Processar as mensagens recebidas

Entidades:
- Stock
- StockMovement

Este serviço:
- Não recebe chamadas HTTP do Order
- Reage exclusivamente a eventos
- Processa mensagens de forma assíncrona
- Mantém baixo acoplamento entre os serviços

Como Executar
- Subir PostgreSQL
- Subir RabbitMQ (ou utilizar CloudAMQP)
- Garantir que o order.exchange exista
- Configurar application.properties
- Executar: mvn spring-boot:run

Considerações
- O serviço depende do evento order.created
- A consistência entre Order e Stock é eventual

> Autor: Thalys Henrique
https://www.linkedin.com/in/thalyshenrique7/

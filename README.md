# Cajuapp
___

### Como foi feito:
A solução do desafio foi construida utilizando:
- Quakus
- Kotlin
- Postgres
- Docker

### Dados iniciais:

Foi utilizado o `Flyway` para inserir dados iniciais de contas se utilizando das `migrations`.

| account_id | food_balance | meal_balance | cash_balance |
|------------|--------------|--------------|--------------|
| 123        | R$ 100.00    | R$ 50.00     | R$ 200.00    |
| 124        | R$ 100.00    | R$ 500.00    | R$ 200.00    |
| 125        | R$ 100.00    | R$ 100.00    | R$ 1000.00   |


## Como rodar:

O projeto foi dockerizado e disponibilizado sua imagem via `Docker Hub` para facilitar os testes, bem como uma massa de
dados inicial.
Por ter depenndência do postgres, foi criado na raiz do projeto um `docker-compose.yml` que contém uma imagem do
postgres e a imagem do projeto.
Portanto, bastando executar o comando abaixo:

```shell script 
docker compose up -d
```

## Como acessar:

Foi disponibilizado também para facilitar os testes, um endpoint do Swagger para serem enviados os payloads.

### <http://localhost:8080/swagger-ui/>

## Se preferir fazer via cURL:

```cURL
curl -X 'POST' \
  'http://localhost:8080/cajuapp/transaction' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
	"account": "123",
	"totalAmount": 10.00,
	"mcc": "5412",
	"merchant": "UBER TRIP               SAO PAULO BR"
}'
```

### Tetes

É importante lembrar também que o projeto é coberto por testes de integração que cobre todos os cenários abordados.
Foi utilizado ``testcontainers`` para emular o banco de dados durante a sua execução.
Para verificar os testes basta rodar:

```shell script
./gradlew quarkusTest
```

### Proposta de solução para o L4:

Optei no projeto por criar uma transação a nível de método, sendo assim, tendo um
``Bloqueio Pessimista a nível de escrita no banco de dados``.
Logo quando há concorrência de 2 transações uma ficará esperando até que a outra finalize.

> Prós:
> - Garantia de consistência (pois será processado uma trasação por vez)
> - Simples (feita por Notation no método)
> - Baixíssima latência (visto que o postgres suporta bem esse tipo de tratamento)

> Contras:
> - Problemas com uma taxa alta de concorrência (visto que as transações irão ficar enfileiradas)
 

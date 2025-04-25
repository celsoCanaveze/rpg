# RPG Store API
API para um mercado medieval onde personagens podem cadastrar, vender, comprar e trocar itens mágicos.

## CELSO CANAVEZE RM556118
## THIAGO MORENO MATHEUS RM554507

## Entidades

### Personagem
- **nome** (obrigatório)
- **classe** (guerreiro, mago, arqueiro)
- **nivel** (mínimo 1, máximo 99)
- **moedas** (saldo para comprar itens)

### Item
- **nome** (obrigatório)
- **tipo** (arma, armadura, poção, acessório)
- **raridade** (comum, incomum, raro, épico, lendário, místico, divino)
- **preco** (valor de venda no mercado)
- **dono** (relacionamento com Personagem)

## Endpoints

### Personagens
- `GET /api/personagens` - Lista todos os personagens (paginados)
- `GET /api/personagens/{id}` - Obtém um personagem específico
- `POST /api/personagens` - Cria um novo personagem
- `PUT /api/personagens/{id}` - Atualiza um personagem existente
- `DELETE /api/personagens/{id}` - Remove um personagem

### Filtros de Personagens
- `GET /api/personagens/por-nome?nome={nome}` - Busca personagens por nome
- `GET /api/personagens/por-classe?classe={classe}` - Busca personagens por classe

### Itens
- `GET /api/itens` - Lista todos os itens (paginados)
- `GET /api/itens/{id}` - Obtém um item específico
- `POST /api/itens?donoId={donoId}` - Cria um novo item (opcional associar a um dono)
- `PUT /api/itens/{id}?donoId={donoId}` - Atualiza um item existente (opcional alterar o dono)
- `DELETE /api/itens/{id}` - Remove um item

### Filtros de Itens
- `GET /api/itens/por-nome?nome={nome}` - Busca itens por nome parcial
- `GET /api/itens/por-tipo?tipo={tipo}` - Busca itens por tipo
- `GET /api/itens/por-raridade?raridade={raridade}` - Busca itens por raridade
- `GET /api/itens/por-preco?minPreco={min}&maxPreco={max}` - Busca itens por faixa de preço

### Transações
- `POST /api/transacoes/comprar/{itemId}/personagem/{personagemId}` - Personagem compra um item
- `POST /api/transacoes/vender/{itemId}/personagem/{personagemId}` - Personagem vende um item
- `POST /api/transacoes/trocar?personagem1Id={id1}&item1Id={itemId1}&personagem2Id={id2}&item2Id={itemId2}` - Troca de itens entre personagens

## Executando o projeto

1. Clone o repositório
2. Execute `./mvnw spring-boot:run`
3. Acesse `http://localhost:8080`

## Banco de Dados

O projeto utiliza H2 Database em memória. O console do H2 pode ser acessado em `http://localhost:8080/h2-console` com as seguintes credenciais:
- JDBC URL: `jdbc:h2:mem:rpgdb`
- Username: `sa`
- Password: (vazio)

## Tecnologias

- Spring Boot 3.4.5
- Spring Data JPA
- H2 Database
- Lombok
- Java 17

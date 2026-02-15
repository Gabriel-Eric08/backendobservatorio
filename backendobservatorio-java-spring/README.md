# Backend Observatório

## 📋 Visão Geral

O Backend Observatório é feita em Spring Boot que fornece uma API REST para:
- **Gerenciamento de DataSets**: Criar, listar e baixar arquivos CSV de datasets
- **Gerenciamento de Usuários**: Registrar novos usuários no sistema
- **Armazenamento de Arquivos**: Salvar e gerenciar arquivos CSV de datasets

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas:

```
├── api/
│   ├── controller/          # Controladores REST
│   ├── dto/                 # Data Transfer Objects
│   └── mapper/              # Mapeadores (DTO → Domain)
├── domain/
│   ├── model/               # Entidades de domínio
│   └── repository/          # Interfaces de repositório
├── infrastructure/
│   └── persistence/
│       ├── adapter/         # Adaptadores de repositório
│       ├── entity/          # Entidades JPA
│       ├── repository/      # Repositórios Spring Data JPA
│       └── utils/           # Utilitários (Ex: Conversores)
├── usecase/                 # Casos de uso (Lógica de negócio)
├── config/                  # Configurações da aplicação
└── BackendobservatorioApplication.java  # Classe principal
```

## 🚀 Endpoints da API

### DataSet Endpoints

#### 1. Criar um novo Dataset
**POST** `/dataset`

**Content-Type**: `multipart/form-data`

**Parâmetros:**
- `file` (File, obrigatório): Arquivo CSV
- `data` (String JSON, obrigatório): Dados do dataset

**Requisição (Exemplo):**
```json
{
  "titulo": "Dados de Mulheres em Política",
  "tema": "Política",
  "orgao": "Câmara dos Deputados",
  "contato": "contato@camara.gov.br",
  "descricao": "Dataset com informações sobre mulheres em cargos políticos",
  "url": "/uploads/arquivo.csv",
  "periodo_inicial": "2023-01",
  "periodo_final": "2024-12"
}
```

**Respostas:**
- **200 OK**: `"DataSet registrado com sucesso!"`
- **400 Bad Request**: 
  - `"Arquivo vazio"`
  - `"Tipo de arquivo inválido. Apenas arquivos CSV são permitidos."`
  - `"JSON inválido"`

---

#### 2. Listar todos os Datasets
**GET** `/dataset/all`

**Resposta (200 OK):**
```json
[
  {
    "id": 1,
    "titulo": "Dados de Mulheres em Política",
    "tema": "Política",
    "orgao": "Câmara dos Deputados",
    "contato": "contato@camara.gov.br",
    "descricao": "Dataset com informações sobre mulheres em cargos políticos",
    "url": "uploads/Dados de Mulheres em Política2023-012024-12.csv",
    "periodo_inicial": "2023-01",
    "periodo_final": "2024-12"
  }
]
```

---

#### 3. Baixar Dataset por ID
**GET** `/dataset/download/{id}`

**Parâmetros:**
- `id` (Path): ID do dataset

**Respostas:**
- **200 OK**: Arquivo CSV (Content-Type: text/csv)
- **404 Not Found**: Dataset não encontrado
- **400 Bad Request**: Erro ao processar arquivo

---

### User Endpoints

#### 1. Criar um novo Usuário
**POST** `/users`

**Content-Type**: `application/json`

**Requisição:**
```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "senha123",
  "roleId": 1
}
```

**Resposta (200 OK):**
```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "senha123",
  "roleId": 1,
  "createdAt": "2026-02-06"
}
```

---

## 🔧 Como Executar

### Pré-requisitos
- Java 21+
- Maven 3.6+
- SQLite (automático via Hibernate)

### Passos

1. **Clonar o repositório**
```bash
git clone <seu-repositorio>
cd backendobservatorio
```

2. **Instalar dependências**
```bash
mvn clean install
```

3. **Executar a aplicação**
```bash
mvn spring-boot:run
```

4. **Acessar a aplicação**
- API: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI Docs: `http://localhost:8080/v3/api-docs`

---

## 📦 Dependências Principais

- **Spring Boot 3.5.10**
- **Spring Data JPA**: ORM e persistência de dados
- **Spring Web**: Framework REST
- **Spring Validation**: Validação de dados
- **SQLite**: Banco de dados
- **Hibernate**: JPA Provider
- **Jackson**: Serialização JSON
- **Lombok**: Redução de boilerplate
- **SpringDoc OpenAPI**: Documentação Swagger/OpenAPI

---

## 🗄️ Banco de Dados

### Tabela: dataset
```sql
CREATE TABLE dataset (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  titulo VARCHAR(255) NOT NULL,
  tema VARCHAR(255) NOT NULL,
  orgao VARCHAR(255) NOT NULL,
  contato VARCHAR(255) NOT NULL,
  descricao VARCHAR(255) NOT NULL,
  url VARCHAR(255) NOT NULL,
  periodo_inicial VARCHAR(7) NOT NULL,  -- formato: yyyy-MM
  periodo_final VARCHAR(7) NOT NULL     -- formato: yyyy-MM
);
```

### Tabela: user
```sql
CREATE TABLE user (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  senha VARCHAR(255) NOT NULL,
  role_id INT NOT NULL,
  created_at DATE NOT NULL
);
```

---

## ⚠️ Notas Importantes

1. **Formato de Data**: Use sempre o formato `yyyy-MM` para `periodo_inicial` e `periodo_final` (ex: `2024-02`)

2. **Tipo de Arquivo**: Apenas arquivos CSV (.csv) são aceitos para upload


## 🔐 Melhorias Futuras

- [ ] Adicionar autenticação JWT
- [ ] Implementar criptografia de senhas (BCrypt)
- [ ] Validar email único para usuários
- [ ] Adicionar paginação nos endpoints de listagem
- [ ] Implementar filtros avançados para datasets
- [ ] Adicionar testes unitários e de integração
- [ ] Implementar rate limiting
- [ ] Adicionar logging estruturado
- [ ] Configurar CORS para produção
- [ ] Adicionar soft delete para datasets
- [ ] Melhor tratamento de erros
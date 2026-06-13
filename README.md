<h2>
  <img src="https://cdn.simpleicons.org/task" width="20" />
  TaskAuto - Backend (API)
</h2>

Este é o repositório do backend para a aplicação **TaskAuto**. Trata-se de um microsserviço RESTful construído com **Java e Spring Boot**, responsável por gerenciar o armazenamento das anotações e a integração direta com o motor de Inteligência Artificial para processamento de linguagem natural. O projeto foi desenhado para rodar localmente de forma simples, sem necessidade de autenticação.

---

## Arquitetura e Funcionalidades

- **Uso Local sem Autenticação:** A aplicação roda localmente na sua máquina, garantindo privacidade dos dados, e não exige criação de conta ou login.
- **Processamento NLP:** Integração com um servidor local Ollama (rodando LLMs) para estruturar textos não-formatados recebidos do usuário em Tarefas, Metas ou Relatórios Diários com datas inferidas e ações detalhadas.
- **Gerenciamento de Entidades:** CRUD completo para Grupos (Pastas) e Notas.

---

## Tecnologias e Stack

- **Linguagem:** Java 17+
- **Framework Principal:** Spring Boot 3
- **Banco de Dados:** PostgreSQL (via Docker)
- **Integração de IA:** Ollama rodando localmente no Docker
- **Gerenciamento de Build:** Gradle

---

## Requisitos e Configuração (Docker)

Independente da opção de execução escolhida abaixo, o sistema depende de um Banco de Dados e da IA Ollama. Nós fornecemos um arquivo `docker-compose.yml` para subir esses serviços facilmente.

**Pré-requisitos iniciais:**
1. Ter o **Docker** e o **Docker Compose** instalados na sua máquina.
2. Na raiz do projeto, crie um arquivo `.env` com as seguintes variáveis de exemplo (ou utilize as suas):
   ```env
   POSTGRES_USERNAME=postgres
   POSTGRES_PASSWORD=sua_senha_aqui
   OLLAMA_IA=gemma:2b
   ```

**Subindo a infraestrutura:**
Na pasta onde está o arquivo `docker-compose.yml`, rode:
```bash
docker-compose up -d
```

> [!WARNING]
> **OLLAMA SETUP:**
> Ao subir o Docker Compose, o contêiner `ollama-setup` começará a fazer o download (pull) do modelo de IA definido na variável `OLLAMA_IA`. **Você precisa aguardar este contêiner finalizar o download para que o sistema funcione corretamente.**
> 
> Você pode acompanhar o progresso verificando os logs do Docker:
> `docker logs -f ollama-setup`
> 
> Quando o log informar que o pull foi concluído (ou o contêiner encerrar com sucesso), a infraestrutura estará pronta!

---

## Opções de Execução

Você pode rodar a aplicação de duas maneiras, dependendo da sua familiaridade com Java:

### Opção 1: Via Arquivo Executável `.jar` (Mais fácil)
Ideal para usuários que não querem configurar um ambiente de desenvolvimento completo.

1. Instale o **Java 17 (ou superior)** na sua máquina.
2. Faça o download do arquivo `.jar` gerado na página de Releases (ou recebido diretamente).
3. Abra o terminal na pasta onde o `.jar` foi baixado e execute:
   ```bash
   java -jar TaskAuto-0.0.1.jar
   ```
4. A API iniciará na porta **8080** (`http://localhost:8080`).

### Opção 2: Clonando e Rodando pelo Código (Para Desenvolvedores)
Ideal se você deseja modificar o código ou contribuir.

1. Instale o **Java Development Kit (JDK) 17+**.
2. Clone este repositório:
   ```bash
   git clone https://github.com/emnuelht/taskauto-api.git
   cd taskauto-api
   ```
3. (Certifique-se de que o Docker já está rodando os serviços, como explicado acima).
4. Rode a aplicação utilizando o wrapper do Gradle:

   **No Windows:**
   ```cmd
   gradlew bootRun
   ```

   **No Linux/Mac:**
   ```bash
   ./gradlew bootRun
   ```
5. O servidor iniciará nativamente na porta **8080** (`http://localhost:8080`).

---

## Endpoints Principais

A API é prefixada com `/api` e não necessita de tokens de autorização (`Authorization` header).

**Grupos:**
- `POST /api/group` - Cria um novo grupo.
- `GET /api/groups` - Lista todos os grupos.
- `PUT /api/group` - Atualiza as informações do grupo.
- `DELETE /api/group/{groupId}` - Remove um grupo.

**IA e Notas:**
- `POST /api/input` - Envia texto livre para processamento da IA e retorna um objeto estruturado.
- `POST /api/note` - Salva a nota estruturada no banco de dados.
- `GET /api/notes/{groupId}` - Retorna as notas pertencentes a um grupo específico.
- `PUT /api/note/{noteId}` - Edita uma nota.
- `DELETE /api/note/{noteId}` - Remove uma nota do banco.

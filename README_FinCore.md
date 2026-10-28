# FinCore

> API backend para uma plataforma de gestão financeira, contábil e empresarial.

O **FinCore** é um projeto de portfólio desenvolvido para praticar Engenharia de Software e construir, por etapas, a base de um ERP voltado a micro e pequenas empresas. A implementação atual concentra-se no backend, especialmente autenticação, segurança e gerenciamento de usuários.

> **Status:** em desenvolvimento. Os módulos financeiros, contábeis, dashboard e frontend ainda fazem parte do planejamento e não devem ser considerados funcionalidades concluídas.

## Funcionalidades implementadas no backend

- Cadastro e login de usuários por endpoints de autenticação.
- Geração de token JWT após autenticação.
- Criptografia de senhas com `PasswordEncoder`.
- Operações CRUD de usuários: criar, listar, consultar por ID, atualizar e excluir.
- DTOs para separar dados de entrada e saída da API.
- Validação de dados com Jakarta Validation.
- Tratamento global de exceções e exceções específicas para usuário não encontrado e e-mail duplicado.
- Persistência com Spring Data JPA.
- Documentação OpenAPI/Swagger configurada.
- Testes automatizados iniciais com JUnit e Mockito.
- Configuração de execução local com H2 e arquivos de configuração para MySQL/Docker.

## Tecnologias

| Área | Tecnologias |
|---|---|
| Linguagem | Java 21 |
| Backend | Spring Boot, Spring Web MVC |
| Persistência | Spring Data JPA, Hibernate |
| Segurança | Spring Security, JWT (`java-jwt`), BCrypt via `PasswordEncoder` |
| Validação | Jakarta Validation |
| Banco local | H2 em memória |
| Banco para ambiente Docker | MySQL 8.4 (configuração incluída) |
| Documentação da API | Springdoc OpenAPI / Swagger UI |
| Testes | JUnit, Mockito, Spring Boot Test |
| Containerização | Docker, Docker Compose |
| Build | Maven Wrapper |

## Endpoints disponíveis

### Autenticação

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/auth/register` | Registrar usuário para autenticação |
| `POST` | `/auth/login` | Autenticar usuário e obter token JWT |

### Usuários

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/users` | Listar usuários |
| `GET` | `/users/{id}` | Consultar usuário por ID |
| `POST` | `/users` | Criar usuário |
| `PUT` | `/users/{id}` | Atualizar usuário |
| `DELETE` | `/users/{id}` | Excluir usuário |

Os endpoints protegidos devem ser acessados conforme as regras definidas na configuração de segurança. Para os formatos exatos dos corpos JSON e respostas, consulte os DTOs no código e a documentação OpenAPI.

## Executar localmente

### Pré-requisitos

- JDK 21 instalado.
- Git (opcional, para clonar o repositório).

### 1. Obter o projeto

```bash
git clone https://github.com/MarlonMachado96/FinCore.git
cd FinCore/fincore
```

> Se o nome ou a organização das pastas do repositório estiver diferente, entre na pasta que contém o arquivo `pom.xml`.

### 2. Iniciar a aplicação

No Windows (PowerShell):

```powershell
.\mvnw.cmd spring-boot:run
```

No Linux/macOS:

```bash
./mvnw spring-boot:run
```

A configuração padrão usa um banco H2 em memória. Quando a aplicação iniciar, tente acessar:

- API: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- Console H2: `http://localhost:8080/h2-console`

A porta pode variar conforme a configuração efetiva da aplicação. O console H2 é destinado apenas ao desenvolvimento local.

## Executar os testes

No Windows (PowerShell):

```powershell
.\mvnw.cmd test
```

No Linux/macOS:

```bash
./mvnw test
```

## Estrutura principal

```text
fincore/
├── src/
│   ├── main/
│   │   ├── java/com/fincore/
│   │   │   ├── config/       # Segurança, JWT e OpenAPI
│   │   │   ├── controller/   # Endpoints REST
│   │   │   ├── dto/          # Objetos de entrada e saída
│   │   │   ├── entitie/      # Entidades e enums
│   │   │   ├── exception/    # Exceções e handler global
│   │   │   ├── repository/   # Persistência
│   │   │   └── service/      # Regras de negócio
│   │   └── resources/        # Configurações da aplicação
│   └── test/                 # Testes automatizados
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── mvnw / mvnw.cmd
```

## Roadmap

- [x] Estrutura inicial do backend Spring Boot
- [x] Entidade e operações CRUD de usuários
- [x] DTOs e validações
- [x] Autenticação e geração de JWT
- [x] Configuração de segurança
- [x] Tratamento global de exceções
- [x] Configuração OpenAPI/Swagger
- [x] Testes automatizados iniciais
- [ ] Revisar e ampliar a cobertura dos testes
- [ ] Revisar configuração e execução via Docker Compose
- [ ] Implementar autorização por perfis e permissões conforme os requisitos do sistema
- [ ] Criar frontend em React e TypeScript
- [ ] Desenvolver dashboard e cadastro de empresas
- [ ] Implementar módulos financeiro e contábil
- [ ] Criar relatórios e indicadores
- [ ] Avaliar recursos de inteligência artificial
- [ ] Preparar deploy e documentação de produção

## Objetivo do projeto

Evoluir o backend atual para uma plataforma ERP modular, com foco em organização do código, segurança, persistência, testes e documentação. Os módulos futuros serão implementados gradualmente e registrados neste README conforme forem concluídos.

## Autor

**Marlon Machado** — estudante de Engenharia de Software.

- GitHub: [@MarlonMachado96](https://github.com/MarlonMachado96)
- LinkedIn: [Marlon Machado](https://www.linkedin.com/in/marlon-machado-b51064233/)

---

Projeto educacional e de portfólio. A configuração atual não deve ser usada em produção sem revisão de segurança, credenciais, autorização, variáveis de ambiente e configuração de infraestrutura.

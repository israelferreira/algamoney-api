# Algamoney-api<br>
Esse repositório contém os arquivos do **back-end** do projeto Algamoney, construído e ensinado pela [AlgaWorks](https://www.algaworks.com/) no curso **Fullstack Angular e Spring**.<br>O Algamoney é um software para controle e acompanhamento financeiro de receitas e despesas.<br>

[**Veja também o repositório com a API do front-end.**](https://github.com/israelferreira/algamoney-ui)

<br>Ele foi desenvolvido com as seguintes tecnologias:

 - Back-end: **API REST** com o framework **Spring Boot** 2.4 no **Java** 11
 - Front-end: Single-Page Application (SPA) com **Angular** 10
 - Biblioteca de componentes **PrimeNG** na interface de usuário
 - Autenticação e autorização de usuário com **OAuth 2** e **JWT** (JSON Web Token)
 - Banco de dados **MySQL** 8
 - **Flyway** (migrações do banco de dados)
 - Jaspersoft **JasperReports** (relatórios em PDF)
 - Apache **Maven** (gerenciador de projetos e dependências)
 - Node.js e NPM (Node Package Manager) para controle de dependências e building do front-end.
 
 ### Funcionalidades do software
-  Categorias de lançamento
      - Buscar todas as categorias cadastradas
      -  Buscar uma categoria pelo código
      -  Criar uma nova categoria

 -   Pessoas
    
	    -   Buscar todas as pessoas cadastradas
	    -  Pesquisar pessoas pelo nome
	    -   Buscar uma pessoa pelo código
	    -   Cadastrar uma nova pessoa
	    -   Remover uma pessoa usando o código
	    -   Atualizar os dados de uma pessoa
	    
 -   Lançamentos financeiros
    
	  -    Buscar lançamentos
	    -  Buscar lançamentos pela descrição e data de vencimento
	    -  Buscar um lançamento pelo código
	    -  Criar um novo lançamento
	    -  Remover um lançamento
	    -  Atualizar um lançamento

- Relatórios em PDF com informações de lançamentos
- Envio de e-mail automático para lembrete de lançamentos próximos da data de vencimento
- Segurança
	- Registrar usuários pelo banco de dados com diferentes privilégios de acessos às funcionalidades

### Softwares usados no desenvolvimento

* [OpenJDK 11 (LTS) - JVM Eclipse OpenJ9](https://adoptopenjdk.net)
* [Eclipse IDE 2020-09 for Enterprise Java](https://www.eclipse.org/downloads/packages)
* [MySQL Community Server 8.0](https://dev.mysql.com/downloads/mysql)
* [Postman 7.36](https://www.postman.com/downloads)
* [Jaspersoft Studio CE 6.12](https://community.jaspersoft.com/project/jaspersoft-studio/releases)

 ### URLs

|  URL |  Método | Descrição |
|----------|--------------|--------------|
|`http://localhost:8080/algamoney-api/oauth/token`                             | POST | Solicita Access Token |
|`http://localhost:8080/algamoney-api/oauth/token`                             | POST | Solicita Refresh Token |
|`http://localhost:8080/algamoney-api/tokens/revoke`                           | DELETE | Revoga o token atual |
|`http://localhost:8080/algamoney-api/people/`                                 | GET | Retorna todas as pessoas registradas no banco (com paginação) |
|`http://localhost:8080/algamoney-api/people/`                                 | POST | Registra uma pessoa |
|`http://localhost:8080/algamoney-api/people/{id}`                              | GET | Retorna o registro de uma pessoa baseada no ID dela |
|`http://localhost:8080/algamoney-api/people/{id}`                              | DELETE | Deleta o registro de uma pessoa baseada no ID dela |
|`http://localhost:8080/algamoney-api/people/{id}`                              | PUT | Atualiza o registro de uma pessoa baseado no ID dela |
|`http://localhost:8080/algamoney-api/people/{id}/active`                              | PUT | Atualiza a propriedade ativo de uma pessoa baseada no ID dela, no corpo da requisição é preciso enviar um JSON com true ou false |
|`http://localhost:8080/algamoney-api/categories`                              | GET | Retorna todas as categorias |
|`http://localhost:8080/algamoney-api/categories`                              | POST | Registra uma categoria |
|`http://localhost:8080/algamoney-api/categories/{id}`                           | GET | Retorna uma categoria baseado no ID |
|`http://localhost:8080/algamoney-api/journalentries`                             | POST | Registra uma lançamento |
|`http://localhost:8080/algamoney-api/journalentries`                             | GET  | Retorna todos os lançamentos (com paginação) |
|`http://localhost:8080/algamoney-api/journalentries/{id}`                          | GET | Retorna um lançamento baseado no ID |
|`http://localhost:8080/algamoney-api/journalentries/{id}`                          | DELETE | Deleta um lançamento baseado no ID |
|`http://localhost:8080/algamoney-api/journalentries?summary`           | GET | Retorna lançamentos os lançamentos resumidos (o resultado só retorna o nome da pessoa ao invés do registro completo com endereço) |
|`http://localhost:8080/algamoney-api/journalentries?description={searchparam}&dueDateFrom=YYYY-MM-DD&dueDateTo=YYYY-MM-DD`                       | GET| Faz uma pesquisa nos lançamentos com os parâmetros inseridos na URL(descrição e intervalos de data de vencimento) |
|`http://localhost:8080/algamoney-api/journalentries/statistics/by-day`        | GET | Retorna estatísticas de lançamento baseado em um período de tempo |
|`http://localhost:8080/algamoney-api/journalentries/statistics/by-category`  | GET | Retorna estatísticas de lançamento baseado em categorias |
|`http://localhost:8080/algamoney-api/journalentries/reports/by-person?begin={YYYY-MM-DD}&end={YYYY-MM-DD}`                                                  | GET | Retorna um relatório em PDF baseado no período de tempo informado na URL  |
|`http://localhost:8080/algamoney-api/states`                                 | GET  | Retorna todos os estados cadastrados no banco de dados |
|`http://localhost:8080/algamoney-api/cities?state={id}` | GET | Retorna uma lista de cidades baseada no ID do estado passado por parâmetro |

---
## Como executar o projeto
Edite o arquivo `application.properties` em `algamoney-api/src/main/resources` com as informações necessárias correspondentes às configurações da sua máquina (usuário/senha do banco de dados e também do provedor de email para envio automático do sistema).
O projeto foi construído com a IDE Eclipse. Para executá-lo:

1.  Faça o Download do zip do projeto ou clone o repositório Git
2.  Extraia o conteúdo do zip
3.  Abra o Eclipse IDE
4.  Importe o projeto baixado: Vá em File > Open Projects from File System. Selecione a pasta pela opção "Directory" e pressione Finish.
5.  Espere o Maven baixar todas as dependências.
6.  Abra a classe java "AlgamoneyApiApplication" e execute o método main.
7.  O projeto irá ser executado.
8.  Para testar os recursos das URLs acima, use alguma ferramenta de testes de API, como o **Postman**; ou execute o cliente do front-end.

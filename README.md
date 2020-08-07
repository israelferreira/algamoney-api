
# Algamoney-api<br>
Esse repositório contém os arquivos do **back-end** do projeto Algamoney, construído e ensinado pela [AlgaWorks](https://www.algaworks.com/) no curso **Fullstack Angular e Spring**.<br>O Algamoney é um software para controle e acompanhamento financeiro de receitas e despesas.<br>

[**Veja também o repositório com a API do front-end.**](https://github.com/israelferreira/algamoney-ui)

<br>Ele foi desenvolvido com as seguintes tecnologias:

 - Back-end: **API REST** com o framework **Spring Boot** 2.3 no **Java** 8
 - Front-end: Single-Page Application (SPA) com **Angular** 10
 - Biblioteca de componentes **PrimeNG** na interface de usuário
 - Autenticação e autorização de usuário com **OAuth 2** e **JWT** (JSON Web Token)
 - Banco de dados **MySQL** 8
 - **Flyway** (migrações do banco de dados)
 - Jaspersoft **JasperReports** (relatórios em PDF)
 - Apache **Maven** (gerenciador de projetos e dependências)
 - Node.js e NPM (Node Package Manager) para controle de dependências e building do front-end.
 
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
 
## Como executar o projeto
O projeto da Algaworks foi pensado para ser empacotado no formato jar e executado em servlet container, como o Jetty ou Tomcat. No meu projeto eu o adaptei para a build ser feita no formato WAR e executar no servidor de aplicação JBoss WildFly 19.

Edite o arquivo `application.properties` em `algamoney-api/src/main/resources` com as informações necessárias correspondentes às configurações da sua máquina (usuário/senha do banco de dados e também do provedor de email para envio automático do sistema).
O projeto foi construído com a IDE Eclipse. Para executá-lo:

1.  Faça o Download do zip do projeto ou clone o repositório Git
2.  Extraia o conteúdo do zip
3.  Abra o Eclipse IDE
4.  Importe o projeto baixado
5.  Selecione o projeto, clique com o botão direito do mouse, clique em Run As > Run on Server > Selecione o WildFly.
6.  O projeto irá ser executado.
7. Para testar os recursos das URLs acima, use alguma ferramenta de testes de API, como o [Postman](https://www.postman.com/); ou execute o cliente do front-end.

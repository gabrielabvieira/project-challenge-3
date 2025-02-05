# DESAFIO 3 - SEMANA 12
#### Gabriela Brito Vieira
&nbsp;
&nbsp;
---
### 1. Necessidade:

Uma Empresa precisa de um sistema para que seus funcionários possam propor melhorias internas. 
Essas propostas de melhorias, poderão ser votadas pelos próprios funcionários.


### 2. Premissas
O desafio foi desenvolvido com as seguintes premissas:

- Projeto: Maven 
- Linguagem: JAVA 17
    - Framework: Sring Boot 3.0.11
    - Packaging: Jar
 - Base de Dados: H2 Persistence
 - Uso de API Gateway
 - Serviços registrados no EUREKA

&nbsp;

---
### 3. Escopo do Desafio 

*3.1 - Desenvolvimento de Serviços*
Foi desenvolvido micro serviços em Rest para o consumo dos sistemas internos da Empresa. 
Os serviços possuem as seguintes funções:

  * Registrar Funcionário.
  * Registrar Proposta de Melhoria.
  * Criar sessões de Votação das Propostas de Melhoria. 
  * Receber votos dos Funcionários registrados.
  * Contabilizar os Votos

&nbsp;
*3.2 - Tratamento de Erros*
Foi aplicada validações sobre as informações transistadas entre os serviços, também foi desenvolvido tratamentos de erros com base nas regras de Regras de Negócios definidas no projeto.
 
*3.3 - Balanceamento de Caraga*
Foi aplicada balanceamento de Carga em todos os serviços através do *API Gateway*.

*3.4 - Testes Unitários*
Foi executado testes unitários através dos serviços *Mockito* e *Junit*.

&nbsp;

---
### 4. Detalhes das Funcionalidades 

##### 4.1 - Micro Service Employees - Funcionarios
Foi desenvolvido um micro serviço para o gerenciamento das informações de Funcionários.

###### 4.1.1 - Salvar Funcionário
O OBJETO tem como objetivo registrar um funcionário.

| POST |URL: http://localhost:8080/api/v1/employees|
| ------ | ------ |
---
Entrada de Dados: JSON
| Campo | Descrição |Tipo |
| ------ | ------ |----|
| nome | Nome do Funcionário | Texto|
| cpf | CPF do Funcionário |Texto|
| idade | Idade do Funcionário | Inteiro |

Resultado Esperado: No Body

| Status | 
| ------ |
| 200 - OK | 
| 500 - Internal Server Error |

*REGRAS DE NEGÓCIO APLICADAS:*
1. Verifica se todos os campos estão preenchidos.
2. Se todos os campos estão preenchidos, seguir com a próxima validação.
3. Se algum dos campos não estiver preenchido, apresetnar erro 500.
4. Verifica se CPF Já existe.
5. Se CPF não existe, registra a pessoa.
6. Se CPF já existe, apresenta erro 500.

&nbsp;

###### 4.1.2 - Dados do Funcionáro
O OBJETO tem como objetivo devolver dados de um funcionário.

| GET |URL: http://localhost:8080/api/v1/employees?cpf=12345678901|
| ------ | ------ |
---
Entrada de Dados: Query
| Campo | Descrição |Tipo |
| ------ | ------ |----|
| cpf | CPF do Funcionário |Texto|


Resultado Esperado: JSON
>200 - OK

| Campo | Descrição |Tipo |
| ------ | ------ |----|
| id | Nome do Funcionário | Inteiro|
| nome | Nome do Funcionário | Texto|
| cpf | CPF do Funcionário |Texto|
| idade | Idade do Funcionário | Inteiro |

> 404 - Not Found
> 500 - Internal Server Error


*REGRAS DE NEGÓCIO APLICADAS:*
1. Verifica so campo CPF está preenchido.
2. Se CPF está preenchidos, seguir com a próxima validação.
3. Se CPF não está preenchidos, apresetnar erro 500.
4. Verifica se CPF  existe na base.
5. Se CPF existe, mostra os resultado.
6. Se CPF não existe, apresenta erro 404.



&nbsp;

###### 4.1.3 - GET - Status
O OBJETO tem como objetivo testar balancemento de carga, onde é aberto várias instâncias no teminal e executado as requisições para identificar em quais instâncias elas estão sendo direcionadas.

| POST | URL: http://localhost:8080/api/v1/employees|
| ------ | ------ |
---
Entrada de Dados: Query
| Campo | Descrição |Tipo |
| ------ | ------ |----|
| cpf | CPF do Funcionário |Texto|


Resultado Esperado: JSON
>200 - OK

| Campo | Descrição |Tipo |
| ------ | ------ |----|
| id | Código do Funcionário | Inteiro|
| nome | Nome do Funcionário | Texto|
| cpf | CPF do Funcionário |Texto|
| idade | Idade do Funcionário | Inteiro |

> 500 - Internal Server Error


*REGRAS DE NEGÓCIO APLICADAS:*
1. Verifica so campo CPF está preenchido.
2. Se CPF está preenchidos, seguir com a próxima validação.
3. Se CPF não está preenchidos, apresetnar erro 500.
4. Verifica se CPF  existe na base.
5. Se CPF existe, mostra os resultado.
6. Se CPF não existe, apresenta erro 500.

&nbsp;

##### 4.2 - Micro Service Proposals - Propostas
Foi desenvolvido um micro serviço para o gerenciamento de Propostas.

###### 4.2.1 - Salvar Proposta
O OBJETO tem como objetivo registrar uma proposta.

| POST |URL:http://localhost:8080/api/v1/proposals|
| ------ | ------ |
---
Entrada de Dados: JSON
| Campo | Descrição |Tipo |
| ------ | ------ |----|
| nome | Nome da Proposta | Texto|
| descricao | Descrição da Proposta do Funcionário | Texto |
| cpf | CPF do Funcionário |Texto|


Resultado Esperado: JSON
>200 - OK

| Campo | Descrição |Tipo |
| ------ | ------ |----|
| id | Código da Proposta | Inteiro|

*REGRAS DE NEGÓCIO APLICADAS:*
1. Verifica se todos os campos estão preenchidos.
2. Se todos os campos estão preenchidos, seguir com a próxima validação.
3. Se algum dos campos não estiver preenchido, apresetnar erro 500.
4. Verifica se CPF existe na base.
5. Se CPF existe, registra a proposta.
6. Se CPF não existe, apresenta erro 500.

&nbsp;

###### 4.2.2 - Dados da Proposta
O OBJETO tem como objetivo devolver dados de uma Proposta.

| GET |URL: http://localhost:8080/api/v1/proposals?id=1|
| ------ | ------ |
---
Entrada de Dados: Query
| Campo | Descrição |Tipo |
| ------ | ------ |----|
| id | Código da Proposta |Inteiro|


Resultado Esperado: JSON
>200 - OK

| Campo | Descrição |Tipo |
| ------ | ------ |----|
| id | Código da Prposta | Inteiro|
| nome | Nome da Proposta | Texto|
| descricao | Descrição da Proposta | Texto|
| cpf | CPF do Funcionário do Funcionário Criador da Proposta |Texto|
| dataCriacao | Data Criação da Proposta | Data |

> 500 - Internal Server Error


*REGRAS DE NEGÓCIO APLICADAS:*
1. Verifica so campo id está preenchido.
2. Se id está preenchidos, seguir com a próxima validação.
3. Se id não está preenchidos, apresetnar erro 500.
4. Verifica se id  existe na base.
5. Se id existe, mostra os resultado.
6.  Se id não existe, apresenta erro 500.



&nbsp;


##### 4.3 - Micro Service Voting Session - Sessão de Votação
Foi desenvolvido um micro serviço para o gerenciamento das Sessões de Votação.

###### 4.3.1 - Cadastra Sessão de Votação
O OBJETO tem como objetivo registrar uma Sessão de Votação.

| POST |URL:http://localhost:8080/api/v1/voting-session|
| ------ | ------ |
---
Entrada de Dados: JSON
| Campo | Descrição |Tipo |
| ------ | ------ |----|
| idProposta | Código da Proposta | Inteiro|
| tempoSessao | Tempo da Sessão em Minutos | Inteiro |
| cpfFuncionario | CPF do Funcionário |Texto|


Resultado Esperado: JSON
>200 - OK

| Campo | Descrição |Tipo |
| ------ | ------ |----|
| id | Código da Sessão | Inteiro|
| idProposta | Código da Proposta | Inteiro|
| cpfFuncionario | CPF do Funcionário que Criou a Sessão | Texto |
| tempoSessao | Tempo final da Sessão de Votação | Data|

> 500 - Internal Server Error

*REGRAS DE NEGÓCIO APLICADAS:*

1. Verifica se o campo Tempo da Sessão está preenchuido
2. Se tempo estiver preenchido, seguir para a próxima validação.
3. se tempo não estiver preenchido, usar valor igual a 1 para o tempo da sessão e seguir para a próxima validação.
4. Verifica se os demais campos estão preenchidos.
5. Se os demais campos estão estão preenchidos, seguir com a próxima validação.
6. Se algum dos demais campos não estiver preenchido, apresetnar erro 500.
7. Verifica se CPF existe na base.
8. Se CPF existe, seguir com a próxima validação.
9. Se CPF não existe, apresenta erro 500.
10. Verifica se Proposta existe na base.
11. Se Proposta existe, registra a Sessão.
12. Se Proposta não existe, apresenta erro 500.

&nbsp;

###### 4.3.2 - Dados da Sessão
O OBJETO tem como objetivo devolver dados de uma Sessão.

| GET |URL: http://localhost:8080/api/v1/voting-session?id=1|
| ------ | ------ |
---
Entrada de Dados: Query
| Campo | Descrição |Tipo |
| ------ | ------ |----|
| id | Código da Sessão |Inteiro|


Resultado Esperado: JSON
>200 - OK

| Campo | Descrição |Tipo |
| ------ | ------ |----|
| id | Código da Sessão | Inteiro|
| id | Código da Prposta | Inteiro|
| cpf | CPF do Funcionário Criador da Sessão |Texto|
| tempoSessao | Tempo final da Sessão de Votação | Data|
| nomeProposta | Nome da Proposta | Texto|
| descricao | Descrição da Proposta | Texto|

> 500 - Internal Server Error


*REGRAS DE NEGÓCIO APLICADAS:*
1. Verifica so campo id está preenchido.
2. Se id está preenchidos, seguir com a próxima validação.
3. Se id não está preenchidos, apresetnar erro 500.
4. Verifica se id  existe na base.
5. Se id existe, mostra os resultado.
6.  Se id não existe, apresenta erro 500.



&nbsp;

##### 4.4 - Micro Service Vote - Voto
Foi desenvolvido um micro serviço para efetuar a votação.

###### 4.4.1 - Grava Voto
O OBJETO tem como objetivo registrar um Voto em uma Sessão de Votação.

| POST |URL: http://localhost:8080/api/v1/votes|
| ------ | ------ |
---
Entrada de Dados: JSON
| Campo | Descrição |Tipo |
| ------ | ------ |----|
| idSessão | Código da Sessão | Inteiro|
| voto | Valor em texto "Aprovar" ou "Rejeitar" | Texto |
| cpfFuncionario | CPF do Funcionário que está Votando |Texto|


Resultado Esperado: JSON
>200 - OK

| Campo | Descrição |Tipo |
| ------ | ------ |----|
|status | Texto "Voto Registrado" | Texto|
| id | Código do Voto | Inteiro|

> 400 - Bad Request


*REGRAS DE NEGÓCIO APLICADAS:*
1. Verifica se todos os campos estão preenchidos.
2. Se todos os campos estão preenchidos, seguir com a próxima validação.
3. Se algum dos campos não estiver preenchido, apresetnar erro 500.
4. Verifica se CPF existe na base.
5. Se CPF existe, seguir com a próxima validação.
6. Se CPF não existe, apresenta erro 500.
7. Verifica se Sessão existe na base.
8. Se sessão existe, seguir com a próxima validação.
9. Se sessão não exsite, apresenta erro 500.
10. Verifica se CPF pode votar.
11. Se CPF ainda não votou na sessão, seguir com a próxima validação.
12. Se CPF já votou na sessão, apresenta erro 400.
13. Verifica se valor no campo "voto" é igual à "Aprovar" ou "Rejeitar".
14. Se valor do campo for igual a uma das palavras, seguir com a próxima validação.
15. Se valor do campo for direferente de uma das palavras, apresenta erro 400.
16. Verifica se a data e hora da requisição do voto, é menor ou igual a ao valor de Tempo Sessão".
17. Se a data e horário for menor ou igual ao tempo da sessão, registrar o voto com sucesso.
18. Se a data e horário for maior do que que o tempo da sessão, aporesenta erri 400.


&nbsp;


##### 4.5 - Micro Service Vote Result - Resultado da Votação
Foi desenvolvido um micro serviço para retornar o resultado da Votação.

###### 4.5.1 - Verifica Resultado de Votação
O OBJETO tem como objetivo apresentar o resultado da votação.

| GET |URL: http://localhost:8080/api/v1/vote-results?idSessao=1|
| ------ | ------ |
---
Entrada de Dados: JSON
| Campo | Descrição |Tipo |
| ------ | ------ |----|
| idSessao | Código da Sessão | Inteiro|


Resultado Esperado: JSON
>200 - OK

| Campo | Descrição |Tipo |
| ------ | ------ |----|
|status | Resultado da votação, "Proposta Aprovada" ou "Proposta Reprovada" | Texto|
| approvedCount | Contador de Votos "Aprovados" | Inteiro|
| rejectedCount | Contador de Votos "Aprovados" | Inteiro|

> 400 - Bad Request

*REGRAS DE NEGÓCIO APLICADAS:*
1. Verifica se o campo id foi preenchido.
2. Se o campo estiver preenchido, seguir com a próxima validação.
3. Se o campo não estiver preenchido, apresetna erro 400.
4. Verifica se id da sessão existe na base.
5. Se id da sessão existe, apresentar resultado.
6. Se id da sessão não existe, apresenta erro 400.

&nbsp;
&nbsp;



##### 4.3 - Melhorias Futuras
Melhorias que serão aplicadas no futuro.

* Correção da implementação do GET Funcionários por CPF.
* Serviços de Mensagerias e Filas.
* Todos testes unitários.


&nbsp;
&nbsp;


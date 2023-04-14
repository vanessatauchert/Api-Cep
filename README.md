# Api-Cep
# Consulta Endereço via CEP

construir uma API REST de consulta de endereço e cálculo de frete para
um determinado CEP

- Fonte de dados:
https://viacep.com.br/

O valor do frete é fixo de acordo com as regiões
do Brasil: Sudeste (R$ 7,85), Centro-Oeste (R$ 12,50), Nordeste (R$ 15,98), Sul (R$
17,30) e Norte (R$ 20,83). O CEP é obrigatório e pode ser passado com ou sem máscara
na entrada e se o CEP não for encontrado uma mensagem informativa deve ser retornada
para o cliente.


## Requisitos

- ``Java 11``
- ``Spring boot``
- ``API REST Template``
- ``Documentação Swagger``
- ``Testes unitários JUnit5``
- ``Testes automatizados utilizando cucumber``

## Desenvolvimento<br>
![Badge Finalizado](http://img.shields.io/static/v1?label=STATUS&message=FINALIZADO&color=GREEN&style=for-the-badge)

## Passo 1:

Clonar o repositório<br>
Copie o link do repositório abaixo e clone para o diretório de sua preferência.

```
https://github.com/vanessatauchert/Api-Cep.git
```

## Passo 2:

Após a execução do programa é possível visualizar os endpoints disponiveis no Swwager atráves do link: <http://localhost:8080/swagger-ui/index.html#/>

## Endpoints:

| Método | Url | Descrição |
| --- | --- | --- |
| POST | localhost:8080/v1/consulta-endereco | Consulta o Endereço e valor do frete pelo Cep digitado |

## Collection Insomnia:

A collection dos endpoints no `Insomnia` pode ser encontrada para download na pasta: `src\main\resources\collection\Insomnia_2023-04-14.json`

## Testes:

Para verificar a cobertura dos testes, basta clicar com o botão direito sobre o pacote principal da pasta teste, 
selecionar `Modify Run Configuration` em `Code Coverage` selecione o `JaCoCo` clique em `Apply` e `OK`

<img width="383" alt="Screenshot 2023-03-06 050038" src="https://media.github.ibm.com/user/416499/files/65bbf85a-4168-47d2-971a-00b6a70042f1">

Clique novamente com o botão direito sobre o pacote principal da pasta teste e selecione a terceira opção `Run Tests with Coverage` após a
execução será apresentada a cobertura dos testes. 

## Desenvolvido com:<br>

- ``Java 11``- ``SpringBoot 2.7.10``- ``Intellij``- ``Maven 3``- ``Swagger``- ``RestTemplate``- ``JUnit5``-  ``Jacoco``-``Insomnia``- ``Git``- ``GitHub``

# Autora

| [<img src="https://avatars.githubusercontent.com/u/60265204?v=4" width=115><br><sub>Vanessa Oliveira</sub>](https://github.ibm.com/Van-Oliveira) |
| :---: |

# Projeto integrador - Requisito 6, Samuel Stalschus

No requisito 6 eu decidi implementar uma mecanica que de maneira otimizada, tentar reduzir o prejuizo do Mercado Livre caso a data de validade do produto venha a expirar.
É feita uma análise de código de todo o ciclo de vida do lote do anuncio em questão.
Quando diagnosticado que o período de vida do lote (até a sua expiração) se comparado a quantidade vendida está levando a crer que o teremos percas com aquele produto
a app entra em ação para oferecer automaticamente descontos no valor do produto (o desconto é em cima do valor de lucro).

Podemos ter 3 tipos de descontos, LOW=20% do valor de lucro, MEDIUM=40% do valor de lucro e HIGH=80% do valor de lucro.

Caso o ultimo desconto oferecido não tenha alterado a situação ruim com as vendas do produto, e for identificado que o lote armazenado vai vencer, a app vai aumentar o nível do desconto.

### Para executar a api testes é necessário

    Java 8+    

    MySQL 8 instalado

    database dev criado

    database test criado

## Instalação

    mvn spring-boot:run

## Para executar o app

    mvn spring-boot:run

## Para executar os testes

    mvn test


# REST API

Para interagir com os endpoints executando a app localmente é necessário usar essa rota como base.

    http://locahost:8080/api/v1

Link para a documentação do postman

    https://documenter.getpostman.com/view/18979872/UVeMH3xQ

Para autenticar, você pode usar esse token gerado

    eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlbWFpbEBlbWFpbCIsImV4cCI6MTY0NTExOTc4NX0.9gva_PuWGQb14lqfKG_zBGnu-qEollHODAawCi2jPc62MEYgIScRiS-T07ITB0WjvuBTEsngwQZEK1JXwt9dEA


## User history

 A User history está na pasta .github na raiz do projeto

## Cobertura de testes de 100% para integração e unitários

![img.png](.github/img.png)
![img.png](.github/img-test-unit.png)

## Modelagem do banco de dados

![img.png](.github/modelagem.png)

## Diagrama de classes

![img.png](.github/diagrama.png)

## User story
![img.png](.github/pageone.png)
![img.png](.github/pagetwo.png)
![img.png](.github/pagethree.png)
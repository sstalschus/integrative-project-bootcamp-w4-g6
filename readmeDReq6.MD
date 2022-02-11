# Integrative project

This is an API for registering properties and calculating price and size


## Install

    mvn spring-boot:run

## Run the app

    mvn spring-boot:run

## Run the tests

    mvn test


# REST API

Below are our main routes in more detail. Execute in

    http://locahost:8080/api/v1

Link to postman documentation online

    http://localhost:8080/api/v1/purchase/change-status?purchaseId=1&status=Open&shoppingCart=1

Link to json postman documentation

    http://localhost:8080/api/v1/purchase/create

Token generic for authenticate

    eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlbWFpbEBlbWFpbCIsImV4cCI6MTY0NTExOTc4NX0.9gva_PuWGQb14lqfKG_zBGnu-qEollHODAawCi2jPc62MEYgIScRiS-T07ITB0WjvuBTEsngwQZEK1JXwt9dEA

Cenário
COMO comprador, QUERO adicionar uma venda no Marketplace PARA efetuar uma compra.
—————————————————————————————————————————————————
CENARIO 1: Uma compra e registrado.
DESDE o carrinho esteja com o status ativo
E que o comprador esteja registrado
E que o produto tem estoque
E que o anúncio esteja criado
QUANDO o comprador adiciona um produto no carrinho
ENTAO, o status muda para ativo
E a compra e efetuada
—————————————————————————————————————————————————
VALIDACAO
*	Consultar carrinho de compra
*	Autentique o status da compra
*	Efetue uma compra


## Stealth Garage API

A Stealth Garage API é uma API REST para gerenciamento de uma garagem: clientes, veículos, peças e registros de manutenção.

Cada cliente tem nome, e-mail e telefone. Cada veículo tem marca, nome, ano e placa — e fica vinculado a um dono. A relação é um para muitos: um cliente pode ter quantos veículos quiser.

Os registros de manutenção guardam data, descrição do serviço e KM do veículo no momento do reparo. Cada registro pode ter peças associadas, conectando tudo ao veículo que recebeu o serviço.

O CRUD cobre usuários e veículos completos — GET, POST, PUT e DELETE. Uma regra importante: não é possível deletar um usuário que ainda tem veículos vinculados. A deleção segue a ordem filho → pai.

A API foi construída em Java + Spring Boot + JPA/Hibernate, com testes unitários via JUnit 5 e Mockito, testes de rota HTTP com MockMvc, cobrindo caminhos felizes e tristes, validações do Spring Security JWT, exceções e muito mais.

## Como executar a Stealth Garage API com Docker:

```Json
git clone https://github.com/SEU-USUARIO/Stealth-Garage-API.git

cd Stealth-Garage-API
```

Depois execute a infraestrutura:

```Json
docker-compose up --build -d
```

e agora sinta-se livre para executar os endpoints apresentados ao longo de todo README abaixo:

Buscar Todos os Veículos (Utilizamos paginacao):
`GET /vehicles`

Retorna uma lista paginada de todos os veículos. 

Parametros da Query: 
- `page` (opcional): O numero da pagina a ser recuperada.
- `size` (opcional): A quantidade de registros por pagina (Padrao e 20).
- `sort` (opcional): O criterio de ordenação no formato `propriedade,direção`.
- `ano` (opcional): Filtra os veiculos pelo ano de fabricação especificado.

Exemplo de requisicao:

`GET /vehicles?page=0&size=10&sort=brandAndName,asc&ano=2018`

Metodo POST:

http://localhost:8080/users (local) / https://stealth-garage-api.onrender.com/users (servidor)

```Json
{
    "name": "Romario Muniz",
    "email": "romariozinho204@gmail.com",
    "phoneNumber": "1199546359"
}
```

http://localhost:8080/vehicles (local) / https://stealth-garage-api.onrender.com/vehicles (servidor)

```Json
{
		"brandAndName": "Chevrolet Cobalt",
		"year": 2020,
		"licensePlate": "woe4d2043"
}
```

  Metodo PUT:

  http://localhost:8080/vehicles/1 (local) / https://stealth-garage-api.onrender.com/vehicles/1 (servidor)

  ```Json
{
		"id": 1,
		"brandAndName": "Chevrolet Cobalt",
		"year": 2026,
		"licensePlate": "oep3g93"
}
```

http://localhost:8080/users/1 (local) / https://stealth-garage-api.onrender.com/users/1 (servidor)

```Json
{
    "name": "Romario Marques",
    "email": "romariozinhomarques2024@gmail.com",
    "phoneNumber": "11995435359"
}
```

Metodo DELETE:

http://localhost:8080/users/1 (local) / https://stealth-garage-api.onrender.com/users/1 (servidor) (So sera deletado se nao houver veiculo vinculado a ele)

http://localhost:8080/vehicles/1 (local) / https://stealth-garage-api.onrender.com/vehicles/1 (servidor)

## Como executar a Stealth Garage API no Swagger (Documentacao interativa):

<img width="1918" height="1080" alt="Swagger Stealth Garage API" src="https://github.com/user-attachments/assets/e8649ea0-172d-477d-b675-5e0994815626" />



1 - Rode a aplicacao via Docker ou localmente

2 - Acesse no seu navegador: http://localhost:8080/swagger-ui/index.html#/

3 - Autenticacao JWT: Expanda a rota POST/auth/login e insira suas credenciais. Apos isso copie o token gerado e cole dentro do botao Authorize no topo da pagina

4 - Pronto!, rodando e permissao concebida a APIRESTful como USER!

## Como executar a Stealth Garage API (Rodando na nuvem):

URL: https://stealth-garage-api.onrender.com

Cole essa URL nos metodos acima, e seja livre para usar e testar a Stealth Garage API dentro do seu Postman, ou variante do mesmo.

(A requisicao pode demorar cerca de 50 segundos para ligar, pois esta no plano gratuito do Render que desliga sem uso a cada 15 minutos)

## Como executar a Stealth Garage API (Rodando o projeto em seu computador):

Certifique-se de ter o Java e o Git instalados em seu computador
Abra o terminal Git e clone esse repositorio: 

```Bash
git clone https://github.com/SEU-USUARIO/Stealth-Garage-API.git
```

Abra sua IDE

Aguarde o Maven baixar toda dependencias necessarias

Execute o StealthGarageApiApplication.java

Acesse o H2 e cole este link: http://localhost:8080/h2-console

Configure ele dessa forma:

JDBC URL: jdbc:h2:mem:testdb

User Name: sa

Password: (Nao digite nada)

Clique em Connect e explore a APIRESTful 

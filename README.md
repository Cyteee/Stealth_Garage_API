## Stealth Garage API

A Stealth Garage API é uma API REST para gerenciamento de uma garagem: clientes, veículos, peças e registros de manutenção.

Cada cliente tem nome, e-mail e telefone. Cada veículo tem marca, nome, ano e placa — e fica vinculado a um dono. A relação é um para muitos: um cliente pode ter quantos veículos quiser.

Os registros de manutenção guardam data, descrição do serviço e KM do veículo no momento do reparo. Cada registro pode ter peças associadas, conectando tudo ao veículo que recebeu o serviço.

O CRUD cobre usuários e veículos completos — GET, POST, PUT e DELETE. Uma regra importante: não é possível deletar um usuário que ainda tem veículos vinculados. A deleção segue a ordem filho → pai.

A API foi construída em Java + Spring Boot + JPA/Hibernate, com testes unitários via JUnit 5 e Mockito, testes de rota HTTP com MockMvc, cobrindo caminhos felizes e tristes, validações do Spring Security JWT, exceções e muito mais.


#### A Stealth Garage API utiliza o Spring Security, com arquitetura Stateless e Tokens JWT, isso significa que para acessar e fazer configuracoes na API, e necessario autenticacao e autorizacao, obtenha o Token com os passos abaixo:

### 1 Obtencao de tokens (login):

METODO POST. http://localhost:8080/auth/login ou acesso na nuvem: https://stealth-garage-api.onrender.com/auth/login

```Json
{

    "login": "SEU-EMAIL@gmail.com",
    "password": "SUA-SENHA"
	
}

```

utilize o Token gerado no console, e coloque ele no Auth, selecione Bearer Token e cole o token la dentro, assim, o seu acesso esta autorizado pelo tempo de 2 horas.

Abaixo segue exemplos de requisicoes que poderao ser utilizadas:

Metodo GET:

http://localhost:8080/vehicles (local) / https://stealth-garage-api.onrender.com/vehicles (servidor)

http://localhost:8080/users (local) / https://stealth-garage-api.onrender.com/users (servidor)

http://localhost:8080/vehicles/1 (local) / https://stealth-garage-api.onrender.com/vehicles/1 (servidor)

http://localhost:8080/users/1 (local) / https://stealth-garage-api.onrender.com/users/1 (servidor)

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

## Como executar a Stealth Garage API (Rodando na nuvem):

URL: https://stealth-garage-api.onrender.com

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

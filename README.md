## Stealth Garage API

Seja bem vindo a minha API Rest sobre minha Garagem Discreta, ou Stealth Garage, ela e uma API feita para armazenar dados dos clientes, sendo eles nome, email, numero de telefone e automaticamente o ID de cada cliente em ordem de chegada, apos a etapa dos dados de nossos clientes, temos os dados dos veiculos cadastrados, constando em nosso sistema a marca do veiculo, o nome do veiculo, o ano do veiculo e a placa do veiculo, com esses dois tipos de dados em maos, podemos vincular o usuario, com o veiculo, assim temos a logica de um para muitos, um usuario pode ter muitos veiculos, cada usuario pode ter quantos veiculos quiser, e eles serao vinculados a este dono. O proximo mecanico da minha APIRESTful e o de pecas e o registro de manutencao, cada peca tem seu ID, nome, preco e cada registro de manutencao tem seu ID, data da manutencao, nome do que foi feito e o KM do carro no momento da manutencao, assim podemos vincular cada peca, com o registro da manutencao e com o veiculo que foi feito o reparo. Em meu CRUD, podemos puxar todos dados sobre os usuarios, veiculos e por ID de cada usuario ou veiculo, podemos adicionar usuarios e veiculos, atualizar dados sobre usuarios e veiculos e deletar veiculos e usuarios, nesta sequencia, de filho para pai, esta restrito delecao de pai antes do filho, no caso, esta restrito delecao de usuario que possui um veiculo vinculado em seu nome. Toda APIRESTful foi feita em Java, Springboot, JPA/Hibernate, podendo ser executado pelo Springboot e utilizada no H2 na memoria local do computador do usuario (momentaneamente), e realizar o CRUD pelo Insominia, Postman, Bruno, Thunder Client, Apidog, HTTPie, Hoppscotch e Restfox (testamos ele pelo Insominia e H2).

Abaixo segue exemplos de requisicoes que poderao ser utilizadas:

Metodo GET:

http://localhost:8080/vehicles

http://localhost:8080/users

http://localhost:8080/vehicles/1

http://localhost:8080/users/1

Metodo POST:

{

    "name": "Romario Muniz",
    "email": "romariozinho204@gmail.com",
    "phoneNumber": "1199546359"
	
}

{

		"brandAndName": "Chevrolet Cobalt",
		"year": 2020,
		"licensePlate": "woe4d2043"
		
}

  Metodo PUT:

  {
  
		"id": 1,
		"brand": "Chevrolet Cobalt",
		"year": 2026,
		"licensePlate": "oep3g93"
		
}

  {
  
    "name": "Romario Marques",
    "email": "romariozinhomarques2024@gmail.com",
    "phoneNumber": "11995435359"

}

Metodo DELETE:

http://localhost:8080/users/1 (So sera deletado se nao houver veiculo vinculado a ele)

http://localhost:8080/vehicles/1

## Como executar ela (Rodando o projeto em seu computador):

Certifique-se de ter o Java e o Git instalados em seu computador
Abra o terminal e clone esse repositorio:

git clone [https://github.com/(Aqui-fica-seu-usuario)/Stealth-Garage-API.git](https://github.com/SEU-USUARIO/Stealth-Garage-API.git)

Abra sua IDE

Aguarde o Maven baixar toda dependencias necessarias

Execute o StealthGarageApiApplication.java

Acesse o H2 e cole este link: http://localhost:8080/h2-console

Configure ele dessa forma:

JDBC URL: jdbc:h2:mem:testdb

User Name: sa

Password: (Nao digite nada)

Clique em Connect e explore a APIRESTful 

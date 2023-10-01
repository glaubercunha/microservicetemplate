Para subir a aplicação no docjer execute o comando abaixo na raiz do projeto
docker compose up


Para alterar o código e rodar a alteração, execute os comando abaixo
mvn clean package
docker build -t glaubermcunha/microservicetemplate:1.0.0 .
docker compose up

Para acessar a API utilize a url abaixo
http://localhost:8080/mensagem/v1/mensagens?salaId=2
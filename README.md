# Mincroservicetemplate
API REST Java utilizando Spring Boot, Cache Redis e Docker. 

Infra: EC2 e RDS com Postgres

IAC: Terraform e Ansible

## Para acessar a API utilize a url abaixo
http://localhost:8080/mensagem/v1/mensagens?salaId=2

## Para accesar no AWS EC2 acessar o link abaixo
http://[SOLICITAR_IP]:8080/mensagem/v1/mensagens?salaId=2

## Compilação e criação de imagem ( Caso tenha alterado o fonte)
```bash
mvn clean package
docker build -t glaubermcunha/microservicetemplate:1.0.0 .
```

## upload da imagem no repositório( Caso tenha alterado o fonte)
```bash
docker push glaubermcunha/microservicetemplate:1.1.0
```

## Deploy
```bash
#Console
docker compose up 

#Rodando em background
docker compose up -d
```
## Para criar a instância EC2 com Terraform
```bash
terraform init
terraform plan
terraform apply
```

## Obter a chave do servidor criado na AWS em seguida alterar a chave para ficar acessível somente pelo criador
```bash
chmod 400 [NOME_CHAVE].pem
```

## Para subir a aplicação na AWS com Ansible
```bash
#Criar arquivo 'hosts' com o IP/DNS do servidor
ansible-playbook -i hosts playbook.yml --private-key iac-aws-us-east2-ed25519.pem -u ubuntu
```

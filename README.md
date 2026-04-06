# 🏟️ Arena de Pernambuco

Aplicação web desenvolvida em Java com Spring Boot para melhorar a ocupação e a divulgação da Arena Pernambuco. O sistema atua entre a administração do equipamento público e a população, combatendo a desaproveitamento do espaço através de uma vitrine digital de eventos e ferramentas de gestão de dados.

## 🎯 Objetivo do Projeto

Facilitar a comunicação entre interessados e a administração da Arena, incentivando o uso do espaço para diversos tipos de eventos, além de fornecer métricas estatísticas para auxiliar na tomada de decisões governamentais, relacionados ao uso do espaço.

## 🛠️ Tecnologias Utilizadas

### ☕ Back-end
- Java
- Spring Boot
- Spring Data JPA

### 🎨 Front-end
- HTML
- CSS
- Thymeleaf

### 🗄️ Banco de Dados
- MySQL (via Docker) 🐳

## 📊 Funcionalidades

- Gestaõ de eventos  
- Vitrini de eventos  
- Sistema de reservas 
- Painel de dados 

## 🚀 Como Executar o Projeto

1. Clone o repositório

git clone https://github.com/SergioGSF/programa-cultural-digital

2. Acesse a pasta do projeto

cd programa-cultural-digital

3. Construir o JAR da aplicação

.\mvnw clean package

4. Subir os containers

docker-compose up -d
docker ps

5. Execute a aplicação

mvn spring-boot:run

6. Acesse no navegador

http://localhost:8080

## 🏗️ Estrutura do Projeto

O projeto segue uma arquitetura baseada no padrão MVC:

- Model: entidades e estrutura de dados do sistema  
- Repository: camada de acesso ao banco de dados com Spring Data JPA  
- Service: regras de negócio da aplicação  
- Controller: controle das requisições HTTP  
- Templates: páginas HTML renderizadas com Thymeleaf  

## 🔮 Possíveis Melhorias Futuras
- Integração com APIs de geolocalização e mapas 
- Sistema de notificação automática  
- Integração odiciais com sistemas governamentais
- Validade jurídica de operações
- Automações avançadas 

## 👥 Equipe

- Allana Sílvia Gadêlha de Carvalho Souza  
- Carlos Henrique Gonçalves da Silva  
- Claudemir Pereira de Araujo Filho  
- Everton Nunes Batista  
- Samara Mendonça Nunes  
- Sergio Gonçalves da Silva Filho

## 📁 Entrega 1
- Histórias de usuário:
https://docs.google.com/document/d/1lhORnMbUCOVKTaVsYzWjMuSxe1MJcixIdLyDGVHj028/edit?usp=sharing
- Screencast do Figma:
https://youtu.be/ahp9xVqzgTo
- Trello:
https://trello.com/invite/b/69
b5c446be6c97a50237b22a/ATTI5c630be81ca5f84d143179c6cc39e15402DBF0AC/poo-projeto-arena-cultural

## 📁 Entrega 2
- Issues/bug tracker: 
https://github.com/SergioGSF/programa-cultural-digital/issues
<img width="1230" height="249" alt="image" src="https://github.com/user-attachments/assets/7818d212-a18f-4777-9078-fa55132fbc60" />
<img width="1233" height="182" alt="image" src="https://github.com/user-attachments/assets/f920d4e5-52d6-4f9f-90be-d77c041cb03d" />

- Screencast 2 histórias:

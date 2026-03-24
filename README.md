# 🏟️ Arena de Pernambuco

Aplicação web desenvolvida em **Java com Spring Boot** com o objetivo de melhorar a ocupação e a divulgação da Arena de Pernambuco.

A plataforma atua como intermediária entre a administração do equipamento público e a população, promovendo maior utilização do espaço por meio de uma vitrine digital de eventos e ferramentas de gestão baseadas em dados.

---

## 📌 Sobre o Projeto

O sistema foi projetado para centralizar informações sobre eventos realizados na Arena de Pernambuco, facilitando o acesso da população e aprimorando a comunicação com a gestão do espaço.

Além disso, a aplicação permite a coleta e análise de dados, contribuindo para a tomada de decisões estratégicas relacionadas ao uso do equipamento público.

---

## 🎯 Objetivos

* Incentivar a realização de eventos na Arena
* Melhorar a divulgação de atividades culturais e institucionais
* Otimizar a gestão do espaço por meio de dados
* Facilitar a comunicação entre população e administração

---

## 🚀 Funcionalidades

* **Gestão de eventos**
* **Vitrine digital de eventos**
* **Sistema de reservas**
* **Painel de dados e estatísticas**

---

## 🛠️ Tecnologias Utilizadas

**Back-end**

* Java
* Spring Boot
* Spring Data JPA

**Front-end**

* HTML
* CSS
* Thymeleaf

**Banco de Dados**

* MySQL (executado via Docker)

---

## ⚙️ Execução do Projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/SergioGSF/programa-cultural-digital
```

### 2. Acessar o diretório

```bash
cd programa-cultural-digital
```

### 3. Subir o banco de dados (MySQL com Docker)

```bash
docker run -d \
  --name mysql-arena \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=arena_db \
  -p 3306:3306 \
  mysql:8
```

### 4. Configurar a aplicação

No arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/arena_db
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```

### 5. Executar a aplicação

```bash
mvn spring-boot:run
```

### 6. Acessar no navegador

```
http://localhost:8080
```

---

## 🏗️ Arquitetura do Sistema

O projeto segue o padrão **MVC (Model-View-Controller)**:

* **Model** → Representação das entidades do sistema
* **Repository** → Camada de acesso a dados (Spring Data JPA)
* **Service** → Regras de negócio
* **Controller** → Gerenciamento das requisições HTTP
* **View (Templates)** → Interface com Thymeleaf

---

## 🔮 Melhorias Futuras

* Integração com APIs de geolocalização e mapas
* Sistema de notificações automatizadas
* Integração com plataformas governamentais
* Validação jurídica de operações
* Implementação de automações avançadas

---

## 👥 Equipe

* Allana Sílvia Gadêlha de Carvalho Souza
* Carlos Henrique Gonçalves da Silva
* Claudemir Pereira de Araújo Filho
* Everton Nunes Batista
* Samara Mendonça Nunes
* Sérgio Gonçalves da Silva Filho

---

## 📦 Entrega 1

* **Histórias de usuário**
  https://docs.google.com/document/d/1lhORnMbUCOVKTaVsYzWjMuSxe1MJcixIdLyDGVHj028/edit?usp=sharing

* **Screencast do protótipo (Figma)**
  https://youtu.be/ahp9xVqzgTo

* **Gerenciamento do projeto (Trello)**
  https://trello.com/invite/b/69b5c446be6c97a50237b22a/ATTI5c630be81ca5f84d143179c6cc39e15402DBF0AC/poo-projeto-arena-cultural
  

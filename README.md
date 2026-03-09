# Sistema de Monitoramento de Sinais Vitais

Sistema web desenvolvido para registro e acompanhamento de indicadores de saúde, permitindo monitorar parâmetros importantes como frequência cardíaca, temperatura corporal e oxigenação sanguínea. O sistema possibilita o acompanhamento do histórico clínico de pacientes e a geração de alertas quando valores críticos são identificados.

## Objetivo do Projeto

O objetivo deste sistema é auxiliar no acompanhamento básico de sinais vitais, permitindo registrar medições, acompanhar a evolução dos indicadores de saúde dos pacientes e identificar possíveis situações de risco a partir de alertas automáticos.

## Tecnologias Utilizadas

### Back-end
- Java
- Spring Boot
- Spring Data JPA

### Front-end
- HTML
- CSS
- Thymeleaf

### Banco de Dados
- MySQL

## Funcionalidades

- Cadastro e gerenciamento de pacientes  
- Registro de medições biométricas  
- Consulta de histórico clínico dos pacientes  
- Sistema de alertas para valores fora da normalidade  

## Como Executar o Projeto

1. Clone o repositório

git clone https://github.com/SergioGSF/monitoramento-sinais-vitais

2. Acesse a pasta do projeto

cd monitoramento-sinais-vitais

3. Execute a aplicação

mvn spring-boot:run

4. Acesse no navegador

http://localhost:8080

## Estrutura do Projeto

O projeto segue uma arquitetura baseada no padrão MVC:

- Model: entidades e estrutura de dados do sistema  
- Repository: camada de acesso ao banco de dados com Spring Data JPA  
- Service: regras de negócio da aplicação  
- Controller: controle das requisições HTTP  
- Templates: páginas HTML renderizadas com Thymeleaf  

## Possíveis Melhorias Futuras

- Implementação de autenticação e controle de acesso de usuários  
- Integração com dispositivos de monitoramento de saúde (IoT / wearables)  
- Implementação de API REST para integração com aplicações externas  
- Dashboard com gráficos para análise dos sinais vitais  
- Sistema de notificações em tempo real

## Equipe

- Allana Sílvia Gadêlha de Carvalho Souza  
- Carlos Henrique Gonçalves da Silva  
- Claudemir Pereira de Araujo Filho  
- Everton Nunes Batista  
- Samara Mendonça Nunes  
- Sergio Gonçalves da Silva Filho

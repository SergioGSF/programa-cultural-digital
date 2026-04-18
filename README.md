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

## 🏗️ Planejamento de Infraestrutura

### Objetivos da Infraestrutura
- Disponibilidade: garantir que a vitrine de eventos esteja sempre online para consulta da população.
- Integridade: assegurar que os dados de reservas e métricas governamentais sejam armazenados de forma segura e consistente.
- Escalabilidade: capacidade de suportar picos de acesso durante o anúncio de grandes eventos.

### Restrições Técnicas e de Negócio
- Custo: priorização de tecnologias Open Source e infraestrutura de baixo custo.
- Conformidade: o tratamento de dados deve seguir as diretrizes da LGPD (Lei Geral de Proteção de Dados).
- Ambiente: a aplicação deve ser executada obrigatoriamente em containers para garantir portabilidade entre servidores governamentais.

## 📈 Indicadores de Monitoramento

### Indicadores de Uso da Plataforma

- Quantidade de acessos ao sistema  
- Número de usuários cadastrados  
- Taxa de visualização de eventos  

Objetivo: medir o alcance da vitrine digital e o interesse da população pelos eventos divulgados.

### Indicadores de Gestão de Eventos

- Quantidade de eventos cadastrados  
- Taxa de ocupação da Arena  
- Taxxa de reservas confirmadas  
- Taxa de cancelamento  

Fórmula da Taxa de Ocupação:

Taxa de Ocupação = (Eventos Realizados / Capacidade de Agenda Disponível) × 100

Objetivo: avaliar o nível de aproveitamento do espaço público e apoiar decisões estratégicas da administração.

### Indicadores Administrativos

- Tempo médio de resposta às solicitações  
- Demandas pendentes  
- Relatórios gerenciais emitidos  

Objetivo: melhorar a eficiência da gestão pública no atendimento e no controle das reservas.

### Indicadores Técnicos

- Disponibilidade do sistema (Uptime)  
- Tempo médio de carregamento  
- Número de falhas registradas  

Fórmula do Uptime:

Uptime = (Tempo de Funcionamento / Tempo Total) × 100

Objetivo: garantir estabilidade, desempenho e acesso contínuo à plataforma.

### Indicadores de Segurança e Conformidade

- Incidentes de segurança  
- Controle de conformidade com a LGPD  

Objetivo: assegurar proteção de dados e conformidade legal no ambiente governamental.

## 🌐 Requisitos Técnicos de Rede

### Desempenho

#### Requisito de Negócio
Garantir acesso rápido e contínuo da população à vitrine digital de eventos da :contentReference[oaicite:0]{index=0}, especialmente em períodos de grande divulgação.

#### Requisito Técnico
- Servidor com capacidade para múltiplas conexões simultâneas  
- Baixa latência entre aplicação e banco de dados  
- Monitoramento de CPU, memória e tráfego de rede  
- Utilização de containers Docker para melhor gerenciamento de recursos  

Objetivo: reduzir o tempo de resposta da aplicação e melhorar a experiência do usuário.

### Segurança

#### Requisito de Negócio
Proteger dados de usuários, reservas e informações administrativas, garantindo conformidade com a LGPD.

#### Requisito Técnico
- Implementação de HTTPS com SSL/TLS  
- Controle de acesso por autenticação e perfis  
- Firewall para restrição de acessos indevidos  
- Backup periódico do banco de dados MySQL  
- Registro de logs e auditoria de acessos  

Objetivo: garantir proteção da informação e conformidade legal.

### Disponibilidade

#### Requisito de Negócio
Manter a plataforma acessível continuamente para consultas públicas e gestão administrativa.

#### Requisito Técnico
- Hospedagem em ambiente estável  
- Monitoramento contínuo de uptime  
- Backup automatizado e recuperação rápida  
- Uso de containers para rápida restauração em falhas  

Objetivo: garantir continuidade operacional e reduzir indisponibilidades.

### Escalabilidade

#### Requisito de Negócio
Permitir crescimento do sistema conforme aumento da demanda e futuras integrações governamentais.

#### Requisito Técnico
- Arquitetura compatível com expansão horizontal  
- Docker Compose para facilitar replicação  
- Banco de dados preparado para aumento de volume  
- Estrutura modular com Spring Boot e padrão MVC  

Objetivo: permitir crescimento sem necessidade de reestruturação completa.

### Conformidade Institucional

#### Requisito de Negócio
Atender às exigências da administração pública e compatibilidade com servidores governamentais.

#### Requisito Técnico
- Priorização de tecnologias Open Source  
- Portabilidade entre ambientes com Docker  
- Compatibilidade com infraestrutura institucional  
- Padronização de implantação e versionamento  

Objetivo: reduzir custos e garantir aderência às políticas internas de TI.

## 🖧 Diagrama de Comunicação e Topologia de Rede

A topologia adotada foi do tipo estrela hierárquica, com separação entre acesso externo e rede interna. O primeiro roteador faz a conexão com a internet, o segundo atua como firewall, e o switch central distribui o acesso aos servidores de aplicação, banco de dados e backup, garantindo segurança, disponibilidade e escalabilidade.

<img width="511" height="424" alt="image" src="https://github.com/user-attachments/assets/04038325-54bc-429b-bb3a-a56a0d60c3a8" />

## 📊 Funcionalidades

- Gestaõ de eventos  
- Vitrini de eventos  
- Sistema de reservas 
- Painel de dados 

## 🚀 Como Executar o Projeto

1. Clone o repositório

git clone https://github.com/SergioGSF/programa-cultural-digital

2. Acesse a pasta do projeto

cd programa-cultural-digital\arenapernambuco

3. Construir o JAR da aplicação

.\mvnw clean package

4. Subir os containers

docker-compose up -d
docker ps

5. Execute a aplicação

mvn spring-boot:run

6. Acesse no navegador

http://localhost:8080

## 🧱 Estrutura do Projeto

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
https://trello.com/b/vi5lSu6D/poo-projeto-arena-cultural

## 📁 Entrega 2
- Issues/bug tracker: 
https://github.com/SergioGSF/programa-cultural-digital/issues
<img width="1230" height="249" alt="image" src="https://github.com/user-attachments/assets/7818d212-a18f-4777-9078-fa55132fbc60" />
<img width="1233" height="182" alt="image" src="https://github.com/user-attachments/assets/f920d4e5-52d6-4f9f-90be-d77c041cb03d" />

- Screencast 2 histórias: 
https://youtu.be/QybrQOebOSA

## 📁 Entrega 3

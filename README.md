# 🏟️ Arena de Pernambuco

Aplicação web desenvolvida em Java com Spring Boot para melhorar a ocupação e a divulgação da Arena Pernambuco. O sistema atua entre a administração do equipamento público e a população, combatendo o subaproveitamento do espaço através de uma vitrine digital de eventos e ferramentas de gestão de dados.

## 🎯 Objetivo do Projeto

Facilitar a comunicação entre interessados e a administração da Arena, incentivando o uso do espaço para diversos tipos de eventos, além de fornecer métricas estatísticas para auxiliar na tomada de decisões governamentais, relacionados ao uso do espaço.

## 📦 Escopo

- Desenvolver aplicação web em Java (Spring Boot)  
- Definir arquitetura do sistema (MVP)  
- Modelar domínio com DDD  
- Implementar funcionalidades principais da aplicação  
- Configurar infraestrutura de comunicação (rede, topologia e serviços)  
- Implementar pipeline de CI/CD  
- Realizar deploy em ambiente de nuvem  
- Aplicar threads para otimização de processos  
- Definir e coletar métricas do sistema  
- Estruturar e armazenar dados  
- Realizar análise estatística dos dados  
- Criar dashboards de monitoramento e análise  
- Validar desempenho, conectividade e disponibilidade

## 🧩 Modelagem de Domínio (DDD)

O sistema Arena Pernambuco possui como domínio principal a gestão, aprovação de eventos e ocupação da arena, com foco na administração eficiente do espaço público, divulgação de eventos e apoio à tomada de decisão por meio de indicadores estratégicos.

A modelagem foi estruturada com base em DDD (Domain-Driven Design), permitindo maior organização das regras de negócio, escalabilidade da aplicação e separação clara entre responsabilidades.

### Linguagem Ubíqua

| Termo | Definição |
|---|---|
| Evento | atividade oficial cadastrada na Arena Pernambuco |
| Solicitação de Evento | pedido realizado por um organizador para utilização da Arena |
| Reserva | solicitação de uso do espaço |
| Organizador | empresa responsável pela criação e execução do evento |
| Cliente | usuário que consulta eventos e adquire ingressos |
| Gestor | responsável pela aprovação ou rejeição dos eventos |
| Agenda da Arena | calendário oficial de ocupação |
| Ingresso | autorização digital de acesso |
| Check-in | validação de entrada no evento |
| QR Code | identificador digital único do ingresso |

### Domínio Principal

### Gestão, Aprovação de Eventos e Ocupação da Arena

Responsável por:

- cadastro de solicitações de eventos
- análise e aprovação de eventos
- gestão da agenda da arena
- vitrine pública de eventos
- venda de ingressos
- geração de indicadores administrativos
- suporte à gestão institucional

### Principais Subdomínios

#### Gestão de Eventos

Responsável pelo cadastro, edição, categorização e publicação dos eventos realizados na arena.

#### Aprovação de Eventos

Responsável pela análise, aprovação ou rejeição das solicitações realizadas pelos organizadores.

#### Sistema de Reservas

Responsável pela solicitação, aprovação e controle de uso do espaço físico, evitando conflitos de agenda.

#### Usuários e Perfis

Responsável pela autenticação, autorização e controle de acesso dos perfis:

- Cliente
- Organizador
- Gestor

#### Indicadores e Relatórios

Responsável pela geração de métricas como taxa de ocupação, reservas confirmadas, cancelamentos e relatórios gerenciais.

---

## 🧱 Contextos Delimitados (Bounded Contexts)

### Contexto de Gestão de Eventos

Responsável pelo gerenciamento dos eventos, agenda da arena e publicação de informações.

### Contexto de Aprovação de Eventos

Responsável pela análise, aprovação, rejeição e acompanhamento das solicitações de eventos realizadas pelos organizadores.

### Contexto de Reservas

Responsável pelo controle de reservas, disponibilidade e aprovação de solicitações.

### Contexto de Identidade e Acesso

Responsável pela autenticação, autorização e gerenciamento de usuários.

### Contexto de Indicadores e Relatórios

Responsável pela geração de indicadores, dashboards e relatórios administrativos.

### Contexto de Ingressos

Responsável pela emissão de ingressos digitais, QR Codes e controle de acesso.

### Contexto de Notificações

Responsável pelo envio de notificações, e-mails e comunicações automáticas.

## 🏛️ Principais Entidades

- Usuário
- Evento
- Reserva
- Agenda da Arena
- Categoria de Evento
- Indicadores
- Auditoria
- Ingresso

#### Usuários e Perfis

Responsável pela autenticação, autorização e controle de acesso dos perfis:

- Cliente
- Organizador
- Gestor

---

## 📦 Objetos de Valor (Value Objects)

- CPF
- Email
- Período de Reserva
- QRCode
- Setor
- Assento

Os Objetos de Valor representam elementos imutáveis do domínio definidos exclusivamente pelos seus atributos.

## 🔗 Relacionamentos Principais

- Um organizador pode criar vários eventos
- Um cliente pode adquirir vários ingressos
- Um gestor pode aprovar ou rejeitar eventos
- Cada evento pertence a um organizador
- Cada reserva está vinculada a um evento
- Cada evento pertence a uma categoria
- Cada evento ocupa um espaço na agenda da arena
- Cada ingresso pertence a um cliente
- Cada ingresso está vinculado a um evento
- O sistema gera indicadores para apoio à gestão
- Todas as ações relevantes possuem rastreabilidade por auditoria

---

## 🏛️ Agregados e Regras de Negócio

### Agregado Reserva

#### Regras Invariantes

- Não pode existir conflito de horário entre reservas
- Reservas canceladas liberam automaticamente a agenda
- Apenas gestores podem aprovar reservas
- Eventos precisam possuir organizador válido

---

### Agregado Evento

#### Raiz do Agregado

Evento

#### Regras Invariantes

- Evento deve possuir data válida
- Evento não pode ultrapassar capacidade máxima da arena
- QR Code deve ser único por ingresso
- Apenas organizadores podem criar eventos
- Todo evento inicia com status PENDENTE
- Apenas gestores podem aprovar ou rejeitar eventos
- Somente eventos aprovados podem ser publicados

---

## 📡 Eventos de Domínio (Domain Events)

| Evento | Publicador | Consumidor |
|---|---|---|
| EventoSolicitado | Serviço de Eventos | Serviço de Aprovação |
| EventoAprovado | Serviço de Aprovação | Serviço de Eventos |
| EventoRejeitado | Serviço de Aprovação | Serviço de Notificações |
| ReservaSolicitada | Serviço de Reservas | Serviço de Notificações |
| ReservaAprovada | Serviço de Reservas | Serviço de Agenda |
| PagamentoConfirmado | Serviço de Pagamentos | Serviço de Ingressos |
| IngressoEmitido | Serviço de Ingressos | Serviço de Notificações |
| CheckinRealizado | Serviço de Acesso | Serviço de Indicadores |

## ⚙️ Arquitetura Inicial do Sistema (MVP)

A versão inicial do sistema foi definida com foco em funcionalidade, escalabilidade e facilidade de evolução, garantindo uma base sólida para futuras integrações e expansão da plataforma.

O MVP (Minimum Viable Product) contempla os principais fluxos operacionais da Arena Pernambuco, permitindo a gestão eficiente de eventos, reservas e indicadores administrativos.

Os principais componentes contemplados nesta fase são:

- vitrine digital de eventos
- sistema de solicitação de eventos pelos organizadores
- fluxo de aprovação e rejeição de eventos pelos gestores
- sistema de reservas e controle da agenda da arena
- painel administrativo para gestão interna
- módulo de indicadores e relatórios gerenciais
- controle de usuários e permissões
- auditoria e rastreabilidade de operações
- emissão de ingressos digitais
- geração de QR Code
- controle de acesso

A estrutura foi planejada para suportar crescimento modular, integração com serviços governamentais e conformidade com as exigências de segurança da informação e LGPD.

A arquitetura foi planejada para futura evolução baseada em microsserviços, permitindo desacoplamento entre contextos, escalabilidade horizontal e comunicação distribuída entre módulos.

Os serviços poderão se comunicar via APIs REST e mensageria orientada a eventos utilizando RabbitMQ.

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

### 🔄 Comunicação e Infraestrutura

- RabbitMQ
- Docker
- Docker Compose
- JWT Authentication

## 🏗️ Planejamento de Infraestrutura

### Objetivos da Infraestrutura
- Disponibilidade: garantir que a vitrine de eventos esteja sempre online para consulta da população.
- Integridade: assegurar que os dados de reservas e métricas governamentais sejam armazenados de forma segura e consistente.
- Escalabilidade: capacidade de suportar picos de acesso durante o anúncio de grandes eventos.

### Restrições Técnicas e de Negócio
- Custo: priorização de tecnologias Open Source e infraestrutura de baixo custo.
- Conformidade: o tratamento de dados deve seguir as diretrizes da LGPD (Lei Geral de Proteção de Dados).
- Ambiente: a aplicação deve ser executada obrigatoriamente em containers para garantir portabilidade entre servidores governamentais.

## ☁️ Arquiteturas em Nuvem e Escalabilidade

A infraestrutura da Arena Pernambuco foi planejada para suportar diferentes níveis de utilização, permitindo evolução gradual da plataforma sem necessidade de reestruturação completa.

### 🟢 Baixo Uso

Destinado ao MVP e ambientes de homologação.

#### Arquitetura

Internet
│
Load Balancer
│
EC2 (Spring Boot)
│
RabbitMQ
│
RDS MySQL
│
S3

#### Características

- 1 instância EC2
- Banco gerenciado RDS MySQL
- Armazenamento em S3
- Monitoramento com CloudWatch
- Escalabilidade vertical

---

### 🟡 Médio Uso

Destinado ao crescimento da plataforma e aumento da quantidade de eventos.

#### Arquitetura

Internet
│
CloudFront
│
Load Balancer
│
├── EC2 App 1
├── EC2 App 2
│
RabbitMQ
│
RDS MySQL
│
S3

#### Características

- Balanceamento de carga
- Duas instâncias da aplicação
- Cache com CloudFront
- Escalabilidade horizontal inicial
- Alta disponibilidade

---

### 🔴 Alto Uso

Destinado a grandes eventos e elevado volume de acessos simultâneos.

#### Arquitetura

Internet
│
CloudFront
│
API Gateway
│
Load Balancer
│
Amazon EKS
├── Serviço de Eventos
├── Serviço de Reservas
├── Serviço de Ingressos
│
RabbitMQ Cluster
│
Aurora MySQL Cluster
│
S3

#### Características

- Microsserviços
- Kubernetes (EKS)
- Auto Scaling
- Banco de dados em cluster
- Alta disponibilidade e tolerância a falhas

---

### 📊 Estratégia de Evolução

| Nível | Aplicação | Banco de Dados | Escalabilidade |
|---------|---------|---------|---------|
| Baixo | 1 EC2 | RDS MySQL | Vertical |
| Médio | 2 EC2 | RDS MySQL | Horizontal |
| Alto | EKS | Aurora Cluster | Horizontal Automática |

A evolução da infraestrutura ocorre de forma gradual, acompanhando o crescimento da demanda da plataforma, garantindo desempenho, disponibilidade e redução de custos operacionais.

## 💰 Estimativa de Custos em Nuvem

| Cenário      | Infraestrutura                                                  | Custo Mensal Estimado |
| ------------ | --------------------------------------------------------------- | --------------------- |
| 🟢 Baixo Uso | 1 EC2, RDS MySQL, S3 e Load Balancer                            | US$ 40 – 80           |
| 🟡 Médio Uso | 2 EC2, RDS MySQL, RabbitMQ, CloudFront e Load Balancer          | US$ 150 – 350         |
| 🔴 Alto Uso  | EKS, Aurora Cluster, RabbitMQ Cluster, API Gateway e CloudFront | US$ 800 – 2.000       |

### Conclusão

A infraestrutura foi projetada para crescer conforme a demanda da plataforma, permitindo iniciar com baixo custo operacional e expandir os recursos de forma gradual à medida que o número de usuários e eventos aumenta.

## 🚀 Estratégia de Deploy em Ambiente Real

A estratégia de implantação da plataforma Arena Pernambuco foi definida para garantir disponibilidade, segurança, escalabilidade e facilidade de manutenção em ambiente de produção.

### Processo de Deploy

O código-fonte é armazenado em repositório Git e submetido a um pipeline de Integração Contínua e Entrega Contínua (CI/CD).

Fluxo de implantação:

```text
Desenvolvedor
      │
      ▼
GitHub
      │
      ▼
Pipeline CI/CD
(Testes e Build)
      │
      ▼
Docker Image
      │
      ▼
Registry de Containers
      │
      ▼
Ambiente de Produção
```

### Pipeline CI/CD

As etapas automatizadas do pipeline são:

1. Validação do código-fonte.
2. Execução de testes automatizados.
3. Geração do artefato da aplicação.
4. Construção da imagem Docker.
5. Publicação da imagem em registry.
6. Implantação automática no ambiente de produção.

### Estratégia de Contêineres

A aplicação é empacotada utilizando Docker, garantindo portabilidade e padronização entre os ambientes de desenvolvimento, homologação e produção.

Componentes executados em containers:

* Aplicação Spring Boot
* Banco de Dados MySQL
* RabbitMQ
* Serviços auxiliares de monitoramento

### Ambientes

#### Desenvolvimento

Utilizado pelos desenvolvedores para implementação e testes locais.

#### Homologação

Utilizado para validação funcional antes da publicação em produção.

#### Produção

Ambiente acessado pelos usuários finais e gestores da Arena Pernambuco.

### Estratégia de Atualização

Para minimizar indisponibilidades, a implantação poderá utilizar a estratégia Rolling Update.

Benefícios:

* Atualização gradual das instâncias.
* Redução do tempo de indisponibilidade.
* Possibilidade de reversão rápida em caso de falhas.

### Monitoramento Pós-Deploy

Após cada implantação serão monitorados:

* Disponibilidade da aplicação.
* Consumo de CPU e memória.
* Tempo de resposta.
* Taxa de erros.
* Logs da aplicação.

### Recuperação de Falhas

Em caso de falha durante a implantação:

* Retorno automático para a versão anterior.
* Recuperação através de imagens Docker versionadas.
* Restauração de backups do banco de dados quando necessário.

### Benefícios da Estratégia

* Implantação automatizada.
* Redução de erros manuais.
* Facilidade de escalabilidade.
* Alta disponibilidade.
* Maior confiabilidade operacional.
* Padronização dos ambientes.

## 📈 Indicadores de Monitoramento

### Indicadores de Uso da Plataforma

- Quantidade de acessos ao sistema  
- Número de usuários cadastrados  
- Taxa de visualização de eventos  

Objetivo: medir o alcance da vitrine digital e o interesse da população pelos eventos divulgados.

### Indicadores de Gestão de Eventos

- Quantidade de eventos cadastrados  
- Taxa de ocupação da Arena  
- Taxa de reservas confirmadas  
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

## 📊 Avaliação de Desempenho da Infraestrutura

A infraestrutura proposta para a Arena Pernambuco apresenta capacidade de crescimento gradual, garantindo disponibilidade, segurança e escalabilidade conforme o aumento da demanda.

### Pontos Fortes

* Arquitetura preparada para escalabilidade horizontal.
* Utilização de balanceamento de carga para distribuição de acessos.
* Comunicação assíncrona com RabbitMQ.
* Possibilidade de evolução para microsserviços em nuvem.

### Pontos de Melhoria

* O banco de dados pode se tornar um gargalo em períodos de grande acesso.
* A geração de relatórios e indicadores pode aumentar o consumo de recursos.
* O crescimento do volume de dados exigirá otimização de consultas e armazenamento.

### Melhorias Recomendadas

* Implementação de cache com Redis.
* Replicação e otimização do banco de dados.
* Monitoramento com Prometheus e Grafana.
* Auto Scaling para ajuste automático da infraestrutura.
* Ampliação das estratégias de backup e recuperação de desastres.

### Conclusão

A infraestrutura atende adequadamente aos requisitos atuais da plataforma, porém futuras otimizações em banco de dados, monitoramento e escalabilidade serão importantes para garantir desempenho e disponibilidade em cenários de alta demanda.

## 🌐 Requisitos Técnicos de Rede

### Desempenho

#### Requisito de Negócio
Garantir acesso rápido e contínuo da população à vitrine digital de eventos, especialmente em períodos de grande divulgação.

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

- Gestão de eventos
- Aprovação de eventos
- Vitrine de eventos
- Sistema de reservas
- Painel de dados
- Emissão de ingressos digitais
- Geração de QR Code
- Controle de acesso
- Sistema de notificações
- Validação de ingressos
- Check-in de participantes
- Integração com controle de catraca

## 🚀 Como Executar o Projeto

Siga as instruções abaixo para configurar o ambiente e rodar a aplicação localmente.

### Pré-requisitos

Antes de começar, você precisará ter instalado em sua máquina:
* Git
* Docker
* Java 17 ou superior

---

### Passo a Passo

1. **Clone o repositório e acesse a pasta:**
```bash
git clone https://github.com/SergioGSF/programa-cultural-digital
cd programa-cultural-digital/arenapernambuco
```

2. **Suba os containers do Docker:**
```bash
docker-compose up -d
```

3. **Crie o banco de dados:**
```bash
docker exec -it mysql_arena mysql -u root -p1234 -e "CREATE DATABASE IF NOT EXISTS arena_pernambuco;"
```

4. **Construa o projeto:**
```bash
./mvnw clean package
```

5. **Execute a aplicação:**
```bash
./mvnw spring-boot:run
```

---

### Acesso

Após a inicialização, a aplicação estará disponível em:
[http://localhost:8080](http://localhost:8080)

## 🧱 Estrutura do Projeto

O projeto segue uma arquitetura baseada no padrão MVC:

- Model: entidades e estrutura de dados do sistema  
- Repository: camada de acesso ao banco de dados com Spring Data JPA  
- Service: regras de negócio da aplicação  
- Controller: controle das requisições HTTP  
- Templates: páginas HTML renderizadas com Thymeleaf

Além do padrão MVC utilizado atualmente, o sistema foi organizado considerando princípios de Clean Architecture e DDD, separando domínio, aplicação, infraestrutura e interface da aplicação para facilitar evolução futura para microsserviços.

## 🔄 Padrões Arquiteturais

### Publicação e Assinatura (Pub/Sub)

Eventos publicados pelos serviços podem ser consumidos por múltiplos módulos sem acoplamento direto.

### CQRS

Separação entre operações de leitura e escrita visando melhor desempenho e escalabilidade.

### Saga

Coordenação distribuída entre serviços para garantir consistência em operações complexas.

## 🚀 Evolução Arquitetural Futura

Com o crescimento da plataforma, as seguintes melhorias arquiteturais poderão ser implementadas para aumentar o desempenho, disponibilidade e escalabilidade do sistema:

### Escalabilidade

- Migração gradual do monólito para arquitetura baseada em microsserviços.
- Implementação de Auto Scaling para ajuste automático da capacidade computacional.
- Utilização de Kubernetes para orquestração de containers.

### Desempenho

- Implementação de cache distribuído com Redis.
- Utilização de CDN para distribuição de conteúdo estático.
- Otimização de consultas ao banco de dados com índices e particionamento.

### Disponibilidade

- Replicação do banco de dados em múltiplas zonas de disponibilidade.
- Balanceamento de carga entre múltiplas instâncias da aplicação.
- Estratégias de recuperação de desastre e failover automático.

### Observabilidade

- Monitoramento centralizado com Prometheus e Grafana.
- Coleta de logs com ELK Stack (Elasticsearch, Logstash e Kibana).
- Alertas automáticos para incidentes operacionais.

### Segurança

- Implementação de Web Application Firewall (WAF).
- Gestão centralizada de segredos e credenciais.
- Ampliação das políticas de auditoria e rastreabilidade.

### Arquitetura Orientada a Eventos

- Expansão do uso do RabbitMQ entre todos os contextos delimitados.
- Processamento assíncrono de notificações, relatórios e indicadores.
- Adoção de padrões Event Sourcing em módulos estratégicos.

Essas melhorias poderão ser implementadas gradualmente conforme o aumento da demanda da plataforma, garantindo crescimento sustentável sem comprometer desempenho, disponibilidade e segurança.

## 🔮 Funcionalidades Futuras

- Integração com APIs de geolocalização e mapas
- Integração com sistemas governamentais
- Sistema avançado de notificações
- Integração com catracas inteligentes
- Assinatura eletrônica de documentos
- Aplicativo móvel para participantes
- Painéis analíticos avançados para gestores

## 👥 Equipe

- Allana Sílvia Gadêlha de Carvalho Souza  
- Carlos Henrique Gonçalves da Silva  
- Claudemir Pereira de Araujo Filho  
- Everton Nunes Batista  
- Samara Mendonça Nunes  
- Sergio Gonçalves da Silva Filho

## 📋 Relatório
https://docs.google.com/document/d/1QH6JocjcplCSpDMpo8D8PWQ_WClk7vnPgfXGS8qXG4w/edit?tab=t.0

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
- Issues/bug tracker: 
https://github.com/SergioGSF/programa-cultural-digital/issues
<img width="1234" height="310" alt="image" src="https://github.com/user-attachments/assets/1fa53591-cb23-41d7-bae4-8ebad4384291" />
<img width="920" height="585" alt="image" src="https://github.com/user-attachments/assets/6eec91a4-cff8-4940-a424-d662b88a142e" />

- Screencast: 
https://youtu.be/wE20pIKdeRU

## 📁 Entrega 4
- Issues/bug tracker: 
https://github.com/SergioGSF/programa-cultural-digital/issues
<img width="1156" height="1743" alt="IMG_20260518_200938" src="https://github.com/user-attachments/assets/81ed3144-6302-4f89-9ea9-6db3fa820711" />

- Screencast:
https://youtu.be/S6uaMfQpTmw

# Sistema de gestão para clínica
Um sistema para gerenciar cadastros de pacientes, agendamentos, e relatórios de evolução de tratamentos:

## Funcionalidades Principais
### Gestão de Pacientes
* Cadastro de Pacientes: O gestor pode criar, editar e excluir registros de pacientes. Cada paciente tem um perfil com informações pessoais (nome, endereço, contato, etc.) e um histórico de relatórios.
* Visualização de Pacientes: O gestor e os profissionais podem visualizar a lista de pacientes e acessar os detalhes de cada paciente.

### Gestão de Profissionais
* Cadastro de Profissionais: O gestor pode adicionar, editar e excluir profissionais que realizarão os atendimentos. Cada profissional tem um perfil com informações pessoais e especialidades.
* Atribuição de Atendimentos: O gestor pode agendar atendimentos, atribuindo pacientes a profissionais em horários específicos.

### Agendamentos
* Agendar Atendimentos: O gestor pode criar, visualizar, editar e cancelar agendamentos. O agendamento inclui informações como data, hora, paciente, e profissional responsável.
* Visualização de Agenda: Os profissionais podem visualizar sua agenda de atendimentos para um determinado período.

### Relatórios de Evolução de Tratamento
* Criação de Relatórios: Após cada atendimento, o profissional pode criar um relatório detalhando a evolução do tratamento do paciente.
* Histórico de Relatórios: O sistema mantém um histórico de todos os relatórios associados a cada paciente, permitindo que tanto o gestor quanto os profissionais revisem o progresso ao longo do tempo.

### Autenticação e Autorização
* Login e Controle de Acesso: O sistema deve ter um mecanismo de autenticação para gestores e profissionais. Diferentes permissões são concedidas conforme o papel (gestor ou profissional).

## Entidades Principais
* Paciente: Nome, endereço, contato, data de nascimento, histórico de relatórios.
* Profissional: Nome, especialidade, contato, agenda.
* Gestor: Nome, contato, permissões.
* Agendamento: Data, hora, paciente, profissional, status.
* Relatório: Data, profissional, paciente, descrição da evolução, arquivos anexos.

## Fluxos de Trabalho

### Cadastro de Paciente
* O gestor acessa o sistema.
* O gestor navega até a seção de "Pacientes" e clica em "Novo Paciente".
* O gestor preenche o formulário com as informações do paciente.
* O gestor salva o cadastro.
### Agendamento de Atendimento
* O gestor acessa a agenda e seleciona um horário disponível.
* O gestor escolhe o paciente e o profissional.
* O gestor salva o agendamento.
* O profissional recebe uma notificação do novo agendamento.
### Atendimento e Criação de Relatório
* O profissional visualiza sua agenda e atende o paciente no horário marcado.
* Após o atendimento, o profissional acessa o perfil do paciente e cria um novo relatório.
* O relatório é salvo e associado ao paciente no histórico de evolução.

## Tecnologias

* Backend: Java com Spring Boot para gerenciar a lógica de negócios, REST API.
* Banco de Dados: H2 para desenvolvimento e um banco de dados relacional (PostgreSQL ou MySQL) em produção para armazenar os dados de pacientes, agendamentos e relatórios.
* Frontend: React ou Flutter.
* Autenticação: Spring Security para gerenciamento de usuários e permissões.
* Documentação da API: Swagger para documentação das APIs.

## Dependencias
* Spring Web para criar a API REST
* Spring Data JPA para persistência de dados
* H2 Database para o banco de dados em memória
* Spring Boot DevTools 
* Swagger para documentação da API

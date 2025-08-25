# 📅 Sistema de Cadastro e Notificação de Eventos

Projeto acadêmico em Java para gerenciar eventos e permitir que usuários se cadastrem, confirmem presença e recebam notificações de eventos na sua cidade.

---

## 📝 Sobre o Projeto

Este sistema foi desenvolvido como parte da disciplina de tecnologia da informação, aplicando conceitos de Orientação a Objetos, manipulação de arquivos e controle de datas. A interface é simples, baseada em console, e permite a gestão completa dos eventos e participações. Obs: Meu primeiro projeto academico realizado, estou sempre aberto para criticas construtivas.

---

## 🚀 Funcionalidades

- Cadastro de usuários com dados básicos (nome, email, telefone)
- Criação de eventos com nome, local, categoria, data/hora e descrição
- Consulta de eventos organizados por status: futuros, em andamento e passados
- Confirmação e cancelamento de participação em eventos
- Salvamento e carregamento automático dos eventos via arquivo de texto (`events.data`)

---

## 🛠 Tecnologias Utilizadas

- Java 8+  
- API java.time para manipulação de datas e horários  
- Entrada e saída via console  
- Persistência simples usando arquivos texto  

---

## 📂 Estrutura do Projeto

src/
- controller/        # Lógica do sistema e interação com o usuário
- model/             # Entidades: User, Event, EventCategory
- utils/             # Utilitários para manipulação de arquivos
- Main.java          # Classe principal para iniciar o programa


## 📈 Possíveis Melhorias

- Interface gráfica com JavaFX ou Swing

- Persistência com banco de dados (SQLite, MySQL)

- Suporte a múltiplos usuários e login

- Validação avançada de dados

- Implementação de testes automatizados

👤 Sobre o Autor

- Matheus Fernandes Meireles
- Curso: Gestão de Tecnologia da Informação
- Instituição: Universidade São Judas Tadeu

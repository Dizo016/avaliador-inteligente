# 📘 Prova Fácil

**Prova Fácil** é um sistema completo que permite a criação de provas escolares baseadas em questões reais do ENEM, com geração automática de gabarito e exportação em PDF. A aplicação foi desenvolvida com backend em **Java Spring Boot**, frontend em **React**, banco de dados **PostgreSQL** e integração com IA (via Groq) para busca inteligente de questões.

---

## ✅ Funcionalidades Principais

- 🔍 Busca de questões reais do ENEM com base em um tema escolhido pelo professor.
- 📝 Criação automática de provas com 5 questões e 4 alternativas cada.
- 📄 Geração de gabarito vinculado à prova.
- 📁 Exportação em PDF da prova e do gabarito.
- 👤 Autenticação de usuários (Professor e Administrador).
- 🌐 Integração entre frontend React e backend Spring Boot.

---

## 🛠️ Tecnologias Utilizadas

### ⚙️ Backend
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- Groq API (Integração com LLM)

### 💻 Frontend
- React.js
- React Router DOM
- CSS Modules

### 🧰 Ferramentas
- Maven
- IntelliJ IDEA
- Postman
- Git & GitHub

---

## 🗂️ Estrutura de Diretórios

```bash
prova-facil/
├── backend/
│   ├── src/main/java/com/ucsal/avaliador-inteligente/
│   ├── resources/application.properties
│   └── pom.xml
├── frontend/
│   ├── public/
│   └── src/
│       ├── components/
│       ├── pages/
│       └── App.jsx
└── README.md


---

## 👥 Equipe

- **Diego de Sousa Silva** – Desenvolvedor  
- **Cid Vinicius Vasconcelos de Jesus** – Desenvolvedor 
- **Vitor Vinicius Vasconcelos da Silva** – Desenvolvedor  
- **Mario Jorge Pereira** – Orientador Acadêmico

---

   ## 🚀 Como rodar o projeto
   
   1. Clone este repositório:
      ```bash
      git clone git@github.com:Dizo016/avaliador-inteligente.git
      cd avaliador-inteligente
      ```
   
   2. Configure o `application.properties` com os dados do seu banco PostgreSQL.
   
   3. Gere sua chave da GroqIA e configure no backend como variável de ambiente:
      ```
     # Chave da API Groq
   groq.api.key=
      ```
   
   4. Rode o projeto com:
      ```bash
      ./mvnw spring-boot:run
      ```
   
   ---
   
   ## 📄 Licença
   
   Este projeto é acadêmico e livre para fins educacionais. Direitos reservados aos autores.

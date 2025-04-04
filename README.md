# 🧠 Avaliador Inteligente – TCC Universidade Católica

Este repositório contém o projeto **Avaliador Inteligente**, desenvolvido como **Trabalho de Conclusão de Curso** (TCC) do curso de **Análise e Desenvolvimento de Sistemas** na **Universidade Católica**.

O sistema propõe uma plataforma educacional que utiliza **Inteligência Artificial** para fornecer feedbacks automáticos em **questões de múltipla escolha**, permitindo que os alunos avaliem a qualidade das explicações. A proposta visa melhorar o processo de ensino-aprendizagem por meio de correções personalizadas e automatizadas.

---

## 🎯 Objetivos

- Automatizar o processo de correção de questões de múltipla escolha
- Gerar feedback textual com auxílio de IA (ex: GPT)
- Permitir que o aluno avalie a qualidade do feedback recebido
- Armazenar os dados para análise pedagógica e melhoria do sistema

---

## 🧰 Tecnologias utilizadas

- **Java 17**
- **Spring Boot 3**
- **PostgreSQL**
- **OpenAI API (GPT)** para geração de feedback
- **HTML/CSS/JS** ou **React** (para a interface do usuário, se aplicável)
- **Lombok**, **Spring Data JPA**, **REST APIs**

---

## 📁 Estrutura do Projeto

```
avaliador-inteligente/
├── backend/                  
│   └── src/main/java/...  
├── frontend/                 
├── docs/                
│   ├── monografia.pdf
│   ├── diagrama-er.png
│   └── resumo.md
├── .gitignore
├── README.md
└── LICENSE
```

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

3. Gere sua chave da OpenAI e configure no backend como variável de ambiente:
   ```
   OPENAI_API_KEY=suachaveaqui
   ```

4. Rode o projeto com:
   ```bash
   ./mvnw spring-boot:run
   ```

---

## 📄 Licença

Este projeto é acadêmico e livre para fins educacionais. Direitos reservados aos autores.

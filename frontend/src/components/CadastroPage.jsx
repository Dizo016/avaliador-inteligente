// src/components/CadastroPage.jsx
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './CadastroPage.css';

const CadastroPage = () => {
  const navigate = useNavigate();

  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [confirmarSenha, setConfirmarSenha] = useState('');
  const [tipoUsuario, setTipoUsuario] = useState('ALUNO');

  const handleSubmit = async () => {
    if (!nome || !email || !senha || !confirmarSenha) {
      alert('Preencha todos os campos.');
      return;
    }

    if (senha !== confirmarSenha) {
      alert('As senhas não coincidem.');
      return;
    }

    try {
      const response = await fetch('/api/usuarios/cadastro', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ nome, email, senha, tipoUsuario })
      });

      if (response.ok) {
        alert('Cadastro realizado com sucesso!');
        navigate('/login');
      } else {
        const msg = await response.text();
        alert('Erro ao cadastrar: ' + msg);
      }
    } catch (err) {
      console.error('Erro:', err);
      alert('Erro ao tentar cadastrar. Tente novamente.');
    }
  };

  return (
    <div className="cadastro-container">
      <div className="cadastro-box">
        <h2>Cadastro de Novo Usuário</h2>

        <label>Nome</label>
        <input type="text" value={nome} onChange={(e) => setNome(e.target.value)} />

        <label>Email</label>
        <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />

        <label>Senha</label>
        <input type="password" value={senha} onChange={(e) => setSenha(e.target.value)} />

        <label>Confirmar Senha</label>
        <input type="password" value={confirmarSenha} onChange={(e) => setConfirmarSenha(e.target.value)} />

        <label>Tipo de Usuário</label>
        <select value={tipoUsuario} onChange={(e) => setTipoUsuario(e.target.value)}>
          <option value="ALUNO">Aluno</option>
          <option value="PROFESSOR">Professor</option>
        </select>

        <button className="cadastro-button" onClick={handleSubmit}>Cadastrar</button>
        <p className="login-link" onClick={() => navigate('/login')}>Já tem conta? Faça login</p>
      </div>
    </div>
  );
};

export default CadastroPage;

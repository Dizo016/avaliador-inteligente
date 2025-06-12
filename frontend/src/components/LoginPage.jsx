// src/components/LoginPage.jsx
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './LoginPage.css';

const LoginPage = () => {
  const navigate = useNavigate();

  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');

  const handleLogin = async () => {
    try {
      console.log('Enviando login:', { email, senha });

      const response = await fetch('/api/usuarios/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email, senha })
      });

      const result = await response.json();

      console.log('Status:', response.status);
      console.log('📥 Resposta JSON:', result);

      if (response.ok) {
        localStorage.removeItem('usuarioLogado'); // 🔄 remove antigo se houver
        localStorage.setItem('usuarioLogado', JSON.stringify(result)); // ✅ salva corretamente
        console.log('📦 Usuário salvo no localStorage como usuarioLogado:', result);
        alert('✅ Login bem-sucedido!');
        navigate('/dashboard'); // redireciona
      } else {
        alert('❌ Credenciais inválidas.');
      }
    } catch (error) {
      console.error('Erro ao logar:', error);
      alert('❌ Erro ao tentar logar. Tente novamente.');
    }
  };

  const handleCadastro = () => {
    navigate('/cadastro');
  };

  return (
    <div className="login-container">
      <div className="login-box">
        <h2>Bora lá!?</h2>

        <label htmlFor="email">Email</label>
        <input
          type="email"
          id="email"
          placeholder="Digite seu email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />

        <label htmlFor="senha">Senha</label>
        <input
          type="password"
          id="senha"
          placeholder="Digite sua senha"
          value={senha}
          onChange={(e) => setSenha(e.target.value)}
        />

        <p className="help-text">Primeiro acesso? Cadastre-se clicando no botão</p>

        <div className="button-group">
          <button className="login-button" onClick={handleLogin}>Entrar</button>
          <button className="login-button" onClick={handleCadastro}>Cadastrar</button>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;

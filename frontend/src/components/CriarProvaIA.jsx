// src/components/CriarProvaIA.jsx
import React, { useState } from 'react';
import './CriarProvaIA.css';

const CriarProvaIA = () => {
  const [tema, setTema] = useState('');
  const [titulo, setTitulo] = useState('');
  const [mensagem, setMensagem] = useState('');

  const handleCriarProva = async () => {
    try {
      const usuario = JSON.parse(localStorage.getItem('usuarioLogado'));
      console.log('👤 Usuário logado:', usuario);

      if (!usuario || !usuario.id) {
        setMensagem('❌ Usuário não autenticado. Faça login novamente.');
        return;
      }

      const provaDTO = {
        tema: tema.trim(),
        titulo: titulo.trim(),
        criadorId: usuario.id
      };

      const response = await fetch('/api/provas/gerar-ia', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(provaDTO)
      });

      if (response.ok) {
        setMensagem('✅ Prova criada com sucesso!');
        setTema('');
        setTitulo('');
      } else {
        const texto = await response.text();
        setMensagem(`❌ Erro: ${texto}`);
      }

    } catch (error) {
      console.error('Erro ao criar prova:', error);
      setMensagem('❌ Erro inesperado. Tente novamente mais tarde.');
    }
  };

  return (
    <div className="criar-prova-container">
      <div className="criar-prova-box">
        <h2>✨ Criar Nova Prova com IA</h2>

        <label htmlFor="titulo">Título da Prova</label>
        <input
          type="text"
          id="titulo"
          placeholder="Ex: Prova ENEM - Matemática"
          value={titulo}
          onChange={(e) => setTitulo(e.target.value)}
        />

        <label htmlFor="tema">Tema (ex: Biologia, História, etc)</label>
        <input
          type="text"
          id="tema"
          placeholder="Digite o tema das questões"
          value={tema}
          onChange={(e) => setTema(e.target.value)}
        />

        <button className="criar-prova-btn" onClick={handleCriarProva}>
          Gerar Prova com IA
        </button>

        {mensagem && (
          <p className="mensagem" style={{ marginTop: '12px', fontWeight: 'bold', color: mensagem.includes('✅') ? 'green' : 'red' }}>
            {mensagem}
          </p>
        )}
      </div>
    </div>
  );
};

export default CriarProvaIA;

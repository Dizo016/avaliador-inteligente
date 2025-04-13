// src/components/DashboardPage.jsx
import React from 'react';
import { useNavigate } from 'react-router-dom';
import './DashboardPage.css';

const DashboardPage = () => {
  const navigate = useNavigate();

  return (
    <div className="dashboard-container">
      <header className="dashboard-header">
        <h1>Avaliador Inteligente</h1>
        <button className="logout-button" onClick={() => navigate('/login')}>
          Sair
        </button>
      </header>

      <h2 className="dashboard-welcome">Bem-vindo!</h2>

      <div className="dashboard-grid">
        <div className="dashboard-card">
          <h3>📝 Responder Provas</h3>
          <p>Descubra novos desafios e teste seu conhecimento.</p>
          <button onClick={() => navigate('/provas')}>Acessar</button>
        </div>

        <div className="dashboard-card">
          <h3>📋 Ver Feedbacks</h3>
          <p>Revise os comentários gerados pela IA sobre suas respostas.</p>
          <button onClick={() => navigate('/feedbacks')}>Acessar</button>
        </div>

        <div className="dashboard-card">
          <h3>⭐ Avaliar Feedbacks</h3>
          <p>Ajude a melhorar dando sua nota aos feedbacks recebidos.</p>
          <button onClick={() => navigate('/avaliar-feedbacks')}>Acessar</button>
        </div>

        <div className="dashboard-card">
          <h3>📚 Provas Respondidas</h3>
          <p>Consulte suas respostas anteriores e acompanhe sua evolução.</p>
          <button onClick={() => navigate('/provas/concluidas')}>Acessar</button>
        </div>
      </div>
    </div>
  );
};

export default DashboardPage;
